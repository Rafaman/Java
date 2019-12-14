package poo.albero;

import java.util.List;

public class AlberoDiEspressione {
    private static class Nodo{
        Nodo fS, fD;
    }
    private static class NodoOperando extends Nodo{
        int opnd;
        @Override
        public String toString() {
            return String.valueOf(opnd);
        }
    }
    private static class NodoOperatore extends Nodo{
        char op;
        @Override
        public String toString(){
            return String.valueOf(op);
        }
    }
    private Nodo radice = null;
    public void build(String expr){}
    public void preOrder(List<String> l){
        preOrder(radice, l);
    }
    private void preOrder(Nodo radice, List<String> l){
        if(radice != null){
            l.add(radice.toString());
            preOrder(radice.fS, l);
            preOrder(radice.fD, l);
        }
    }
    public void postOrder(List<String> l){
        postOrder(radice, l);
    }
    private void postOrder(Nodo radice, java.util.List<String> l){
        if(radice != null){
            postOrder(radice.fS, l);
            postOrder(radice.fD, l);
            l.add(radice.toString());
        }
    }
    public void inOrder(List<String> l){
        inOrder(radice, l);
    }
    private void inOrder(Nodo radice, java.util.List<String> l){
        if(radice != null){
            if(radice instanceof NodoOperatore) l.add("(");
            inOrder(radice.fS, l);
            l.add(radice.toString());
            inOrder(radice.fD, l);
            l.add(radice.toString());
            if(radice instanceof NodoOperatore) l.add(")");
        }
    }
    public int valore(){
        if(radice == null) throw new RuntimeException("Nessuna espressione!");
        return valore(radice);
    }
    private int valore(Nodo radice){
        if(radice instanceof NodoOperando) return ((NodoOperando)radice).opnd;
        else{
            int v1 = valore(radice.fS);
            int v2 = valore(radice.fD);
            char op = ((NodoOperatore) radice).op;
            switch (op){
                case '+':
                    break;
            }
        }
    }
}
