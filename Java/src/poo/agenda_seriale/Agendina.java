package poo.agenda_seriale;
import java.io.*;
import java.util.Iterator;
/**
 * Tipo di dato astratto che descrive un'agendina telefonica.
 * Gli elementi sono ti tipo Nominativo. Non si ammettono le
 * omonimie. L'agendina e' supposta mantenuta ordinata
 * per cognome crescente e a parita' di cognome per nome.
 * @author Libero Nigro
 */
public interface Agendina extends Iterable<Nominativo>{
	
	/**
	 * Restituisce il numero di nominativi dell'agenda.
	 * @return il numero di nominativi in agenda.
	 */
	default int size() {
		int conta=0;
		for( Nominativo n: this ) conta++;
		return conta;		
	}//size
	
	/**
	 * Svuota il contenuto dell'agendina.
	 */
	default void svuota() {
		Iterator<Nominativo> it=this.iterator();
		while( it.hasNext() ) {
			it.next(); it.remove();
		}		
	}//svuota
	
	/** 
	 * Aggiunge un nominativo all'agenda. Non si ammettono
	 * le omonimie. L'aggiunta avviene in ordine alfabetico crescente
	 * del cognome ed a parita' di cognome in ordine alfabetico del nome.
	 * @param n il nominativo da aggiungere
	 */
	void aggiungi( Nominativo n );
	
	/**
	 * Rimuove un nominativo dall'agenda.
	 * @param n il nominativo da rimuovere dall'agenda.
	 */
	default void rimuovi( Nominativo n ) {
		Iterator<Nominativo> it=this.iterator();
		while( it.hasNext() ) {
			Nominativo x=it.next();
			if( x.equals(n) ){ it.remove(); break; }
			if( x.compareTo(n)>0 ) break;
		}		
	}//rimuovi
	
	/**
	 * Cerca un nominativo uguale ad n.
	 * @param n il nominativo da cercare, significativo solo per nome e cognome.
	 * @return il nominativo dell'agenda uguale ad n o null se n non e' in agenda.
	 */
	default Nominativo cerca( Nominativo n ) {
		for( Nominativo x: this ){
			if( x.equals(n) ) return x;
			if( x.compareTo(n)>0 ) break;
		}		
		return null;		
	}//cerca
	
	/**
	 * Cerca un nominativo nell'agenda, di assegnato prefisso e numero di telefono.
	 * @param prefisso 
	 * @param telefono
	 * @return il nominativo trovato o null
	 */
	default Nominativo cerca( String prefisso, String telefono ) {
		for( Nominativo x: this )
			if( x.getPrefisso().equals(prefisso) &&
				x.getTelefono().equals(telefono) ) return x;
		return null;		
	}//cerca
	
	/**
	 * Salva il contenuto dell'agenda su file.
	 * @param nomeFile il nome esterno del file per il salvataggio.
	 * @throws IOException 
	 */
	default void salva(String nomeFile) throws IOException{
		ObjectOutputStream oos=
				new ObjectOutputStream(new FileOutputStream(nomeFile));
			for( Nominativo n: this ) 
				oos.writeObject(n);
			oos.close();		
	}//salva
	
	/**
	 * Ripristina il contenuto dell'agenda, a partire da un file.
	 * @param nomeFile il nome esterno del file da cui attingere.
	 * @throws IOException es. se il file non esiste
	 */
	default void ripristina(String nomeFile) throws IOException{
		ObjectInputStream ois=
				new ObjectInputStream(new FileInputStream(nomeFile));
		Nominativo n=null;
		this.svuota();
		for(;;){
			try{
				n=(Nominativo)ois.readObject();
			}catch(ClassNotFoundException e){
				throw new IOException();
			}catch(ClassCastException cce){
				throw new IOException();				
			}catch(EOFException eof){
				break;
			}
			this.aggiungi(n);
		}//for
		ois.close();
	}//ripristina
	
}//Agendina