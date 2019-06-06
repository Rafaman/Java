package esami.teatro;

public class Attore {
    private String nome;
    private int eta;

    public Attore(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }

    public String getNome() {
        return nome;
    }

    public int getEta() {
        return eta;
    }

    @Override
    public String toString() {
        return "Attore{" +
                "nome='" + nome + '\'' +
                ", eta=" + eta +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Attore)) return false;
        Attore attore = (Attore) o;
        return eta == attore.eta && nome.equals(attore.nome);
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }
}
