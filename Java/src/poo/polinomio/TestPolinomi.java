package poo.polinomio;

public class TestPolinomi {
	public static void main( String[] args ) {
		//P1(x)=-5x^7+3x^2-2x+4
		//P2(x)=3x^4-2x^3+7x-2
		Polinomio p1 = new PolinomioLL();
		Polinomio p2 = new PolinomioSet();
		Polinomio p3 = new PolinomioMap();
		p1.add( new Monomio(-5,7) );
		p1.add( new Monomio(3,2) );
		p1.add( new Monomio(-2,1) );
		p1.add( new Monomio(4,0) );
		System.out.println("P1 = "+p1);
		p2.add( new Monomio(3,4) ); 
		p2.add( new Monomio(-2,3) );
		p2.add( new Monomio(7,1) );
		p2.add( new Monomio(-2,0) );
		System.out.println("P2 = "+p2);
		p3.add(new Monomio(4, 5));
		p3.add(new Monomio(7, 3));
		p3.add(new Monomio(-1, 1));
		p3.add(new Monomio(8, 0));
		System.out.println("P3 = " + p3);
		System.out.println( "P1 + P2 = "+p1.add(p2) );
		System.out.println( "P1 * P2 = "+p1.mul(p2) );
		System.out.println( "(P1 * P2)' = "+p1.mul(p2).derivata());
		System.out.printf( "(P1 * P2)(2.5) = %1.2f%n",p1.mul(p2).valore(0.5));
	}
}
