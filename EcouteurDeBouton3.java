import javax.swing.*;

import java.awt.Point;
import java.awt.event.*;
import java.io.IOException;


public class EcouteurDeBouton3 implements ActionListener{
	Options opt;
	PlateauGraphique plateau;
	 final int slowness = 100;
	 Timer timer;
	 int current;
	 boolean ok;
	    
	EcouteurDeBouton3(Options opt, PlateauGraphique plateau){
		this.opt=opt;
		this.plateau=plateau;
		current=0;
        timer=new Timer(10,this);
        current=1;
        ok=true;
	}

	public void actionPerformed( ActionEvent e){
		if(ok && e.getActionCommand().equals("Annuler")){
			 opt.dispose();
			 
			 
		}
		
		if(ok && e.getActionCommand().equals("OK")){
			int couleurj1=opt.j1.getSelectedIndex();   // 0 noir 1 blanc 
			int couleurj2=opt.j2.getSelectedIndex(); // 0 blanc et 1 noir
			
			
			if(couleurj1!=couleurj2){ 
				
				if(couleurj1==1 && couleurj2==0){//j1 devient blanc
					plateau.couleurInverse=true;
					plateau.j1.joueur=2;
	    			plateau.j2.joueur=1;
	    			plateau.j1.score = 0;
		    		plateau.j2.score = 0;
		    		plateau.matrice.init_2_joueurs();
		    		plateau.matrice.jBlanc=true;
		    		plateau.j2.tour=1;
		    		plateau.j1.tour=0;
		    		
	    			plateau.clicfleche=-1;
        			plateau.depart1.x=-1;
            		plateau.depart1.y=-1;
            		plateau.arrivee.x=-1;
            		plateau.arrivee.y=-1;
				}else if(couleurj1==0 && couleurj2==1){	 // j1 noir
					plateau.couleurInverse=false;
	    			plateau.j1.joueur=1;
	    			plateau.j2.joueur=2;
	    			plateau.j1.score = 0;
		    		plateau.j2.score = 0;
		    		plateau.j2.tour=0;
		    		plateau.j1.tour=1;
		    		plateau.matrice.init_2_joueurs();
		    		plateau.clicfleche=-1;
        			plateau.depart1.x=-1;
            		plateau.depart1.y=-1;
            		plateau.arrivee.x=-1;
            		plateau.arrivee.y=-1;
		    		
				}
				
				
				if(opt.listeBouton.getSelection()!=null){
					plateau.debut=false;
					plateau.j1.debut=false;
					plateau.j2.debut=false;
					opt.dispose(); 
					
					//System.out.println("ghj"+opt.niveau + " "+ couleurj1
					//+" "+couleurj2);
		
						if(opt.niveau!=0 && couleurj1==1 && couleurj2==0){
							timer.start();
							ok=false;
						}
						
				}
				else{
					 JOptionPane jop = new JOptionPane();
					 jop.showMessageDialog(null, "Veuillez choisir un niveau!", "Erreur Choix niveau", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			else{
				 JOptionPane jop1 = new JOptionPane();
				 jop1.showMessageDialog(null, "Veuillez chosir deux couleurs différentes"
				 		+ " pour les deux joueurs", "Erreur Nouvelle Partie", JOptionPane.ERROR_MESSAGE);
			}
			System.out.println(couleurj1+" "+couleurj2);
			
			 
		}

		if(!ok){
			plateau.matrice.jBlanc=true;
			if (current <= slowness) {
	        	current++;
	
	        }
	        else{
	        	IA IA=new IA();
	        	CoupJouable coupjouable;
	        	if(plateau.m.niveau==1)
	        		coupjouable=IA.niveau1(plateau.matrice);
	        	else if(plateau.m.niveau==2){
	        		coupjouable=IA.normal(plateau.matrice);
	        	}	
	        	else //if(m.niveau==3)
	        		coupjouable=IA.hard(plateau.matrice,1);       		
	
	        	
	        	if(coupjouable.estCase()){
	        		Point dep=coupjouable.PointDep();
	           		Point arr=coupjouable.PointArr();
		            if(Moteur.pointJouable(dep,arr,plateau.matrice) ){
	            		plateau.repaint();
		            } 
	        	}
	        	else if(coupjouable.estColonne()){
	    			int colonne=coupjouable.Colonne();
	        		if(coupjouable.Sens())
	        			plateau.m.colonneJouableH(colonne, plateau.matrice);
	        		else
	        			plateau.m.colonneJouableB(colonne, plateau.matrice);           					
	        	}
	        	else{
	    			int rangee=coupjouable.Rangee();
	        		if(coupjouable.Sens())
	        			plateau.m.rangeeJouableD(rangee, plateau.matrice);
	        		else
	        			plateau.m.rangeeJouableG(rangee, plateau.matrice); 
	        	}
	
	        	plateau.joueur_joue=true;
	
	    		timer.stop();
	    		plateau.fleche=0;
	    		if(plateau.matrice.jBlanc){
	    			plateau.j2.tour=1;
	    			plateau.j1.tour=0;
	    			
	    		}else{
	    			plateau.j2.tour=0;
	    			plateau.j1.tour=1;
	    			
	    		}
	    		plateau.j1.score= plateau.matrice.nbMarronSortis;
	    		plateau.j2.score=plateau.matrice.nbBlancSortis;
	    		plateau.j1.repaint();
	    		plateau.j2.repaint();
	    		plateau.repaint();   
	    		
	    		ok=true;
	    		plateau.annuler.setEnabled(true);
	    		
		
	        }
		}
	}
}
	
	
	
	