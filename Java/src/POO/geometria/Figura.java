package POO.geometria;

public abstract class Figura implements FiguraPiana{
    private double dimensione;
    public Figura(double d){
        if(d <= 0) throw new IllegalArgumentException("Dimensione negativa!");
        dimensione = d;
    }
    protected double getDimensione(){
        return dimensione;
    }
    public abstract double perimetro();
    public abstract double area();
    public String toString(){
        return "Figura di dimensione " + String.format("%5.2f", dimensione);
    }
    public static Figura areaMassima(Figura[] f){
        Figura figuraAreaMassima = null;
        double areaMassima = 0.0;
        for(int i = 0; i < f.length; i++) {
            double area = f[i].area();
            if(area > areaMassima){
                figuraAreaMassima = f[i];
                areaMassima = area;
            }
        }
        return figuraAreaMassima;
    }
}
