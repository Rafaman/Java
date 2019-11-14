package poo.eratostene;

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
        return size() * 83;
    }
    public boolean equals(Object o) {
        if(!(o instanceof Crivello)) return false;
        if(o == this) return true;
        Crivello c = (Crivello) o;
        return c.size() == size();
    }
}
