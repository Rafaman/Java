package poo.util.didattica;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaConcatenata<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
    private static class Nodo<E>{
        E info;
        Nodo<E> next;
    }
    private Nodo<T> testa = null;
    private int size = 0;

    @Override
    public int size(){
        return size;
    }
    @Override
    public boolean isEmpty() {
        return testa == null;
    }
    @Override
    public boolean isFull(){
        return false;
    }
    @Override
    public void clear(){
        testa = null;
        size = 0;
    }
    @Override
    public boolean contains(T x){
        Nodo<T> cor = testa;
        while(cor != null && cor.info.compareTo(x) < 0) cor = cor.next;
        return cor != null && cor.info.equals(x);
    }
    @Override
    public T get(T x){
        Nodo<T> cor = testa;
        while (cor != null && cor.info.compareTo(x) < 0) cor = cor.next;
        if(cor != null && cor.info.equals(x)) return cor.info;
        return null;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder(100);
        sb.append('[');
        Nodo<T> cor = testa;
        while(cor != null) {
            sb.append(cor.info);
            cor = cor.next;
            if(cor != null) sb.append(", ");
        }
        sb.append(']');
        return sb.toString();
    }
    @Override @SuppressWarnings("unchecked")
    public boolean equals(Object o){
        if(!(o instanceof CollezioneOrdinata)) return false;
        if(o == this) return true;
        CollezioneOrdinata<T> co = (CollezioneOrdinata<T>)o;
        if(size != co.size()) return false;
        Iterator<T> it1 = iterator(), it2 = co.iterator();
        while(it1.hasNext()) if(!it1.next().equals(it2.next())) return false;
        return true;
    }
    @Override
    public int hashCode(){
        final int M = 83;
        int h = 0;
        Nodo<T> cor = testa;
        while(cor != null) {
            h = h * M + cor.info.hashCode();
            cor = cor.next;
        }
        return h;
    }
    @Override
    public void add(T x){
        /*Nodo<T> pre = null, cor = testa;
        while (cor != null && cor.info.compareTo(x) < 0) {
            pre = cor;
            cor = cor.next;
        }
        Nodo<T> nuovo = new Nodo<T>();
        nuovo.info = x;
        if(cor == testa) {
            nuovo.next = testa;
            testa = nuovo;
        } else {
            nuovo.next = cor;
            pre.next = nuovo;
        }
        size++;*/
        Nodo<T> nuovo = new Nodo<>();
        nuovo.info = x;
        if(testa == null || testa.info.compareTo(x) >= 0) {
            nuovo.next = testa;
            testa = nuovo;
        } else{
            Nodo<T> pre = testa, cor = testa.next;
            while(cor != null && cor.info.compareTo(x) < 0){
                pre = cor;
                cor = cor.next;
            }
            pre.next = nuovo;
            nuovo.next = cor;
        }
        size++;
    }
    @Override
    public void remove(T x){
        Nodo<T> pre = null, cor = testa;
        while(cor != null && cor.info.compareTo(x) < 0){
            pre = cor;
            cor = cor.next;
        }
        if(cor != null && cor.info.equals(x))
            if(cor == testa) testa = testa.next;
            else pre.next = cor.next;
        size--;
    }
    @Override
    public Iterator<T> iterator(){
        return new LCIterator();
    }
    private class LCIterator implements Iterator<T>{
        Nodo<T> pre = null, cor = null;
        public boolean hasNext(){
            if(cor == null) return testa != null;
            return cor.next != null;
        }
        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            if(cor == null) cor = testa;
            else {
                pre = cor;
                cor = cor.next;
            }
            return cor.info;
        }
        public void remove(){
            if(pre == cor) throw new IllegalStateException();
            if(cor == testa) testa = testa.next;
            else pre.next = cor.next;
            size--;
            cor = pre;
        }
    }
}
