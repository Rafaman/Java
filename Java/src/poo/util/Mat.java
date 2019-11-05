package poo.util;

public final class Mat{
	public static final double EPSILON=1.0E-10;
	private Mat(){} //costruttore vuoto si utilizza nelle classi di utilità poiché non devo creare oggetti
	
	public static int mcd(int x, int y){
		if(x<=0 || y<=0) throw new IllegalArgumentException("Numeri non positivi");
		return mcd_euclide(x,y);
	} //mcd
	
	private static int mcd_euclide(int x, int y){
		if(y==0) return x;
		return mcd_euclide(y, x%y);
	} //mcd_euclide
	
	public static int mcm(int x, int y){
		if(x<=0 || y<=0) throw new IllegalArgumentException("Numeri non positivi");
		return (x*y)/mcd_euclide(x,y);
	} //mcm
	
	public static boolean sufficientementeProssimi(double x, double y){
		return Math.abs(x-y)<=EPSILON;
	} //circa
	
	public static void scambia(int[] v){ //scambio per riferimento come c++)
		int t=v[0];
		v[0]=v[1];
		v[1]=t;
	} //scambia 
	
} //Mat
