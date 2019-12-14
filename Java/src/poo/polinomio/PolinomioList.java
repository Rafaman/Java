package poo.polinomio;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Iterator;
=======
import java.util.Iterator;
import java.util.ArrayList;
>>>>>>> 6fe7f2954168e2d88b5e449ddd5d7c3f8336066b
import java.util.ListIterator;

public class PolinomioList extends PolinomioAstratto {
    private ArrayList<Monomio> lista = new ArrayList<>();

<<<<<<< HEAD
    public PolinomioList(){}
    public PolinomioList(Polinomio p){
        for(Monomio m:p)
            this.add(m);
    }
    @Override
    public int size() {
        return lista.size();
    }
    @Override
    public void add(Monomio m) {
=======
    public PolinomioList(){};
    public PolinomioList( Polinomio p ){
        for( Monomio m: p ) add(m);
    }
    @Override
    public PolinomioList crea(){ //covarianza tipo di ritorno
        return new PolinomioList();
    }//create
    public Iterator<Monomio> iterator(){
        return lista.iterator();
    }//iterator
    public int size(){ return lista.size(); }
    public void add( Monomio m ){
>>>>>>> 6fe7f2954168e2d88b5e449ddd5d7c3f8336066b
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
<<<<<<< HEAD
    @Override
    public Polinomio crea() {
        return new PolinomioList();
    }
    @Override
    public Iterator<Monomio> iterator() {
        return lista.iterator();
    }
=======
>>>>>>> 6fe7f2954168e2d88b5e449ddd5d7c3f8336066b
}
