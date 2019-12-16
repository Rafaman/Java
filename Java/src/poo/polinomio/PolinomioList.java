package poo.polinomio;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class PolinomioList extends PolinomioAstratto {
    private ArrayList<Monomio> lista = new ArrayList<>();

    public PolinomioList(){}
    public PolinomioList(Polinomio p){
        for(Monomio m:p)
            this.add(m);
    }
    public int size() {
        return lista.size();
    }
    public PolinomioList crea(){ //covarianza tipo di ritorno
        return new PolinomioList();
    }
    public void add( Monomio m ){
        if( m.getCoeff() == 0 ) return;
        ListIterator<Monomio> lit = lista.listIterator();
        boolean flag = false;
        while( lit.hasNext() && !flag ){
            Monomio m1 = lit.next();
            if( m.equals(m1) ){
                Monomio m2 = m.add(m1);
                if( m2.getCoeff() != 0 ){
                    lit.set( m2 );
                }
                else lit.remove();
                flag = true;
            }
            else if( m1.compareTo(m) > 0 ){
                lit.previous();
                lit.add(m);
                flag = true;
            }
        }
        if( !flag ) lit.add( m );
    }
    public Iterator<Monomio> iterator() {
        return lista.iterator();
    }
}
