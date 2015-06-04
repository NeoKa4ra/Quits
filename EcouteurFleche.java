import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.Timer;


public class EcouteurFleche implements MouseListener,ActionListener {
	PlateauGraphique plateau;
	Timer timer;
	int current;
    final int slowness = 100;
    Moteur m;
    Etats j1;
    Etats j2;
    
	EcouteurFleche(PlateauGraphique plateau,Moteur m,Etats j1, Etats j2){
		this.plateau=plateau;
        current=0;
        timer=new Timer(10,this);
        this.m=m;
        this.j1=j1;
        this.j2=j2;
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if(plateau.joueur_joue && !(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3)){
			int x=e.getX();
		    int y1=e.getY();
		    int i=plateau.clicFleche((double)x,(double)y1);
			int d =plateau.direction(i);
			int c=-1;
			if(i==4 || i==0 || i==5 || i==13)
				c=0;
			if(i==7 || i==8 || i==1 || i==15)
				c=1;
			if(i==10 || i==9 || i==2 || i==16)
				c=2;	
			if(i==12 || i==11 || i==3 || i==17)
				c=3;
			if(i==6 || i==19 || i==18 || i==14)
				c=4;
			
			switch(d){
				case 1:
					System.out.println("nord" + c +" fleche" + i);
					if(m.colonneJouableH(c,plateau.matrice)){
	            		plateau.clicfleche=i;
	            		plateau.depart1.x=-1;
		    			plateau.depart1.y=-1;
		    			plateau.arrivee.x=-1;
		    			plateau.arrivee.y=-1;
		    			
	            		
						int position=plateau.matrice.position;
			    		int L=plateau.matrice.historique.size();
			    		//System.out.println(L);
			    		//System.out.println(position);
		
		
			    		if(position!=L){
			    			plateau.refaire.setEnabled(false);
					    	//System.out.println(L+"="+position);
				    		int j;

				    		
				    	  	for(j=position;j<L;j++){
				    	    	//System.out.println("j"+j);
				    	    	plateau.matrice.historique.removeLast();
				    	    }

			    		}
						
						if( m.niveau!=0 && !(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3))
							plateau.joueur_joue=false;
			    	        timer.start();
			    	        current = 1;
			    	        plateau.fleche=2;
			    	        plateau.annuler.setEnabled(true);
						}else{
			    			plateau.clicfleche=-1;
			    			System.out.println("est" + c +" fleche" + i);


			    		}

		    		break;
		    	case 2:
					
				
					System.out.println("sud" + c +" fleche" + i);
		    		if(m.colonneJouableB(c,plateau.matrice)){
		    			plateau.clicfleche=i;
		    			plateau.depart1.x=-1;
		    			plateau.depart1.y=-1;
		    			plateau.arrivee.x=-1;
		    			plateau.arrivee.y=-1;
		    			
	            		int position=plateau.matrice.position;
			    		int L=plateau.matrice.historique.size();
			    		//System.out.println(L);
			    		//System.out.println(position);
		
		
			    		if(position!=L){
			    			plateau.refaire.setEnabled(false);
					    	//System.out.println(L+"="+position);
				    		int j;

				    		
				    	  	for(j=position;j<L;j++){
				    	    	//System.out.println("j"+j);
				    	    	plateau.matrice.historique.removeLast();
				    	    }

			    		}
		    			
		    			if( m.niveau!=0 && !(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3))
							plateau.joueur_joue=false;
			    	        timer.start();
			    	        current = 1;
			    	        plateau.fleche=2;
			    	        plateau.annuler.setEnabled(true);
					}else{
		    			plateau.clicfleche=-1;
		    			System.out.println("est" + c +" fleche" + i);

		    		}
	

		    		break;
		    	case 3:
					System.out.println("est" + c +" fleche" + i);
		    		if(m.rangeeJouableD(c,plateau.matrice)){
		    			System.out.println("est" + c +" fleche" + i);
		    			plateau.clicfleche=i;
		    			plateau.depart1.x=-1;
		    			plateau.depart1.y=-1;
		    			plateau.arrivee.x=-1;
		    			plateau.arrivee.y=-1;
		    			
		    			int position=plateau.matrice.position;
			    		int L=plateau.matrice.historique.size();
			    		//System.out.println(L);
			    		//System.out.println(position);
		
		
			    		if(position!=L){
			    			plateau.refaire.setEnabled(false);
					    	//System.out.println(L+"="+position);
				    		int j;

				    		
				    	  	for(j=position;j<L;j++){
				    	    	//System.out.println("j"+j);
				    	    	plateau.matrice.historique.removeLast();
				    	    }
			    		}
			    	
		    			
		    			if( m.niveau!=0 && !(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3))
							plateau.joueur_joue=false;
			    	        timer.start();
			    	        current = 1;
			    	        plateau.fleche=2;
			    	        plateau.annuler.setEnabled(true);
						
		    		}else{
		    			System.out.println("est" + c +" fleche" + i);
		    			plateau.clicfleche=-1;

		    		}
		    		break;	
		    	case 4:
					System.out.println("ouest" + c +" fleche" + i);
		    		if(m.rangeeJouableG(c,plateau.matrice)){
		    			plateau.clicfleche=i;
		    			plateau.depart1.x=-1;
		    			plateau.depart1.y=-1;
		    			plateau.arrivee.x=-1;
		    			plateau.arrivee.y=-1;
		    			
		    			int position=plateau.matrice.position;
			    		int L=plateau.matrice.historique.size();
			    		//System.out.println(L);
			    		//System.out.println(position);
		
		
			    		if(position!=L){
			    			plateau.refaire.setEnabled(false);
					    	//System.out.println(L+"="+position);
				    		int j;

				    		
				    	  	for(j=position;j<L;j++){
				    	    	//System.out.println("j"+j);
				    	    	plateau.matrice.historique.removeLast();
				    	    }

			    		}
		    			
		    			
		    			if( m.niveau!=0 && !(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3))
							plateau.joueur_joue=false;
			    	        timer.start();
			    	        current = 1;
			    	        plateau.fleche=2;
			    	        plateau.annuler.setEnabled(true);
					}else{
		    			System.out.println("est" + c +" fleche" + i);

		    			plateau.clicfleche=-1;

		    		}
		    		
		    		break;
				default:;
				
			}
			
			j1.score= plateau.matrice.nbMarronSortis;
			j2.score=plateau.matrice.nbBlancSortis;
			if(plateau.matrice.jBlanc){
				j2.tour=1;
				j1.tour=0;
				
			}else{
				j2.tour=0;
				j1.tour=1;
			}		
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
			/*	plateau.matrice.init_2_joueurs();
				j1.init();
				j2.init();
				j1.repaint();
				j2.repaint();	
				plateau.repaint(); */
			}

		}	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("Survolle sur la fleche");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseReleased(MouseEvent arg0) {
	/*	// TODO Auto-generated method stub
		
	plateaugraphique.matrice.afficher();*/
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(!plateau.joueur_joue && plateau.fleche==2){
	        if (current <= slowness) {
	        	current++;
	        }
	        else{
	        	IA IA=new IA();
	        	CoupJouable coupjouable;
	        	if(m.niveau==1)
	        		coupjouable=IA.niveau1(plateau.matrice);
	        	else if(m.niveau==2)
	        		coupjouable=IA.normal(plateau.matrice);	        		
	        	else //if(ia==3)
	        		coupjouable=IA.hard(plateau.matrice,4);   
	        	
	        	if(coupjouable.estCase()){
	        		Point dep=coupjouable.PointDep();
	           		Point arr=coupjouable.PointArr();
		            if(m.pointJouable(dep,arr,plateau.matrice) ){
		            	plateau.depart1.x=dep.x;
		        		plateau.depart1.y=dep.y;
		        		plateau.arrivee.x=arr.x;
		        		plateau.arrivee.y=arr.y;
		        		plateau.clicfleche=-1;
	            		plateau.repaint();
	            		plateau.repaint();
		            } 
	        	}
	        	else if(coupjouable.estColonne()){
	    			int colonne=coupjouable.Colonne();
	    			System.out.println("pol" + colonne);
	        		if(coupjouable.Sens()){
	        			m.colonneJouableH(colonne, plateau.matrice);		   
	        		}
	        		else{
	        			m.colonneJouableB(colonne, plateau.matrice);  
	        		  
	        		}
	        	}
	        	else{
	    			int rangee=coupjouable.Rangee();
	    			System.out.println("rangee" + rangee);

	        		if(coupjouable.Sens())
	        			m.rangeeJouableD(rangee, plateau.matrice);
	        		else
	        			m.rangeeJouableG(rangee, plateau.matrice); 
	        	}
	        	

	 
	
	        	plateau.joueur_joue=true;
	        	plateau.fleche=0;
	
	    		timer.stop();
	    		
				j1.score= plateau.matrice.nbMarronSortis;
				j2.score=plateau.matrice.nbBlancSortis;
				if(plateau.matrice.jBlanc){
					j2.tour=1;
					j1.tour=0;
					
				}else{
					j2.tour=0;
					j1.tour=1;
				}		
		        j1.repaint();
		        j2.repaint();
    	        plateau.annuler.setEnabled(true);
				plateau.repaint();
				if(plateau.matrice.nbBlancSortis()==3 || plateau.matrice.nbMarronSortis()==3){
					JOptionPane jop2 = new JOptionPane();
					jop2.showMessageDialog(null, "VOUS AVEZ PERDU", "Fin De Partie", JOptionPane.INFORMATION_MESSAGE);
				/*	plateau.matrice.init_2_joueurs();
					j1.init();
					j2.init();
					j1.repaint();
					j2.repaint();
					plateau.repaint(); */
				}
	    	
	        }
	
		}
	}

}

