package poo.agenda;

import java.util.*;

public class AgendaAL extends AbstractAgenda {
	private List<Contatto> tabella;

	public AgendaAL(){
		this(100);
	}
	public AgendaAL(int n ){
		if( n<=0 ) throw new IllegalArgumentException();
		tabella=new ArrayList<Contatto>(n);
	}
	
	@Override
	public int size(){ return tabella.size(); }
	
	@Override
	public void clear(){ tabella.clear(); }
	
	@Override
    public void add( Contatto n ){
		int i=Collections.binarySearch( tabella, n );
		if( i>=0 ){ tabella.set(i,n); return; }
		i=0;
		while( i<tabella.size() ){
			Contatto x=tabella.get(i);
			if( x.compareTo(n)>0 ) break;
			i++;
		}
		tabella.add(i,n); //inserisce n in posizione i
	}//aggiungi
	
	@Override
	public void remove( Contatto n ){
		int i=Collections.binarySearch( tabella, n );
		if( i<0 ) return;
		tabella.remove(i);
	}//rimuovi
	
	@Override
	public Contatto search( Contatto n ){
		int i=Collections.binarySearch( tabella, n );
		if( i<0 ) return null;
		return tabella.get(i);
	}//cerca
	
	@Override
	public Iterator<Contatto> iterator(){
		return tabella.iterator();
	}//iterator

}//AgendinaAL