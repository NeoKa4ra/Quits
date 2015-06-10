import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;


public class PlateauGraphique extends JComponent{
	
	
	Graphics2D drawable;
	Plateau matrice;
	Shape [] listeCase;
	Shape [] listeFleche;
	Point [] listeCentre;
	LinkedList <CoupJouable> listeColonne;
	LinkedList <CoupJouable> listeRangee;
	Point depart1;
	Point depart;
	Point arrivee;
	boolean coupvalide;
	Image bb,bn,fg,fh,fd,fb,fg2,fh2,fd2,fb2, img,caseG, caseG1, plateau_ombre;
	int tailleCase;
	int niveau;
	boolean selectionBille;
	public boolean joueur_joue;
	public JButton annuler;
	public JButton refaire;
	public int fleche;
	public int clicfleche;
	Moteur m;
	boolean debut;
	boolean couleurInverse;
	boolean coupdepouce;
	boolean coupPossible;
	Etats j1;
	Etats j2;
	int itemj1, itemj2;
	boolean ok2,ok3;
	int [] listeFlecheBlanc;
	
	PlateauGraphique(Plateau matrice,Moteur m,Etats j1, Etats j2){
		ok2=true;
		ok3=true;
		listeFlecheBlanc = new int [20];
		itemj1=0;
		itemj2=1;
		this.j1=j1;
		this.j2=j2;
		couleurInverse=false;
		clicfleche=-1;
		debut=true;
		this.matrice = matrice;
		setLayout(null);
		listeCase = new Shape[25];
		listeCentre = new Point[25];
		listeFleche = new Shape[20];
		listeColonne = new LinkedList();
		listeRangee = new LinkedList();
		tailleCase=0;
		depart= new Point();
		depart1= new Point();
		depart1.x=-1;
		depart1.y=-1;
		arrivee= new Point();
		arrivee.x=-1;
		arrivee.y=-1;
		coupPossible=false;
		
		if(!debut){
			
		}
		selectionBille=false;
		joueur_joue=true;
		fleche=0;
		coupvalide=false;
		coupdepouce=false;
	}

