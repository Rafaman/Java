package POO;

import java.lang.IllegalArgumentException;

public class Geometria {
    public static void main(String[] args) {
        Punto v1 = new Punto(3, 6);
        Punto v2 = new Punto(5, 9);
        Punto v3 = new Punto(10, 2);
        Triangolo t = new Triangolo(v1, v2, v3);

        System.out.println(t + "\nPerimetro: " + t.perimetro() + "\nArea: " + t.area());
    }
}

class Punto {
    private double x, y;

    public Punto(){
        this(0, 0);
    }
    public Punto(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }
    public String toString(){
        return "(" + x + "; " + y + ")";
    }
    public void muovi(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double distanza(Punto p){
        return Math.sqrt((p.x - this.x) * (p.x - this.x) + (p.y - this.y) * (p.y - this.y));
    }
}

class Triangolo {
    private Punto vertice1, vertice2, vertice3;
    private double a, b, c;

    public Triangolo(Punto vertice1, Punto vertice2, Punto vertice3){
        a = vertice1.distanza(vertice2);
        b = vertice2.distanza(vertice3);
        c = vertice3.distanza(vertice1);
        if (a >= b + c || b >= a + c || c >= a + b)
          throw new IllegalArgumentException("Triangolo inesistente!");
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.vertice3 = vertice3;
    }
    public Triangolo(Triangolo t){
      vertice1 = t.vertice1;
      vertice2 = t.vertice2;
      vertice3 = t.vertice3;
    }
    public Punto[] getVertici() {
        Punto[] ret = new Punto[3];
        ret[0] = vertice1;
        ret[1] = vertice2;
        ret[2] = vertice3;
        return ret;
    }
    public double[] getLati(){
        double[] ret = new double[3];
        ret[0] = a;
        ret[1] = b;
        ret[2] = c;
        return ret;
    }
    public String toString(){
        return "Triangolo con vertici in " + vertice1 + " " + vertice2 + " " + vertice3 + " e avente i lati " + a + ", " + b + " e " + c;
    }
    public double perimetro(){
        return  a + b + c;
    }
    public double area(){
        return Math.sqrt(perimetro() / 2 * (perimetro() / 2 - a) * (perimetro() / 2 - b) * (perimetro() / 2 - c));
    }
}
