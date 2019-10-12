package Fondamenti.esami.teatro;

import java.util.ArrayList;
import java.util.HashMap;

public class Gestione {
    private ArrayList<Attore> attori;
    private ArrayList<Spettacolo> spettacoli;

    public Gestione(ArrayList<Attore> attori, ArrayList<Spettacolo> spettacoli) {
        this.attori = attori;
        this.spettacoli = spettacoli;
    }

    public ArrayList<Attore> getAttori() {
        return new ArrayList<Attore>(attori);
    }

    public ArrayList<Spettacolo> getSpettacoli() {
        return new ArrayList<Spettacolo>(spettacoli);
    }

    @Override
    public String toString() {
        return "Gestione{" +
                "attori=" + attori +
                ", spettacoli=" + spettacoli +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Gestione)) return false;
        Gestione gestione = (Gestione) o;
        return attori.equals(gestione.attori) && spettacoli.equals(gestione.spettacoli);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public int spettacoliRicchi(String t) {
        int maxAttori = 0;
        for (Spettacolo s : spettacoli) {
            if (s.getTipologia() == t)
                if (maxAttori < s.getAttori().size())
                    maxAttori = s.getAttori().size();
        }
        return maxAttori;
    }

    public ArrayList<Spettacolo> spettacoliGiovani() {
        ArrayList<Spettacolo> ret = new ArrayList<>();
        int minimo = spettacoli.get(0).mediaEta();
        for (Spettacolo s : spettacoli) {
            if (s.mediaEta() < minimo)
                minimo = s.mediaEta();
        }
        for (Spettacolo s : spettacoli) {
            if (s.mediaEta() == minimo)
                ret.add(s);
        }
        return ret;
    }

    public HashMap<String, Integer> attoriImpegnati(String t) {
        HashMap<String, Integer> ret = new HashMap<>();
        for (Spettacolo s : spettacoli) {
            if (s.getTipologia() == t) {
                for (Attore a : s.getAttori()) {
                    if (ret.containsKey(a.getNome()))
                        ret.put(a.getNome(), ret.get(a.getNome()) + 1);
                    else
                        ret.put(a.getNome(), 1);
                }
            }
        }
        return ret;
    }
}

/*Si definiscano in Java le classi Attore, Spettacolo, Gestione. Oltre a scrivere eventuali metodi che si ritengano necessari
per realizzare l'applicazione, occorre fornire almeno i seguenti metodi nella classe Gestione:
    1. public int spettacoliRicchi(String t). Il metodo restituisce il numero massimo di attori recitanti in uno spettacolo di tipologia t;
    2. public ArrayList<Spettacolo> spettacoliGiovani(). Il metodo restituisce la lista degli spettacoli tali che l'età media degli attori che
       vi partecipano è minima (tra tutti gli spettacoli presenti);
    3. public HashMap<String, Integer> attoriImpegnati(String t). Il metodo restituisce una HashMap in cui, per ogni attore che ha partecipato
       ad almeno uno spettacolo di tipologia t, è riportato il suo nome ed il numero complessivo di spettacoli di tipologia t in cui ha recitato.
    4. public ArrayList<Attore> attoriEclettici(). Il metodo restituisce la lista degli attori che hanno recitato in spettacoli di tutte le tipologie disponibili.*/