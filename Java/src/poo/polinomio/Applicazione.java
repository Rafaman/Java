package poo.polinomio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.*;

public class Applicazione {
    public static void main(String[] args) {
        /*
        * Scelta del metodo con cui si vuole procedere
        * */
        System.out.println("Come vuoi procedere?\nC. Riga di comando\nG. Grafico\nQ. Quit");
        Scanner sc = new Scanner(System.in);
        while(true) {
            String scelta = sc.nextLine().toUpperCase();
            try {
                switch (scelta) {
                    case "C":
                        commandLine();
                        break;
                    case "G":
                        GUI.creaGUI();
                        break;
                    case "Q":
                        System.exit(0);
                    default:
                        throw new IllegalArgumentException();
                }
                break;
            }catch(IllegalArgumentException iae){
                System.out.println("Opzione errata!");
            }
        }
        sc.close();
    }
    private static void commandLine(){
        String espressione2 = "", espressione1;
        Polinomio polinomio, polinomio2;
        int tipo;

        Scanner sc = new Scanner(System.in);
        /*
        * Scelta tipo di polinomio
        * */
        while(true){
            System.out.println("Tipo polinomio:\n1. PolinomioLL\n2. PolinomioSet\n3. PolinomioList\n4. PolinomioMap");
            try {
                tipo = sc.nextInt();
                if(tipo != 1 && tipo != 2 && tipo != 3 && tipo != 4) throw new IllegalArgumentException();
                break;
            }catch(Exception e){
                System.out.println("Scelta errata!");
            }
        }
        sc = new Scanner(System.in);
        /*
        * Inserimento polinomi da tastiera e controllo validità
        * */
        while(true){
            try {
                System.out.println("Inserisci due polinomi (Q per terminare): ");
                espressione1 = sc.nextLine();
                if(espressione1.equals("Q") || espressione1.equals("q")) break;
                valutaEspressione(espressione1);
                espressione2 = sc.nextLine();
                if(espressione2.equals("Q") || espressione2.equals("q")) break;
                valutaEspressione(espressione2);
                break;
            } catch(IllegalArgumentException iae) {
                System.out.println("Polinomio errato!");
            }
        }
        sc.close();
        /*
         * Riconoscimento dei polinomi
         * */
        polinomio = riconosciPolinomio(espressione1, tipo);
        polinomio2 = riconosciPolinomio(espressione2, tipo);
        /*
         * Stampa operazioni polinomi
         * */
        System.out.println("Polinomi: " + polinomio + ", " + polinomio2);
        System.out.println("Addizione: " + polinomio.add(polinomio2));
        System.out.println("Moltiplicazione: " + polinomio.mul(polinomio2));
        System.out.println("Derivate: " + polinomio.derivata() + ", " + polinomio2.derivata());
    }
    private static Polinomio riconosciPolinomio(String s, int t){
        Polinomio polinomio;
        int coefficiente = 0, grado = -1;
        boolean segno = false, esponente = false;
        /*
        * Controllo scelta tipo polinomio dell' utente
        * */
        if(t == 1)
            polinomio = new PolinomioLL();
        else if(t == 2)
            polinomio = new PolinomioSet();
        else if(t == 3)
            polinomio = new PolinomioList();
        else polinomio = new PolinomioMap();
        /*
        * Inizio del riconoscimento dei monomi
        * */
        StringTokenizer st = new StringTokenizer(s, "=-x^", true);
        while(st.hasMoreTokens()){
            String corrente = st.nextToken();
            if (corrente.equals("x")) grado = 1;
            else if (corrente.equals("-")) segno = true;
            else if (corrente.equals("^")) esponente = true;
            else if (Character.isDigit(corrente.charAt(0)))
                if (segno) {
                    coefficiente = Integer.parseInt("-" + corrente);
                    segno = false;
                } else if (esponente) {
                    grado = Integer.parseInt(corrente);
                    esponente = false;
                } else coefficiente = Integer.parseInt(corrente);
            else grado = 0;
            if(grado != -1) {
                polinomio.add(new Monomio(coefficiente, grado));
                coefficiente = 0; grado = -1;
            }
        }
        return polinomio;
    }
    private static void valutaEspressione(String s){
        /*
        * Match tra regex e la stringa inserita dall' utente
        * */
        if(s.equals("")) throw new IllegalArgumentException();
        else if(s.contains("++") || s.contains("--")) throw new IllegalArgumentException();
        String polinomioControllo = "[\\-?[[0-9]+]s[[x]^[0-9]]*]*";
        if(!Pattern.matches(polinomioControllo, s)) throw new IllegalArgumentException();
    }
}

