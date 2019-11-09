package poo.agenda;

import java.util.*;

public class AgendaLL extends AbstractAgenda{
	private LinkedList<Contatto> table=
		new LinkedList<Contatto>();
	
	@Override
	public Iterator<Contatto> iterator(){
		return table.iterator();
	}//iterator
	
	@Override
	public void clear(){ table.clear(); }
	
	@Override
	public void add( Contatto n ){
		//aggiunge n in ordine, evitando le omonimie
		//se n e' gia' presente, si sovrascrive
		ListIterator<Contatto> lit=table.listIterator();
		boolean flag=false;
		while( lit.hasNext() && !flag ){
		      Contatto x=lit.next();
		      if( x.equals(n) ){ lit.set(n); flag=true; }
		      else if( x.compareTo(n)>0 ){
		    	  lit.previous();
		    	  lit.add(n);
		    	  flag=true;
		      }
		}
		if( !flag ) lit.add(n);
	}//add
	
	@Override
	public int size(){ return table.size(); }
	
}//agendaLL