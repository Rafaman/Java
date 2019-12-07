package poo.agenda;

import java.util.Iterator;

public abstract class AbstractAgenda implements Agenda{
    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        for(Contatto c:this)
            sb.append(c + "\n");
        return sb.toString();
    }
    public boolean equals(Object o){
        if(!(o instanceof Agenda)) return false;
        if(o == this) return true;
        Agenda a = (Agenda) o;
        for(Contatto c:this)
            for(Contatto c1:a)
                if(!c.equals(c1))
                    return false;
        return true;
    }
    public int hashCode(){
        int p = 17;
        int hash = 0;
        for(Contatto c:this)
            hash = hash * p + c.hashCode();
        return hash;
    }
}
