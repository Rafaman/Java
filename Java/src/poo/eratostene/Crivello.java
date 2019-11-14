package poo.eratostene;

public interface Crivello extends Iterable<Integer>{
    default int size(){
        int c = 0;
        for(int x : this) c++;
        return c;
    }
    default boolean isPrime(int x) {
        for(int i = 2; i < Math.sqrt(x); i++)
            if(x % i == 0)
                return true;
        return false;
    }
    void filtra();
}
