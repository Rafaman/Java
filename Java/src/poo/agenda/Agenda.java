package poo.agenda;
import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
/**
 * Tipo di dato astratto che descrive un'Agenda telefonica.
 * Gli elementi sono ti tipo Contatto. Non si ammettono le
 * omonimie. L'Agenda e' supposta mantenuta ordinata
 * per cognome crescente e a parita' di cognome per nome crescente.
 * @author Libero Nigro
 */
public interface Agenda extends Iterable<Contatto>{

    /**
     * Restituisce il numero di nominativi dell'agenda.
     * @return il numero di nominativi in agenda.
     */
    default int size(){
        int c=0;
        for( Iterator<Contatto> it=iterator(); it.hasNext(); it.next(),c++ );
        return c;
    }//size

    /**
     * Svuota il contenuto dell'Agenda.
     */
    default void clear(){
        Iterator<Contatto> it=iterator();
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
    void add( Contatto n );

    /**
     * Rimuove un Contatto dall'agenda.
     * @param n il Contatto da rimuovere dall'agenda.
     */
    default void remove( Contatto n ){
        Iterator<Contatto> it=iterator();
        while( it.hasNext() ) {
            Contatto x=it.next();
            if( x.equals(n) ){ it.remove(); break;}
            else if( x.compareTo(n)>0 ) break;
        }
    }//rimuovi

    /**
     * Cerca un Contatto uguale ad n.
     * @param n il Contatto da cercare, significativo solo per nome e cognome.
     * @return il Contatto dell'agenda uguale ad n o null se n non e' in agenda.
     */
    default Contatto search( Contatto n ){
        for( Contatto x: this ){
            if( x.equals(n) ) return x;
            if( x.compareTo(n)>0 ) return null;
        }
        return null;
    }//cerca

    /**
     * Cerca un Contatto nell'agenda, di assegnato prefisso e numero di telefono.
     * @param prefisso
     * @param telefono
     * @return il Contatto trovato o null
     */
    default Contatto search( String prefisso, String telefono ){
        for( Contatto n: this )
            if( n.getPrefisso().equals(prefisso) && n.getNumero().equals(telefono) ) return n;
        return null;
    }//cerca

    /**
     * Salva il contenuto dell'agenda su file.
     * @param nomeFile il nome esterno del file per il salvataggio.
     * @throws IOException
     */
    default void save(String nomeFile) throws IOException{
        PrintWriter pw=new PrintWriter( new FileWriter(nomeFile));
        for( Contatto n: this ) pw.println(n);
        pw.close();
    }//salva

    /**
     * Ripristina il contenuto dell'agenda, a partire da un file.
     * @param nomeFile il nome esterno del file da cui attingere.
     * @throws IOException es. se il file non esiste
     */
    default void load(String nomeFile)throws IOException{
        BufferedReader br=new BufferedReader( new FileReader(nomeFile) );
        String linea=null;
        StringTokenizer st=null;

        LinkedList<Contatto> tmp=new LinkedList<Contatto>();
        //tmp e' utile per far fronte a malformazioni del file
        boolean okLettura=true;
        for(;;){
            linea=br.readLine();
            if( linea==null ) break; //eof di br
            st=new StringTokenizer(linea," -");
            try{
                String cog=st.nextToken();
                String nom=st.nextToken();
                String pre=st.nextToken();
                String tel=st.nextToken();
                tmp.add( new Contatto( cog, nom, pre, tel ) ); //aggiunge in coda
            }catch(Exception e){
                okLettura=false;
                break;
            }
        }
        br.close();
        if( okLettura ){
            this.clear();
            for( Contatto n: tmp ) this.add(n);
        }
        else
            throw new IOException();
    }//ripristina

}//Agenda