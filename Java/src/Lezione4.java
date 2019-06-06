import IO.*;
import numeri.*;

public class Lezione4 {
  public static void main(String[] args) {
    boolean finito = false;
    Razionale r = null;

    while (!finito) {
      int n = IO.readInt("Numeratore: ");
      int d = IO.readInt("Denominatore: ");

      finito = true;

      try {
        r = new Razionale(n, d);
      }
      catch (EccezioneDenominatoreZero e) {
        IO.println("Denominatore non valido");
        finito = false;
      }
      IO.println("Razionale: " + r);
    }
  }
}
