package poo.giochi;

public class MonteCarlo {
    private long colpiTot = 0, colpiInterni = 0;
    private double pi;
    public void montecarlo(long n){
        for(long i = 0; i < n; i++){
            double x = Math.random() * 2 - 1;
            double y = Math.random() * 2 - 1;
            colpiTot++;
            if(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) <= 1)
                colpiInterni++;
        }
        pi = 4 * ((double)colpiInterni / colpiTot);
    }
    public static void main(String[] args) {
        long n = 900000000;
        MonteCarlo mc = new MonteCarlo();
        mc.montecarlo(n);
        System.out.println("Pi greco approssimato a " + mc.pi);
    }
}
