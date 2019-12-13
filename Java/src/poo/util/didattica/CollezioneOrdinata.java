package poo.util.didattica;

public interface CollezioneOrdinata<T extends Comparable<? super T>> extends Iterable<T> {
    int size();
    boolean contains(T e);
    boolean isEmpty();
    boolean isFull();
    T get(T e);
    void clear();
    void add(T e);
    void remove(T e);
}
