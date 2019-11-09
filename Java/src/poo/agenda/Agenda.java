package poo.agenda;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public interface Agenda extends Iterable<Contatto> {
    default int size(){
        int ret = 0;
        for(Contatto c:this) ret++;
        return ret;
    }
    default void clear(){
        Iterator<Contatto> it = iterator();
        while (it.hasNext()){
            it.next();
            it.remove();
        }
    }
    default void add(Contatto c){

    }
    default void remove(Contatto c){
        Iterator<Contatto> it = iterator();
        while (it.hasNext()) {
            if(c.equals(it.next())) {
                it.next();
                it.remove();
            }
        }
    }
    default Contatto search(Contatto c){
        Iterator<Contatto> it = iterator();
        while (it.hasNext()){
            Contatto c1 = it.next();
            if(c1.equals(c)) return c1;
            if(c1.compareTo(c) > 0) return null;
        }
        return null;
    }
    default Contatto search(String prefisso, String numero){
        Iterator<Contatto> it = iterator();
        while (it.hasNext()){
            Contatto c1 = it.next();
            if(c1.getPrefisso() == prefisso && c1.getNumero() == numero) return c1;
        }
        return null;
    }
    default void save(String path) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(path));
        for (Contatto c : this)
            pw.println(c);
        pw.close();
    }
    default void load(String path) throws IOException {
        this.clear();
        BufferedReader br = new BufferedReader(new FileReader(path));
        try {
            for (; ; ) {
                String linea = br.readLine();
                if (linea == null) break;
                StringTokenizer st = new StringTokenizer(linea, " -");
                String cognome = st.nextToken();
                String nome = st.nextToken();
                String prefisso = st.nextToken();
                String numero = st.nextToken();
                this.add(new Contatto(nome, cognome, prefisso, numero));
            }
        } catch(Exception e) {
            throw new IOException();
        } finally {
            br.close();
        }
    }
}