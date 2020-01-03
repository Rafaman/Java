package poo.stack;

import java.util.*;
import java.util.Stack;

public class StackArray<T> extends Stack<T> {
	private T[] pila;
	private int size;
	public StackArray(int n) {
		if(n<=0) throw new IllegalArgumentException();
		pila =(T[]) new Object[n];
		size=0;
	}
	public StackArray() {this(17);}
	public int size() {return size;}
	public void clear() {
		for(int i=0; i<size;++i) pila[i] = null;
		size=0;
	}
	public boolean isEmpty() {return size==0;}
	public boolean isFull() {return false;}
	public T push(T x) {
		if(size==pila.length) pila=java.util.Arrays.copyOf(pila, size*2);
		pila[size] = x;
		size++;
		return x;
	}
	public T pop() {
		if(size==0) throw new NoSuchElementException();
		size--; T x = pila[size]; pila[size] = null;
		return x;
	}
	public T top() {
		if(size==0) throw new NoSuchElementException();
		return pila[size-1];
	}
	public Iterator<T> iterator(){
		return new StackArrayIterator();
	}
	public class StackArrayIterator implements Iterator<T>{
		public int cor = size;
		private boolean rimuovibile = false;
		public boolean hasNext() {
			return cor>0;
		}
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			rimuovibile = true;
			cor--; return pila[cor];
		}
		public void remove() {
			if(!rimuovibile) throw new IllegalStateException(); //l'elemento rimosso sar� quello puntato da cor
			rimuovibile=false;
			for(int i = cor; i<size-1;++i)
				pila[i] = pila[i+1];
			size--; // cor non si tocca
		}
	}
}
