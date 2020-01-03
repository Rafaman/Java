package poo.stack;

public interface Stack<T> extends Iterable<T> {
	int size();
	void clear();
	void push(T e);
	T pop(); //rimuove
	T top(); //vedere cosa c'� in cima
	//possono andare in eccezione sia pop che top
	boolean isEmpty();
	boolean isFull();
}
