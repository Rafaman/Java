package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

class Espressione{

	private int cont=0;
	private StringTokenizer st;
	private String EXPR="(\\d+|[\\+\\-\\*/\\(\\)])+"; //solo condizione necessaria

	public Espressione( String expr ) {
		if( !expr.matches(EXPR) )
			throw new IllegalArgumentException();
		st=new StringTokenizer(expr,"+-*/()",true);
	}

	public int valutaEspressione() {
		int ris=valutaOperando();
		while( st.hasMoreTokens() ) {
			char op=st.nextToken().charAt(0);
			if( op==')' ) {
				if( cont==0 ) throw new RuntimeException(") e cont==0");
				cont--;
				return ris;
			};
			int opnd=valutaOperando();
			switch( op ) {
			case '+': ris=ris+opnd; break;
			case '-': ris=ris-opnd; break;
			case '*': ris=ris*opnd; break;
			case '/': ris=ris/opnd; break;
			default : throw new RuntimeException();
			}
		}
		if( cont>0 ) throw new RuntimeException("Fine espressione e cont>0");
		return ris;
	}//valutaEspressione

	private int valutaOperando() {
		String tk=st.nextToken();
		if( tk.contentEquals("(") ) {
			cont++;
			return valutaEspressione();
		}
		return Integer.parseInt(tk);
	}//valutaOperando

}//Espressione

public class ValutatoreFT {

	public static void main( String...args ) {
		Scanner sc=new Scanner( System.in );
		System.out.println("Valutatore interattivo fault-tolerant");
		System.out.println("Inserire una espressione aritmetica o . per terminare");
		for(;;) {
			System.out.print("> ");
			String espr=sc.nextLine();
			if( espr.equals(".") ) break;
			try {
				int ris=new Espressione(espr).valutaEspressione();
				System.out.println(espr+"="+ris);
			}catch( RuntimeException e ) {
				//e.printStackTrace();
				System.out.println("Espressione "+espr+" malformata!");
			}
		}
		System.out.println("Bye.");
		sc.close();
	}//main

}//ValutatoreFT
