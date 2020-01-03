package poo.util;
import java.util.*;

public class Token {
	public static void main(String[] args) {
		String espr = null;
		if(args.length==1) {
			espr=args[0];
		}
		else {
			Scanner sc = new Scanner(System.in);
			System.out.print("Espr>");
			espr=sc.nextLine();
			sc.close();
		}
		StringTokenizer st = new StringTokenizer(espr,"+-*/",true); //include i limitatori come altri token
		int ris = Integer.parseInt(st.nextToken());
		while(st.hasMoreElements()) {
			char op = st.nextToken().charAt(0); //otteniamo l'operatore
			int num = Integer.parseInt(st.nextToken()); //otteniamo numero
			switch(op) {
			case '+' : ris = ris+num;
			case '-' : ris = ris-num;
			case '*' : ris = ris*num;
			default: ris=ris/num;
			}
		}
		System.out.println(espr+'='+ris);
	}

}
