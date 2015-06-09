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
    			plateau.debut=false;
    			plateau.couleurInverse=false;
        		
    			j1.debut=false;
    			j2.debut=false;
    			j1.repaint();
    	    	j2.repaint();

        			
        			plateau.matrice.init_2_joueurs();
        			plateau.reinit_couleur_joueurs();
        			m.niveau=m.dernier_niveau;
        			plateau.clicfleche=-1;
        			plateau.depart1.x=-1;
            		plateau.depart1.y=-1;
            		plateau.arrivee.x=-1;
            		plateau.arrivee.y=-1;
        			j1.joueur=1;
        			j2.joueur=2;
        			j1.tour=1;
        			j2.tour=0;
        			j1.score=0;
        			j2.score=0;
        			plateau.matrice.jBlanc=false;
    	    		plateau.repaint();

        		}
        		
    		
    		//SAUVERGARDER PARTIE
        	if(bouton==-2){
           		plateau.matrice.estinverse=plateau.couleurInverse;
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
        	
        	//CHARGER PARTIE
        	if(bouton==-3){
				
				if(sauvegarde.listFichier.size()!=0){
					ChargerPartie cp = new ChargerPartie(null, "Charger une partie", true, this, this.sauvegarde, plateau);				}
				else{
					 JOptionPane jop = new JOptionPane();
					 jop.showMessageDialog(null, "Aucune partie sauvegardée", "Erreur Chargement", JOptionPane.ERROR_MESSAGE);
				}
				j1.debut=false;
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
				plateau.repaint();
					
        	}
        	     	 	
        	
        	//OPTIONS
         	if(bouton==-4){
         		 Options opt= new Options(null, "Options", true, m, plateau, m.niveau);
        		 this.opt=opt;
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
        		plateau.depart1.x=-1;
        		plateau.depart1.y=-1;
        		plateau.arrivee.x=-1;
        		plateau.arrivee.y=-1;
        		plateau.clicfleche=-1;
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
        		plateau.depart1.x=-1;
        		plateau.depart1.y=-1;
        		plateau.arrivee.x=-1;
        		plateau.arrivee.y=-1;
        		plateau.clicfleche=-1;
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
        	
        	
