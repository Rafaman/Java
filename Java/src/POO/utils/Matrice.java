package POO.utils;

public class Matrice {
    public static double[][] addizione(double[][] m, double[][] m2){
        double[][] ret = new double[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                ret[i][j] = m[i][j] + m2[i][j];
        return ret;
    }
    public static double[][] sottrazione(double[][] m, double[][] m2){
        double[][] ret = new double[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                ret[i][j] = m[i][j] - m2[i][j];
        return ret;
    }
    public static double[][] moltiplicazione(double[][] m, double[][] m2){
        double[][] ret = new double[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                ret[i][j] = m[i][j] * m2[i][j];
        return ret;
    }
    public static double[][] divisione(double[][] m, double[][] m2){
        double[][] ret = new double[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                ret[i][j] = m[i][j] + m2[i][j];
        return ret;
    }
    public static double[][] trasposta(int[][] m){
        double[][] ret = new double[m.length][m[0].length];
        for(int i = 0; i < m.length; i++)
            for(int j = 0; j < m[0].length; j++)
                ret[i][j] = m[j][i];
        return ret;
    }
}
