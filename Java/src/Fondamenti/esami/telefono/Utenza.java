package Fondamenti.esami.telefono;

public class Utenza {
    private String numeroTelefono, categoria, provinciaResidenza;

    public Utenza(String numeroTelefono, String categoria, String provinciaResidenza) {
        this.numeroTelefono = numeroTelefono;
        this.categoria = categoria;
        this.provinciaResidenza = provinciaResidenza;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getProvinciaResidenza() {
        return provinciaResidenza;
    }

    @Override
    public String toString() {
        return "Utenza{" +
                "numeroTelefono='" + numeroTelefono + '\'' +
                ", categoria='" + categoria + '\'' +
                ", provinciaResidenza='" + provinciaResidenza + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Utenza)) return false;
        Utenza utenza = (Utenza) o;
        return numeroTelefono.equals(utenza.numeroTelefono) &&
                categoria.equals(utenza.categoria) &&
                provinciaResidenza.equals(utenza.provinciaResidenza);
    }

    @Override
    public int hashCode() {
        return numeroTelefono.hashCode();
    }
}
