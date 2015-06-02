import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class EcouteurDeBouton implements ActionListener{ 
	PlateauGraphique plateau;
	NouvellePartie np;
	Sauvegarde sauvegarde;
	Moteur m;
	int bouton;
	Etats j1,j2;
	
	EcouteurDeBouton(int b, Sauvegarde sauvegarde,PlateauGraphique plateau,Moteur m, Etats j1, Etats j2){
		this.sauvegarde=sauvegarde;
		this.plateau=plateau;
		bouton=b;
		this.j1=j1;
		this.j2=j2;
		this.m=m;

	}

    	public void actionPerformed(ActionEvent e) {
    		if(e.getActionCommand().equals("Nouvelle Partie")){
    			 System.out.println("coucou new partie" );

    			 NouvellePartie np = new NouvellePartie(null, "Choisir le niveau", true, m);
         		 this.np=np;
    		
    		}
    		
    		
        	if(e.getActionCommand().equals("Sauvegarder Partie")){
        		 System.out.println("save");
        		 JOptionPane jop = new JOptionPane();
 			     String nom = jop.showInputDialog(null, "Entrez un nom pour votre partie: ", "Entrez un nom pour votre partie: ", JOptionPane.QUESTION_MESSAGE);
 			     //mais ici ne sauvegarder que si on a cliqué sur ok faut chercher ca
 			     //appel de la fonction de sauvegarde (classe Sauvegarde)
 			     if(nom!=null && nom.length()>0) {
	 			     try{
						Moteur.sauvegarde(nom,plateau.matrice,sauvegarde);
						sauvegarde.afficher();
	 			     }
				     catch(IOException ex) {
						ex.printStackTrace();
					}		     
        		}else{
        			JOptionPane jop2 = new JOptionPane();
					jop2.showMessageDialog(null, "veuillez saisir un nom de fichier", "Erreur sauvegarde", JOptionPane.ERROR_MESSAGE);
        			
        		}
        	}
        	
        	if(e.getActionCommand().equals("Charger Partie")){
				
				if(sauvegarde.listFichier.size()!=0){
					ChargerPartie cp = new ChargerPartie(null, "Charger une partie", true, this, this.sauvegarde, plateau.matrice);				}
				else{
					 JOptionPane jop = new JOptionPane();
					 jop.showMessageDialog(null, "Aucune partie sauvegardée", "Erreur Chargement", JOptionPane.ERROR_MESSAGE);
				}
					
        	}
        	
         	if(e.getActionCommand().equals("Options")){
        		System.out.println("options");
        	}
        	
        	if(bouton==0){
        		System.out.println("Je dois afficher les regles");
        	}
        	
        	if(bouton==1){
        		System.out.println("coupdepouce");
        	}
        	
        	if(bouton==2){
        		Moteur.annuler(plateau.matrice);
	        		j1.score= plateau.matrice.nbMarronSortis;
	        		j2.score=plateau.matrice.nbBlancSortis;
	        		j1.repaint();
	        		j2.repaint();
	        		plateau.repaint();
        		}
        	
        	
        	if(bouton==3){
        		Moteur.refaire(plateau.matrice);
	        		j1.score= plateau.matrice.nbMarronSortis;
	        		j2.score=plateau.matrice.nbBlancSortis;
	        		j1.repaint();
	        		j2.repaint();
	        		plateau.repaint();
        		
        	}
   	 }
 }
        	
        	
