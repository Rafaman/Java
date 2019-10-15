package POO.geometria;

public class Quadrato extends Figura{
    public Quadrato(double lato){
        super(lato);
    }
    public double getLato(){
        return getDimensione();
    }
    public double perimetro(){
        return getLato() * 4;
    }
    public double area(){
        return Math.pow(getLato(), 2);
    }
    public String toString(){
        return "Quadrato di lato " + String.format("%1.2f", getLato());
    }
    @Override
    public int hashCode() {
        return new Double(getLato()).hashCode();
    }
}