	public void init () {

		try { 
			caseG=ImageIO.read(new File("case_bois.png")); 
			caseG1=ImageIO.read(new File("case.png")); 
			bb=ImageIO.read(new File("Boule-Blanche.png")); 
			bn=ImageIO.read(new File("Boule-Noire.png")); 
			fd=ImageIO.read(new File("HautDroiteOff.png"));
			fh=ImageIO.read(new File("HautGaucheOff.png"));
		    fb=ImageIO.read(new File("BasDroiteOff.png"));
		    fg=ImageIO.read(new File("BasGaucheOff.png"));
		    fh2=ImageIO.read(new File("HautGauche.png"));
			fd2=ImageIO.read(new File("HautDroite.png"));
		    fb2=ImageIO.read(new File("BasDroite.png"));
		    fg2=ImageIO.read(new File("BasGauche.png"));
		    plateau_ombre=ImageIO.read(new File("plateau_ombre.png"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}	
		
				
	}
	
	public void lancerEcouteur(){
		addMouseListener(new EcouteurBille(this));
		addMouseListener(new EcouteurDeDrop(this,m,j1,j2));
		addMouseListener(new EcouteurFleche(this,m,j1,j2));
	}

	public void inverserCouleurJoueur(){
		try { 
			 
			bb=ImageIO.read(new File("Boule-Noire.png")); 
			bn=ImageIO.read(new File("Boule-Blanche.png")); 
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}	
		
	}

	public void reinit_couleur_joueurs(){
		try { 
			 
			bn=ImageIO.read(new File("Boule-Noire.png")); 
			bb=ImageIO.read(new File("Boule-Blanche.png")); 
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}	
		
	}

	
	
	public void deplacementRangeeColonne(){
		
		LinkedList<CoupJouable> list = matrice.listeCoupsPossibles();
		CoupJouable cj;
		Iterator <CoupJouable> it = list.iterator();
		Iterator <CoupJouable> it1 = listeColonne.iterator();
		Iterator <CoupJouable> it2 = listeRangee.iterator();
		
		
		
		int i=0;
		while(it.hasNext()){
			cj=it.next();
			if(cj.colonne!=-1){
	        	if(cj.sens){
	        		listeFlecheBlanc[i]=colFleche(cj.colonne,true);	 
	        		i++;
	        	}else{
	        		listeFlecheBlanc[i]=colFleche(cj.colonne,false);
	        		i++;
		     
	        	}
			}
			if(cj.rangee!=-1){
				if(cj.sens){
					listeFlecheBlanc[i]=rangFleche(cj.rangee,true);
	        		i++;
		        	
		        
	        	}else{
	        		listeFlecheBlanc[i]=rangFleche(cj.rangee,false);
	        		i++;
		     
	        	}
			}
			
		}
		
		
		for(int j=i;j<20;j++){
			listeFlecheBlanc[j]=-1;		
		}
		

	}
	
	
	public boolean estDanslisteFlecheBlanc(int x){
		int i=0;
		while(i<20 && x!=listeFlecheBlanc[i]){			
			i++;
		}
		return i!=20;
	}
	
	public void paintComponent(Graphics g) {
		//matrice.afficheMatrice();
		//System.out.println();
	       
		// servira a affciher les fleches cliquables, maj des coups colonnes et rangees jouables;
		deplacementRangeeColonne();
	
		// Graphics 2D est le vrai type de l'objet passe en parametre
		// Le cast permet d'avoir acces a un peu plus de primitives de dessin
		drawable = (Graphics2D) g;
		//drawable.rotate(Math.toRadians(45));
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, getSize().width, getSize().height);
		if(!debut)
			lancerEcouteur();
		
		if(couleurInverse){
			inverserCouleurJoueur();
		}else{
			reinit_couleur_joueurs();
		}

		int width = getSize().width; 
		int height = getSize().height;
		
		int a=width/2;
		int b=height-100;
		int a1=width/2-50;
		int b1=height-100-50;
	
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, getSize().width, getSize().height);
		
		
		
		
		// CALCULE DE LA TAILLE D'UNE CASE 
		 
		
		int margeX=0,margeY=0,w,h; 
		int largeurCase = (width)/5; 
		int hauteurCase = (height)/5; 
		int demiD; 
		double diagonale; 
		
		
		if(largeurCase > hauteurCase){ 
			tailleCase=hauteurCase; 
			diagonale = Math.sqrt(Math.pow(tailleCase,2)+Math.pow(tailleCase,2)); 
		
			if((int) (diagonale*5) > height){
				//diagonale²=2taillecase² donc on obtient tailleCase 
				tailleCase= (int) Math.sqrt(Math.pow(height,2)/2)/5;
				diagonale=(int) height/5;
			} 
		} 
		else{ 
			tailleCase=largeurCase; 
			diagonale = Math.sqrt(Math.pow(tailleCase,2)+Math.pow(tailleCase,2)); 
			if((int) (diagonale*5) > width){
				//diagonale²=2taillecase² donc on obtient tailleCase 
				tailleCase = (int) Math.sqrt(Math.pow(width,2)/2)/5;
			}
		}
		
		///////////////////////////////////////////////
		int d= (int) Math.sqrt(Math.pow(tailleCase,2)+Math.pow(tailleCase,2)); 
		
		w=(int) d*5;
		h=(int) d*5;	
		demiD=(int) d/2;
		margeX = width-(d*5);
		margeY = height -(d*5);
	
		//drawable.drawImage(plateau_ombre,margeX/2,margeY/2,d*5,d*5,null);
		
		///// DESSINER LE PLATEAU ///////
		////// 1ere case  ////
		int [] init_polX= {w/2+margeX/2,w/2+demiD+margeX/2,w/2+margeX/2,w/2-demiD+margeX/2};
		int [] init_polY= {h+margeY/2,h-demiD+margeY/2,h-d+margeY/2,h-demiD+margeY/2};
		
		
		listeCentre[0]=new Point((int) init_polX[0], (int) init_polY[2]+demiD ); 
		listeCase[0] = new Polygon(init_polX,init_polY,4);
	
		drawable.drawImage(caseG,(int) (init_polX[0]-demiD),(int) (init_polY[0]-diagonale),d,d,null);
		
		if(0==depart1.x && 0==depart1.y){
			drawable.drawImage(caseG1 ,(int) (init_polX[0]-demiD),(int) (init_polY[0]-diagonale),d,d,null);
		}
		if(0==arrivee.x && 0==arrivee.y){
			drawable.drawImage(caseG1 ,(int) (init_polX[0]-demiD),(int) (init_polY[0]-diagonale),d,d,null);
		}
		
		if(!debut){
			if(matrice.echiquier[0][0].contenu==1){
				drawable.drawImage(bb,listeCentre[0].x-demiD/3,listeCentre[0].y-demiD/3,tailleCase/2,tailleCase/2,null);		
				}
			if(matrice.echiquier[0][0].contenu==2){
				drawable.drawImage(bn,listeCentre[0].x-demiD/3,listeCentre[0].y-demiD/3,tailleCase/2,tailleCase/2,null);		
				}
		}
		

    	if(estDanslisteFlecheBlanc(0) && !debut)
			drawable.drawImage(fb,listeCentre[0].x+(3*demiD)/4,listeCentre[0].y+(demiD)/2,tailleCase/3,tailleCase/3,null);
    	if(estDanslisteFlecheBlanc(5) && !debut)
    		drawable.drawImage(fg,listeCentre[0].x-demiD,listeCentre[0].y+demiD/2,tailleCase/3,tailleCase/3,null);
    	
	    	listeFleche[0]=new Rectangle(listeCentre[0].x+(3*demiD)/4,listeCentre[0].y+demiD/2,tailleCase/3,tailleCase/3);
	    	listeFleche[5]=new Rectangle(listeCentre[0].x-demiD,listeCentre[0].y+demiD/2,tailleCase/3,tailleCase/3);

	    	
	    	if(clicfleche==0){
        		drawable.drawImage(fb2,listeCentre[0].x+(3*demiD)/4,listeCentre[0].y+demiD/2,tailleCase/3,tailleCase/3,null);	
	    	}
        	if(clicfleche==5){
            	drawable.drawImage(fg2,listeCentre[0].x-demiD,listeCentre[0].y+demiD/2,tailleCase/3,tailleCase/3,null);
        	}
	    	
	    ///////////////////////////////////////
        	
    	int [] polX= {w/2+margeX/2,w/2+demiD+margeX/2,w/2+margeX/2,w/2-demiD+margeX/2};
		int [] polY= {h+margeY/2,h-demiD+margeY/2,h-d+margeY/2,h-demiD+margeY/2};
	   
		
		 /**** boucle pour dessiner toute la grille de jeu ****/
		//// on remplit les liste de case, centre et fleches au fur et à mesure ////
        int c=1, f=1;	int l=1;
        int i,j;
        
        for(i=0;i<5;i++){
        	for(j=0;j<5;j++){
        	
        		
        	// calcul des coordonnees des 4 points formant le polygone	
        	if(!(i==0 && j==0)){
        		polX[0]= polX[0]-demiD;
    			polX[1]= polX[1]-demiD;
    			polX[2]= polX[2]-demiD;
    			polX[3]= polX[3]-demiD;
    					
    			polY[0]= polY[0]-demiD;
    			polY[1]= polY[1]-demiD;
    			polY[2]= polY[2]-demiD;
    			polY[3]= polY[3]-demiD;
            
    			if(c%5==0){
    				
    				 	polX[0]= init_polX[0]+(demiD)*l;
    					polX[1]= init_polX[1]+(demiD)*l;
    					polX[2]= init_polX[2]+(demiD)*l;
    					polX[3]= init_polX[3]+(demiD)*l;
    							
    					polY[0]= init_polY[0]-(demiD)*l;
    					polY[1]= init_polY[1]-(demiD)*l;
    					polY[2]= init_polY[2]-(demiD)*l;
    					polY[3]= init_polY[3]-(demiD)*l;	
    					l++;
    			}
    			
    			listeCentre[c]=new Point((int) polX[0], (int) polY[2]+demiD );
	            listeCase[c] = new Polygon(polX,polY,4);
	        	
	        	drawable.drawImage(caseG ,(int) (polX[0]-demiD),(int) (polY[0]-diagonale),d,d,null);
	        	
	        	
	        	
	       		//colorier le point de depart et arrivéé (dernier coup joue //
        		if(i==depart1.x && j==depart1.y){
        			drawable.drawImage(caseG1 ,(int) (polX[0]-demiD),(int) (polY[0]-diagonale),d,d,null);
        		}
        		if(i==arrivee.x && j==arrivee.y){
        			drawable.drawImage(caseG1 ,(int) (polX[0]-demiD),(int) (polY[0]-diagonale),d,d,null);
        		}
        		
        		// afficher les cases où on peut déplacer la bille   FAUT ENCORE VERIFIER SI ILS NE SONT PAS SUR LES BORDS
        		if(coupPossible){
        			
        			int X,Y;
        			Polygon p1,p2,p3;
        			p1=null;
        			p2=null;
        			p3=null;
        			
        			X=depart.x;
    				Y=depart.y;
        			if(matrice.echiquier[depart.x][depart.y].contenu==2){ //noir
        	
	        			if(X-1>=0 && X<=24 && Y>=0 && Y<=4  && matrice.echiquier[X-1][Y].contenu==0){
	        				p1=(Polygon) listeCase[(X-1)*5+Y];
	        				drawable.setPaint(Color.green);
	    	        		drawable.fillPolygon(p1);
	    	        		drawable.setPaint(Color.black);
	    	        		drawable.setStroke(new BasicStroke( 2.0f ));
	    	        		drawable.drawPolygon(p1);

	        			}
	        		
	        			if(X>=0 && X<=4 && Y-1>=0 && Y-1<=4 && matrice.echiquier[X][Y-1].contenu==0){
	        				p2=(Polygon) listeCase[X*5+(Y-1)];
	        				drawable.setPaint(Color.green);
	    	        		drawable.fillPolygon(p2);
	    	        		drawable.setPaint(Color.black);
	    	        		drawable.setStroke(new BasicStroke( 2.0f ));
	    	        		drawable.drawPolygon(p2);
	        			}
	        			
	        		
	        			if(X-1>=0 && X-1<=4 &&Y-1>=0 && Y-1<=4 && matrice.echiquier[X-1][Y-1].contenu==0){
	        				
	        				p3=(Polygon) listeCase[(X-1)*5+(Y-1)];
	        				drawable.setPaint(Color.green);
	    	        		drawable.fillPolygon(p3);
	    	        		drawable.setPaint(Color.black);
	    	        		drawable.setStroke(new BasicStroke( 2.0f ));
	    	        		drawable.drawPolygon(p3);
        				}
        			}
        			
        			if(matrice.echiquier[depart.x][depart.y].contenu==1){ //blanc
        		
        				if(X+1>=0 && X+1<=4 && Y>=0 && Y<=4  && matrice.echiquier[X+1][Y].contenu==0){
        					p1=(Polygon) listeCase[(X+1)*5+Y];
        					drawable.setPaint(Color.green);
	    	        		drawable.fillPolygon(p1);
	    	        		drawable.setPaint(Color.black);
	    	        		drawable.setStroke(new BasicStroke( 2.0f ));
	    	        		drawable.drawPolygon(p1);
        				}
        				
        				
	        			if(X>=0 && X<=4 && Y+1>=0 && Y+1<=4 && matrice.echiquier[X][Y+1].contenu==0){
	        				p2=(Polygon) listeCase[(X*5+(Y+1))];
	        				drawable.setPaint(Color.green);
	    	        		drawable.fillPolygon(p2);
	    	        		drawable.setPaint(Color.black);
	    	        		drawable.setStroke(new BasicStroke( 2.0f ));
	    	        		drawable.drawPolygon(p2);
	        			}
	        			
	        	
	        			if(X+1>=0 && X+1<=4 && Y+1>=0 && Y+1<=4  && matrice.echiquier[X+1][Y+1].contenu==0) {
	        				p3=(Polygon) listeCase[(X+1)*5+(Y+1)];
	        				drawable.setPaint(Color.green);
	    	        		drawable.fillPolygon(p3);
	    	        		drawable.setPaint(Color.black);
	    	        		drawable.setStroke(new BasicStroke( 2.0f ));
	    	        		drawable.drawPolygon(p3);
	        			}
        			}
        			
        
        		}
        		
        		
        		if(!debut){
		            //dessiner les billes
	        		if(matrice.echiquier[i][j].contenu==1){
	        			drawable.drawImage(bb,listeCentre[c].x-demiD/3,listeCentre[c].y-demiD/3,tailleCase/2,tailleCase/2,null);
	        		}
	        		if(matrice.echiquier[i][j].contenu==2){
	        			drawable.drawImage(bn,listeCentre[c].x-demiD/3,listeCentre[c].y-demiD/3,tailleCase/2,tailleCase/2,null);
	        		}
        		
        		}
        		
        		       		
        		
        		
        		///////////dessiner les fleches/////////////////
        	
	            if(j==4  /*&& matrice.estJouableCHaut(i, matrice.jBlanc)*/){ // vers le haut
	            	
	            	
	            	if(f==5)
	            		f++;	
	            	listeFleche[f]=new Rectangle(listeCentre[c].x-demiD,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3);
	            	
	            	if(estDanslisteFlecheBlanc(f) && !debut){
	            		drawable.drawImage(fh,listeCentre[c].x-demiD,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3,null);
	            	}
	            	if(clicfleche==f){
	            		drawable.drawImage(fh2,listeCentre[c].x-demiD,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3,null);
	            	}
	            	f++;	
	            	
	            }
	            if(i==4 /*&& matrice.estJouableRDroite(j, matrice.jBlanc)*/){ // vers la droite
	            	
	            	
	            	if(f==5)
	            		f++;
	            	listeFleche[f]=new Rectangle(listeCentre[c].x+demiD/2,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3);
	            	
	            	if(estDanslisteFlecheBlanc(f) && !debut){
	            			drawable.drawImage(fd,listeCentre[c].x+demiD/2,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3,null);
	            	}	
	            	if(clicfleche==f){
	            		drawable.drawImage(fd2,listeCentre[c].x+demiD/2,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3,null);
	            	
	            	}
	            	f++;

	            }
	            if(j==0 /*&& matrice.estJouableCBas(i, matrice.jBlanc)*/){ // vers le bas
	            	
	            	
	            	if(f==5)
	            		f++;
	            	listeFleche[f]=new Rectangle(listeCentre[c].x+(3*demiD)/4,listeCentre[c].y+demiD/2,tailleCase/3,tailleCase/3);
	            	
	            	if(estDanslisteFlecheBlanc(f) && !debut){
	            		drawable.drawImage(fb,listeCentre[c].x+(3*demiD)/4,listeCentre[c].y+demiD/2,tailleCase/3,tailleCase/3,null);
	            	}
	            	if(clicfleche==f){	    
	            		drawable.drawImage(fb2,listeCentre[c].x+(3*demiD)/4,listeCentre[c].y+demiD/2,tailleCase/3,tailleCase/3,null);
	            	}
	            	f++;

	            }
	            if(i==0 /*&& matrice.estJouableRGauche(j, matrice.jBlanc)*/){ // vers la gauche
	            	
	            	
	            	if(f==5)
	            		f++;
	            	listeFleche[f]=new Rectangle(listeCentre[c].x-demiD,listeCentre[c].y+demiD-(demiD/3),tailleCase/3,tailleCase/3);
	            	
	            	if(estDanslisteFlecheBlanc(f) && !debut){
	            		drawable.drawImage(fg,listeCentre[c].x-demiD,listeCentre[c].y+demiD-(demiD/3),tailleCase/3,tailleCase/3,null);
	            	}
	            	
	            	if(clicfleche==f){
	            		drawable.drawImage(fg2,listeCentre[c].x-demiD,listeCentre[c].y+demiD-(demiD/3),tailleCase/3,tailleCase/3,null);
	            	}
	            	f++;
	            }
	            
        	
	            /////////////////////////////	          
        		c++;
        	}//fin if i!=0 et j!=0

        	}  //fin boucle for

       	}//fin boucle for

        
        
        ///////////////////////////////////////////////////////////////////////////////////
        
        
        ///// AFFICHER UNE SUGGESTION DE COUP ////
        if(coupdepouce && !debut){ // mettre en evidence la liste des coups jouables
        	Color v= new Color(0,250,0);//new Color(165, 38, 10);
        	drawable.setStroke(new BasicStroke( 2.0f ));
			IA IA=new IA();
        	CoupJouable cj;
        	cj=IA.hard(matrice,5);	  
			
			Polygon p;
				if(cj.pDep.x!=-1 && cj.pDep.y!=-1 && cj.pArr.x!=-1 && cj.pArr.x!=-1){
	        		p = (Polygon) listeCase[5*cj.pDep.x+cj.pDep.y];
	        		drawable.setPaint(v);
	        		drawable.fillPolygon(p);
	        		drawable.setPaint(Color.black);
		  			drawable.drawPolygon(p);

	       			p = (Polygon) listeCase[5*cj.pArr.x+cj.pArr.y];
	       			drawable.setPaint(v);
	        		drawable.fillPolygon(p);
	        		drawable.setPaint(Color.black);
		  			drawable.drawPolygon(p);
		  			v.brighter();
		  			v.brighter();
				}else if(cj.colonne!=-1){
					int t;
					for(t=0;t<5;t++){
						p= (Polygon) listeCase[t+5*cj.colonne];
						drawable.setPaint(v);
		        		drawable.fillPolygon(p);
		        		drawable.setPaint(Color.black);
			  			drawable.drawPolygon(p);
					}
					t--;
					if(cj.sens) //vers le haut
						drawable.drawImage(fh2,listeCentre[t+5*cj.colonne].x-demiD,listeCentre[t+5*cj.colonne].y-demiD,tailleCase/3,tailleCase/3,null);
					else		//vers le bas
						drawable.drawImage(fb2,listeCentre[5*cj.colonne].x+(3*demiD)/4,listeCentre[5*cj.colonne].y+demiD/2,tailleCase/3,tailleCase/3,null);
					
				}else if(cj.rangee!=-1){
					int t;
					for(t=0;t<5;t++){
						p= (Polygon) listeCase[t*5+cj.rangee];
						drawable.setPaint(v);
		        		drawable.fillPolygon(p);
		        		drawable.setPaint(Color.black);
			  			drawable.drawPolygon(p);
					}
					t--;
					if(cj.sens) //vers la droite
						drawable.drawImage(fd2,listeCentre[t*5+cj.rangee].x+demiD/2,listeCentre[t*5+cj.rangee].y-demiD,tailleCase/3,tailleCase/3,null);
					else		//vers la gauche
						drawable.drawImage(fg2,listeCentre[cj.rangee].x-demiD,listeCentre[cj.rangee].y+demiD-(demiD/3),tailleCase/3,tailleCase/3,null);	
				}
				
				
				if(!debut){//dessiner les billes
		          for(i=0;i<5;i++){
		        	  for(j=0;j<5;j++){
			        		if(matrice.echiquier[i][j].contenu==1){
			        			drawable.drawImage(bb,listeCentre[i*5+j].x-demiD/3,listeCentre[i*5+j].y-demiD/3,tailleCase/2,tailleCase/2,null);
			        		}
			        		if(matrice.echiquier[i][j].contenu==2){
			        			drawable.drawImage(bn,listeCentre[i*5+j].x-demiD/3,listeCentre[i*5+j].y-demiD/3,tailleCase/2,tailleCase/2,null);
			        		}
		        	  }
		          }
        		
        		}
			
			coupdepouce=false;
			
			
        }//fin if coupdepouce
   
        
        // Remplit en blanc les fleches qui ne sont pas cliquables
        // avec la liste des colonnes et rangees, on calcul avec colFleche et rangFleche les numeros de fleches correspondant et on remplit le Shape en blanc
        
       /* if(!debut){
	       
	        //remplir en blanc toutes les fleches qui ne font pas partie des listeColonnes et listeRangee

	        for(int x=0;x<20;x++){
	        	if(!estDanslisteFlecheBlanc(x)){
		        	Rectangle r = (Rectangle) listeFleche[x];
		        	drawable.fillRect(r.x,r.y,r.width,r.height);
	        	}
	        }
	       
        }  */ 
	        //vides les listes des coup jouables
	        for(i=0;i<listeColonne.size();i++){
	        	listeColonne.remove(i);
	        }
	        for(i=0;i<listeRangee.size();i++){
	        	listeRangee.remove(i);
	        }
	        
	      
        
        
 }//fin paint component
        
        
	public int calculNUmeroCase(int x, int y){
		int i=0;
		Point p=null;
		while(i<25 && !listeCase[i].contains(x,y)){
			i++;
		}
		if(i!=25)
			return i;
		else return -1;
	}
	
	
        
