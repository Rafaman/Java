package poo.agenda;

public class Contatto implements Comparable<Contatto>{
    private String nome, cognome, prefisso, numero;

    public Contatto(String nome, String cognome, String prefisso, String numero){
        this.nome = nome;
        this.cognome = cognome;
        this.prefisso = prefisso;
        this.numero = numero;
    }
    public String getNome() {
        return nome;
    }
    public String getCognome() {
        return cognome;
    }
    public String getPrefisso() {
        return prefisso;
    }
    public String getNumero() {
        return numero;
    }
    @Override
    public String toString(){
        return cognome + " " + nome + " " + prefisso + " " + numero;
    }
    @Override
    public boolean equals(Object o){
        if(!(o instanceof Contatto)) return false;
        if(o == this) return true;
        Contatto c = (Contatto)o;
        return this.cognome.equals(c.cognome) && this.nome.equals(c.nome);
    }
    @Override
    public int hashCode(){
        final int M = 7;
        int h = 0;
        h = h * M + nome.hashCode();
        h = h * M + cognome.hashCode();
        return h;
    }
    public int compareTo(Contatto c){
        if(cognome.compareTo(c.cognome) < 0 || cognome.equals(c.cognome) && nome.compareTo(c.nome) < 0) return -1;
        if(this.equals(c)) return 0;
        return 1;
    }
}
