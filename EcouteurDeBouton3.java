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
	 
			
			String couleurj1=(String) opt.j1.getSelectedItem();  // 0 noir 1 blanc 
			String couleurj2=(String) opt.j2.getSelectedItem(); // 0 blanc et 1 noir
			
			
			if(couleurj1!=couleurj2){    // CHOIX DES COULEURS 
				
				if(couleurj1=="Blanc" && couleurj2=="Noir"){//j1 devient blanc
					plateau.couleurInverse=true;
					plateau.j1.joueur=2;
	    			plateau.j2.joueur=1;
	    			plateau.j1.score = 0;
		    		plateau.j2.score = 0;
		    		plateau.matrice.init_2_joueurs();
		    		plateau.matrice.jBlanc=true;
		    		plateau.j2.tour=1;
		    		plateau.j1.tour=0;
		    		
		    		
		    		plateau.itemj1=1;
		    		plateau.itemj2=0;
		    	
	    			plateau.clicfleche=-1;
        			plateau.depart1.x=-1;
            		plateau.depart1.y=-1;
            		plateau.arrivee.x=-1;
            		plateau.arrivee.y=-1;
				}else if(couleurj1=="Noir" && couleurj2=="Blanc"){	 // j1 noir
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
            		
            		plateau.itemj1=0;
		    		plateau.itemj2=1;
		    		
		    		plateau.matrice.jBlanc=false;
		    		
				}
				
				
				if(opt.listeBouton.getSelection()!=null){  // choix du niveau
					plateau.debut=false;
					plateau.j1.debut=false;
					plateau.j2.debut=false;
					//opt.dispose(); 
					
					
						if(opt.niveau!=0 && couleurj1=="Noir" && couleurj2=="Blanc"){
							timer.start();
							ok=false;
							
						}
						
				}//fin if choix de niveau
				else{
					 JOptionPane jop = new JOptionPane();
					 jop.showMessageDialog(null, "Veuillez choisir un niveau!", "Erreur Choix niveau", JOptionPane.ERROR_MESSAGE);
				}
				
			} //FIN IF CHOIX DES COULEURS
			else{
				 JOptionPane jop1 = new JOptionPane();
				 jop1.showMessageDialog(null, "Veuillez chosir deux couleurs différentes"
				 		+ " pour les deux joueurs", "Erreur Nouvelle Partie", JOptionPane.ERROR_MESSAGE);
			}
		
			plateau.debut=false;
			
		plateau.j1.repaint();
		plateau.j2.repaint();
		plateau.repaint();
		opt.dispose();
		}

		if(!ok){
			plateau.joueur_joue=false;
			plateau.matrice.jBlanc=false;
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
	        		coupjouable=IA.hard(plateau.matrice,6);       		
	
	        	
	        	if(coupjouable.estCase()){
	        		Point dep=coupjouable.PointDep();
	           		Point arr=coupjouable.PointArr();
		            if(Moteur.pointJouable(dep,arr,plateau.matrice) ){
		          		plateau.depart1.x=dep.x;
	            		plateau.depart1.y=dep.y;
	            		plateau.arrivee.x=arr.x;
	            		plateau.arrivee.y=arr.y;
	            		plateau.clicfleche=-1;
	     
	            		plateau.repaint();
		            } 
	        	}
	        	else if(coupjouable.estColonne()){
	    			int colonne=coupjouable.Colonne();
	        		if(coupjouable.Sens())
	        			if(plateau.m.colonneJouableH(colonne, plateau.matrice)){
	        				plateau.clicfleche=plateau.colFleche(colonne,true);
	        			
			    			plateau.depart1.x=-1;
			    			plateau.depart1.y=-1;
			    			plateau.arrivee.x=-1;
			    			plateau.arrivee.y=-1;
			    			
	        			}
	        		else
	        			if (plateau.m.colonneJouableB(colonne, plateau.matrice)){
	        				plateau.clicfleche=plateau.colFleche(colonne,false);
	        		
			    			plateau.depart1.x=-1;
			    			plateau.depart1.y=-1;
			    			plateau.arrivee.x=-1;
			    			plateau.arrivee.y=-1;
			    			
	        			}
	        	}
	        	else{
	    			int rangee=coupjouable.Rangee();
	        		if(coupjouable.Sens())
	        			if( plateau.m.rangeeJouableD(rangee, plateau.matrice)){
	        				plateau.clicfleche=plateau.rangFleche(rangee,true);
	        			
			    			plateau.depart1.x=-1;
			    			plateau.depart1.y=-1;
			    			plateau.arrivee.x=-1;
			    			plateau.arrivee.y=-1;
			    			
	        			}
	        		else
	        			if(plateau.m.rangeeJouableG(rangee, plateau.matrice)){
	        				
	        				plateau.clicfleche=plateau.rangFleche(rangee,false);
			    			plateau.depart1.x=-1;
			    			plateau.depart1.y=-1;
			    			plateau.arrivee.x=-1;
			    			plateau.arrivee.y=-1;
			    			
	        			}
	        				
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
	    		
	    		plateau.joueur_joue=true;
	        }
		}
	}
	


}
	
	
	
	