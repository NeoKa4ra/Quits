import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class EcouteurDeBouton implements ActionListener{ 
	ChargerPartie cp;
	NouvellePartie np;
	Sauvegarde sauvegarde;
	
	EcouteurDeBouton(Sauvegarde sauvegarde){
		this.sauvegarde=sauvegarde;
	}

    	public void actionPerformed(ActionEvent e) {
    	
    		if(e.getActionCommand().equals("Nouvelle Partie")){
    			 System.out.println("new mais pour l'instant tu t'en fous tu feras une fenetre avec des liste apres" );
    			 NouvellePartie np = new NouvellePartie(null, "Choisir le niveau", true);
         		this.np=np;
    		
    		}
    		
    		
        	if(e.getActionCommand().equals("Sauvegarder Partie")){
        		 System.out.println("save");
        		 JOptionPane jop = new JOptionPane();
 			     String nom = jop.showInputDialog(null, "Entrez un nom pour votre partie: ", "Entrez un nom pour votre partie: ", JOptionPane.QUESTION_MESSAGE);
 			     
 			     //appel de la fonction de sauvegarde (classe Sauvegarde)
 			     try{
					sauvegarde.sauv_Fich(nom);
					//System.out.println(nom);
					sauvegarde.afficher();
 			     }
			     catch(IOException ex) {
					ex.printStackTrace();
				}
				
        	}
        	
        	if(e.getActionCommand().equals("Charger Partie")){
        		System.out.println("charger");
        		
				ChargerPartie cp = new ChargerPartie(null, "Charger une partie", true, this, this.sauvegarde);
        		this.cp=cp;
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
        	
        	
