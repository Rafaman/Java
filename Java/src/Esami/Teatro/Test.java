package Esami.Teatro;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        int ret1;
        ArrayList<Spettacolo> ret2 = new ArrayList<>();
        HashMap<String, Integer> ret3 = new HashMap<>();

        Attore a1 = new Attore("Sara", 27);
        Attore a2 = new Attore("Laura", 28);
        Attore a3 = new Attore("Andrea", 37);
        Attore a4 = new Attore("Francesco", 32);
        Attore a5 = new Attore("Maria", 40);

        ArrayList<Attore> b0 = new ArrayList<>();
        ArrayList<Attore> b1 = new ArrayList<>();
        ArrayList<Attore> b2 = new ArrayList<>();
        ArrayList<Attore> b3 = new ArrayList<>();
        ArrayList<Attore> b4 = new ArrayList<>();
        ArrayList<Attore> b5 = new ArrayList<>();
        ArrayList<Attore> b6 = new ArrayList<>();
        ArrayList<Attore> b7 = new ArrayList<>();

        b0.add(a1);
        b0.add(a4);
        b1.add(a3);
        b1.add(a5);
        b2.add(a1);
        b2.add(a2);
        b3.add(a2);
        b3.add(a3);
        b3.add(a5);
        b4.add(a1);
        b4.add(a2);
        b4.add(a3);
        b4.add(a5);
        b5.add(a1);
        b5.add(a3);
        b5.add(a4);
        b5.add(a5);
        b6.add(a2);
        b7.add(a1);
        b7.add(a2);

        Spettacolo s0 = new Spettacolo(0, "Storico", b0);
        Spettacolo s1 = new Spettacolo(1, "Musicale", b1);
        Spettacolo s2 = new Spettacolo(2, "Storico", b2);
        Spettacolo s3 = new Spettacolo(3, "Sperimentale", b3);
        Spettacolo s4 = new Spettacolo(4, "Commedia", b4);
        Spettacolo s5 = new Spettacolo(5, "Storico", b5);
        Spettacolo s6 = new Spettacolo(6, "Commedia", b6);
        Spettacolo s7 = new Spettacolo(7, "Sperimentale", b7);

        ArrayList<Attore> g0 = new ArrayList<>();
        g0.add(a1);
        g0.add(a2);
        g0.add(a3);
        g0.add(a4);
        g0.add(a5);

        ArrayList<Spettacolo> g1 = new ArrayList<>();
        g1.add(s0);
        g1.add(s1);
        g1.add(s2);
        g1.add(s3);
        g1.add(s4);
        g1.add(s5);
        g1.add(s6);
        g1.add(s7);

        Gestione g = new Gestione(g0, g1);

        ret1 = g.spettacoliRicchi("Storico");
        ret2 = g.spettacoliGiovani();
        ret3 = g.attoriImpegnati("Sperimentale");

        System.out.println(ret1);
        System.out.println(ret2);
        System.out.println(ret3);
    }
}
