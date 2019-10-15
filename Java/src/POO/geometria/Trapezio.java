package POO.geometria;

public class Trapezio extends Figura{
    private final double baseMinore = 7.1, baseMaggiore = 12.4;
    public Trapezio(double lato){
        super(lato);
    }
    public double getLato(){
        return getDimensione();
    }
    public double perimetro(){
        return getLato() * 2 + baseMaggiore + baseMinore;
    }
    private double altezza(){
        return Math.sqrt(Math.pow(getLato(), 2) - Math.pow((baseMaggiore - baseMinore) / 2, 2));
    }
    public double area(){
        return (baseMaggiore + baseMinore) * altezza() / 2;
    }
    public String toString(){
        return "Trapezio isoscele di lato " + String.format("%1.2f", getLato()) + " e basi " + String.format("%1.2f", baseMaggiore, baseMinore);
    }
    @Override
    public int hashCode() {
        return new Double(getLato()).hashCode() + new Double(baseMaggiore).hashCode() + new Double(baseMinore).hashCode();
    }
}
