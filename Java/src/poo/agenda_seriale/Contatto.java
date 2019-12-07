//file Contatto.java
package poo.agenda_seriale;
import java.io.*;

public class Contatto implements Serializable, Comparable<Contatto>{
	private static final long serialVersionUID = -6944153582493164448L;
	
	private String cognome, nome, prefisso, telefono;
	public Contatto( String cognome, String nome,
		String prefisso, String telefono ){
		this.cognome=cognome; this.nome=nome;
		this.prefisso=prefisso; this.telefono=telefono;
	}
	//metodi accessori
	public String getCognome(){ return cognome; }
	public String getNome(){ return nome; }
	public String getPrefisso(){ return prefisso; }
	public String getTelefono(){ return telefono; }
	public boolean equals( Object o ){
		if( o==null || !(o instanceof Contatto) ) return false;
		if( o==this ) return true;
		Contatto n=(Contatto)o;
		return this.cognome.equals(n.cognome) &&
		       this.nome.equals(n.nome);
	}//equals
	public int compareTo( Contatto n ){
		if( this.cognome.compareTo(n.cognome)<0 ||
			this.cognome.equals(n.cognome) &&
		    this.nome.compareTo(n.nome)<0 ) return -1;
		if( this.equals(n) ) return 0;
		return +1;
	}//compareTo
	public String toString(){
		return cognome+" "+nome+" "+prefisso+"-"+telefono;
	}//toString
	public int hashCode(){ 
		int PRIMO=43;
		int h1=cognome.hashCode();
		int h2=nome.hashCode();
		return h1*PRIMO+h2;
	}//hashCode
}//Contatto