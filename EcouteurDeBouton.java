import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class EcouteurDeBouton implements ActionListener{ 
	Plateau matrice;
	NouvellePartie np;
	Sauvegarde sauvegarde;
	
	EcouteurDeBouton(Sauvegarde sauvegarde, Plateau p){
		this.sauvegarde=sauvegarde;
		this.matrice=p;
	}

    	public void actionPerformed(ActionEvent e) {
    	
    		if(e.getActionCommand().equals("Nouvelle Partie")){
    			 System.out.println("coucou new partie" );
    			 NouvellePartie np = new NouvellePartie(null, "Choisir le niveau", true);
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
						Moteur.sauvegarde(nom,matrice,sauvegarde);
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
					ChargerPartie cp = new ChargerPartie(null, "Charger une partie", true, this, this.sauvegarde, matrice);				}
				else{
					 JOptionPane jop = new JOptionPane();
					 jop.showMessageDialog(null, "Aucune partie sauvegardée", "Erreur Chargement", JOptionPane.ERROR_MESSAGE);
				}
					
        	}
        	
        	if(e.getActionCommand().equals("Options")){
        		System.out.println("options");
        	}
        	
        	if(e.getActionCommand().equals("aide")){
        		System.out.println("aide");
        	}
        	
        	if(e.getActionCommand().equals("coupdepouce")){
        		System.out.println("coupdepouce");
        	}
        	
        	if(e.getActionCommand().equals("annuler")){
        		System.out.println("annuler");
        	}
        	
        	if(e.getActionCommand().equals("refaire")){
        		System.out.println("refaire");
        	}
   	 }
 }
        	
        	
