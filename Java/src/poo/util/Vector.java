package poo.util;

import java.util.Iterator;
import java.util.List;

public interface Vector<T> extends Iterable<T> {
    default int size() {
        int c = 0;
        Iterator<T> it = iterator();
        while(it.hasNext()){
            it.next();
            c++;
        }
        return c;
    }
    default T get(int i){
        if(i < 0 || i >= size()) throw new IndexOutOfBoundsException("Indice errato");
        T x = null;
        int j = 0;
        for(T e : this)
            if(j == i){
                x = e;
                break;
            } else j++;
        return x;
    }
    T set(int i, T x);
    default void add(T x){
        add(size(), x);
    }
    void add(int i, T x);
    default T remove(int i){
        if(i < 0 || i >= size() - 1) throw new IndexOutOfBoundsException("Indice non valido");
        int j = 0;
        Iterator<T> it = iterator();
        while(it.hasNext()){
            T y = it.next();
            if(i == j){
                it.remove();
                return y;
            }
            j++;
        }
        return null;
    }
    default void remove(T x){
        Iterator<T> it = iterator();
        while (it.hasNext()){
            T y = it.next();
            if(y.equals(x)) {
                it.remove();
                break;
            }
        }
    }
    default void clear(){
        Iterator<T> it = iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
    }
    default int indexOf(T x){
        int i = 0;
        for (T o : this) {
            if(o.equals(x)) return i;
            i++;
        }
        return -1;
    }
    default boolean isEmpty(){
        return size() == 0;
    }
    default boolean contains(T x){
        int i = indexOf(x);
        return i != -1;
    }
}
