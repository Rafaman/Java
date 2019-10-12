package Fondamenti;

public class MetodiRicorsivi {
    private static boolean ricercaBinaria(int[] v, int x, int imin, int imax) {
        if (imax < imin)
            return false;
        int centro = (imax + imin) / 2;
        if (v[centro] == x)
            return true;
        if (v[centro] > x)
            imax = centro - 1;
        else
            imin = centro + 1;
        return ricercaBinaria(v, x, imin, imax);
    }

    public static boolean ricercaBinaria(int[] v, int x) {
        return ricercaBinaria(v, x, 0, v.length - 1);
    }

    public static int[] mergeSort(int[] v) {
        int n = v.length;
        if (n == 1)
            return v;

        int[] v1 = new int[n / 2];
        int[] v2 = new int[n - n / 2];

        for (int i = 0; i < n / 2; i++)
            v1[i] = v[i];
        for (int i = n / 2; i < n; i++)
            v2[i - n / 2] = v[i];

        v1 = mergeSort(v1);
        v2 = mergeSort(v2);

        return merge(v1, v2);
    }

    private static int[] merge(int[] v1, int[] v2) {
        int[] ret = new int[v1.length + v2.length];
        int posLibera = 0;
        int i = 0;
        int j = 0;
        while (i < v1.length && j < v2.length) {
            if (v1[i] < v2[j]) {
                ret[posLibera] = v1[i];
                i++;
            } else {
                ret[posLibera] = v2[j];
                j++;
            }
            posLibera++;
        }
        while (i < v1.length) {
            ret[posLibera] = v1[i];
            i++;
            posLibera++;
        }
        while (j < v2.length) {
            ret[posLibera] = v2[j];
            j++;
            posLibera++;
        }
        return ret;
    }

    private static void quickSort(int[] v, int imin, int imax) {
        if (imin < imax) {
            int p = partiziona(v, imin, imax);
            quickSort(v, imin, p - 1);
            quickSort(v, p + 1, imax);
        }
    }

    private static int partiziona(int[] v, int imin, int imax) {
        int pivot = v[imax];
        int i = imin;
        for (int j = imin; j < imax; j++)
            if (v[j] <= pivot) {
                scambia(v, i, j);
                i++;
            }
        scambia(v, i, imax);
        return i;
    }

    private static void scambia(int[] v, int i, int j) {
        int t = v[i];
        v[i] = v[j];
        v[j] = t;
    }

    public static void quickSort(int[] v) {
        quickSort(v, 0, v.length - 1);
    }
}
