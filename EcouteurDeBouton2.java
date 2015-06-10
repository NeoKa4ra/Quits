import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;


public class EcouteurDeBouton2 implements ActionListener{
	ChargerPartie cp;
	Sauvegarde sauvegarde;
	PlateauGraphique plateau;
	
	
	EcouteurDeBouton2(ChargerPartie cp,Sauvegarde sauvegarde,PlateauGraphique plateau){
		this.plateau=plateau;
		this.cp=cp;
		this.sauvegarde=sauvegarde;
	}

	public void actionPerformed( ActionEvent e){
		if(e.getActionCommand().equals("Supprimer")){
			
			if(cp.listeBouton.getSelection()!=null){
				 try{
					    Moteur.supp_sauv(cp.fichierSelectionne,sauvegarde);
						sauvegarde.afficher();
				 }
				     catch(IOException ex) {
						ex.printStackTrace();
				     }		
				 cp.dispose();
			}else{
				JOptionPane jop = new JOptionPane();
				 jop.showMessageDialog(null, "Veuillez choisir un fichier!", "Erreur Chargement", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		
		
		if(e.getActionCommand().equals("Annuler")){
			 cp.dispose();
		}
		
		if(e.getActionCommand().equals("Charger")){

			if(cp.listeBouton.getSelection()!=null){
				 try{
					    Moteur.charger(cp.fichierSelectionne,cp.p.matrice);
					    cp.p.couleurInverse=cp.p.matrice.estinverse;
					    plateau.j2.score=plateau.matrice.nbBlancSortis;
		    			plateau.j1.score=plateau.matrice.nbMarronSortis;
					    if(cp.p.couleurInverse){
					    	plateau.j2.tour=1;
			    			plateau.j1.tour=0;
			    			plateau.j2.joueur=1;
			    			plateau.j1.joueur=2;
			    			plateau.j1.repaint();
			    			plateau.j2.repaint();
					    }
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
			cp.p.repaint();
		}
		
		
	}

}