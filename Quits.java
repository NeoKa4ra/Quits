import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.swing.*;


public class Quits implements Runnable{

   public void run(){
	   
	   
	   final JFrame fenetre = new JFrame();
	   Sauvegarde sauvegarde = null;
	   
	   
        // Creation d'une fenetre
        fenetre.setTitle("QUITS");
        try{
        	sauvegarde = new Sauvegarde();
        }catch(IOException e) {
		e.printStackTrace();
        }
        Plateau matrice = new Plateau();
        matrice.init_2_joueurs();
        Donnees donnees = new Donnees(matrice);
        Menu notremenu = new Menu(sauvegarde,matrice);
        PlateauGraphique plateau = new PlateauGraphique(donnees);
        plateau.init();
		Etats panel1 = new Etats();
		Outils panel3 = new Outils(sauvegarde,matrice);
	

		
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		pane.add(panel1,BorderLayout.NORTH);
		pane.add(plateau,BorderLayout.CENTER);
		pane.add(panel3,BorderLayout.SOUTH);


		
		MyGlassPane glass = new MyGlassPane(donnees);
    	glass.addMouseMotionListener(new EcouteurDeMouvement(glass));
    	glass.addMouseListener(new EcouteurRetransmetteur(glass,
    			fenetre.getContentPane(), donnees));
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
	   fenetre.setResizable(false);
	   fenetre.setLocation(400,250);
	   fenetre.setSize(1100, 900);
	   fenetre.setVisible(true);
    }

public static void main (String [] args){
	 SwingUtilities.invokeLater(new Quits());
}


}


