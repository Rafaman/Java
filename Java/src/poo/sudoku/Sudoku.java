package poo.sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Sudoku {
    private final int SIZE = 9;
    private int[][] board = new int[SIZE][SIZE], boardBloccata = new int[SIZE][SIZE];

    public Sudoku() {
        /*
         * Costruttore di default: inizializza la board a zero
         * */
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                board[i][j] = 0;
    }
    public Sudoku(int[][] imp) {
        /*
         * Costruttore di impostazione: inizializza la matrice board copiando i valori dalla matrice parametro
         * */
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                imposta(i, j, imp[i][j]);
        boardBloccata = imp.clone();
    }
    public void imposta(int row, int col, int v){
        /*
         * Metodo che imposta il contenuto di una cella <i,j> uguale a v, controllando la correttezza dei parametri
         * */
        if(row < 0 || row > 8) throw new IllegalArgumentException("Numero riga errato");
        if(col < 0 || col > 8) throw new IllegalArgumentException("Numero colonna errato");
        if(v < 0 || v > 9) throw new IllegalArgumentException("Valore errato");
        assegna(row, col, v);
    }
    public void risolvi(){
        /*
         * Metodo per la risoluzione
         * */
        colloca(0, 0);
    }
    private void colloca(int row, int col){
        /*
         * Backtracking
         * */
        if(boardBloccata[row][col] != 0){
            if(row == SIZE - 1 && col == SIZE - 1) scriviSoluzione();
            else if(col < SIZE - 1) colloca(row, col + 1);
            else if(row < SIZE - 1) colloca(row + 1, 0);}
        else
            for(int n = 1; n < 10; n++)
                if(assegnabile(row, col, n)) {
                    assegna(row, col, n);
                    if(row == SIZE - 1 && col == SIZE - 1) scriviSoluzione();
                    else if(col < SIZE - 1) colloca(row, col + 1);
                    else if(row < SIZE - 1) colloca(row + 1, 0);
                    deassegna(row, col);
                }
    }
    private boolean assegnabile(int row, int col, int v){
        /*
         * Controlla se il valore v è assegnabile ad una cella <i,j>
         * */
        if(board[row][col] != 0) return false;
        for(int j = 0; j < SIZE; j++)
            if(board[row][j] == v) return false;
        for(int i = 0; i < SIZE; i++)
            if(board[i][col] == v) return false;
        return checkSottoMatrici(row, col, v);
    }
    private void assegna(int row, int col, int v){
        board[row][col] = v;
    }
    private void deassegna(int row, int col){
        /*
         * Metodo che svuota la cella <i,j>
         * */
        board[row][col] = 0;
    }
    private void scriviSoluzione(){
        /*
         * Metodo che aggiunge ad un ArrayList la soluzione trovata
         * */
        int[][] soluzione = new int[SIZE][SIZE];
        for(int i = 0; i < SIZE; i++)
            for(int j = 0; j < SIZE; j++)
                soluzione[i][j] = board[i][j];
        GUI.Finestra.soluzioni.add(soluzione);
    }
    private boolean checkSottoMatrici(int row, int col, int v){
        /*
         * Metodo per il controllo della presenza di v nelle sottomatrici 3x3 del sudoku
         * */
        int boxRow = row - row % 3, boxCol = col - col % 3;

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[boxRow + i][boxCol + j] == v)
                    return false;
        return true;
    }
    public static void main(String[] args) {
        GUI.creaGUI();
    }
}

class GUI{
    public static void creaGUI(){
        /*
         * Creazione GUI
         * */
        EventQueue.invokeLater(() -> {
            JFrame frame = new Finestra();
            frame.setVisible(true);
        });
    }

    static class Finestra extends JFrame{
        private final int SIZE = 9;
        private int numeriInseriti, indiceSoluzione;
        private JTextField[][] iniziale = new JTextField[SIZE][SIZE];
        private int[][] impostata = new int[SIZE][SIZE];
        protected static ArrayList<int[][]> soluzioni = new ArrayList<>();
        private JMenuItem salva, salvaConNome, apri, esci, help, about;
        private JButton previous, next, risolvi, pulisci;
        private JPanel panelDati;
        private File fileSalvataggio = null;

