package poo.util.didattica;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> extends AbstractList<T> {
    private enum Move{ UNKNOW, FORWARD, BACKWARD }
    private static class Nodo<E>{
        E info;
        Nodo<E> next, prior;
    }
    private Nodo<T> first = null, last = null;
    private int size;

    public int size(){
        return size;
    }
    public void clear(){
        first = null;
        last = null;
        size = 0;
    }
    public void addFirst(T e){
        Nodo<T> n = new Nodo<>();
        n.info = e;
        n.next = first;
        n.prior = null;
        if(first != null) first.prior = n;
        first = n;
        if(last == null) last = n;
        size++;
    }
    public void addLast(T e){
        Nodo<T> n = new Nodo<>();
        n.info = e;
        n.next = null;
        n.prior = last;
        if(last != null) last.next = n;
        last = n;
        if(first == null) first = n;
        size++;
    }
    public T getFirst(){
        if(first == null) throw new NoSuchElementException();
        return first.info;
    }
    public T getLast(){
        if(last == null) throw new NoSuchElementException();
        return last.info;
    }
    public T removeFirst(){
        if(first == null) throw new NoSuchElementException();
        T ret = first.info;
        first = first.next;
        if(first != null) first.prior = null;
        else last = null;
        size--;
        return ret;
    }
    public T removeLast(){
        if(last == null) throw new NoSuchElementException();
        T ret = last.info;
        last = last.prior;
        if(last != null) last.next = null;
        else first = null;
        size--;
        return ret;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public boolean isFull(){
        return false;
    }
    @Override
    public Iterator<T> iterator() {
        return new ListIteratorImplements();
    }
    public ListIterator<T> listIterator(){
        return new ListIteratorImplements();
    }
    public ListIterator<T> listIterator(int pos){
        return new ListIteratorImplements(pos);
    }

    private class ListIteratorImplements implements ListIterator<T>{
        private Nodo<T> previous, next;
        private Move lastMove = Move.UNKNOW;

        public ListIteratorImplements(){
            previous = null;
            next = first;
        }
        public ListIteratorImplements(int pos){
            if(pos < 0 || pos > size) throw new IllegalArgumentException();
            if(pos == size || pos == 0 && isEmpty()) {
                previous = last;
                next = null;
            } else {
                previous = null;
                next = first;
                for(int i = 0; i < pos; i++){
                    previous = next;
                    next = next.next;
                }
            }
        }
        @Override
        public boolean hasNext(){
            return next != null;
        }
        @Override
        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            lastMove = Move.FORWARD;
            previous = next;
            next = next.next;
            return previous.info;
        }
        @Override
        public boolean hasPrevious(){
            return previous != null;
        }
        @Override
        public T previous(){
            if(!hasPrevious()) throw new NoSuchElementException();
            lastMove = Move.BACKWARD;
            next = previous;
            previous = previous.prior;
            return next.info;
        }
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException();
        }
        @Override
        public void remove(){
            if(lastMove == Move.UNKNOW) throw new IllegalStateException();
            Nodo<T> r = null;
            if(lastMove == Move.FORWARD) r = previous;
            else r = next;
            if(r == first){
                first = first.next;
                if(first != null) first.prior = null;
                else last = null;
            } else if(r == last) {
                last = last.prior;
                last.next = null;
            } else {
                r.prior.next = r.next;
                r.next.prior = r.prior;
            }
            if(lastMove == Move.BACKWARD)
                previous = r.prior;
            else
                next = r.next;
            size--;
            lastMove = Move.UNKNOW;
        }
        @Override
        public void set(T e){
            if(lastMove == Move.UNKNOW) throw new IllegalStateException();
            if(lastMove == Move.FORWARD) previous.info = e;
            else next.info = e;
        }
        @Override
        public void add(T e){
            Nodo<T> n = new Nodo<>();
            n.info = e;
            n.next = next;
            n.prior = previous;
            if(next != null) next.prior = n;
            if(previous != null) previous.next = n;
            previous = n;
            if(n.next == first) first = n;
            if(n.prior == last) last = n;
            size++;
            lastMove = Move.UNKNOW;
        }
    }
}
