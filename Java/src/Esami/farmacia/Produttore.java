package Esami.farmacia;

import java.util.Objects;

public class Produttore {
    private int codice;

    public Produttore(int codice) {
        this.codice = codice;
    }

    public int getCodice() {
        return codice;
    }

    @Override
    public String toString() {
        return "Produttore con codice" + codice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produttore)) return false;
        Produttore that = (Produttore) o;
        return codice == that.codice;
    }

    @Override
    public int hashCode() {
        return codice;
    }
}
