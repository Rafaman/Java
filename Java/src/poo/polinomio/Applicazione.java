package poo.polinomio;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
            //String scelta = sc.nextLine().toUpperCase();
            String scelta = "G";
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
        sc.nextLine();
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
    public static Polinomio riconosciPolinomio(String s, int t){
        Polinomio polinomio;
        int coefficiente = 0, grado = 0;
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
        StringTokenizer st = new StringTokenizer(s, "=+-x^", true);
        while(st.hasMoreTokens()){
            String corrente = st.nextToken();
            if (corrente.equals("-")) segno = true;
            else if (corrente.equals("^")) esponente = true;
            if (Character.isDigit(corrente.charAt(0)))
                if (segno) {
                    coefficiente = Integer.parseInt("-" + corrente);
                    segno = false;
                } else if (esponente) {
                    grado = Integer.parseInt(corrente);
                    polinomio.add(new Monomio(coefficiente, grado));
                    coefficiente = 0; grado = 0;
                    esponente = false;
                } else coefficiente = Integer.parseInt(corrente);
            else if (corrente.equals("x")){
                grado = 1;
                if(segno && coefficiente == 0) coefficiente = -1;
                else if(coefficiente == 0) coefficiente = 1;
            }
            else if (!esponente){
                polinomio.add(new Monomio(coefficiente, grado));
                coefficiente = 0; grado = 0;
            }
        }
        polinomio.add(new Monomio(coefficiente, grado));
        return polinomio;
    }
    public static void valutaEspressione(String s){
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
            JFrame frame = new Finestra();
            frame.setVisible(true);
        });
    }
    static class Finestra extends JFrame{
        private ArrayList<Polinomio> polinomi = new ArrayList<>();
        private ArrayList<JCheckBox> checkPolinomi = new ArrayList<>();
        private JMenuItem salva, salvaConNome, apri, esci, aggiungiPolinomio, modificaPolinomio, rimuoviPolinomio, polinomioLL, polinomioSet, polinomioList, polinomioMap;
        private File fileSalvataggio = null;
        private int tipoPolinomio;
        private JPanel panel, panelPolinomi, panelOperazioni;
        private JCheckBox addizione, moltiplicazione, derivata;

        private Finestra() {
            int WIDTH = 500, HEIGHT = 500;
            /* Imposto l'aspetto della finestra secondo quello del sistema operativo */
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored){}
            setTitle("Polinomio"); /* Titolo finestra */
            setSize(WIDTH, HEIGHT); /* Dimensioni finestra */
            /*
            * Location al centro
            * */
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (dimension.width - WIDTH) / 2;
            int y = (dimension.height - HEIGHT) / 2;
            setLocation(x, y);
            /*
            * Creazione Listener
            * */
            ListenerFileMenu listenerFileMenu = new ListenerFileMenu();
            ListenerCommandMenu listenerCommandMenu = new ListenerCommandMenu();
            ListenerTipologieMenu listenerTipologieMenu = new ListenerTipologieMenu();
            ListenerOperation listenerOperation = new ListenerOperation();
            /*
            * Inserimento componenti aggiuntivi:
            * Menù "File" e relative voci
            * */
            JMenuBar menuBar = new JMenuBar();
            this.setJMenuBar( menuBar );
            JMenu fileMenu = new JMenu("File");
            menuBar.add(fileMenu);
            apri = new JMenuItem("Apri");
            apri.addActionListener(listenerFileMenu);
            fileMenu.add(apri);
            salva = new JMenuItem("Salva");
            salva.addActionListener(listenerFileMenu);
            fileMenu.add(salva);
            salvaConNome = new JMenuItem("Salva con nome");
            salvaConNome.addActionListener(listenerFileMenu);
            fileMenu.add(salvaConNome);
            esci = new JMenuItem("Esci");
            esci.addActionListener(listenerFileMenu);
            fileMenu.add(esci);
            /*
            * Menù "Comandi" e relative voci
            * */
            JMenu commandMenu = new JMenu("Comandi");
            menuBar.add(commandMenu);
            JMenu tipologie = new JMenu("Tipo polinomio");
            commandMenu.add(tipologie);
            polinomioLL = new JMenuItem("PolinomioLL");
            polinomioLL.addActionListener(listenerTipologieMenu);
            polinomioSet = new JMenuItem("PolinomioSet");
            polinomioSet.addActionListener(listenerTipologieMenu);
            polinomioList = new JMenuItem("PolinomioList");
            polinomioList.addActionListener(listenerTipologieMenu);
            polinomioMap = new JMenuItem("PolinomioMap");
            polinomioMap.addActionListener(listenerTipologieMenu);
            tipologie.add(polinomioLL);
            tipologie.add(polinomioSet);
            tipologie.add(polinomioList);
            tipologie.add(polinomioMap);
            commandMenu.addSeparator();
            aggiungiPolinomio = new JMenuItem("Aggiungi polinomio");
            aggiungiPolinomio.addActionListener(listenerCommandMenu);
            commandMenu.add(aggiungiPolinomio);
            modificaPolinomio = new JMenuItem("Modifica polinomio");
            modificaPolinomio.addActionListener(listenerCommandMenu);
            commandMenu.add(modificaPolinomio);
            rimuoviPolinomio = new JMenuItem("Rimuovi polinomio");
            rimuoviPolinomio.addActionListener(listenerCommandMenu);
            commandMenu.add(rimuoviPolinomio);

            start();
            /*
            * Pannelli e componenti aggiuntivi
            * */
            panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2));
            panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            panelPolinomi = new JPanel();
            panelPolinomi.setLayout(new GridLayout(4, 1));
            panelOperazioni = new JPanel();
            panelOperazioni.setLayout(new GridLayout(4, 1));
            panelOperazioni.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            JLabel label = new JLabel("Polinomi", JLabel.LEFT);
            label.setFont(new Font("", Font.PLAIN, 14));
            JLabel label2 = new JLabel("Operazioni");
            label2.setFont(new Font("", Font.PLAIN, 14));
            panelPolinomi.add(label);
            panelOperazioni.add(label2);
            addizione = new JCheckBox("Addizione");
            addizione.addActionListener(listenerOperation);
            moltiplicazione = new JCheckBox("Moltiplicazione");
            moltiplicazione.addActionListener(listenerOperation);
            derivata = new JCheckBox("Derivata");
            derivata.addActionListener(listenerOperation);
            panelOperazioni.add(addizione);
            panelOperazioni.add(moltiplicazione);
            panelOperazioni.add(derivata);
            panel.add(panelPolinomi);
            panel.add(panelOperazioni);
            panel.setSize(WIDTH, HEIGHT);
            add(panel, BorderLayout.NORTH);
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
                                JOptionPane.showMessageDialog(null,"Nessun salvataggio!");
                            return;
                        }
                        if( fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION )
                            fileSalvataggio = fileChooser.getSelectedFile();
                        if( fileSalvataggio != null ){
                            save( fileSalvataggio.getAbsolutePath() );
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Nessun salvataggio!");
                    }catch( Exception exc ){
                        exc.printStackTrace();
                    }
                } else if(e.getSource() == salvaConNome){
                    JFileChooser chooser=new JFileChooser();
                    try{
                        if( chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION )
                            fileSalvataggio=chooser.getSelectedFile();
                        if( fileSalvataggio!=null ){
                            save( fileSalvataggio.getAbsolutePath() );
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Nessun salvataggio!");
                    }catch( Exception exc ){
                        exc.printStackTrace();
                    }
                } else if(e.getSource() == apri){

                }
            }
        }
        private class ListenerCommandMenu implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == aggiungiPolinomio){
                    String polinomio = JOptionPane.showInputDialog(null, "Inserisci polinomio:", "Aggiungi polinomio", JOptionPane.PLAIN_MESSAGE);
                    try {
                        Applicazione.valutaEspressione(polinomio);
                        polinomi.add(Applicazione.riconosciPolinomio(polinomio, tipoPolinomio));
                        checkPolinomi.add(new JCheckBox(polinomio));
                        panelPolinomi.add(checkPolinomi.get(checkPolinomi.size() - 1));
                    } catch(IllegalArgumentException iae) {
                        JOptionPane.showMessageDialog(null, "Polinomio errato!", "Errore", JOptionPane.ERROR_MESSAGE);
                    }
                    Finestra.this.validate();
                } else if(e.getSource() == modificaPolinomio) {
                    String newPolinomio = null;
                    if(countSelected(checkPolinomi) > 1) {
                        JOptionPane.showMessageDialog(null, "Troppi polinomi selezionati!", "Errore", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    for(JCheckBox cb:checkPolinomi)
                        if(cb.isSelected()) {
                            try{
                                newPolinomio = JOptionPane.showInputDialog(null, "Polinomio modificato:", "Modifica polinomio", JOptionPane.PLAIN_MESSAGE);
                                Applicazione.valutaEspressione(newPolinomio);
                                Polinomio polinomioCorrente = Applicazione.riconosciPolinomio(newPolinomio, tipoPolinomio);
                                int index = polinomi.indexOf(polinomi.get(checkPolinomi.indexOf(cb)));
                                polinomi.add(index, polinomioCorrente);
                            } catch (IllegalArgumentException iae){
                                JOptionPane.showMessageDialog(null, "Polinomio errato!", "Errore", JOptionPane.ERROR_MESSAGE);
                            }
                            cb.setText(newPolinomio);
                            cb.setSelected(false);
                        }
                } else if(e.getSource() == rimuoviPolinomio){
                    for(int i = 0; i < checkPolinomi.size(); i++) {
                        if (checkPolinomi.get(i).isSelected()) {
                            panelPolinomi.remove(checkPolinomi.get(i));
                            checkPolinomi.remove(i);
                            polinomi.remove(i);
                        } else{
                            JOptionPane.showMessageDialog(null, "Nessun polinomio selezionato!", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    Finestra.this.repaint();
                    Finestra.this.validate();
                    JOptionPane.showMessageDialog(null, "Polinomi rimossi corretatmente!", "Completato", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        private class ListenerTipologieMenu implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == polinomioLL) tipoPolinomio = 1;
                else if(e.getSource() == polinomioSet) tipoPolinomio = 2;
                else if(e.getSource() == polinomioList) tipoPolinomio = 3;
                else tipoPolinomio = 4;
                postScelta();
            }
        }
        private class ListenerOperation implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == addizione){

                } else if(e.getSource() == moltiplicazione){

                } else {

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
            pw.println(s);
            pw.close();
        }
        private void start(){
            salva.setEnabled(false);
            salvaConNome.setEnabled(false);
            apri.setEnabled(false);
            aggiungiPolinomio.setEnabled(false);
            modificaPolinomio.setEnabled(false);
            rimuoviPolinomio.setEnabled(false);
        }
        private void postScelta(){
            if(tipoPolinomio != 0) {
                salva.setEnabled(true);
                salvaConNome.setEnabled(true);
                apri.setEnabled(true);
                aggiungiPolinomio.setEnabled(true);
                modificaPolinomio.setEnabled(true);
                rimuoviPolinomio.setEnabled(true);
                addizione.setEnabled(true);
                moltiplicazione.setEnabled(true);
                derivata.setEnabled(true);
            }
        }
        private static int countSelected(ArrayList<JCheckBox> al){
            int count = 0;
            for(JCheckBox cb:al)
                if(cb.isSelected()) count++;
            return count;
        }
    }
}