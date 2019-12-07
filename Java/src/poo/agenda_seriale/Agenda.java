package poo.agenda_seriale;
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
	 * Svuota il contenuto dell'agendina.
	 */
	default void svuota() {
		Iterator<Contatto> it=this.iterator();
		while( it.hasNext() ) {
			it.next(); it.remove();
		}		
	}//svuota
	
	/** 
	 * Aggiunge un Contatto all'agenda. Non si ammettono
	 * le omonimie. L'aggiunta avviene in ordine alfabetico crescente
	 * del cognome ed a parita' di cognome in ordine alfabetico del nome.
	 * @param n il Contatto da aggiungere
	 */
	void aggiungi( Contatto n );
	
	/**
	 * Rimuove un Contatto dall'agenda.
	 * @param n il Contatto da rimuovere dall'agenda.
	 */
	default void rimuovi( Contatto n ) {
		Iterator<Contatto> it=this.iterator();
		while( it.hasNext() ) {
			Contatto x=it.next();
			if( x.equals(n) ){ it.remove(); break; }
			if( x.compareTo(n)>0 ) break;
		}		
	}//rimuovi
	
	/**
	 * Cerca un Contatto uguale ad n.
	 * @param n il Contatto da cercare, significativo solo per nome e cognome.
	 * @return il Contatto dell'agenda uguale ad n o null se n non e' in agenda.
	 */
	default Contatto cerca( Contatto n ) {
		for( Contatto x: this ){
			if( x.equals(n) ) return x;
			if( x.compareTo(n)>0 ) break;
		}		
		return null;		
	}//cerca
	
	/**
	 * Cerca un Contatto nell'agenda, di assegnato prefisso e numero di telefono.
	 * @param prefisso 
	 * @param telefono
	 * @return il Contatto trovato o null
	 */
	default Contatto cerca( String prefisso, String telefono ) {
		for( Contatto x: this )
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
			for( Contatto n: this ) 
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
		Contatto n=null;
		this.svuota();
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
			this.aggiungi(n);
		}//for
		ois.close();
	}//ripristina
	
}//Agendina