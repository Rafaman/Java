import java.util.ArrayList;
import java.util.Arrays;

public class Positivi {
  public void myPositive(int vet[]) {
    ArrayList<Integer> positivi = new ArrayList<Integer>();
    for ( int i = 0; i < vet.length; i++ ) {
      if ( vet[i] >= 0 ) {
        positivi.add( vet[i] );
      }
    }
    System.out.println( "Funzione myPositive: " + positivi );
  }
  public void pugliese1(int vet[]) {
    int contaPositivi = 0;
    for ( int i = 0; i < vet.length; i++ ){
      if ( vet[i] >= 0 ) contaPositivi++;
    }
    int positivi[] = new int[contaPositivi];
    int j = 0;
    for ( int i = 0; i < vet.length; i++ ) {
      if ( vet[i] >= 0 ) {
        positivi[j] = vet[i]; j++;
      }
    }
    System.out.println("Funzione pugliese1: " + Arrays.toString(positivi));
  }
  public void pugliese2( int vet[] ){
    int n = vet.length, posLibera = 0;
    int temp[] = new int[n];

    for ( int i = 0; i < n; i++ )
      if ( vet[i] >= 0 ) {
        temp[posLibera] = vet[i];
        posLibera++;
      }
    if ( posLibera == vet.length ) System.out.println("Funzione pugliese2: " + Arrays.toString(temp));
    int ret[] = new int [posLibera];
    for ( int i = 0; i < posLibera; i++ ) ret[i] = temp[i];
    System.out.println("Funzione pugliese2: " + Arrays.toString(ret));
  }
}
