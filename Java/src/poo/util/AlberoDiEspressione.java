package poo.util;

import java.util.StringTokenizer;
import poo.util.didattica.List;

public class AlberoDiEspressione {
	/*
	 * TODO Scrivere un altro metodo build che riceve un' espressione in formato preOrder
	 * */
	private static class Nodo{
		Nodo fS, fD;
	}
	private static class NodoOperando extends Nodo{
		int opnd;
		public String toString() {
			return ""+opnd;
		}
	}
	private static class NodoOperatore extends Nodo{
		char op;
		public String toString() {
			return ""+op;
		}
	}
	private Nodo radice = null;
	public void build(String expr) {
		String EXPR = "(\\d+|[\\+\\-\\*/\\(\\)])+";
		if(!expr.matches(EXPR)) throw new RuntimeException("Espressione malformata!");
		StringTokenizer st = new StringTokenizer(expr, "+-*/()", true);
		radice = creaEspressione(st);
	}
	private Nodo creaEspressione(StringTokenizer st) {
		Nodo radice = creaOperando(st);
		while(st.hasMoreTokens()) {
			char op = st.nextToken().charAt(0);
			if(op == ')') return radice;
			Nodo opnd = creaOperando(st);
			NodoOperatore nop = new NodoOperatore();
			nop.op = op;
			nop.fS = radice;
			nop.fD = opnd;
			radice = nop;
		}
		return radice;
	}
	private Nodo creaOperando(StringTokenizer st) {
		String tk = st.nextToken();
		if(tk.matches("\\d+")) {
			NodoOperando nopnd = new NodoOperando();
			nopnd.opnd = Integer.parseInt(tk);
			nopnd.fS = null; nopnd.fD = null;
			return nopnd;
		}
		return creaEspressione(st);
	}
	public void preOrder(List<String> l) {
		preOrder(radice, l);
	}
	private void preOrder(Nodo radice, List<String> l) {
		if(radice!=null) {
			l.addLast(radice.toString());
			preOrder(radice.fS, l);
			preOrder(radice.fD, l);
		}
	}
	public void postOrder(List<String> l) {
		postOrder(radice, l);
	}
	private void postOrder(Nodo radice, List<String> l) {
		if(radice!=null) {
			postOrder(radice.fS, l);
			postOrder(radice.fD, l);
			l.addLast(radice.toString());
		}
	}
	public void inOrder(List<String> l) {
		inOrder(radice, l);
	}
	private void inOrder(Nodo radice, List<String> l) {
		if(radice!=null) {
			if(radice instanceof NodoOperatore) l.addLast("(");
			inOrder(radice.fS, l);
			inOrder(radice.fD, l);
			l.addLast(radice.toString());
			inOrder(radice.fD, l);
		}
		if(radice instanceof NodoOperatore) l.addLast(")");
	}
	public int valore() {
		if(radice==null) throw new RuntimeException("Nessuna espressione!");
		return valore(radice);
	}
	private int valore(Nodo radice) {
		if(radice instanceof NodoOperando) return ((NodoOperando)radice).opnd;
		else {
			int v1 = valore(radice.fS);
			int v2 = valore(radice.fD);
			char op = ((NodoOperatore)radice).op;
			switch(op) {
				case '+' : return v1+v2;
				case '-' : return v1-v2;
				case '*' : return v1*v2;
				case '/' : return v1/v2;
				default : throw new RuntimeException("operatore inatteso");
			}
		}
	}
}