    public Point  calculIndice (int i){
    	
    	  Point p=new Point();
    	  
    	  if(i!=-1){
    		 p.x=i/5;
    		 p.y=i%5;
    	  }
    	  
    	  return p;
      }
      
      
	public boolean clicBille (Point p, int i, int x, int y){
		boolean b=false;
		int rayon = tailleCase/4;
		int d;
		if(p!=null && matrice.echiquier[p.x][p.y].contenu!=0 && i!=-1){
			 d= (int) Math.sqrt(Math.pow(listeCentre[i].x-x,2)+Math.pow(listeCentre[i].y-y,2));
			if (rayon > (Math.sqrt(Math.pow(listeCentre[i].x-x,2)+Math.pow(listeCentre[i].y-y,2)))) 
				b=true;
		}
		return b;
	}
	
    public int clicFleche(double x, double y){
    	int i=0;
    	while(i<20 && !(listeFleche[i].contains(x,y))){
    		i++;	
    	}
   
    	if(i!=25){
    		return i;
    	}
    	else	
    		return -1;
    	
    }
    
    
  
    
    public int direction (int i){ // nord sud est ouest respectivement 1 2 3 4
    	Point p;
    	switch(i){
    	case 0:
    		return 2;
    	case 1:
    		return 4;
    	case 2:
    		return 4;
    	case 3:
    		return 4;	
    	case 4:
    		return 1;
    	case 5:
    		return 4;	
    	case 6:
    		return 4;
    	case 7:
    		return 2;	
    	case 8:
    		return 1;
    	case 9:
    		return 2;	
    	case 10:
    		return 1;
    	case 11:
    		return 2;	
    	case 12:
    		return 1;
    	case 13:
    		return 3;	
    	case 14:
    		return 2;
    	case 15:
    		return 3;	
    	case 16:
    		return 3;
    	case 17:
    		return 3;	
    	case 18:
    		return 1;
    	case 19:
    		return 3;
    	default:;
    	}
    	return -1;
    	
    }
    
  
    
    public int colFleche(int c,boolean sensH){
    	int f=-1;
    	if(sensH){
    		switch(c){
	    		case 0:
	        		return 4;
	        	case 1:
	        		return 8;
	        	case 2:
	        		return 10;
	        	case 3:
	        		return 12;	
	        	case 4:
	        		return 18;
	        	default:;
    		}
    	}
    	else{
    		switch(c){
    		case 0:
        		return 0;
        	case 1:
        		return 7;
        	case 2:
        		return 9;
        	case 3:
        		return 11;	
        	case 4:
        		return 14;
        	default:;
		}
    	}
    	
    	return f;
    }
    
    
    public int rangFleche(int r, boolean sensH){
    	int f=-1;
    	if(sensH){
    		switch(r){
	    		case 0:
	        		return 13;
	        	case 1:
	        		return 15;
	        	case 2:
	        		return 16;
	        	case 3:
	        		return 17;	
	        	case 4:
	        		return 19;
	        	default:;
    		}
    	}
    	else{
    		switch(r){
    		case 0:
        		return 5;
        	case 1:
        		return 1;
        	case 2:
        		return 2;
        	case 3:
        		return 3;	
        	case 4:
        		return 6;
        	default:;
		}
    	}
    	
    	return f;
    }
        
}
