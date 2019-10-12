package Fondamenti.esami.telefono;

import java.util.ArrayList;
import java.util.HashMap;

public class Gestione {
    private ArrayList<Utenza> utenti;
    private ArrayList<Bolletta> bollette;

    public Gestione(ArrayList<Utenza> utenti, ArrayList<Bolletta> bollette) {
        this.utenti = utenti;
        this.bollette = bollette;
    }

    public ArrayList<Utenza> getUtenti() {
        return new ArrayList<>(utenti);
    }

    public ArrayList<Bolletta> getBollette() {
        return new ArrayList<>(bollette);
    }

    @Override
    public String toString() {
        return "Gestione{" +
                "utenti=" + utenti +
                ", bollette=" + bollette +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gestione)) return false;
        Gestione gestione = (Gestione) o;
        return utenti.equals(gestione.utenti) &&
                bollette.equals(gestione.bollette);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public ArrayList<Bolletta> bolletteCarePerPeriodo(Utenza u, int d1, int d2){
        ArrayList<Bolletta> ret = new ArrayList<>();
        int media = media(u, bollette);
        for (Bolletta b : bollette)
            if (u.getNumeroTelefono() == b.getNumeroTelefono())
                if (b.getImporto() > media && b.getDataEmissione() > d1 && b.getDataEmissione() < d2)
                    ret.add(b);
        return ret;
    }

    private int media (Utenza utente, ArrayList<Bolletta> bollette){
        int ret = 0, contaUtenze = 0;
        for(Bolletta b : bollette)
            if(b.getNumeroTelefono() == utente.getNumeroTelefono())
                ret += b.getImporto();
        return ret / contaUtenze;
    }

    public ArrayList<Bolletta> trovaBolletteScadute(int d){
        ArrayList<Bolletta> ret = new ArrayList<>();
        for (Bolletta b:bollette)
            if(!(b.isPagata()) && b.getDataEmissione() < d - 60)
                ret.add(b);
        return ret;
    }

    public ArrayList<Utenza> utenteInRegola(String p){
        ArrayList<Utenza> ret = new ArrayList<>();
        ArrayList<Utenza> utentiProvincia = cercaUtentiProvincia(utenti, p);
        boolean inRegola = true;
        for(Utenza u : utentiProvincia) {
            for (Bolletta b : bollette)
                if (b.getNumeroTelefono() == u.getNumeroTelefono())
                    if(!(b.isPagata()))
                        inRegola = false;
            if(inRegola)
                ret.add(u);
        }
        return ret;
    }

    private ArrayList<Utenza> cercaUtentiProvincia(ArrayList<Utenza> utenti, String provincia){
        ArrayList<Utenza> ret = new ArrayList<>();
        for(Utenza u : utenti)
            if(u.getProvinciaResidenza() == provincia)
                ret.add(u);
        return ret;
    }

    public HashMap<String, Double> totale(){
        HashMap<String, Double> ret = new HashMap<>();
        for(Utenza u : utenti) {
            if (!ret.containsKey(u.getNumeroTelefono()))
                ret.put(u.getNumeroTelefono(), 0.0);
            for (Bolletta b : bollette)
                if(b.getNumeroTelefono() == u.getNumeroTelefono())
                    ret.put(u.getNumeroTelefono(), ret.get(u.getNumeroTelefono()) + b.getImporto());
        }
        return ret;
    }
}
