package poo.geometria;

public class Cerchio extends Figura {
    private final double pi = 3.14;
    public Cerchio(double raggio){
        super(raggio);
    }
    public double getRaggio(){
        return getDimensione();
    }
    public double perimetro(){
        return getRaggio() * 2 * pi;
    }
    public double area(){
        return Math.pow(getRaggio(), 2) * pi;
    }
    public String toString(){
        return "Cerchio di raggio " + String.format("%1.2f", getRaggio());
    }
    @Override
    public int hashCode() {
        return new Double(getRaggio()).hashCode();
    }
}
