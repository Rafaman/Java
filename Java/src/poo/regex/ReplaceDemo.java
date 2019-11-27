package poo.regex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReplaceDemo {
	public static void main( String[] args ) throws IOException{
		Scanner sc=new Scanner( System.in );
		System.out.print("Nome file testo : ");
		String nomeFile=sc.nextLine();
		BufferedReader br=new BufferedReader(
				new FileReader( nomeFile ) );
		StringBuilder sb=new StringBuilder(2000);
		for(;;) {
			String linea=br.readLine();
			if( linea==null ) break;
			sb.append(linea+"\n");
		}
		br.close();
		String documento=sb.toString();
		//comunque trova la parola java la rimpiazza con JAVA
		documento=documento.replaceAll("[jJ][aA][vV][aA]", "JAVA");
		System.out.println(documento);
		sc.close();
	}//main
}//poo.regex.ReplaceDemo
