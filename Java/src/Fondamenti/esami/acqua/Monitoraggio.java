package Fondamenti.esami.acqua;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Monitoraggio {
    private ArrayList<Citta> citta;
    private ArrayList<Acquedotto> acquedotti;
    private ArrayList<Fornitura> forniture;

    public Monitoraggio(ArrayList<Citta> citta, ArrayList<Acquedotto> acquedotti, ArrayList<Fornitura> forniture) {
        this.citta = citta;
        this.acquedotti = acquedotti;
        this.forniture = forniture;
    }

    public ArrayList<Citta> getCitta() {
        return new ArrayList<>(citta);
    }

    public ArrayList<Acquedotto> getAcquedotti() {
        return new ArrayList<>(acquedotti);
    }

    public ArrayList<Fornitura> getForniture() {
        return new ArrayList<>(forniture);
    }

    @Override
    public String toString() {
        return "Monitoraggio{" +
                "citta=" + citta +
                ", acquedotti=" + acquedotti +
                ", forniture=" + forniture +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Monitoraggio)) return false;
        Monitoraggio that = (Monitoraggio) o;
        return citta.equals(that.citta) &&
                acquedotti.equals(that.acquedotti) &&
                forniture.equals(that.forniture);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public ArrayList<Acquedotto> listaAcquedotti (String c){
        ArrayList<Acquedotto> ret = new ArrayList<>();
        for (Fornitura f:forniture)
            if(f.getNomeCitta() == c)
                for(Acquedotto a: acquedotti)
                    if (a.getCodice() == f.getCodiceAcquedotto())
                        ret.add(a);
        return ret;
    }

    public ArrayList<String> trovaCitta (int pop, int k){
        ArrayList<String> ret = new ArrayList<>();
        ArrayList<Citta> maggioriPop = maggiori(pop);
        for(Citta c: maggioriPop)
            if(contaAcquedottti(c.getNome()) == k)
                ret.add(c.getNome());
        return ret;
    }

    private int contaAcquedottti(String c){
        ArrayList<Acquedotto> a = listaAcquedotti(c);
        int ret = a.size();
        return ret;
    }

    private ArrayList<Citta> maggiori(int p) {
        ArrayList<Citta> ret = new ArrayList<>();
        for(Citta c:citta)
            if (c.getAbitanti() > p)
                ret.add(c);
        return ret;
    }

    public HashMap<String, Double> totalePerCitta(){
        HashMap<String, Double> ret = new HashMap<>();
        double portata;
        for(Citta c: citta) {
            portata = portataCitta(c);
            ret.put(c.getNome(), portata);
        }
        return ret;
    }

    private double portataCitta(Citta c){
        double ret = 0;
        for(Fornitura f: forniture)
            if(f.getNomeCitta() == c.getNome())
                ret+=f.getPortata();
        return ret;
    }

    public Citta cittaGold(){
        HashMap<String, Double> hm = new HashMap<>();
        double maxPortata = Collections.max(hm.values());
        for(String s : hm.keySet())
            if(hm.get(s) == maxPortata)
                for(Citta c: citta)
                    if(c.getNome() == s)
                        return c;
        return null;
    }
}
