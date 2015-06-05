import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.TimerTask;

class EcouteurDeDrop implements MouseListener,ActionListener {

    PlateauGraphique plateau;
    final int slowness = 1000;
    Timer timer;
    int current;
    Moteur m;
    Etats j1,j2;
    
    // Ecouteur de souris a besoin de connaitre l'aire pour y tracer des choses
    public EcouteurDeDrop(PlateauGraphique plateau,Moteur m, Etats j1, Etats j2) {

       
        this.plateau = plateau;
        //this.ia=ia;
        current=0;
        timer=new Timer(10,this);
        this.m=m;
        this.j1=j1;
        this.j2=j2;
        
    }
	

    // Lors d'un relachement de bouton, on trace l'image selectionnee, s'il y
    // en a une
    public void mouseReleased(MouseEvent e) {
    	
    	if(plateau.joueur_joue && !(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3)){
	        if (plateau.selectionBille) {
	        	 
	        	/* if(plateau.couleurInverse)
	        		 plateau.matrice.inverser();*/
		        	 
	        	 int x=e.getX();
	             int y=e.getY();
	              
	             int i= plateau.calculNUmeroCase(x, y);
	             if(i!=-1){
		             Point arrivee = plateau.calculIndice(i);
		             
		        	 plateau.selectionBille = false;
		            
	
		            
		         //   System.out.println(arrivee.x +" "+arrivee.y);
		            if(m.pointJouable(plateau.depart,arrivee,plateau.matrice)){
		            	
		    				plateau.refaire.setEnabled(false);
		    				
		            		plateau.depart1.x=plateau.depart.x;
		            		plateau.depart1.y=plateau.depart.y;
		            		plateau.arrivee.x=arrivee.x;
		            		plateau.arrivee.y=arrivee.y;
		            		plateau.clicfleche=-1;
		            		plateau.repaint();
		            		int position=plateau.matrice.position;
				    		int L=plateau.matrice.historique.size();
				    		//System.out.println(L);
				    		//System.out.println(position);
			
			
				    		if(position!=L){

						    	//System.out.println(L+"="+position);
					    		int j;
	
					    		
					    	  	for(j=position;j<L;j++){
					    	    	//System.out.println("j"+j);
					    	    	plateau.matrice.historique.removeLast();
					    	    }

				    		}
		            	
		            		if(m.niveau!=0 && !(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3)){
				     	        plateau.joueur_joue=false;
				    	        timer.start();
				    	        current = 1;
				    	        plateau.fleche=1;
				    	        plateau.annuler.setEnabled(true);
		            		}
		            		else
				    	        plateau.annuler.setEnabled(true);
	
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
		    		
					if(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3){
						JOptionPane jop2 = new JOptionPane();
						if(m.niveau!=0)
							jop2.showMessageDialog(null, "VOUS AVEZ GAGNE", "Fin De Partie", JOptionPane.INFORMATION_MESSAGE);
							
						if(plateau.matrice.jBlanc && m.niveau==0)
							jop2.showMessageDialog(null, "JOUEUR NOIR A GAGNE", "Fin De Partie", JOptionPane.INFORMATION_MESSAGE);
						else if(m.niveau==0)
							jop2.showMessageDialog(null, "JOUEUR BLANC A GAGNE", "Fin De Partie", JOptionPane.INFORMATION_MESSAGE);
				/*		plateau.matrice.init_2_joueurs();
						j1.init();
						j2.init();
						j1.repaint();
						j2.repaint();
						plateau.repaint();*/
					}
	            }
	        	/*if(plateau.couleurInverse)
	        		 plateau.matrice.inverser();*/
	        }
	            
	            
	    // matrice.afficher();

	    
	        

    	}

    	
    }

    // Il faut aussi une implementation pour les autres methodes de l'interface
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

		
		
		if(!plateau.joueur_joue && plateau.fleche==1){
	        if (current <= slowness) {
	        	current++;

	        }
	        else{
	       	 	/*if(plateau.couleurInverse)
	       	 		plateau.matrice.inverser(); */
	        	
	        	IA IA=new IA();
	        	CoupJouable coupjouable;
	        	if(m.niveau==1)
	        		coupjouable=IA.niveau1(plateau.matrice);
	        	else if(m.niveau==2){
	        		System.out.println("ici");
	        		coupjouable=IA.normal(plateau.matrice);
	        	}	
	        	else //if(m.niveau==3)
	        		coupjouable=IA.hard(plateau.matrice,4);       		

	        	
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
	    			System.out.println("pol" + colonne);
	        		if(coupjouable.Sens())
	        			m.colonneJouableH(colonne, plateau.matrice);
	        		else
	        			m.colonneJouableB(colonne, plateau.matrice);           					
	        	}
	        	else{
	    			int rangee=coupjouable.Rangee();
	        		if(coupjouable.Sens())
	        			m.rangeeJouableD(rangee, plateau.matrice);
	        		else
	        			m.rangeeJouableG(rangee, plateau.matrice); 
	        	}
	
	        	plateau.joueur_joue=true;

	    		timer.stop();
	    		plateau.fleche=0;
	    		if(plateau.matrice.jBlanc){
	    			j2.tour=1;
	    			j1.tour=0;
	    			
	    		}else{
	    			j2.tour=0;
	    			j1.tour=1;
	    			
	    		}
    	        plateau.annuler.setEnabled(true);
	    		j1.score= plateau.matrice.nbMarronSortis;
	    		j2.score=plateau.matrice.nbBlancSortis;
	    		j1.repaint();
	    		j2.repaint();
	    		plateau.repaint(); 
	    		
				if(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3){
					JOptionPane jop2 = new JOptionPane();
					jop2.showMessageDialog(null, "VOUS AVEZ PERDU", "Fin De Partie", JOptionPane.INFORMATION_MESSAGE);							
				/*	plateau.matrice.init_2_joueurs();
					j1.init();
					j2.init();
					j1.repaint();
					j2.repaint();		
					plateau.repaint();*/
				}

	       	 	/*if(plateau.couleurInverse)
	       	 		plateau.matrice.inverser(); */

	        }

		}
	}
}

