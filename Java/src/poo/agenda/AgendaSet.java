package poo.agenda;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AgendaSet extends AbstractAgenda {
    private Set<Contatto> table = new TreeSet<Contatto>();

    public Iterator<Contatto> iterator(){
        return table.iterator();
    }
    public void add(Contatto c){
        table.remove(c);
        table.add(c);
    }
    public int size(){
        return table.size();
    }
    public void remove(Contatto c){
        table.remove(c);
    }
}
