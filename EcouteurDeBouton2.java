import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;


public class EcouteurDeBouton2 implements ActionListener{
	ChargerPartie cp;
	Sauvegarde sauvegarde;
	
	
	
	EcouteurDeBouton2(ChargerPartie cp,Sauvegarde sauvegarde){
		this.cp=cp;
		this.sauvegarde=sauvegarde;
	}

	public void actionPerformed( ActionEvent e){
		if(e.getActionCommand().equals("Supprimer")){
			 try{
				    Moteur.supp_sauv(cp.fichierSelectionne,sauvegarde);
					sauvegarde.afficher();
			 }
			     catch(IOException ex) {
					ex.printStackTrace();
			     }		     
			 cp.dispose();
		}
		
		if(e.getActionCommand().equals("Renommer")){
			 System.out.println("joris!!");
		}
		
		if(e.getActionCommand().equals("Annuler")){
			 System.out.println("joris!!");
			 cp.dispose();
		}
		
		if(e.getActionCommand().equals("Charger")){
			
			if(cp.listeBouton.getSelection()!=null){
				 try{
					    Moteur.charger(cp.fichierSelectionne,cp.p);
						sauvegarde.afficher();
						//ici normalement on devrais charger la partie donc modifier le plateau graphique
						cp.dispose();
				 }
				     catch(IOException ex) {
						ex.printStackTrace();
				     }		     
			}
			else{
				 JOptionPane jop = new JOptionPane();
				 jop.showMessageDialog(null, "Veuillez choisir un fichier!", "Erreur Chargement", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if(e.getActionCommand().equals("OK")){
			 System.out.println("joris!!"); //appel de sa fonction charger avc cp.fichierAcharge
		}
	}

}
