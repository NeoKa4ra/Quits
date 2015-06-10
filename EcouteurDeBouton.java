import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;

public class EcouteurDeBouton implements ActionListener{ 
	PlateauGraphique plateau;
	Options opt;
	Sauvegarde sauvegarde;
	Moteur m;
	int bouton;
	Etats j1,j2;
	JButton annuler,refaire;


	
	EcouteurDeBouton(int b, Sauvegarde sauvegarde,PlateauGraphique plateau,Moteur m, Etats j1, Etats j2,JButton annuler,JButton refaire){
		this.sauvegarde=sauvegarde;
		this.plateau=plateau;
		bouton=b;
		this.j1=j1;
		this.j2=j2;
		this.m=m;
		this.annuler=annuler;
		this.refaire=refaire;

	}

    	public void actionPerformed(ActionEvent e) {
    		// NOUVELLE PARTIE
    		if(bouton==-1){
    			
    			plateau.couleurInverse=false;

    			
        			m.niveau=m.dernier_niveau;
        			plateau.clicfleche=-1;
        			plateau.depart1.x=-1;
            		plateau.depart1.y=-1;
            		plateau.arrivee.x=-1;
            		plateau.arrivee.y=-1;
            		//mise a jour des etats joueur=1 => noir joueur=2 => blanc
            		// tout =1pour j1 ca toujours au noir de jouer
            		
        			j1.joueur=1;
        			j2.joueur=2;
        			j1.tour=1;
        			j2.tour=0;
        			j1.score=0;
        			j2.score=0;
        			plateau.matrice.jBlanc=false;
    	    		
    	    		
    	    		 Options opt= new Options(null, "Options", true, m, plateau, m.niveau, plateau.itemj1, plateau.itemj2);
    	       		 this.opt=opt;
    	       		
        		}
        		
    		
    		//SAUVERGARDER PARTIE
        	if(bouton==-2){
           		plateau.matrice.estinverse=plateau.couleurInverse;
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
        		}
 			}
        	
        	//CHARGER PARTIE
        	if(bouton==-3){
				
				if(sauvegarde.listFichier.size()!=0){
					ChargerPartie cp = new ChargerPartie(null, "Charger une partie", true, this, this.sauvegarde, plateau);				}
				else{
					 JOptionPane jop = new JOptionPane();
					 jop.showMessageDialog(null, "Aucune partie sauvegardée", "Erreur Chargement", JOptionPane.ERROR_MESSAGE);
				}
				/*j1.debut=false;
				j2.debut=false;
				if(plateau.matrice.jBlanc){
	    			j2.tour=1;
	    			j1.tour=0;
	    			
	    		}else{
	    			j2.tour=0;
	    			j1.tour=1;
	    			
	    		}
	    		j1.score= plateau.matrice.nbMarronSortis;
	    		j2.score=plateau.matrice.nbBlancSortis;
				j1.repaint();
	    		j2.repaint();	
				plateau.debut=false;
				plateau.repaint();*/
					
        	}
        	     	 	
        	
        	
        	
         	//AIDE
        	if(bouton==0){
        		Aide aide=new Aide(null, "Règles du jeu", true);
        	}
        	
        	
        	//SUGGESTION DE COUP coupdepouce
        	if(bouton==1){
        		plateau.coupdepouce=true;
        	}
        	
        	//ANNULER
        	if(bouton==2){
        		Moteur.annuler(plateau.matrice,annuler,refaire,plateau.couleurInverse);
        		
        		if(plateau.matrice.adv.estCase()){
	        		plateau.depart1.x=plateau.matrice.adv.pDep.x;
	        		plateau.depart1.y=plateau.matrice.adv.pDep.y;
	        		plateau.arrivee.x=plateau.matrice.adv.pArr.x;
	        		plateau.arrivee.y=plateau.matrice.adv.pArr.y;
	        		plateau.clicfleche=-1;
        		}
        		else{
	        		plateau.depart1.x=plateau.matrice.adv.pDep.x;
	        		plateau.depart1.y=plateau.matrice.adv.pDep.y;
	        		plateau.arrivee.x=plateau.matrice.adv.pArr.x;
	        		plateau.arrivee.y=plateau.matrice.adv.pArr.y;
	        		if(plateau.matrice.adv.estColonne())
	        			plateau.clicfleche=plateau.colFleche(plateau.matrice.adv.Colonne(),plateau.matrice.adv.Sens());
	        		else
	        			plateau.clicfleche=plateau.rangFleche(plateau.matrice.adv.Rangee(),plateau.matrice.adv.Sens());
        		}
        			
        		if(plateau.matrice.jBlanc){
	    			j2.tour=1;
	    			j1.tour=0;
	    			
	    		}else{
	    			j2.tour=0;
	    			j1.tour=1;
	    			
	    		}
	    		j1.score= plateau.matrice.nbMarronSortis;
	    		j2.score=plateau.matrice.nbBlancSortis;
	        		j1.repaint();
	        		j2.repaint();
	        		plateau.repaint();
        	}
        	
	        	
        	
        	//REFAIRE
        	if(bouton==3){
        		Moteur.refaire(plateau.matrice,refaire,annuler);
        		
        		if(plateau.matrice.adv.estCase()){
	        		plateau.depart1.x=plateau.matrice.adv.pDep.x;
	        		plateau.depart1.y=plateau.matrice.adv.pDep.y;
	        		plateau.arrivee.x=plateau.matrice.adv.pArr.x;
	        		plateau.arrivee.y=plateau.matrice.adv.pArr.y;
	        		plateau.clicfleche=-1;
        		}
        		else{
	        		plateau.depart1.x=plateau.matrice.adv.pDep.x;
	        		plateau.depart1.y=plateau.matrice.adv.pDep.y;
	        		plateau.arrivee.x=plateau.matrice.adv.pArr.x;
	        		plateau.arrivee.y=plateau.matrice.adv.pArr.y;
	        		if(plateau.matrice.adv.estColonne())
	        			plateau.clicfleche=plateau.colFleche(plateau.matrice.adv.Colonne(),plateau.matrice.adv.Sens());
	        		else
	        			plateau.clicfleche=plateau.rangFleche(plateau.matrice.adv.Rangee(),plateau.matrice.adv.Sens());
        		}
        		if(plateau.matrice.jBlanc){
	    			j2.tour=1;
	    			j1.tour=0;
	    			
	    		}else{
	    			j2.tour=0;
	    			j1.tour=1;
	    			
	    		}
	    		j1.score= plateau.matrice.nbMarronSortis;
	    		j2.score=plateau.matrice.nbBlancSortis;
	        		j1.repaint();
	        		j2.repaint();
	        		plateau.repaint();
        		}
        	
	        		
   	 }
    	
    
 }
        	
        	