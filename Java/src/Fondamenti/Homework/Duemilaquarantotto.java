package Fondamenti.Homework;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Duemilaquarantotto {
	private JFrame frame;
	private JTextField[][] matrice;
	private JButton[] bottoni;
	private JLabel messaggio;
	private String titoloFinestra;
	private String[] etichetta = {"Sopra", "Sotto", "Destra", "Sinistra"};
	private int numeroRighe, numeroColonne, numeroBottoni, punteggio = 0;

	public Duemilaquarantotto(){
		titoloFinestra = "2048";
		numeroRighe = 4;
		numeroColonne = 4;
		numeroBottoni = 4;
		
		inizializza();
	}

	private void bottonePremuto(int numeroBottone){
		switch (numeroBottone) {
			case 1:
				vaiSopra();
				break;
			case 2:
				vaiSotto();
				break;
			case 3:
				vaiDestra();
				break;
			case 4:
				vaiSinistra();
				break;
		}
	}

	private void setMessaggio(String m)	{
		messaggio.setText(m);
	}

	private void setEtichettaBottone(){
		for (int i = 0; i < numeroBottoni; i++)
			bottoni[i].setText(etichetta[i]);
	}

	private String[][] getmatrice()	{
		String[][] ret = new String[numeroRighe][numeroColonne];
		for(int i=0;i<numeroRighe;i++)
			for(int j=0;j<numeroColonne;j++)
				ret[i][j] = matrice[i][j].getText();
		return ret;
	}

	private void setmatrice(String[][] A){
		for(int i=0;i<numeroRighe;i++)
			for(int j=0;j<numeroColonne;j++)
				matrice[i][j].setText(A[i][j]);
	}

	private void bloccamatrice(){
		for(int i=0;i<numeroRighe;i++)
			for(int j=0;j<numeroColonne;j++)
				matrice[i][j].setEditable(false);
	}

	private void sbloccamatrice(){
		for(int i=0;i<numeroRighe;i++)
			for(int j=0;j<numeroColonne;j++)
				matrice[i][j].setEditable(true);;
	}
	
	private int[][] convertiInMatriceIntera(String[][] m){
		int nRighe=m.length;
		int nColonne=m[0].length;
		int[][] ret = new int[nRighe][nColonne];
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++)
				if(m[i][j].equals(""))
					ret[i][j]=0;
				else
					ret[i][j]=Integer.parseInt(m[i][j]);
		return ret;
	}

	private String[][] convertiDaMatriceIntera(int[][] m){
		int nRighe=m.length;
		int nColonne=m[0].length;
		String[][] ret = new String[nRighe][nColonne];
		for(int i=0;i<nRighe;i++)
			for(int j=0;j<nColonne;j++)
				ret[i][j]=""+m[i][j];
		return ret;
	}

	private void inizializza(){
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try	{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			System.out.println(e);
			System.exit(0);
		}
		frame.setVisible(true);
		frame.setBounds(745, 400, 430, 280);
		frame.setTitle(titoloFinestra);

		messaggio = new JLabel("");
		frame.getContentPane().add(messaggio);
		messaggio.setFont(new Font("",Font.BOLD,30));
		messaggio.setBounds(165, 40, 300, 30);
		messaggio.setHorizontalAlignment(JLabel.CENTER);

		bottoni=new JButton[numeroBottoni];
		ActionListener listener = new PressioneBottoni();
		for(int i=0;i<numeroBottoni;i++){
			JButton bottone=new JButton();
			bottone.setBounds(240, 100+30*i, 150, 30);
			bottoni[i]=bottone;
			bottone.addActionListener(listener);
			frame.getContentPane().add(bottone);
		}
		setEtichettaBottone();

		matrice = new JTextField[numeroRighe][numeroColonne];
		for(int i=0;i<numeroRighe;i++)
			for(int j=0;j<numeroColonne;j++){
				JTextField campoTesto = new JTextField("");
				frame.getContentPane().add(campoTesto);
				campoTesto.setBounds(20+50*j, 20+50*i, 50, 50);
				campoTesto.setHorizontalAlignment(JTextField.CENTER);
				matrice[i][j] = campoTesto;
			}
	}

	private class PressioneBottoni implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int numeroBottonePremuto = -1;
			for(int i=0;i<numeroBottoni;i++)
				if(e.getSource()==bottoni[i])
					numeroBottonePremuto = i + 1;
			bottonePremuto(numeroBottonePremuto);
		}
	}

	public void startGame(){
		generaInizio();
		setMessaggio(""+punteggio);
	}

	private void generaInizio(){
		int numeriIniziali = 4, inseriti = 0;
		while(inseriti < numeriIniziali)
			if(generaNuovoNumero())
				inseriti++;
	}

	private boolean generaNuovoNumero(){
		String[][] mat = getmatrice();
		int x, y;
		Random r = new Random();
		x = r.nextInt(4);
		y = r.nextInt(4);
		if (mat[x][y].equals("")) {
			mat[x][y] = "2";
			setmatrice(mat);
			return true;
		}
		return false;
 	}

	private void vaiSopra(){
		int[][] mat = convertiInMatriceIntera(getmatrice());
		for (int i = 0; i < numeroColonne; i++) {
			for(int j = numeroRighe; j > 1; j--)
				break;
		}
	}

	private void vaiSotto(){

	}

	private void vaiDestra(){

	}

	private void vaiSinistra(){

	}
}


