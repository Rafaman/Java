package poo.util.didattica;

public abstract class ListaRecurs<T extends Comparable<? super T >> implements CollezioneOrdinata<T>{
	private static class Lista<E> {//nodo
		E info;
		Lista<E> next;
	}
	private Lista<T> lista = null;
	public int size() {
		return size(lista);
	}
	private int size(Lista<T> lista) {
		if(lista==null) return 0;
		return 1+size(lista.next);//testa + lista residua
	}
	public boolean contains(T e) {
		return contains(lista,e);
	}
	private boolean contains(Lista<T> lista, T e) {
		if(lista==null) return false;
		if(lista.info.equals(e)) return true;
		if(lista.info.compareTo(e)>0) return false;
		return contains(lista.next, e);
	}
	public T get(T e) {
		return get(lista, e);
	}
	private T get(Lista<T> lista, T e) {
		if(lista==null) return null;
		if(lista.info.equals(e)) return lista.info;
		if(lista.info.compareTo(e)>0) return null;
		return null;
	}
	public String toString() {
		StringBuilder sb = new StringBuilder(300);
		sb.append('[');
		toString(lista, sb);
		sb.append('[');
		return sb.toString();
	}
	private void toString(Lista<T> lista, StringBuilder sb ) {
		if(lista==null) return;
		sb.append(lista.info);
		if(lista.next!=null)
			sb.append(", ");
		toString(lista.next, sb);
	}
	public boolean equals(Object x) {
		if(!(x instanceof ListaRecurs)) return false;
		if(x == this) return true;
		ListaRecurs<T> l = (ListaRecurs<T>)x;
		return equals(lista, l.lista);
	}
	private boolean equals(Lista<T> l1, Lista<T> l2) {
		if(l1==null && l2==null)
			return true;
		if(l1==null || l2==null) return false;
		if(!(l1.info.equals(l2.info))) return false;
		return equals(l1.next,l2.next);
	}
	public void add(T e) {
		lista = add(lista, e);
	}
	private Lista<T> add(Lista<T> lista, T e){
		if(lista==null) {
			Lista<T> n = new Lista<>();
			n.info=e; n.next=null;
			return n;
		}
		if(lista.info.compareTo(e)>=0) {
			Lista<T> n = new Lista<>();
			n.info = e; n.next=lista;
			return n;
		}
		lista.next = add(lista.next, e);
		return lista;
	}
	public void remove(T e) {
		lista = remove(lista,e);
	}
	private Lista<T> remove(Lista<T> lista, T e){
		if(lista==null) return lista;
		if(lista.info.equals(e)) return lista.next;
		if(lista.info.compareTo(e)>0) return lista;
		lista.next=remove(lista.next, e);
		return lista;
	}
}
