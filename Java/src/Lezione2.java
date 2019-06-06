import java.util.Random;
import IO.*;

public class Lezione2 {
  public static void main(String args[]) {
    Matrice matrice = new Matrice();
    int mat[][] = new int[5][5];
    for ( int i = 0; i < mat.length; i++ )
      for ( int j = 0; j < mat[0].length; j++ )
        mat[i][j] = (int)Math.random();
    matrice.normalizza(mat);
  }
}
