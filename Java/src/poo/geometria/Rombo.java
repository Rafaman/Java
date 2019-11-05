package poo.geometria;

public class Rombo extends Figura{
    private final double diagonaleMinore = 7.1, diagonaleMaggiore = 12.4;
    public Rombo(double lato){
        super(lato);
    }
    public double getLato(){
        return getDimensione();
    }
    public double perimetro(){
        return getLato() * 4;
    }
    public double area(){
        return (diagonaleMaggiore * diagonaleMinore) / 2;
    }
    public String toString(){
        return "Rombo di lato " + String.format("%1.2f", getLato()) + " e diagonali " + String.format("%1.2f", diagonaleMaggiore, diagonaleMinore);
    }
    @Override
    public int hashCode() {
        return new Double(getLato()).hashCode() + new Double(diagonaleMaggiore).hashCode() + new Double(diagonaleMinore).hashCode();
    }
}
