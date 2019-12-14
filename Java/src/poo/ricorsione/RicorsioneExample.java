package poo.ricorsione;

public class RicorsioneExample {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(gauss(n));
        System.out.println(fattoriale(n));
        System.out.println(potenza(n, 3));
    }
    private static int GAUSS(int n){
        if(n == 1) return 1;
        return n + gauss(n - 1);
    }
    public static int gauss(int n){
        if(n <= 0) throw new IllegalArgumentException();
        return GAUSS(n);
    }
    private static int FATTORIALE(int n){
        if(n == 1) return 1;
        return n * FATTORIALE(n - 1);
    }
    public static int fattoriale(int n){
        if(n == 0) return 1;
        if(n < 0) throw new IllegalArgumentException();
        return FATTORIALE(n);
    }
    private static int POTENZA(int b, int e){
        if(e == 0) return 1;
        return potenza(b, e - 1) * b;
    }
    public static int potenza(int b, int e){
        if(e < 0) throw new IllegalArgumentException();
        return POTENZA(b, e);
    }
    public static boolean palindroma(String s){
        if(s.length() <= 1) return true;
        if(s.charAt(0) != s.charAt(s.length() - 1)) return false;
        return palindroma(s.substring(s.charAt(1), s.length() - 1));
    }
    public static void inverso(int n){
        /*
        * DA FARE UTILIZZANDO LA DIVISIONE PER 10, TENENDO PRESENTE IL QUOZIENTE E IL RESTO
        */
        //TODO
    }
    public <T extends Comparable<? super T>> T max(T[] v){
        return max(v, 0, v.length - 1);
    }
    private <T extends Comparable<? super T>> T max(T[] v, int start, int end){
        if(start == end) return v[start];
        int medio = (start + end) / 2;
        T max1 = max(v, start, medio);
        T max2 = max(v, medio + 1, end);
        if(max1.compareTo(max2) > 0) return max1;
        return max2;
    }
    /*
    * TODO Ricerca binaria <T extends Comparable<? super T>>
    * TODO Gioco di Hanoi
    * */
}
