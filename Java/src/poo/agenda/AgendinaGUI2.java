package poo.agenda;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;

class FrameGUI extends JFrame{
	 private File fileDiSalvataggio=null;
	 private JMenuItem apri, salva, salvaConNome, esci, about,
	 				   aggiungiContatto, rimuoviContatto,
	 				   numeroNominativi, svuota,
	 				   telefonoDi, personaDi, elenco;
	 private String titolo="Agenda GUI";
	 private String impl=" Map ";
	 private Agenda Agenda=null;

	 private enum Tipo{ AgendaArrayList, AgendaLinkedList, 
		                AgendaSet, AgendaMap };
	 private Tipo tipo=Tipo.AgendaMap; //default

	 private int capacita=50; //default per array list
	 private FrameAggiungiContatto fAN=null;
	 private String cognome, nome, prefisso, telefono;
	 private boolean cognomeOk, nomeOk, prefissoOk, telefonoOk;
	 private FrameAL fAL=null;
	 private FrameRimuoviContatto fRN=null;
	 private FrameTelefonoDi fTD=null;
	 private FramePersonaDi fPD=null;
	 
	 public FrameGUI(){
		 setTitle(titolo+impl);
		 setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
         addWindowListener( new WindowAdapter() {
	        public void windowClosing(WindowEvent e){
	       	 if( consensoUscita() ) System.exit(0);
	        }
	     } );

         RadioButtonFrame rBF=new RadioButtonFrame();
         rBF.setVisible(true);
         
         AscoltatoreEventiAzione listener=new AscoltatoreEventiAzione();
         
         //creazione barra dei menu'
         JMenuBar menuBar=new JMenuBar();
         this.setJMenuBar( menuBar );
         
         //creazione file menu
         JMenu fileMenu=new JMenu("File");
         menuBar.add(fileMenu);
         //creazione voci del menu File
         apri=new JMenuItem("Apri");
         apri.addActionListener(listener);
         fileMenu.add(apri);
         salva=new JMenuItem("Salva");
         salva.addActionListener(listener);
         fileMenu.add(salva);
         salvaConNome=new JMenuItem("Salva con nome");
         salvaConNome.addActionListener(listener);
         fileMenu.add(salvaConNome);
         fileMenu.addSeparator();
         esci=new JMenuItem("Esci");
         esci.addActionListener(listener);
         fileMenu.add(esci);

         //creazione menu Comandi
         JMenu commandMenu=new JMenu("Comandi");
         menuBar.add(commandMenu);
         //creazione voci del menu Gestione
         aggiungiContatto=new JMenuItem("Aggiungi Contatto");
         aggiungiContatto.addActionListener(listener);
         commandMenu.add(aggiungiContatto);
         rimuoviContatto=new JMenuItem("Rimuovi Contatto");
         rimuoviContatto.addActionListener(listener);
         commandMenu.add(rimuoviContatto);
         numeroNominativi=new JMenuItem("Numero nominativi");
         numeroNominativi.addActionListener(listener);
         commandMenu.add(numeroNominativi);
         svuota=new JMenuItem("Svuota Agenda");
         svuota.addActionListener(listener);
         commandMenu.add(svuota);         
         commandMenu.addSeparator();
         telefonoDi=new JMenuItem("Telefono di");
         telefonoDi.addActionListener(listener);
         commandMenu.add(telefonoDi);
         personaDi=new JMenuItem("Persona di");
         personaDi.addActionListener(listener);
         commandMenu.add(personaDi);   
         elenco=new JMenuItem("Elenco");
         elenco.addActionListener(listener);
         commandMenu.add(elenco);
         
         //creazione menu Help
         JMenu helpMenu=new JMenu("Help");
         menuBar.add(helpMenu);
         about=new JMenuItem("About");
         about.addActionListener(listener);
         helpMenu.add(about);
         //menuIniziale();
         
         Toolkit kit=Toolkit.getDefaultToolkit();
         Dimension screenSize=kit.getScreenSize();
         int larghezzaScreen=screenSize.width;
         int altezzaScreen=screenSize.height;
         pack();
         setSize(larghezzaScreen/2, altezzaScreen/2);
         setLocationByPlatform(true);
	 }//costruttore