class GUI{
    public static void creaGUI(){
        EventQueue.invokeLater(() -> {
            JFrame frame = null;
            try {
                frame = new Finestra();
            } catch(Exception e){
                System.out.println("Look and Feel errato!");
            }
            assert frame != null;
            frame.setVisible(true);
        });
    }
    static class Finestra extends JFrame{
        private JMenuItem salva, salvaConNome, carica, esci;
        private File fileSalvataggio = null;

        private Finestra() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
            int WIDTH = 500, HEIGHT = 500;
            /* Imposto l'aspetto della finestra secondo quello del sistema operativo */
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            setTitle("Polinomio"); /* Titolo finestra */
            setSize(WIDTH, HEIGHT); /* Dimensioni finestra */
            /*
            * Location al centro
            * */
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - WIDTH) / 2);
            int y = (int) ((dimension.getHeight() - HEIGHT) / 2);
            setLocation(x, y);
            /*
            * Creazione ActionListener
            * */
            ListenerFileMenu listenerFileMenu = new ListenerFileMenu();
            /*
            * Inserimento componenti aggiuntivi:
            * Menù "File" e relative voci
            * */
            JMenuBar menuBar = new JMenuBar();
            this.setJMenuBar( menuBar );
            JMenu fileMenu = new JMenu("File");
            menuBar.add(fileMenu);
            salva = new JMenuItem("Salva");
            salva.addActionListener(listenerFileMenu);
            fileMenu.add(salva);
            salvaConNome = new JMenuItem("Salva con nome");
            salvaConNome.addActionListener(listenerFileMenu);
            fileMenu.add(salvaConNome);
            carica = new JMenuItem("Carica");
            fileMenu.add(carica);
            esci = new JMenuItem("Esci");
            esci.addActionListener(listenerFileMenu);
            fileMenu.add(esci);
            /*
            * Menù "Comandi" e relative voci
            * */
            JMenu commandMenu = new JMenu("Comandi");
            menuBar.add(commandMenu);
            /*
            * Chiusura
            * */
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    if(consensoUscita()) System.exit(0);
                }
            });
        }
        private class ListenerFileMenu implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == esci){
                    if(consensoUscita()) System.exit(0);
                } else if(e.getSource() == salva){
                    JFileChooser fileChooser = new JFileChooser();
                    try{
                        if( fileSalvataggio != null ){
                            int ans = JOptionPane.showConfirmDialog(null,"Sovrascrivere " + fileSalvataggio.getAbsolutePath() + " ?");
                            if( ans == 0)
                                save( fileSalvataggio.getAbsolutePath() );
                            else
                                JOptionPane.showMessageDialog(null,"Nessun savetaggio!");
                            return;
                        }
                        if( fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ){
                            fileSalvataggio = fileChooser.getSelectedFile();
                            Finestra.this.setTitle(fileSalvataggio.getName());
                        }
                        if( fileSalvataggio != null ){
                            save( fileSalvataggio.getAbsolutePath() );
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Nessun savetaggio!");
                    }catch( Exception exc ){
                        exc.printStackTrace();
                    }
                }
            }
        }
        private boolean consensoUscita(){
            int option = JOptionPane.showConfirmDialog(
                    null, "Uscendo si perderanno tutti i dati!\n\nContinuare?", "Esci",
                    JOptionPane.YES_NO_OPTION);
            return option == JOptionPane.YES_OPTION;
        }
        private static void save(String s) throws IOException {
            PrintWriter pw = new PrintWriter(new FileWriter(s));
        }
    }
}