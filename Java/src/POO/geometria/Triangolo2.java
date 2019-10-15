package POO.geometria;

public class Triangolo2 extends Figura {
    private final double a = 5.7, b = 3.1;
    public Triangolo2(double lato){
        super(lato);
    }
    public double getLato(){
        return getDimensione();
    }
    public double perimetro(){
        return getLato() + a + b;
    }
    public double area(){
        return Math.sqrt(perimetro() / 2 * (perimetro() / 2 - a) * (perimetro() / 2 - b));
    }
    public String toString(){
        return "Trinagolo di lati " + String.format("%1.2f", getLato(), a, b);
    }
    @Override
    public int hashCode() {
        return new Double(getLato()).hashCode() + new Double(a).hashCode() + new Double(b).hashCode();
    }
}
