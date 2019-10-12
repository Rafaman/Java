package Fondamenti.esami.acqua;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<Acquedotto> ret1;
        ArrayList<String> ret2;
        HashMap<String, Double> ret3;

        Citta c1 = new Citta("Cosenza", 27);
        Citta c2 = new Citta("Lamezia Terme", 28);
        Citta c3 = new Citta("Catanzaro", 37);
        Citta c4 = new Citta("Reggio Calabria", 32);
        Citta c5 = new Citta("Corigliano Calabro", 40);

        Acquedotto a0 = new Acquedotto(0, "a0");
        Acquedotto a1 = new Acquedotto(1, "a1");
        Acquedotto a2 = new Acquedotto(2, "a2");
        Acquedotto a3 = new Acquedotto(3, "a3");
        Acquedotto a4 = new Acquedotto(4, "a4");
        Acquedotto a5 = new Acquedotto(5, "a5");
        Acquedotto a6 = new Acquedotto(6, "a6");
        Acquedotto a7 = new Acquedotto(7, "a7");

        Fornitura f0 = new Fornitura(0, 0, 100.50, "Cosenza");
        Fornitura f1 = new Fornitura(1, 1, 121.30, "Cosenza");
        Fornitura f2 = new Fornitura(2, 2, 122.90, "Lamezia Terme");
        Fornitura f3 = new Fornitura(3, 3, 130.52, "Lamezia Terme");
        Fornitura f4 = new Fornitura(4, 4, 250.89, "Cataznaro");
        Fornitura f5 = new Fornitura(5, 5, 240.46, "Reggio Calabria");
        Fornitura f6 = new Fornitura(6, 6, 200.00, "Reggio Calabria");
        Fornitura f7 = new Fornitura(7, 7, 220.19, "Corigliano Calabro");

        ArrayList<Citta> citta = new ArrayList<>();
        ArrayList<Acquedotto> acquedotti = new ArrayList<>();
        ArrayList<Fornitura> forniture = new ArrayList<>();

        citta.add(c1);
        citta.add(c2);
        citta.add(c3);
        citta.add(c4);
        citta.add(c5);

        acquedotti.add(a0);
        acquedotti.add(a1);
        acquedotti.add(a2);
        acquedotti.add(a3);
        acquedotti.add(a4);
        acquedotti.add(a5);
        acquedotti.add(a6);
        acquedotti.add(a7);

        forniture.add(f0);
        forniture.add(f1);
        forniture.add(f2);
        forniture.add(f3);
        forniture.add(f4);
        forniture.add(f5);
        forniture.add(f6);
        forniture.add(f7);

        Monitoraggio monitoraggio = new Monitoraggio(citta, acquedotti, forniture);

        ret1 = monitoraggio.listaAcquedotti("Cosenza");
        ret2 = monitoraggio.trovaCitta(80000, 2);
        ret3 = monitoraggio.totalePerCitta();
        //Citta ret4 = monitoraggio.cittaGold();

        System.out.println(ret1);
        System.out.println(ret2);
        System.out.println(ret3);
        //System.out.println(ret4);
    }
}
