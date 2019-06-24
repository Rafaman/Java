package esami.acqua;

public class Acquedotto {
    private int codice;
    private String nome;

    public Acquedotto(int codice, String nome) {
        this.codice = codice;
        this.nome = nome;
    }

    public int getCodice() {
        return codice;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Acquedotto{" +
                "codice=" + codice +
                ", nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Acquedotto)) return false;
        Acquedotto that = (Acquedotto) o;
        return codice == that.codice && nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return codice;
    }
}
