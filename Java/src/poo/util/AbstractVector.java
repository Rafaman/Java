package poo.util;

import java.util.Iterator;

public abstract class AbstractVector<T> implements Vector<T> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(300);
        sb.append('[');
        for (T e : this){
            sb.append(e);
            sb.append(", ");
        }
        if(sb.length() > 1) sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
    @Override @SuppressWarnings("unchecked")
    public boolean equals(Object o){
        if(!(o instanceof Vector)) return false;
        if(o == this) return true;
        Vector<T> v = (Vector<T>) o;
        if(size() != v.size())  return false;
        Iterator<T> it1 = iterator(), it2 = v.iterator();
        while(it1.hasNext()) {
            T e1 = it1.next(), e2 = it2.next();
            if (!e1.equals(e2)) return false;
        }
        return true;
    }
    @Override
    public int hashCode(){
        final int M = 83;
        int h = 0;
        for(T e:this)
            h = h * M + e.hashCode();
        return h;
    }
}
