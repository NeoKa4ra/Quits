import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

import javax.swing.*;


public class Quits implements Runnable{

   public void run(){
	   
	   
	   final JFrame fenetre = new JFrame();
	   Sauvegarde sauvegarde = null;
	   
	   
	   
        // Creation d'une fenetre 12
        fenetre.setTitle("QUITS");
        try{
        	sauvegarde = new Sauvegarde();
        }catch(IOException e) {
		e.printStackTrace();
        }
        
        
        Etats etat1= new Etats(1);
		Etats etat2= new Etats(2);	
        Moteur m = new Moteur();
        Plateau matrice = new Plateau();
        matrice.init_2_joueurs();
      
        PlateauGraphique plateau = new PlateauGraphique(matrice,m,etat1,etat2);
        plateau.init();
        
        JPanel panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		etat1.setPreferredSize(new Dimension(40,35));
		panel1.add(etat1,BorderLayout.NORTH);
		etat2.setPreferredSize(new Dimension(40,35));
		panel1.add(etat2,BorderLayout.SOUTH);
		panel1.add(plateau,BorderLayout.CENTER);
	
		
		
		Menu notremenu = new Menu(sauvegarde,plateau,m,etat1, etat2);
		Outils panel2 = new Outils(sauvegarde,plateau,etat1, etat2,m);
	

		
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
	
		pane.add(panel1,BorderLayout.CENTER);
		pane.add(panel2,BorderLayout.SOUTH);


		
		MyGlassPane glass = new MyGlassPane(plateau);
    	glass.addMouseMotionListener(new EcouteurDeMouvement(glass));
    	glass.addMouseListener(new EcouteurRetransmetteur(glass,
    			fenetre.getContentPane(),plateau));
    	fenetre.setGlassPane(glass);
    	glass.setVisible(true);
		
		
	   JSplitPane hpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, notremenu, pane);
	   fenetre.add(hpane);

        // Un clic sur le bouton de fermeture clos l'application
	   fenetre.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
    
    
	   fenetre.addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
            	 
                   int reponse = JOptionPane.showConfirmDialog(null,
                                        "Etes-vous sûr de quitter l'application?",
                                        "Confirmation",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
                   
                   if (reponse==JOptionPane.YES_OPTION){
                	   fenetre.dispose();
                   }
             }
   	 });
   	 
	
        // On fixe la taille et on demarre
	   fenetre.setResizable(true);
	   fenetre.setLocation(0,0);
	   fenetre.setSize(900, 700);
	   fenetre.setVisible(true);
    }

public static void main (String [] args){
	 SwingUtilities.invokeLater(new Quits());
}


}


