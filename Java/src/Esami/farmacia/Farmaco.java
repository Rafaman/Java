package Esami.farmacia;

import java.util.ArrayList;
import java.util.Objects;

public class Farmaco {
    private int codice, codiceProduttore;
    private String nome;
    private ArrayList<String> principiAttivi;
    private double prezzo;

    public Farmaco(int codice, int codiceProduttore, String nome, ArrayList<String> principiAttivi, double prezzo) {
        this.codice = codice;
        this.codiceProduttore = codiceProduttore;
        this.nome = nome;
        this.principiAttivi = principiAttivi;
        this.prezzo = prezzo;
    }

    public int getCodice() {
        return codice;
    }

    public int getCodiceProduttore() {
        return codiceProduttore;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<String> getPrincipiAttivi() {
        return new ArrayList<>(principiAttivi);
    }

    public double getPrezzo() {
        return prezzo;
    }

    @Override
    public String toString() {
        return "Farmaco{" +
                "codice=" + codice +
                ", codiceProduttore=" + codiceProduttore +
                ", nome='" + nome + '\'' +
                ", principiAttivi=" + principiAttivi +
                ", prezzo=" + prezzo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Farmaco)) return false;
        Farmaco farmaco = (Farmaco) o;
        return codice == farmaco.codice &&
                codiceProduttore == farmaco.codiceProduttore &&
                Double.compare(farmaco.prezzo, prezzo) == 0 &&
                nome.equals(farmaco.nome) &&
                principiAttivi.equals(farmaco.principiAttivi);
    }

    @Override
    public int hashCode() {
        return codice;
    }
    public boolean contienePrincipioAttivo (String p) {
        return principiAttivi.contains(p);
    }
    public boolean equivalente(Farmaco f){
        ArrayList<String> principiF = f.getPrincipiAttivi();
        if (principiAttivi.size() != principiF.size())
            return false;
        for (String p : principiAttivi)
            if(!principiF.contains(p))
                return false;
        return true;
    }
}
