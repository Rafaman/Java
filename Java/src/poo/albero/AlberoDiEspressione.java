package poo.albero;

import poo.util.List;

public class AlberoDiEspressione {
    private static class Nodo{
        Nodo fS, fD;
    }
    private static class NodoOperando {
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
    public void preOrder(List<String> l){}
}