        private Finestra() {
            int WIDTH = 500, HEIGHT = 250;
            /* Imposto l'aspetto della finestra secondo quello del sistema operativo */
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored){}
            setTitle("Sudoku"); /* Titolo finestra */
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
            ListenerHelpMenu listenerHelpMenu = new ListenerHelpMenu();
            ListenerButton listenerButton = new ListenerButton();
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
             * Menù "Help" e relative voci
             * */
            JMenu helpMenu = new JMenu("Help");
            menuBar.add(helpMenu);
            help = new JMenuItem("Help");
            help.addActionListener(listenerHelpMenu);
            about = new JMenuItem("About");
            about.addActionListener(listenerHelpMenu);
            helpMenu.add(help);
            helpMenu.add(about);
            /*
             * Pannelli e componenti aggiuntivi:
             * Pannello generale
             * */
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 2));
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            /*
             * Pannello sudoku
             * */
            JPanel panelSudoku = new JPanel();
            panelSudoku.setLayout(new GridLayout(9, 9));
            for(int i = 0; i < SIZE; i++)
                for(int j = 0; j < SIZE; j++) {
                    JTextField cella = new JTextField();
                    cella.setHorizontalAlignment(JTextField.CENTER);
                    iniziale[i][j] = cella;
                    panelSudoku.add(iniziale[i][j]);
                }
            /*
             * Pannello bottoni
             * */
            JPanel panelBottoni = new JPanel();
            panelBottoni.setLayout(new FlowLayout());
            panelBottoni.setSize(WIDTH, 20);
            previous = new JButton("Previous");
            previous.addActionListener(listenerButton);
            next = new JButton("Next");
            next.addActionListener(listenerButton);
            risolvi = new JButton("Risolvi");
            risolvi.addActionListener(listenerButton);
            pulisci = new JButton("Pulisci");
            pulisci.addActionListener(listenerButton);
            panelBottoni.add(previous, BorderLayout.WEST);
            panelBottoni.add(next, BorderLayout.EAST);
            panelBottoni.add(risolvi, BorderLayout.SOUTH);
            panelBottoni.add(pulisci, BorderLayout.SOUTH);
            /*
            * Pannello dati
            * */
            panelDati = new JPanel();
            panelDati.setLayout(new GridLayout(3, 1));
            /*
            * Pannello contenente dati e bottoni
            * */
            JPanel panelMix = new JPanel();
            panelMix.setLayout(new GridLayout(2, 1));
            panelMix.add(panelBottoni, BorderLayout.WEST);
            panelMix.add(panelDati);
            /*
             * Aggiunta al pannello generale di tutti i componenti
             * */
            panel.add(panelSudoku);
            panel.add(panelMix);
            add(panel);
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
        private class ListenerFileMenu implements ActionListener {
            /*
             * Listener per menù "File" della GUI, nel quale sono presenti "Apri, Salva, Salva con nome, esci"
             * */
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * Utente seleziona esci
                 * */
                if(e.getSource() == esci){
                    if(consensoUscita()) System.exit(0);
                }
                /*
                 * Utente seleziona salva
                 * */
                else if(e.getSource() == salva){
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
                            setTitle(getTitle() + " ~ " + fileSalvataggio.getAbsolutePath());
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Nessun salvataggio!");
                    }catch( Exception exc ){
                        exc.printStackTrace();
                    }
                }
                /*
                 * Utente seleziona salva con nome
                 * */
                else if(e.getSource() == salvaConNome){
                    JFileChooser chooser=new JFileChooser();
                    try{
                        if( chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION )
                            fileSalvataggio = chooser.getSelectedFile();
                        if( fileSalvataggio != null ){
                            save( fileSalvataggio.getAbsolutePath() );
                            setTitle(getTitle() + " ~ " + fileSalvataggio.getAbsolutePath());
                        }
                        else
                            JOptionPane.showMessageDialog(null,"Nessun salvataggio!");
                    }catch( Exception exc ){
                        exc.printStackTrace();
                    }
                }
                /*
                 * Utente seleziona apri
                 * */
                else if(e.getSource() == apri){
                    JFileChooser chooser = new JFileChooser();
                    try {
                        if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
                            if(!chooser.getSelectedFile().exists())
                                JOptionPane.showMessageDialog(null, "File inesistente!", "Error", JOptionPane.ERROR_MESSAGE);
                            else{
                                fileSalvataggio = chooser.getSelectedFile();
                                try{
                                    load(fileSalvataggio.getAbsolutePath());
                                    setTitle(getTitle() + " ~ " + fileSalvataggio.getAbsolutePath());
                                } catch (IOException ioe){
                                    JOptionPane.showMessageDialog(null, "Impossibile aprire. File malformato!", "Errore", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        else
                            JOptionPane.showMessageDialog(null,"Nessuna apertura!");
                    } catch(Exception exc){
                        exc.printStackTrace();
                    }
                }
            }
        }
        private class ListenerHelpMenu implements ActionListener{
            /*
             * Listener menù "Help"
             * */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == help)
                    JOptionPane.showMessageDialog(null, "Per iniziare:\n1. Inserire le celle bloccate\n" +
                            "2. Cliccare sul pulsante \"Risolvi\"\n" +
                            "3. Navigare tra le soluzioni (se maggiori di una) con gli appositi pulsanti\n" +
                            "4. Cliccare sul pulsante \"Pulisci\" pre inserire un nuovo pattern\n", "Help", JOptionPane.INFORMATION_MESSAGE);
                else if(e.getSource() == about){
                    JOptionPane.showMessageDialog(null, "Sudoku solver", "About", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
        private class ListenerButton implements ActionListener{
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                * Utente clicca risolvi
                * */
                if(e.getSource() == risolvi){
                    for(int i = 0; i < SIZE; i++)
                        for(int j = 0; j < SIZE; j++)
                            if(iniziale[i][j].getText().equals(""))
                                impostata[i][j] = 0;
                            else if(iniziale[i][j].getText().matches("[1-9]")) {
                                impostata[i][j] = Integer.parseInt(iniziale[i][j].getText());
                                numeriInseriti++;
                            }
                    Sudoku s = new Sudoku(impostata);
                    s.risolvi();
                    JLabel label = new JLabel("Numeri inseriti: " + numeriInseriti);
                    label.setFont(new Font("", Font.PLAIN, 14));
                    JLabel label1 = new JLabel("Numero soluzioni: " + soluzioni.size());
                    label1.setFont(new Font("", Font.PLAIN, 14));
                    JLabel label2 = new JLabel("Difficolta': " + calcoloDifficolta());
                    label2.setFont(new Font("", Font.PLAIN, 14));
                    panelDati.add(label);
                    panelDati.add(label1);
                    panelDati.add(label2);
                    panelDati.setBorder(BorderFactory.createEmptyBorder(0, 60, 40, 0));
                    risolvi.setEnabled(false);
                    displaySolution(soluzioni.get(0));
                    Finestra.this.validate();
                }
                /*
                 * Utente clicca previous
                 * */
                else if(e.getSource() == previous){
                    try{
                        if(indiceSoluzione == 0) throw new Exception();
                        indiceSoluzione--;
                        displaySolution(soluzioni.get(indiceSoluzione));
                        Finestra.this.repaint();
                        Finestra.this.validate();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Nessuna soluzione precedente", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                /*
                 * Utente clicca next
                 * */
                else if(e.getSource() == next){
                    try{
                        if(indiceSoluzione == soluzioni.size() - 1) throw new Exception();
                        indiceSoluzione++;
                        displaySolution(soluzioni.get(indiceSoluzione));
                        Finestra.this.repaint();
                        Finestra.this.validate();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Nessuna soluzione successiva", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                /*
                 * Utente clicca pulisci
                 * */
                else if(e.getSource() == pulisci){
                    for(JTextField[] riga: iniziale)
                        for(JTextField cella: riga)
                            cella.setText("");
                    numeriInseriti = 0; indiceSoluzione = 0;
                    soluzioni.clear();
                    panelDati.removeAll();
                    risolvi.setEnabled(true);
                    Finestra.this.repaint();
                    Finestra.this.validate();
                }
            }
        }
        private static boolean consensoUscita(){
            /*
             * Popup per richiesta del consenso di uscita dal programma
             * */
            int option = JOptionPane.showConfirmDialog(
                    null, "Uscendo si perderanno tutti i dati!\n\nContinuare?", "Esci",
                    JOptionPane.YES_NO_OPTION);
            return option == JOptionPane.YES_OPTION;
        }
        private void save(String s){
            /*
             * Salvataggio su file
             * */
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(s));
                for (int[] riga : impostata) {
                    for (int e : riga)
                        pw.print(e + " ");
                    pw.println();
                }
                pw.println();
                pw.close();
            } catch (IOException ioe){
                JOptionPane.showMessageDialog(null, "Errore lettura file", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        private void load(String s) throws IOException{
            /*
             * Lettura sudoku da file
             * */
            int row = 0;
            String riga;
            BufferedReader br = new BufferedReader(new FileReader(s));
            while(br.ready())
                try{
                    int col = 0;
                    riga = br.readLine();
                    for(char c: riga.replaceAll(" ", "").toCharArray()){
                        int numero = Integer.parseInt("" + c);
                        if(numero < 0 || numero > 9) throw new InputMismatchException();
                        if(numero != 0)
                            iniziale[row][col].setText("" + numero);
                        col++;
                    }
                    row++;
                } catch (InputMismatchException ime){
                    JOptionPane.showMessageDialog(null, "Errore contenuto file, un numero non e' compreso tra 0 e 9 ", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (Exception e){
                    JOptionPane.showMessageDialog(null, "Errore lettura file" + e, "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        }
        private String calcoloDifficolta(){
            /*
             * Metodo molto semplice per il calcolo della difficoltà, basato solamente sui numeri inseriti nella matrice bloccata
             * */
            if(numeriInseriti > 55) return "FACILE";
            if(numeriInseriti < 25) return "DIFFICILE";
            return "MEDIO";
        }
        private void displaySolution(int[][] mat){
            /*
             * Metodo per la visualizzazione delle soluzioni
             * */
            for(int i = 0; i < SIZE; i++)
                for(int j = 0; j < SIZE; j++)
                    iniziale[i][j].setText("" + mat[i][j]);
        }
    }
}
