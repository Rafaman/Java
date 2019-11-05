package poo.sistema;

import poo.util.Mat;

public class Gauss extends Sistema{
	protected double [][]a;
	public Gauss( double [][]a, double []y ){
		super( a, y );
		//genera matrice n*(n+1) dei coeff+termini noti
		double [][] copia=new double[a.length][a.length+1];
		//conserva copia dei termini noti
		for( int i=0; i<a.length; i++ ){
			System.arraycopy(a[i],0,copia[i],0,a[0].length);
			copia[i][a.length]=y[i];
		}
	    this.a=copia;
	}
	protected void triangolazione(){
		//rende a triangolare superiore
		int n=this.getN();
		for( int j=0; j<n; j++ ){
			if( Mat.sufficientementeProssimi(a[j][j],0D) ){
				int p=j+1;
				for( ; p<n; p++ )
					if( !Mat.sufficientementeProssimi(a[p][j],0D) ) break;
				if( p==n ) throw new RuntimeException("Sistema singolare");
				//scambia riga p con riga j
				double[] tmp=a[j];
				a[j]=a[p]; a[p]=tmp;
			}
			//azzera elementi sulla colonna j, dalla riga (j+1)-esima all'ultima
			for( int i=j+1; i<n; i++ ){
				if( !Mat.sufficientementeProssimi(a[i][j],0D) ){
					double coeff=a[i][j]/a[j][j];
					//sottrai dalla riga i-esima la riga j-esima moltip per coeff
					for( int k=j; k<n+1; k++ )
						a[i][k] -= a[j][k]*coeff;
				}
			}
		}//for esterno su j
	}//triangolazione
	protected double[] calcoloSoluzione(){
		//a e' triangolare superiore
		int n=this.getN();
		double []x=new double[n];
		for( int i=n-1; i>=0; i-- ){
			double sm=a[i][n]; //secondo membro inizializzato al termine noto
			for( int j=i+1; j<n; j++ )
				sm -= a[i][j]*x[j];
			x[i]=sm/a[i][i];
		}
		return x;
	}//calcoloSoluzione
	@Override
	public double[] risolvi(){
		triangolazione();
		return calcoloSoluzione();
	}//risolvi
	public String toString(){
		StringBuilder sb=new StringBuilder(500);
		for( int i=0; i<a.length; i++ ){
			for( int j=0; j<=a.length; j++ ){
				sb.append( String.format("%5.2f", a[i][j]) );
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}//toString
}//Gauss