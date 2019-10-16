package POO.esempi;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int[] v = null;
        if(args.length > 0){
            v = new int[args.length];
            for(int i = 0; i < v.length; i++)
                v[i] = Integer.parseInt(args[i]);
        } else {
            Scanner sc = new Scanner(System.in);
            System.out.print("Numero dati: ");
            int n = sc.nextInt();
            v = new int[n];
            System.out.print("Inserisci " + n + " valori: ");
            for(int i = 0; i < v.length; i++)
                v[i] = sc.nextInt();
            sc.close();
        }
    }
}
