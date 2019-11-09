package poo.eratostene;

import java.util.Iterator;

public abstract class CrivelloAstratto implements Crivello {
    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        int c = 0;
        for(int p : this){
            sb.append(String.format("%10d", p));
            if(c % 7 == 0) sb.append("\n");
            c++;
        }
        sb.append("\n");
        return sb.toString();
    }
    public int hashCode(){
        return 0;// TODO: 05/11/2019
    }
    public boolean equals(Object obj) {
        return false; // TODO: 05/11/2019
    }
}
