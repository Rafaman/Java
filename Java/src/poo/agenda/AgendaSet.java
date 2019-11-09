package poo.agenda;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class AgendaSet extends AbstractAgenda {
    private Set<Contatto> table = new TreeSet<Contatto>();

    @Override
    public Iterator<Contatto> iterator(){
        return table.iterator();
    }
    @Override
    public void add(Contatto c){
        table.remove(c);
        table.add(c);
    }
    @Override
    public int size(){
        return table.size();
    }
    @Override
    public void remove(Contatto c){
        table.remove(c);
    }
    @Override
    public void clear() {
        table.clear();
    }
}
