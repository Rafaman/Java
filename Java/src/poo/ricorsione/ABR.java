package poo.ricorsione;

import java.util.Iterator;
import poo.util.didattica.*;

public class ABR<T extends Comparable<? super T>> implements CollezioneOrdinata<T> {
	private static class Nodo<E>{
		E info;
		Nodo<E> fS, fD;
	}

	private Nodo<T> radice = null;

	public int size() {
		return size(radice);
	}
	private int size(Nodo<T> radice) {
		if(radice==null) return 0;
		return 1+size(radice.fS)+size(radice.fD);
	}

	public void clear() {
		radice=null;
	}

	public boolean contains(T e) {
		return contains(radice,e);
	}
	private boolean contains(Nodo<T> radice, T e) {
		if(radice==null) return false;
		if(radice.info.equals(e)) return true;
		if(radice.info.compareTo(e)>0)
			return contains(radice.fS,e);
		return contains(radice.fD, e);
	}

	public T get(T e) {
		return get(radice,e);
	}
	private T get(Nodo<T> radice, T e) {
		if(radice==null) return null;
		if(radice.info.equals(e)) return radice.info;
		if(radice.info.compareTo(e)>0)
			return get(radice.fS,e);
		return get(radice.fD, e);
	}

	public boolean isEmpty() {
		return radice == null;
	}
	public boolean isFull() {
		return false;
	}

	public void add(T e) {
		radice = add(radice, e);
	}
	private Nodo<T> add(Nodo<T> radice, T e){
		if(radice == null) {
			radice = new Nodo<>();
			radice.info = e;
			radice.fS=null;
			radice.fD=null;
			return radice;
		}
		if(radice.info.compareTo(e)>=0) {
			radice.fS=add(radice.fS,e);
			return radice;
		}
		radice.fS=add(radice.fD,e);
		if(!bilanciato()) bilancia();
		return radice;
	}

	private boolean bilanciato(){
		int radiciFD, radiciFS;
		radiciFD = countRadici(radice.fD);
		radiciFS = countRadici(radice.fS);
		return radiciFD == radiciFS;
	}
	private int countRadici(Nodo<T> radice){
		int conta = 0;
		if(radice == null) return 0;
		conta = 1 + countRadici(radice.fD);
		conta = 1 + countRadici(radice.fS);
		return conta;
	}
	private void bilancia(){

	}

	public void addIte(T e) {
		boolean flag=false;
		Nodo<T> padre = null, figlio = null;
		while(figlio == null) {
			if(figlio.info.compareTo(e)>0) {
				padre=figlio; figlio=figlio.fS;
				flag = true;
			}
			else {
				radice = figlio; figlio = figlio.fD;
				flag=false;
			}
		}
		Nodo<T> n = new Nodo<>(); n.info=e;
		n.fD=null; n.fS=null;
		if(padre==null)
			radice=n;
		else {
			if(flag) padre.fS=n;
			else padre.fD=n;
		}
	}

	public void remove(T e) {
		radice = remove(radice,e);
	}
	private Nodo<T> remove(Nodo<T> radice, T e){
		if(radice==null) return null;
		if(radice.info.compareTo(e)>0) {
			radice.fS = remove(radice.fS, e);
			return radice;
		}
		if(radice.info.compareTo(e)<0) {
			radice.fD = remove(radice.fD,e);
			return radice;
		}
		if(radice.fD == null && radice.fS==null) {
			return null;
		}
		if(radice.fS == null) {
			return radice.fD;
		}
		if(radice.fD==null) {
			return radice.fS;
		}
		if(radice.fD.fS == null) {
			//promozione
			radice.info = radice.fD.info;
			//eliminazione nodo più a sinix
			radice.fD=radice.fD.fD;
			return radice;
		}
		Nodo<T> padre = radice.fD, figlio = radice.fD.fS;
		while(figlio.fS!=null) {
			padre=figlio;
			figlio=figlio.fS;
		}
		//promozione
		radice.info=figlio.info;
		//eliminazione nps
		padre.fS= figlio.fD;
		return radice;
	}

	public void visitaSimmetrica() {
		visitaSimmetrica(radice);
	}
	private void visitaSimmetrica(Nodo<T> radice) {
		if(radice!=null) {
			visitaSimmetrica(radice.fS);
			System.out.println(radice.info+" ");
			visitaSimmetrica(radice.fD);
		}
	}


	public String toString() {
		StringBuilder sb=new StringBuilder(200);
		sb.append('[');
		toString( radice, sb );
		if( sb.length()>0 ) sb.setLength( sb.length()-2 );
		sb.append(']');
		return sb.toString();
	}
	private void toString( Nodo<T> radice, StringBuilder sb ){
		if( radice==null ) return;
		toString( radice.fS, sb );
		sb.append( radice.info );
		sb.append(", ");
		toString( radice.fD, sb );
	}//toString

	public Iterator<T> iterator(){
		return new ABRIterator();
	}

	private class ABRIterator implements Iterator<T>{
		private T cor = null;
		private Iterator<T> it = null;

		List<T> l = new LinkedList();
		public ABRIterator() {
			visitaSimmetrica((Nodo<T>) l);
			it = l.iterator();
		}

		public boolean hasNext() {
			return it.hasNext();
		}
		public void remove() {
			it.remove();
			ABR.this.remove(cor);
			cor = null;
		}
		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}  
