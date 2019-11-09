package poo.agenda;

public abstract class AbstractAgenda implements Agenda{
    public String toString(){
        StringBuilder sb = new StringBuilder(500);
        for(Contatto c:this)
            sb.append(c + "\n");
        return sb.toString();
    }
    public boolean equals(Object o){
        return false; // TODO: 06/11/2019  
    }
    public int hashCode(){
        return 0; // TODO: 06/11/2019  
    }
}
