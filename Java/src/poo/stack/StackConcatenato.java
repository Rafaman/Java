package poo.stack;
import java.util.*;
public class StackConcatenato<T> extends StackAstratto<T> {
	 private static class Nodo<E>{
		 E info;
		 Nodo<E> next;
	 }
	 private Nodo<T> testa= null;
	 private int size = 0;
	 public int size(){ return size;}
	 public void clear() {
		 testa = null;
	 }
	 public void push(T x) {
		 Nodo<T> n = new Nodo<>();
		 n.info = x;
		 n.next = testa; testa = n;
		 size++;
	 }
	 public T pop(){
		 if(testa==null) throw new NoSuchElementException();
		 T x = testa.info;
		 testa = testa.next;
		 size--;
		 return x;
	 }
	 public T top() {
		 if(testa==null) throw new NoSuchElementException();
		 return testa.info;
	 }
	 public boolean isEmpty() {return testa==null;}
	 public boolean isFull() {return false;}
	public String toString(){
			StringBuilder sb = new StringBuilder(200);
			sb.append('[');
			Nodo<T> cor = testa;
			while(cor!=null) {
				sb.append(cor.info);
				cor=cor.next;
				if(cor!=null)
					sb.append(", ");
			}
			sb.append("]");
			return sb.toString();
		}
	public Iterator<T> iterator(){
		return new StackIterator();
	}
	private class StackIterator implements Iterator<T>{
		Nodo<T> pre=null, cor = null;
		public boolean hasNext() {
			if(cor==null) return testa!=null;
			return cor.next != null;
		}
		public T next() {
			if(!hasNext()) throw new NoSuchElementException();
			if(cor==null) cor = testa;
			else {
				pre = cor;
				cor = cor.next;}
			return cor.info;
			}
		public void remove() {
			if(cor==pre) throw new IllegalStateException();
			if(cor==testa) testa=testa.next;
			else {pre.next = cor.next;}
			cor=pre;
		}
	}
}
