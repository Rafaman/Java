package esami.telefono;

public class Bolletta {
    private int codice, dataEmissione, dataPagamento, importo;
    private String numeroTelefono;
    private boolean pagata;

    public Bolletta(int codice, int dataEmissione, int dataPagamento, int importo, String numeroTelefono, boolean pagata) {
        this.codice = codice;
        this.dataEmissione = dataEmissione;
        this.dataPagamento = dataPagamento;
        this.importo = importo;
        this.numeroTelefono = numeroTelefono;
        this.pagata = pagata;
    }

    public int getCodice() {
        return codice;
    }

    public int getDataEmissione() {
        return dataEmissione;
    }

    public int getDataPagamento() {
        return dataPagamento;
    }

    public int getImporto() {
        return importo;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public boolean isPagata() {
        return pagata;
    }

    @Override
    public String toString() {
        return "Bolletta{" +
                "codice=" + codice +
                ", dataEmissione=" + dataEmissione +
                ", dataPagamento=" + dataPagamento +
                ", importo=" + importo +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", pagata=" + pagata +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bolletta)) return false;
        Bolletta bolletta = (Bolletta) o;
        return codice == bolletta.codice &&
                dataEmissione == bolletta.dataEmissione &&
                dataPagamento == bolletta.dataPagamento &&
                importo == bolletta.importo &&
                pagata == bolletta.pagata &&
                numeroTelefono.equals(bolletta.numeroTelefono);
    }

    @Override
    public int hashCode() {
        return codice;
    }
}
