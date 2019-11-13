package poo.polinomio;

import java.util.*;

public class PolinomioMap extends PolinomioAstratto {
    private static Map<Monomio, Monomio> polinomio = new TreeMap<>();

    public PolinomioMap(){}
    public PolinomioMap(Polinomio p){
        for(Monomio m:p)
            polinomio.put(m, m);
    }
    @Override
    public int size() {
        return polinomio.size();
    }
    @Override
    public void add(Monomio m) {
        if(m.getCoeff() == 0) return;
        if(polinomio.containsKey(m))
            m.add(polinomio.get(m));
        polinomio.put(m, m);
    }
    @Override
    public Polinomio crea() {
        return new PolinomioMap();
    }
    @Override
    public Iterator<Monomio> iterator() {
        return polinomio.values().iterator();
    }
}