	 private class RadioButtonFrame extends JFrame implements ActionListener{
		 private JLabel etichetta;
		 private ButtonGroup gruppo;
		 private JPanel pannello;
		 private JRadioButton buttonAL, buttonLL, buttonSet, buttonMap;
		 private Tipo tipo=Tipo.AgendaMap;
		 public RadioButtonFrame(){
			 setTitle("Programma Agenda Telefonica");
			 setLocationByPlatform(true);
			 setSize(430,200);
			 setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );			 
	         
			 addWindowListener( new WindowAdapter() {
	 	        public void windowClosing(WindowEvent e){
	 	        	 if( tipo==Tipo.AgendaArrayList ) 
	 	        		 Agenda=new AgendaAL(capacita);
	 	        	 else if( tipo==Tipo.AgendaLinkedList ) 
	 	        		 Agenda=new AgendaLL();
	 	        	 else if( tipo==Tipo.AgendaSet ) 
	 	        		 Agenda=new AgendaSet();
	 	        	 else 
	 	        		 Agenda=new AgendaMap();
	 				 FrameGUI.this.setTitle(titolo+impl);
	 				 FrameGUI.this.setVisible(true);
	 				 dispose();	 	       	 
	 	        }
	 	     } );			 
			
			 etichetta=new JLabel("Scelta Tipo Implementazione dell'Agenda");
			 etichetta.setFont(new Font("Serif", Font.PLAIN, 24));
			 add(etichetta, BorderLayout.CENTER);
			 
			 pannello=new JPanel();
			 gruppo=new ButtonGroup();
			 buttonAL=new JRadioButton("ArrayList",false);
			 buttonLL=new JRadioButton("LinkedList",false);
			 buttonSet=new JRadioButton("Set",false);
			 buttonMap=new JRadioButton("Map",true);
			 buttonAL.addActionListener(this);
			 buttonLL.addActionListener(this);
			 buttonSet.addActionListener(this);
			 buttonMap.addActionListener(this);	
			 gruppo.add(buttonAL);
			 gruppo.add(buttonLL);
			 gruppo.add(buttonSet);
			 gruppo.add(buttonMap);
			 pannello.add(buttonAL);
			 pannello.add(buttonLL);
			 pannello.add(buttonSet);
			 pannello.add(buttonMap);
			 add(pannello, BorderLayout.SOUTH);
		 }//RadioButtonFrame
		 
