package poo.eratostene;

import java.util.*;

public class CrivelloEratostene extends CrivelloAstratto {
    private final int N;
    private Set<Integer> crivello = new LinkedHashSet<>();

    public CrivelloEratostene(final int N){
        if(N < 2) throw new IllegalArgumentException();
        this.N = N;
        for(int x = 2; x <= N; x++) crivello.add(x);
    }
    public Iterator<Integer> iterator(){
        return crivello.iterator();
    }
    public void filtra(){
        for(int x = 2; x <= Math.round(Math.sqrt(N)); x = (x == 2) ? x + 1 : x + 2){
            if(crivello.contains(x)){
                int mx = 2 * x;
                while(mx <= N){
                    crivello.remove(mx);
                    mx += x;
                }
            }
        }
    }

    public static void main(String[] args) {
        CrivelloEratostene ce = new CrivelloEratostene(100);
        ce.filtra();
        System.out.println(ce.crivello);
    }
}
