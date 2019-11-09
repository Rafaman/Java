package poo.agenda;

import java.util.*;
import java.io.*;

public class GestioneAgenda {
	//ambiente globale
	static Agenda agenda;
	static String linea;
	static StringTokenizer st;
	static Scanner sc;
	
	public static void main( String []args ) throws IOException{
		System.out.println("Programma Agenda Telefonica");
		System.out.println();
		System.out.println("Scegli tra: 0-AgendaAL 1-AgendaLL 2-AgendaSet 3-AgendaMap 4-AgendaVector");
		sc=new Scanner( System.in );
		int tipo=0, capacita=0;
		do{
			tipo=sc.nextInt();
			if( tipo<0 || tipo>4 )
				System.out.print("Cosa ? Digitare un numero da 0 a 4 ");
		}while(tipo<0 || tipo>4);
		if( tipo==0 || tipo==4 ){
			System.out.print("Capacita' = ");
			capacita=sc.nextInt();
		}
		switch(tipo){
			case 0: 
				agenda=new AgendaAL(capacita); break;
			case 1: 
				agenda=new AgendaLL(); break;
			case 2: 
				agenda=new AgendaSet(); break;
			default:
				agenda=new AgendaMap(); break;
		}
		
		sc.nextLine(); //salta fine linea
		comandi();
		ciclo: for(;;){
			System.out.print(">");
			linea=sc.nextLine();
			st=new StringTokenizer(linea, " ");
			char comando=(st.nextToken().toUpperCase()).charAt(0);
			switch( comando ){
				case 'Q': quit(); break ciclo;
				case 'A': addContatto(); break;
				case 'R': removeContatto(); break;
				case 'T': risearchTelefono(); break;
				case 'P': risearchPersona(); break;
				case 'Z': azzera(); break;
				case 'E': mostraElenco(); break;
				case 'S': save(); break;
				case 'C': carica(); break;
				default: errore();
			}
		}//for
		System.out.println("Bye.");
	}//main
	
	static void addContatto(){
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			String pre=st.nextToken();
			String tel=st.nextToken();
			Contatto n=new Contatto( cog, nom, pre, tel );
			agenda.add( n );
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//aggiugiContatto
	
	static void removeContatto(){
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			agenda.remove( new Contatto(cog, nom, "", "") );
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//removeContatto
	
	static void azzera(){
		agenda.clear();
	}//azzera
	
	static void risearchTelefono(){
		try{
			String cog=st.nextToken().toUpperCase();
			String nom=st.nextToken().toUpperCase();
			Contatto n=agenda.search( new Contatto(cog, nom, "", "") );
			if( n==null ) 
				System.out.println("Contatto inesistente!");
			else 
				System.out.println(n.getPrefisso()+"-"+n.getNumero());		
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//risearchTelefono
	
	static void risearchPersona(){
		try{
			String pre=st.nextToken();
			String tel=st.nextToken();
			Contatto n=agenda.search( pre, tel );
			if( n==null ) 
				System.out.println("Contatto inesistente!");
			else 
				System.out.println(n.getCognome()+" "+n.getNome());			
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
		}
	}//risearchPersona
	
	static void mostraElenco(){
		System.out.println( agenda );
	}//mostraElenco
	
	static void save(){
		String nomeFile=null;
		try{
        	nomeFile=st.nextToken();
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
			return;
		}
		try{
			agenda.save( nomeFile );
		}catch( IOException e ){
			System.out.println("Nessun savetaggio!");
		}
	}//save
	
	static void carica(){
		String nomeFile=null;
		try{
        	nomeFile=st.nextToken();
		}catch( Exception e ){
			System.out.println("Dati incompleti!");
			return;
		}
		File f=new File( nomeFile );
		if( !f.exists() ){
			System.out.println("File inesistente!");
			return;
		}		
		try{
			agenda.load( nomeFile );
		}catch(IOException e){
			System.out.println("Nessuna apertura!");
		}
	}//carica
	
	static void comandi(){
		System.out.println();
		System.out.println("Comandi ammessi e relativi parametri:");
		System.out.println("A(ggiungi  cog  nom  pre  tel");
		System.out.println("R(imuovi  cog  nom");
		System.out.println("Z(azzera");
		System.out.println("T(elefono  cog  nom");
		System.out.println("P(persona  pre  tel");
		System.out.println("E(lenco");
		System.out.println("S(alva  nomefile");
		System.out.println("C(arica  nomefile");
		System.out.println("Q(uit");
		System.out.println();
	}//comandi
	
	static void errore(){
		System.out.println("Comando sconosciuto!");
		comandi();
	}//errore
	
	static void quit(){
		System.out.print("Vuoi savere il contenuto dell'agenda prima di terminare(y/n)?");
		String yesno=sc.nextLine();
		if( yesno.toLowerCase().equals("y") ){
			System.out.print("nome file=");
			String nomeFile=sc.nextLine();
			try{
				agenda.save( nomeFile );
			}catch( IOException ioe ){
				System.out.println("Errore savetaggio!");
			}
		}
	}//quit
	
}//GestioneAgenda
