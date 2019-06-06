import IO.*;

public class FSA {    // Riconosce le stringhe ab+cc

    private int stato;

    public FSA() {
        stato = 0;
    }

    private void transizione(char c) {
        switch (stato) {
            case 0:
                if (c == 'a')
                    stato = 1;
                else
                    stato = 5;
                break;
            case 1:
                if (c == 'b')
                    stato = 2;
                else
                    stato = 5;
                break;
            case 2:
                if (c == 'c')
                    stato = 3;
                if (c != 'b' && c != 'c')
                    stato = 5;
                break;
            case 3:
                if (c == 'c')
                    stato = 4;
                else
                    stato = 5;
                break;
            case 4:
                stato = 5;
                break;
            case 5:
                break;
        }
        IO.println("Carattere " + c + ", passo allo stato " + stato);
    }

    public boolean riconosci(String s) {
        stato = 0;
        for (int i = 0; i < s.length() && stato != 5; i++) {
            char c = s.charAt(i);
            transizione(c);
        }
        return stato == 4;
    }
}
