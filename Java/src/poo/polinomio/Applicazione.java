package poo.polinomio;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.*;

public class Applicazione {
    public static void main(String[] args) {
        /*
        * Dichiarazione variabili
        * */
        PolinomioLL polinomio, polinomio2;
        String espressione1, espressione2;
        /*
        * Lettura polinomi da tastiera
        * */
        Scanner sc = new Scanner(System.in);
        System.out.println("Prima espressione: ");
        espressione1 = sc.nextLine();
        System.out.println("Seconda espressione: ");
        espressione2 = sc.nextLine();
        sc.close();
        /*
        * Riconoscimento dei polinomi
        * */
        polinomio = riconosciPolinomio(espressione1);
        polinomio2 = riconosciPolinomio(espressione2);
        /*
        * Stampa polinomi
        * */
        System.out.println(polinomio);
        System.out.println(polinomio2);
    }
    private static PolinomioLL riconosciPolinomio(String s){
        String coefficiente = "\\-?[0-9]+";
        Pattern pattern = Pattern.compile(coefficiente);
        Matcher matcher = pattern.matcher(s);
        while( matcher.find() )
            System.out.println("Trovato match ***"+s.substring( matcher.start(), matcher.end())+ "***" + " in posizione "+matcher.start() );
        return new PolinomioLL();
    }
}

class GUI{

}