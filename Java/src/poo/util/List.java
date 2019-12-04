package poo.util;

import java.util.Comparator;
import java.util.ListIterator;

public interface List<T> extends Iterable<T> {
    int size();
    void clear();
    void addFirst(T x);
    void addLast(T x);
    T getFirst();
    T getLast();
    T removeFirst();
    T removeLast();
    boolean isEmpty();
    boolean isFull();
    static <T> void sort(List<T> e){

    }
    static <T> void sort(List<T> l, Comparator<T> c){
        if(l.isEmpty() || l.size() == 1) return;
        boolean scambi = true;
        while(scambi){
            ListIterator<T> lit = l.listIterator();
            T pre = lit.next(), cor = null;
            scambi = false;
            while(lit.hasNext()){
                cor = lit.next();
                if(c.compare(cor, pre) < 0){
                    lit.set(pre);
                    lit.previous();
                    lit.previous();
                    lit.set(cor);
                    lit.next();
                    pre = lit.next();
                    scambi = true;
                }else pre = cor;
            }
        }
    }
    ListIterator<T> listIterator();
    ListIterator<T> listIterator(int pos);
}
