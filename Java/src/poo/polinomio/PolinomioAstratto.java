package poo.polinomio;
import java.util.*;

public abstract class PolinomioAstratto implements Polinomio{

	public String toString(){
		StringBuilder sb=new StringBuilder();
		Iterator<Monomio> it=this.iterator();
		boolean flag=true;
		while( it.hasNext() ){
			Monomio m=it.next();
			if( m.getCoeff()>0 && !flag ) sb.append('+');
			sb.append( m );
			if( flag ) flag=!flag;
		}
		return sb.toString();
	}//toString
	public boolean equals( Object o ){
		if( !(o instanceof Polinomio) ) return false;
		if( o==this ) return true;
		Polinomio p=(Polinomio)o;
		if( this.size()!=p.size() ) return false;
		Iterator<Monomio> it=this.iterator();
		for( Monomio m: p){
			Monomio q=it.next();
			if( m.getCoeff()!=q.getCoeff() ||
				m.getGrado()!=q.getGrado() ) return false;
		}
		return true;
	}//equals
	public int hashCode(){
		int p=17, hash=0;
		for( Monomio m: this ){
			int hc=(String.valueOf(m.getCoeff())+String.valueOf(m.getGrado())).hashCode();
			hash=hash*p+hc;
		}
		return hash;
	}//hashCode
}//PolinimoAstratto