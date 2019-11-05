package poo.string;

import java.util.Scanner;
import java.util.StringTokenizer;

public class EnjoyString {
    private static String linea;
    public static void main(String[] args) {
        /* String Tokenizer */
        StringBuilder sb = tokenStringBuilder();
        System.out.println(sb);
        /* Tokenizer con scanner */
        tokenScanner(linea);
        /* Tokenizer aritmetica */
        System.out.println("Risultato espressione " + tokenAritmetica());
    }
    public static StringBuilder tokenStringBuilder(){
        Scanner sc = new Scanner(System.in);
        linea = sc.nextLine();
        StringTokenizer st = new StringTokenizer(linea, " ,.;:!=");
        StringBuilder sb = new StringBuilder(linea.length());
        while(st.hasMoreTokens()){
            String parola = st.nextToken().toUpperCase();
            sb.append(" " + parola);
        }
        return sb;
    }
    public static void tokenScanner(String s){
        Scanner sc = new Scanner(s);
        sc.useDelimiter("\\W");
        while(sc.hasNext()){
            String parole = sc.next();
            System.out.println(parole);
        }
    }
    public static int tokenAritmetica(){
        Scanner sc1 = new Scanner(System.in);
        String espressione = sc1.nextLine();
        StringTokenizer st1 = new StringTokenizer(espressione, "+-*/()", true);
        int ris = valutaEspressione(st1);
        return ris;
    }
    private static int valutaEspressione(StringTokenizer st){
        int ris = valutaOperando(st);
        while(st.hasMoreTokens()){
            char op = st.nextToken().charAt(0);
            if(op == ')') return ris;
            int opnd = valutaOperando(st);
            switch(opnd){
                case '+': ris += opnd; break;
                case '-': ris -= opnd; break;
                case '*': ris *= opnd; break;
                case '/': ris /= opnd; break;
                default: throw new RuntimeException();
            }
        }
        return ris;
    }
    private static int valutaOperando(StringTokenizer st){
        String token = st.nextToken();
        if(token.equals('(')) return valutaEspressione(st);
        return Integer.parseInt(token);
    }
}
