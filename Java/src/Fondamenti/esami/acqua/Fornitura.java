package Fondamenti.esami.acqua;

public class Fornitura {
    private int codice, codiceAcquedotto;
    private double portata;
    private String nomeCitta;

    public Fornitura(int codice, int codiceAcquedotto, double portata, String nomeCitta) {
        this.codice = codice;
        this.codiceAcquedotto = codiceAcquedotto;
        this.portata = portata;
        this.nomeCitta = nomeCitta;
    }

    public int getCodice() {
        return codice;
    }

    public int getCodiceAcquedotto() {
        return codiceAcquedotto;
    }

    public double getPortata() {
        return portata;
    }

    public String getNomeCitta() {
        return nomeCitta;
    }

    @Override
    public String toString() {
        return "Fornitura{" +
                "codice=" + codice +
                ", codiceAcquedotto=" + codiceAcquedotto +
                ", portata=" + portata +
                ", nomeCitta='" + nomeCitta + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Fornitura)) return false;
        Fornitura fornitura = (Fornitura) o;
        return codice == fornitura.codice && codiceAcquedotto == fornitura.codiceAcquedotto && Double.compare(fornitura.portata, portata) == 0 && nomeCitta.equals(fornitura.nomeCitta);
    }

    @Override
    public int hashCode() {
        return codice;
    }
}
