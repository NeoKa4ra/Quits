import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javax.swing.*;


public class Quits extends JFrame {

    Sauvegarde sauvegarde;
	  
    Quits() {
	
        // Creation d'une fenetre
        setTitle("QUITS");
        try{
		sauvegarde = new Sauvegarde();
	}catch(IOException e) {
		e.printStackTrace();
	}
        
        Donnees donnees = new Donnees();
        Menu notremenu = new Menu(sauvegarde);
        PlateauGraphique plateau = new PlateauGraphique(donnees);
        plateau.init();
		Etats panel1 = new Etats();
		Outils panel3 = new Outils(sauvegarde);
	

		
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());
		pane.add(panel1,BorderLayout.NORTH);
		pane.add(plateau,BorderLayout.CENTER);
		pane.add(panel3,BorderLayout.SOUTH);


		
		MyGlassPane glass = new MyGlassPane(donnees);
    	glass.addMouseMotionListener(new EcouteurDeMouvement(glass));
    	glass.addMouseListener(new EcouteurRetransmetteur(glass,
                                         this.getContentPane(), donnees));
    	setGlassPane(glass);
    	glass.setVisible(true);
		
		
	   JSplitPane hpane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, notremenu, pane);
	   add(hpane);

        // Un clic sur le bouton de fermeture clos l'application
     setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
       
    
    
	 addWindowListener(new WindowAdapter(){
             public void windowClosing(WindowEvent e){
            	 
                   int reponse = JOptionPane.showConfirmDialog(null,
                                        "Etes-vous sûr de quitter l'application?",
                                        "Confirmation",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);
                   
                   if (reponse==JOptionPane.YES_OPTION){
                            dispose();
                   }
             }
   	 });
   	 
	
        // On fixe la taille et on demarre
         setResizable(false);
         setLocation(400,250);
         setSize(1100, 900);
         setVisible(true);
    }

public static void main (String [] args){
	new Quits();
}


}


