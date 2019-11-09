package poo.eratostene;

import java.util.Set;

public interface Crivello extends Iterable<Integer>{
    default int size(){
        int c = 0;
        for(int x : this) c++;
        return c;
    }
    default boolean isPrime(int x) {// TODO
        return false;
    }
    void filtra();
}
