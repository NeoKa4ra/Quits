import javax.swing.*;

import java.awt.event.*;


public class EcouteurDeBouton2 implements ActionListener{
	ChargerPartie cp;
	
	EcouteurDeBouton2(ChargerPartie cp){
		this.cp=cp;
	}

	public void actionPerformed( ActionEvent e){
		if(e.getActionCommand().equals("Supprimer")){
			 System.out.println("joris!!");
			 //enlever de la liste feremer puis reouvrir la fenetre?
			 //ou si il y a un truc genre rafraischissement de fenetre
		}
		
		if(e.getActionCommand().equals("Renommer")){
			 System.out.println("joris!!");
		}
		
		if(e.getActionCommand().equals("Annuler")){
			 System.out.println("joris!!");
			 cp.dispose();
		}
		
		if(e.getActionCommand().equals("Charger")){
			 System.out.println("joris!!"); //appel de sa fonction charger avc cp.fichierAcharge
		}
		
		if(e.getActionCommand().equals("OK")){
			 System.out.println("joris!!"); //appel de sa fonction charger avc cp.fichierAcharge
		}
	}

}
