package poo.util;

import Fondamenti.MetodiRicorsivi;
import poo.esempi.Comparator;

public class Array{
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
        int[] ret = v.clone();
        int temp;
        boolean state = true;
        while(state){
            state = false;
            for(int i = 0; i < ret.length - 1; i++)
                if(ret[i] > ret[i + 1]){
                    swap(ret, i, i + 1);
                    state = true;
                }
        }
        return ret;
    }
    public static int[] selectionSort(int[] v){
        int[] ret = v.clone();
        int posMin, temp = 0;
        for(int i = 0; i < ret.length - 1; i++){
            posMin = i;
            for(int j = i + 1; j < ret.length; j++)
                if(ret[j] < ret[posMin])
                    posMin = j;
            swap(ret, i, posMin);
        }
        return ret;
    }
    public static void selectionSort(Comparable[] v){
        for(int j = v.length - 1; j > 0; j--){
            int iMax = 0;
            for(int i = 0; i <= j; i++)
                if(v[i].compareTo(v[iMax]) > 0)
                    iMax = i;
            Comparable park = v[iMax];
            v[iMax] = v[j];
            v[j] = park;
        }
    }
    public static int[] mergeSort(int[] v){
        return MetodiRicorsivi.mergeSort(v);
    }
    private static void swap(int[] v, int a, int b){
        int temp = v[a];
        v[a] = v[b];
        v[b] = temp;
    }
    public static void insertionSort(Object[] v, Comparator c){
        for (int i = 0; i < v.length; i++){
            Object x = v[i];
            int j = i;
            while(j > 0 && c.compare(v[j - 1], x) > 0){
                v[j] = v[j - 1];
                j--;
            }
        }
    }
    private static void stampaArray(int[] a){
        for(int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
    }
    public static <T extends Comparable<? super T>> void bubbleSort( Vector<T> v ) {
        boolean scambi=true;
        int limite=0, ius=v.size()-1;
        while( scambi ) {
            limite=ius; scambi=false;
            for( int i=1; i<=limite; ++i )
                if( v.get(i-1).compareTo(v.get(i))>0 ) {
                    //scambia v(i) con v(i-1)
                    T park=v.get(i); v.set(i, v.get(i-1));
                    v.set(i-1,park); scambi=true;
                    ius=i-1;
                }
        }
    }
    public static <T> void bubbleSort( Vector<T> v, Comparator<T> c ) {
        boolean scambi=true;
        int limite=0, ius=v.size()-1;
        while( scambi ) {
            limite=ius; scambi=false;
            for( int i=1; i<=limite; ++i )
                if( c.compare(v.get(i-1),v.get(i))>0 ) {
                    //scambia v(i) con v(i-1)
                    T park=v.get(i); v.set(i, v.get(i-1));
                    v.set(i-1,park); scambi=true;
                    ius=i-1;
                }
        }
    }

    public static void main( String...args ) {
        Vector<String> vs = new EnjoyVector<>();
        vs.add("zorro"); vs.add("alba"); vs.add("sera"); vs.add("boreale");
        vs.add("dado"); vs.add("casa");
        System.out.println("prima "+vs);
        //bubbleSort(vs);
        System.out.println("dopo "+vs);
        bubbleSort(vs, (s1,s2)->{
            if( s1.length()<s2.length() ||
                    s1.length()==s2.length() && s1.compareTo(s2)<0 ) return -1;
            if( s1.contentEquals(s2) ) return 0;
            return 1;
        });
        System.out.println("dopo ancora "+vs);
    }
}
