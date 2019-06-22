package esami.acqua;

public class Citta {
    private String nome;
    private int abitanti;

    public Citta(String nome, int abitanti) {
        this.nome = nome;
        this.abitanti = abitanti;
    }

    public String getNome() {
        return nome;
    }

    public int getAbitanti() {
        return abitanti;
    }

    @Override
    public String toString() {
        return "Citta{" +
                "nome='" + nome + '\'' +
                ", abitanti=" + abitanti +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Citta)) return false;
        Citta citta = (Citta) o;
        return abitanti == citta.abitanti && nome.equals(citta.nome);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
