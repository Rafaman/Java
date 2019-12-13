package poo.util.didattica;

import java.util.*;

public class EnjoyVector<T> extends AbstractVector<T>{
    private T[] array;
    private int size;

    @SuppressWarnings("unchecked")
    public EnjoyVector(int capacity){
        if(capacity <= 0) throw new IllegalArgumentException("Capacità negativa");
        array = (T[])new Object[capacity];
    }
    public EnjoyVector(){ this(17); }
    @Override
    public int size(){ return size; }
    public T set(int i, T x){
        if(i < 0 || i >= size) throw new IndexOutOfBoundsException("Indice errato");
        T y = array[i];
        array[i] = x;
        return y;
    }
    public void add(int i, T x){
        if(i < 0 || i > size) throw new IndexOutOfBoundsException("Indice errato");
        if(size == array.length) array = Arrays.copyOf(array, size * 2);
        System.arraycopy(array, size - 1, array, i, size - i - 1);
        array[i] = x; size++;
    }
    public Iterator<T> iterator(){
        return new EnjoyVectorIterator();
    }
    private class EnjoyVectorIterator implements Iterator<T>{
        private int cur;
        private boolean rimovibile;
        @Override
        public boolean hasNext() {
            if(cur == -1) return size > 0;
            return cur < size - 1;
        }
        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            cur++; rimovibile = true;
            return array[cur];
        }
        @Override
        public void remove() {
            if(!rimovibile) throw new IllegalStateException();
            rimovibile = false;
            System.arraycopy(array, cur - 1, array, size - 1, size - cur - 2);
            size--; cur--;
        }
    }

    public static void main(String[] args) {
        String[] as = {"zaino", "tana", "lupo", "fuoco", "abaco", "deal"};
        Vector<String> vs = new EnjoyVector<>();
        for(String s: as){
            int i = 0;
            boolean flag = false;
            while(i < vs.size() && !flag){
                if(vs.get(i).compareTo(s) >= 0) {
                    vs.add(i, s);
                    flag = true;
                } else i++;
            }
            if(!flag) vs.add(s);
        }

        System.out.println(vs);
    }
}
