package poo.agenda;

import java.util.*;

public class AgendaMap extends AbstractAgenda{
	private Map<Contatto,Contatto> tabella = new TreeMap<Contatto,Contatto>();
	
	@Override
	public int size(){ return tabella.size(); }
	
	@Override
	public void clear(){ tabella.clear(); }
    
	@Override
	public void add( Contatto n ){
		tabella.put(n, n);
	}//aggiungi
	
	@Override
	public void remove( Contatto n ){
		tabella.remove( n );
	}//rimuovi
	
	@Override
	public Contatto search( Contatto n ){
		return tabella.get( n );
	}//cerca
	
	@Override
	public Iterator<Contatto> iterator(){
		return tabella.values().iterator();
	}//iterator
	
}//AgendinaMap