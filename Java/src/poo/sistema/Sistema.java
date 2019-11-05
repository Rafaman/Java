package poo.sistema;

public abstract class Sistema{
	private int n;
	public int getN(){ return n; }
	public Sistema( double [][]a, double []y){
		if( a.length != y.length )
			throw new RuntimeException("Sistema Inconsistente");
		for( int i=0; i<a.length; i++ )
			if( a[i].length != a.length )
				throw new RuntimeException("Sistema Inconsistente");
		this.n=a.length;
	}
	public abstract double[] risolvi();
}//Sistema