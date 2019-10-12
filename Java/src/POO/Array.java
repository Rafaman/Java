package POO;

import java.util.Arrays;

public class Array {
    public static int ricercaBinaria(int[] v, int e){
        return ricercaBinaria_Indici(v, e, 0, v.length);
    }
    private static int ricercaBinaria_Indici(int[] v, int e, int inizio, int fine){
        int half = (inizio + fine) / 2;
        if(e == v[half])
            return half;
        if(e > v[half]) {
            ricercaBinaria_Indici(v, e, half, v.length);
        } else {
            ricercaBinaria_Indici(v, e, 0, half);
        }
        return half;
    }
    public static int ricercaLineare(int[] v, int e){
        for(int i = 0; i < v.length; i++)
            if(v[i] == e)
                return i;
        return -1;
    }
    public static int[] bubbleSort(int[] v){
        boolean state = true;
        while(state){
            state = false;
            for(int i = 0; i < v.length - 2; i++)
                if(v[i] > v[i + 1]){
                    int temp = v[i];
                    v[i] = v[i + 1];
                    v[i + 1] = temp;
                    state = true;
                }
        }
        return v;
    }
    public static void main(String[] args) {
        int[] v = {1, 3, 4, 5, 8, 9, 11, 12};
        int[] v2 = {3, 12, 4, 8, 21, 5, 1, 9};
        System.out.println("Ricerca binaria: " + Array.ricercaBinaria(v, 8));
        System.out.println("Ricerca lineare: " + Array.ricercaLineare(v, 8));
    }
}
