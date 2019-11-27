package poo.regex;

import java.util.Scanner;

public class Classificatore {
	public static void main( String[] args ) {
		System.out.println("Inserisci un identificatore o un numero e termina con STOP");
		//String INT="[\\+\\-]?\\d+";
		String IDENT="[a-zA-Z_]\\w*";
		//String REAL="[\\+\\-]?(\\d+|\\d*\\.\\d+)([eE][\\+\\-]?\\d{1,3})?[dDfF]?";
		
		String SIGN="[\\+\\-]";
		String UNSIGNED_INT="\\d+";
		String INT=SIGN+"?"+UNSIGNED_INT;
		
		String SUF="[dDfF]";
		String MANTISSA="("+UNSIGNED_INT+"|("+UNSIGNED_INT+")?"+"\\."+UNSIGNED_INT+")";
		String EXPO="([eE]"+SIGN+"?"+UNSIGNED_INT+")";
		
		String REAL=SIGN+"?"+MANTISSA+EXPO+"?"+SUF+"?";
		
		Scanner sc=new Scanner( System.in );
		for(;;) {
			System.out.print("> ");
			String linea=sc.nextLine();
			if( linea.equalsIgnoreCase("STOP") ) break;
			if( linea.matches(INT) ) {
				System.out.println(linea+" intero");
			}
			else if( linea.matches(REAL) ) {
				System.out.println(linea+" numero reale");
			}
			else if( linea.matches(IDENT) ) {
				System.out.println(linea+" identificatore Java");
			}
			else {
				System.out.println(linea+" stringa non ricosciuta!");
			}
		}
		System.out.println("Bye.");
		sc.close();
	}//main
}//poo.regex.Classificatore
