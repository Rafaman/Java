package esami.farmacia;

import java.util.ArrayList;

public class Gestione {
    private ArrayList<Farmaco> farmaci;
    private ArrayList<Produttore> produttori;
    private ArrayList<String> principiAttivi;

    public Gestione(ArrayList<Farmaco> farmaci, ArrayList<Produttore> produttori, ArrayList<String> principiAttivi) {
        this.farmaci = new ArrayList<>(farmaci);
        this.produttori = new ArrayList<>(produttori);
        this.principiAttivi = new ArrayList<>(principiAttivi);
    }
    public String farmacoCaro(String p){
        ArrayList<Farmaco> farmaciInteresse = new ArrayList<>();
        for (Farmaco f : farmaci)
            if (f.contienePrincipioAttivo(p))
                farmaciInteresse.add(f);
        Farmaco costoso = null;
        double prezzoMax = 0;
        for (Farmaco f : farmaciInteresse)
            if(f.getPrezzo() > prezzoMax) {
                prezzoMax = f.getPrezzo();
                costoso = f;
            }
        return costoso.getNome();
    }
    private ArrayList<Produttore> esclusivisti(){
        ArrayList<Produttore> ret = new ArrayList<>();
        for (int i = 0; i < farmaci.size(); i++) {
            boolean nonHaEquivalenti = true;
            for (int j = 0; j < farmaci.size(); j++)
                if (farmaci.get(i).equivalente(farmaci.get(j)))
                    nonHaEquivalenti = false;
            if(nonHaEquivalenti)
                ret.add(cercaProduttore(farmaci.get(i).getCodiceProduttore()));
        }
        return ret;
    }
    private Produttore cercaProduttore(int codice){
        for (Produttore p: produttori)
            if(p.getCodice() == codice)
                return p;
        return null;
    }
}
