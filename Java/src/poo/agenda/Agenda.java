package poo.agenda;

import java.io.*;
import java.util.Iterator;

/**
 * Tipo di dato astratto che descrive un'agendina telefonica.
 * Gli elementi sono ti tipo Contatto. Non si ammettono le
 * omonimie. L'agendina e' supposta mantenuta ordinata
 * per cognome crescente e a parita' di cognome per nome.
 * @author Libero Nigro
 */
public interface Agenda extends Iterable<Contatto>{
	
	/**
	 * Restituisce il numero di nominativi dell'agenda.
	 * @return il numero di nominativi in agenda.
	 */
	default int size() {
		int conta=0;
		for( Contatto n: this ) conta++;
		return conta;		
	}//size
	
	/**
	 * clear il contenuto dell'agendina.
	 */
	default void clear() {
		Iterator<Contatto> it=this.iterator();
		while( it.hasNext() ) {
			it.next(); it.remove();
		}		
	}//clear
	
	/** 
	 * Aggiunge un Contatto all'agenda. Non si ammettono
	 * le omonimie. L'aggiunta avviene in ordine alfabetico crescente
	 * del cognome ed a parita' di cognome in ordine alfabetico del nome.
	 * @param n il Contatto da aggiungere
	 */
	void add(Contatto n);
	
	/**
	 * Rimuove un Contatto dall'agenda.
	 * @param n il Contatto da rimuovere dall'agenda.
	 */
	default void remove(Contatto n) {
		Iterator<Contatto> it=this.iterator();
		while( it.hasNext() ) {
			Contatto x=it.next();
			if( x.equals(n) ){ it.remove(); break; }
			if( x.compareTo(n)>0 ) break;
		}		
	}//remove
	
	/**
	 * search un Contatto uguale ad n.
	 * @param n il Contatto da cercare, significativo solo per nome e cognome.
	 * @return il Contatto dell'agenda uguale ad n o null se n non e' in agenda.
	 */
	default Contatto search(Contatto n) {
		for( Contatto x: this ){
			if( x.equals(n) ) return x;
			if( x.compareTo(n)>0 ) break;
		}		
		return null;		
	}//search
	
	/**
	 * search un Contatto nell'agenda, di assegnato prefisso e numero di telefono.
	 * @param prefisso 
	 * @param telefono
	 * @return il Contatto trovato o null
	 */
	default Contatto search(String prefisso, String telefono) {
		for( Contatto x: this )
			if( x.getPrefisso().equals(prefisso) &&
				x.getNumero().equals(telefono) ) return x;
		return null;		
	}//search
	
	/**
	 * save il contenuto dell'agenda su file.
	 * @param nomeFile il nome esterno del file per il savetaggio.
	 * @throws IOException 
	 */
	default void save(String nomeFile) throws IOException{
		ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream(nomeFile));
			for( Contatto n: this ) 
				oos.writeObject(n);
			oos.close();		
	}//save
	
	/**
	 * load il contenuto dell'agenda, a partire da un file.
	 * @param nomeFile il nome esterno del file da cui attingere.
	 * @throws IOException es. se il file non esiste
	 */
	default void load(String nomeFile) throws IOException{
		ObjectInputStream ois=
				new ObjectInputStream(new FileInputStream(nomeFile));
		Contatto n=null;
		this.clear();
		for(;;){
			try{
				n=(Contatto)ois.readObject();
			}catch(ClassNotFoundException e){
				throw new IOException();
			}catch(ClassCastException cce){
				throw new IOException();				
			}catch(EOFException eof){
				break;
			}
			this.add(n);
		}//for
		ois.close();
	}//load
	
}//Agendina