		 public void actionPerformed(ActionEvent e){
			 if( e.getSource()==buttonAL ){
				 impl=" AL ";
				 tipo=Tipo.AgendaArrayList;
				 FrameGUI.this.setTitle(titolo+impl); 
				 if( fAL==null ) fAL=new FrameAL();
	  			 fAL.setVisible(true);
			 }
			 else if( e.getSource()==buttonLL ){
				 impl=" LL ";
				 tipo=Tipo.AgendaLinkedList;
				 FrameGUI.this.setTitle(titolo+impl); 			 
			 }
			 else if( e.getSource()==buttonSet ){
				 impl=" Set ";
				 tipo=Tipo.AgendaSet;
				 FrameGUI.this.setTitle(titolo+impl); 					 
			 }
			 else{//e.getSource()==buttonMap
				 impl=" Map ";
				 tipo=Tipo.AgendaMap;
				 FrameGUI.this.setTitle(titolo+impl); 				 
			 }
		 }//actionPerformed
	 }//RadioButtonFrame
	 
	 private class FrameAL extends JFrame implements ActionListener{
    	 private JTextField capacita;
    	 private JButton ok;
    	 public FrameAL(){
    		 setTitle("Capacita' Array List");
    		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	     JPanel p=new JPanel();
    	     p.setLayout( new FlowLayout() ); //ridondante
    	     p.add( new JLabel("Capacita'", JLabel.RIGHT) );
    	     p.add( capacita=new JTextField(""+FrameGUI.this.capacita,12) );
    	     p.add( ok=new JButton("OK") );
    	     add(p);	 
    		 capacita.addActionListener(this);
    		 ok.addActionListener(this);
    		 setLocationByPlatform(true);
    		 setSize(400,100);
    	 }
    	 public void actionPerformed( ActionEvent e ){
    		 if( e.getSource()==capacita ){
    			 FrameGUI.this.capacita=Integer.parseInt(capacita.getText() );
    		 }
    		 else if( e.getSource()==ok ){   		 			 
    			 this.dispose();
    		 }
    	 }//actionPerformed
     }//FrameAL 
	 
	 private class FrameAggiungiContatto extends JFrame implements ActionListener{
    	 private JTextField cognome, nome, prefisso, telefono;
    	 private JButton ok;
    	 public FrameAggiungiContatto(){
    		 setTitle("Aggiunta Contatto");
    		 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	     addWindowListener( new WindowAdapter() {
    		        public void windowClosing(WindowEvent e){
    		       	 if( verificaUscita() ){
        				 Agenda.aggiungi( new Contatto( 
        						 FrameGUI.this.cognome.toUpperCase(),
        						 FrameGUI.this.nome.toUpperCase(),
        						 FrameGUI.this.prefisso,
        						 FrameGUI.this.telefono ));    		       		 
    		       	 }
		       		 cognome.setText("");
    				 nome.setText("");
    				 prefisso.setText("");
    				 telefono.setText("");
    				 cognomeOk=false;
    				 nomeOk=false;
    				 prefissoOk=false;
    				 telefonoOk=false;    		       	 
		       		 FrameAggiungiContatto.this.setVisible(false);
    		        }
    		     } );
    	     cognomeOk=false;
    	     nomeOk=false;
    	     prefissoOk=false;
    	     telefonoOk=false;
    	     JPanel p=new JPanel();
    	     p.setLayout( new GridLayout(5,1) );
    	     JPanel p1=new JPanel();
    	     p1.setLayout(new GridLayout(1,2));
    	     p1.add( new JLabel("Cognome", JLabel.RIGHT) );
    	     p1.add( cognome=new JTextField("",12) );
    	     p.add(p1);
    	     JPanel p2=new JPanel();
    	     p2.setLayout( new GridLayout(1,2) );
       	     p2.add( new JLabel("Nome", JLabel.RIGHT) );
    	     p2.add( nome=new JTextField("",12) ); 
    	     p.add(p2);
    	     JPanel p3=new JPanel();
    	     p3.setLayout( new GridLayout(1,2) );
       	     p3.add( new JLabel("Prefisso", JLabel.RIGHT) );
    	     p3.add( prefisso=new JTextField("",12) );    
    	     p.add(p3);
    	     JPanel p4=new JPanel();
    	     p4.setLayout( new GridLayout(1,2));
       	     p4.add( new JLabel("Telefono", JLabel.RIGHT) );
    	     p4.add( telefono=new JTextField("",12) );  
    	     p.add(p4);
    	     JPanel p5=new JPanel();
    	     p5.add( ok=new JButton("OK") );
    	     p.add(p5);
    	     add(p, BorderLayout.NORTH);	 
    	     pack();   
    		 cognome.addActionListener(this);
    		 nome.addActionListener(this);
    		 prefisso.addActionListener(this);
    		 telefono.addActionListener(this);
    		 ok.addActionListener(this); 		 
    		 
    		 setLocationByPlatform(true);
    		 setSize(400,210);
    	 }
    	 public void actionPerformed( ActionEvent e ){
    		 if( e.getSource()==cognome ){
    			 FrameGUI.this.cognome=cognome.getText();
    			 FrameGUI.this.cognomeOk=true;
     		 }
    		 if( e.getSource()==nome ){
    			 FrameGUI.this.nome=nome.getText();  			 
    			 FrameGUI.this.nomeOk=true;
     		 }
    		 if( e.getSource()==prefisso ){
    			 FrameGUI.this.prefisso=prefisso.getText(); 
    			 FrameGUI.this.prefissoOk=true;
     		 }  
    		 if( e.getSource()==telefono ){
    			 FrameGUI.this.telefono=telefono.getText();
    			 FrameGUI.this.telefonoOk=true;
     		 }    		 
    		 else if( e.getSource()==ok ){
    			 if( verificaUscita() ){
    				 Agenda.aggiungi( new Contatto( 
    						 FrameGUI.this.cognome.toUpperCase(),
    						 FrameGUI.this.nome.toUpperCase(),
    						 FrameGUI.this.prefisso,
    						 FrameGUI.this.telefono ));
      				 this.setVisible(false);
    				 cognomeOk=false;
    				 nomeOk=false;
    				 prefissoOk=false;
    				 telefonoOk=false;
    				 cognome.setText("");
    				 nome.setText("");
    				 prefisso.setText("");
    				 telefono.setText("");
     			 }  			 
    		 }
    	 }//actionPerformed
    	 private boolean verificaUscita(){
    		 return cognomeOk && nomeOk && prefissoOk && telefonoOk;
    	 }//verificaUscita
     }//FrameAggiungiContatto

	 private class FrameRimuoviContatto extends JFrame implements ActionListener{
    	 private JTextField cognome, nome;
    	 private JButton ok;
    	 public FrameRimuoviContatto(){
    		 setTitle("Rimozione Contatto");
    		 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	     addWindowListener( new WindowAdapter() {
    		    public void windowClosing(WindowEvent e){
    		       	 if( verificaUscita() ){
        				 Agenda.rimuovi( new Contatto( 
        						 FrameGUI.this.cognome.toUpperCase(),
        						 FrameGUI.this.nome.toUpperCase(),
        						 "", "" ));    		       		 
    		       	 }
       				 cognomeOk=false;
    				 nomeOk=false;
    				 cognome.setText("");
    				 nome.setText("");    		        	
		       		 FrameRimuoviContatto.this.setVisible(false);    		       	 
    		        }
    		     } );
    	     cognomeOk=false;
    	     nomeOk=false;
    	     prefissoOk=false;
    	     telefonoOk=false;
    	     JPanel p=new JPanel();
    	     p.setLayout( new FlowLayout() ); //ridondante
    	     p.add( new JLabel("Cognome", JLabel.RIGHT) );
    	     p.add( cognome=new JTextField("",12) );
       	     p.add( new JLabel("Nome", JLabel.RIGHT) );
    	     p.add( nome=new JTextField("",12) );  
     
    	     p.add( ok=new JButton("OK") );
    	     add(p);	 
    		 cognome.addActionListener(this);
    		 nome.addActionListener(this);

    		 ok.addActionListener(this); 		 
    		 
    		 setLocationByPlatform(true);
    		 setSize(400,150);
    	 }
    	 public void actionPerformed( ActionEvent e ){
    		 if( e.getSource()==cognome ){
    			 FrameGUI.this.cognome=cognome.getText();
    			 FrameGUI.this.cognomeOk=true;
     		 }
    		 if( e.getSource()==nome ){
    			 FrameGUI.this.nome=nome.getText();  			 
    			 FrameGUI.this.nomeOk=true;
     		 }
    		 else if( e.getSource()==ok ){
    			 if( verificaUscita() ){
    				 Agenda.rimuovi( new Contatto( 
    						 FrameGUI.this.cognome.toUpperCase(),
    						 FrameGUI.this.nome.toUpperCase(), "","" ));
    				 this.setVisible(false);
    				 cognome.setText("");
    				 nome.setText("");
    			 }
    		 }
    	 }//actionPerformed
    	 private boolean verificaUscita(){
    		 return cognomeOk && nomeOk;
    	 }//verificaUscita
     }//FrameRimuoviContatto	 

	 private class FrameTelefonoDi extends JFrame implements ActionListener{
    	 private JTextField cognome, nome;
    	 private JButton ok;
    	 public FrameTelefonoDi(){
    		 setTitle("Ricerca telefono di un Contatto");
    		 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	     addWindowListener( new WindowAdapter() {
    		        public void windowClosing(WindowEvent e){
    		       	 if( verificaUscita() ){
        				 Contatto n=Agenda.cerca( 
        					new Contatto( 
        						FrameGUI.this.cognome.toUpperCase(),
        						FrameGUI.this.nome.toUpperCase(),
        						 "", "" ));    		       		 
        				 cognome.setText("");
        				 nome.setText("");
           				 if( n!=null )
        					 JOptionPane.showMessageDialog(null, "Telefono della persona cercata: "+
        						 n.getPrefisso()+"-"+n.getTelefono() );
        				 else
        					 JOptionPane.showMessageDialog(null, "Contatto inesistente!" );    			
    		       	 }
		       		 FrameTelefonoDi.this.setVisible(false);
    		        }
    		     } );
    	     cognomeOk=false;
    	     nomeOk=false;
    	     JPanel p=new JPanel();
    	     p.setLayout( new FlowLayout() ); //ridondante
    	     p.add( new JLabel("Cognome", JLabel.RIGHT) );
    	     p.add( cognome=new JTextField("",12) );
       	     p.add( new JLabel("Nome", JLabel.RIGHT) );
    	     p.add( nome=new JTextField("",12) );  
     
    	     p.add( ok=new JButton("OK") );
    	     add(p);	 
    		 cognome.addActionListener(this);
    		 nome.addActionListener(this);

    		 ok.addActionListener(this); 		 
    		 
    		 setLocationByPlatform(true);
    		 setSize(400,150);
    	 }
    	 public void actionPerformed( ActionEvent e ){
    		 if( e.getSource()==cognome ){
    			 FrameGUI.this.cognome=cognome.getText();
    			 FrameGUI.this.cognomeOk=true;    
     		 }
    		 if( e.getSource()==nome ){
    			 FrameGUI.this.nome=nome.getText();  			 
    			 FrameGUI.this.nomeOk=true;
     		 }
    		 else if( e.getSource()==ok ){
    			 if( verificaUscita() ){
    				 Contatto n=
    				 Agenda.cerca( new Contatto( 
    						 FrameGUI.this.cognome.toUpperCase(),
    						 FrameGUI.this.nome.toUpperCase(), "","" ));
    				 this.setVisible(false);
    				 cognome.setText("");
    				 nome.setText("");
    				 if( n!=null )
    					 JOptionPane.showMessageDialog(null, "Telefono della persona cercata: "+
    						 n.getPrefisso()+"-"+n.getTelefono() );
    				 else
    					 JOptionPane.showMessageDialog(null, "Contatto inesistente!" );    					 
    			 }
    		 }
    	 }//actionPerformed
    	 private boolean verificaUscita(){
    		 return cognomeOk && nomeOk;
    	 }//verificaUscita
     }//FrameTelefonoDi		 

	 private class FramePersonaDi extends JFrame implements ActionListener{
    	 private JTextField prefisso, telefono;
    	 private JButton ok;
    	 public FramePersonaDi(){
    		 setTitle("Ricerca persona di prefisso e telefono");
    		 setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    	     addWindowListener( new WindowAdapter() {
    		        public void windowClosing(WindowEvent e){
    		       	 if( verificaUscita() ){
        				 Contatto n=Agenda.cerca(  
        						 FrameGUI.this.prefisso,
        						 FrameGUI.this.telefono );    		       		 
        				 prefisso.setText("");
        				 telefono.setText("");
           				 if( n!=null )
        					 JOptionPane.showMessageDialog(null, "Persona trovata: "+
        						 n.getCognome().toUpperCase()+" "+n.getNome().toUpperCase() );
        				 else
        					 JOptionPane.showMessageDialog(null, "Contatto inesistente!" );    			
    		       	 }
		       		 FramePersonaDi.this.setVisible(false);
    		        }
    		     } );
    	     prefissoOk=false;
    	     telefonoOk=false;
    	     JPanel p=new JPanel();
    	     p.setLayout( new FlowLayout() ); //ridondante
    	     p.add( new JLabel("Prefisso", JLabel.RIGHT) );
    	     p.add( prefisso=new JTextField("",12) );
       	     p.add( new JLabel("Telefono", JLabel.RIGHT) );
    	     p.add( telefono=new JTextField("",12) );  
     
    	     p.add( ok=new JButton("OK") );
    	     add(p);	 
    		 prefisso.addActionListener(this);
    		 telefono.addActionListener(this);

    		 ok.addActionListener(this); 		 
    		 
    		 setLocationByPlatform(true);
    		 setSize(400,150);
    	 }
    	 public void actionPerformed( ActionEvent e ){
    		 if( e.getSource()==prefisso ){
    			 FrameGUI.this.prefisso=prefisso.getText();
    			 FrameGUI.this.prefissoOk=true;
     		 }
    		 if( e.getSource()==telefono ){
    			 FrameGUI.this.telefono=telefono.getText();  			 
    			 FrameGUI.this.telefonoOk=true;
     		 }
    		 else if( e.getSource()==ok ){
    			 if( verificaUscita() ){
    				 Contatto n=
    				 Agenda.cerca(
    						 FrameGUI.this.prefisso,
    						 FrameGUI.this.telefono );
    				 this.setVisible(false);
    				 prefisso.setText("");
    				 telefono.setText("");
    				 if( n!=null )
    					 JOptionPane.showMessageDialog(null, "Persona trovata: "+
    						 n.getCognome().toUpperCase()+" "+n.getNome().toUpperCase() );
    				 else
    					 JOptionPane.showMessageDialog(null, "Contatto inesistente!" );    					 
    			 }
    		 }
    	 }//actionPerformed
    	 private boolean verificaUscita(){
    		 return prefissoOk && telefonoOk;
    	 }//verificaUscita
     }//FramePersonaDi	 
	 
	 private class FrameElenco extends JFrame{
    	 private JTextArea area;
    	 public FrameElenco(){
    		 setTitle("Elenco Nominativi");
    		 setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	     JPanel p=new JPanel();
    	     p.setLayout( new FlowLayout() ); //ridondante
    	     JTextArea area=new JTextArea(10, 25);
    	     area.setEditable(false);
    	 	 JScrollPane sp=new JScrollPane( area );
    	     p.add( sp ); 
    	     add(p);
    	     if( Agenda.size()!=0 )
    	    	 area.append( Agenda.toString() );
    	     else
    	    	 area.append("Agenda vuota!");
    	     setLocationByPlatform(true);
    		 setSize(400,150);
    	 }
     }//FrameElenco
	 
     private boolean consensoUscita(){
	   int option=JOptionPane.showConfirmDialog(
			   null, "Continuare ?", "Uscendo si perderanno tutti i dati!",
			   JOptionPane.YES_NO_OPTION);
	   return option==JOptionPane.YES_OPTION;
	 }//consensoUscita
	    
     private class AscoltatoreEventiAzione implements ActionListener{
  	   public void actionPerformed(ActionEvent e){
  		   if( e.getSource()==esci ){
  			   if( consensoUscita() ) System.exit(0);
  		   }
   		   else if( e.getSource()==salva ){
  			   //file chooser
  			   JFileChooser chooser=new JFileChooser();
  			   try{
  				   if( fileDiSalvataggio!=null ){
  					   int ans=JOptionPane.showConfirmDialog(null,"Sovrascrivere "+fileDiSalvataggio.getAbsolutePath()+" ?");
					   if( ans==0 /*SI*/)
						   Agenda.salva( fileDiSalvataggio.getAbsolutePath() );
					   else
						   JOptionPane.showMessageDialog(null,"Nessun salvataggio!");
					   return;
				   }
  				   if( chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION ){
  					   fileDiSalvataggio=chooser.getSelectedFile();
  					   FrameGUI.this.setTitle(titolo+impl+" - "+fileDiSalvataggio.getName());
  				   }
  				   if( fileDiSalvataggio!=null ){
  					   Agenda.salva( fileDiSalvataggio.getAbsolutePath() );
  				   }
  				   else
  					   JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
  			   }catch( Exception exc ){
  				   exc.printStackTrace();
  			   }
  		   }
  		   else if( e.getSource()==salvaConNome ){
  			   //file chooser
  			   JFileChooser chooser=new JFileChooser();
  			   try{
  				   if( chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION ){
  						   fileDiSalvataggio=chooser.getSelectedFile();
  						   FrameGUI.this.setTitle(titolo+impl+" - "+fileDiSalvataggio.getName());
  					   }
  				   if( fileDiSalvataggio!=null ){
  					   Agenda.salva( fileDiSalvataggio.getAbsolutePath() );
  				   }
  				   else
  					   JOptionPane.showMessageDialog(null,"Nessun Salvataggio!");
  			   }catch( Exception exc ){
  				   exc.printStackTrace();
  			   }  			   
  		   }
  		   else if( e.getSource()==apri ){
  			   //file chooser
  			   JFileChooser chooser=new JFileChooser();
  			   try{
  				   if( chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION ){
  					   if( !chooser.getSelectedFile().exists() ){
  						   JOptionPane.showMessageDialog(null,"File inesistente!"); 
  					   }
  					   else{	
  						   fileDiSalvataggio=chooser.getSelectedFile();
  						   FrameGUI.this.setTitle(titolo+impl+" - "+fileDiSalvataggio.getName());
  						   try{
  							   Agenda.ripristina( fileDiSalvataggio.getAbsolutePath() );
  						   }catch(IOException ioe){
  							   JOptionPane.showMessageDialog(null,"Fallimento apertura. File malformato!");
  						   }
  					   }
  				   }
  				   else
  					   JOptionPane.showMessageDialog(null,"Nessuna apertura!");
  			   }catch( Exception exc ){
  				   exc.printStackTrace();
  			   }
  		   }
  		   else if( e.getSource()==aggiungiContatto ){
  			   if( fAN==null ) fAN=new FrameAggiungiContatto();
  			   fAN.setVisible(true);
  		   }
  		   else if( e.getSource()==rimuoviContatto ){
  			   if( fRN==null ) fRN=new FrameRimuoviContatto();
  			   fRN.setVisible(true);
  		   }
  		   else if( e.getSource()==telefonoDi ){
  			   if( fTD==null ) fTD=new FrameTelefonoDi();
  			   fTD.setVisible(true);
  		   }
  		   else if( e.getSource()==personaDi ){
  			   if( fPD==null ) fPD=new FramePersonaDi();
  			   fPD.setVisible(true);  			   
  		   }
  		   else if( e.getSource()==numeroNominativi ){
  			   JOptionPane.showMessageDialog(null, "Numero nominativi in agenda: "+Agenda.size());
  		   }  		   
  		   else if( e.getSource()==svuota ){
  			   Agenda.svuota();
  		   }
  		   else if( e.getSource()==elenco ){
  			   FrameElenco fE=new FrameElenco();
  			   fE.setVisible(true);
  		   }
  		   else if( e.getSource()==about ){
  			   JOptionPane.showMessageDialog( null,
  					   "Programma di Gestione di un'Agenda Telefonica\n",
  					   "About", JOptionPane.PLAIN_MESSAGE );
  		   }
  	   }
     }//AscoltatoreEventiAzione
   
}//FrameGUI

public class AgendaGUI2 {
	public static void main( String []args ){
		//per generalita', l'inizializzazione del frame
		//e' trasferita allo "Event Dispatch Thread" di Swing
		EventQueue.invokeLater( new Runnable(){
			public void run(){
				FrameGUI f=new FrameGUI();
			}
		});
	}//main
}//AgendaGUI
