package Esami.Teatro;

import java.util.ArrayList;

public class Spettacolo {
    private int codice;
    private String tipologia;
    private ArrayList<String> attori;

    public Spettacolo(int codice, String tipologia, ArrayList<String> attori) {
        this.codice = codice;
        this.tipologia = tipologia;
        this.attori = attori;
    }

    public int getCodice() {
        return codice;
    }

    public String getTipologia() {
        return tipologia;
    }

    public ArrayList<String> getAttori() {
        return attori;
    }

    @Override
    public String toString() {
        return "Spettacolo{" +
                "codice=" + codice +
                ", tipologia='" + tipologia + '\'' +
                ", attori=" + attori +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Spettacolo)) return false;
        Spettacolo that = (Spettacolo) o;
        return codice == that.codice && tipologia.equals(that.tipologia) && attori.equals(that.attori);
    }

    @Override
    public int hashCode() {
        return codice;
    }
}
