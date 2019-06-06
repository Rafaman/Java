import java.util.Arrays;

public class Matrice {
  public static double[][] normalizza( int mat[][] ) {
    int max = massimo(mat), nRighe = mat.length, nColonne = mat[0].length;
    double ret[][] = new double[nRighe][nColonne];
    String temp[] = new String[nRighe];
    for ( int i = 0; i < nRighe; i++ )
      for ( int j = 0; j < nColonne; j++)
        ret[i][j] = mat[i][j] / (double) max;
    return ret;
  }

  public static int massimo ( int m[][] ) {
    int max = m[0][0];
    for ( int i = 0; i < m.length; i++ )
      for ( int j = 0; j < m[0].length; j++)
        if ( m[i][j] > max ) max = m[i][j];
    return max;
  }
}
