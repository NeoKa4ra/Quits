import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class PlateauGraphique extends JComponent{
	
	
	Graphics2D drawable;
	Plateau matrice;
	Shape [] listeCase;
	Shape [] listeFleche;
	Point [] listeCentre;
	Point depart;
	Image bb,bn,fg,fh,fd,fb,img,caseG;
	int tailleCase;
	boolean selectionBille;
	public boolean joueur_joue;
	public JButton annuler;
	public JButton refaire;
	public int fleche;
	Moteur m;
	boolean debut;
	boolean couleurInverse;
	Etats j1;
	Etats j2;
	
	PlateauGraphique(Plateau matrice,Moteur m,Etats j1, Etats j2){
		this.j1=j1;
		this.j2=j2;
		couleurInverse=false;
		debut=true;
		this.matrice = matrice;
		setLayout(null);
		listeCase = new Shape[25];
		listeCentre = new Point[25];
		listeFleche = new Shape[20];
		tailleCase=0;
		depart= new Point();
		addMouseListener(new EcouteurBille(this));
		addMouseListener(new EcouteurDeDrop(this,m,j1,j2));
		addMouseListener(new EcouteurFleche(this,m,j1,j2));
		selectionBille=false;
		joueur_joue=true;
		fleche=0;
	}

	public void init () {

		try { 
			caseG=ImageIO.read(new File("case.png")); 
			img=ImageIO.read(new File("quits-image.jpg")); 
			bb=ImageIO.read(new File("Boule-Blanche.png")); 
			bn=ImageIO.read(new File("Boule-Noire.png")); 
			fd=ImageIO.read(new File("haut.png"));
			fh=ImageIO.read(new File("droite.png"));
		    fb=ImageIO.read(new File("bas.png"));
		    fg=ImageIO.read(new File("gauche.png"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}	
		
				
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

	public void paintComponent(Graphics g) {
	       

		// Graphics 2D est le vrai type de l'objet passe en parametre
		// Le cast permet d'avoir acces a un peu plus de primitives de dessin
		drawable = (Graphics2D) g;
		//drawable.rotate(Math.toRadians(45));
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, getSize().width, getSize().height);
		
		if(couleurInverse){
			inverserCouleurJoueur();
		}

		int width = getSize().width; 
		int height = getSize().height;
		
		int a=width/2;
		int b=height-100;
		int a1=width/2-50;
		int b1=height-100-50;
		if(debut){
			drawable.drawImage(img,0,0, width, height,null);

		}
		else {
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, getSize().width, getSize().height);
		//drawable.setStroke(new BasicStroke( 2.0f ));
		
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
		int d= (int) Math.sqrt(Math.pow(tailleCase,2)+Math.pow(tailleCase,2)); 
		
		w=(int) d*5;
		h=(int) d*5;	
		demiD=(int) d/2;
		margeX = width-(d*5);
		margeY = height -(d*5);
	
		int [] init_polX= {w/2+margeX/2,w/2+demiD+margeX/2,w/2+margeX/2,w/2-demiD+margeX/2};
		int [] init_polY= {h+margeY/2,h-demiD+margeY/2,h-d+margeY/2,h-demiD+margeY/2};
		
		
		listeCentre[0]=new Point((int) init_polX[0], (int) init_polY[2]+demiD ); 
		listeCase[0] = new Polygon(init_polX,init_polY,4);
	
		drawable.drawImage(caseG,(int) (init_polX[0]-demiD),(int) (init_polY[0]-diagonale),d,d,null);
		
		if(matrice.echiquier[0][0].contenu==1){
			drawable.drawImage(bb,listeCentre[0].x-demiD/3,listeCentre[0].y-demiD/3,tailleCase/2,tailleCase/2,null);		}
		if(matrice.echiquier[0][0].contenu==2){
			drawable.drawImage(bn,listeCentre[0].x-demiD/3,listeCentre[0].y-demiD/3,tailleCase/2,tailleCase/2,null);		}
    	
		drawable.drawImage(fb,listeCentre[0].x+(3*demiD)/4,listeCentre[0].y+(demiD)/2,tailleCase/3,tailleCase/3,null);
    	drawable.drawImage(fg,listeCentre[0].x-demiD,listeCentre[0].y+demiD-(demiD/3),tailleCase/3,tailleCase/3,null);
    	
	    	listeFleche[0]=new Rectangle(listeCentre[0].x+(3*demiD)/4,listeCentre[0].y+demiD/2,tailleCase/3,tailleCase/3);
	    	listeFleche[5]=new Rectangle(listeCentre[0].x-demiD,listeCentre[0].y+demiD-(demiD/3),tailleCase/3,tailleCase/3);

    	int [] polX= {w/2+margeX/2,w/2+demiD+margeX/2,w/2+margeX/2,w/2-demiD+margeX/2};
		int [] polY= {h+margeY/2,h-demiD+margeY/2,h-d+margeY/2,h-demiD+margeY/2};
	   
		
        
        // dessiner la grille 
        int c=1, f=1;	int l=1;
        int i,j;
        
        for(i=0;i<5;i++){
        	for(j=0;j<5;j++){
        	
        		
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
	        	
	            
	            //dessiner les billes
        		if(matrice.echiquier[i][j].contenu==1){
        			drawable.drawImage(bb,listeCentre[c].x-demiD/3,listeCentre[c].y-demiD/3,tailleCase/2,tailleCase/2,null);
        		}
        		if(matrice.echiquier[i][j].contenu==2){
        			drawable.drawImage(bn,listeCentre[c].x-demiD/3,listeCentre[c].y-demiD/3,tailleCase/2,tailleCase/2,null);
        		}
        		
        		//dessiner les fleches

	            if(j==4){ // vers le haut
	            	drawable.drawImage(fh,listeCentre[c].x-demiD,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3,null);
	            	if(f==5)
	            		f++;	
	            	listeFleche[f]=new Rectangle(listeCentre[c].x-demiD,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3);
	            	f++;	
	            }
	            if(i==4){ // vers la droite
	            	drawable.drawImage(fd,listeCentre[c].x+demiD/2,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3,null);
	            	if(f==5)
	            		f++;
	            	listeFleche[f]=new Rectangle(listeCentre[c].x+demiD/2,listeCentre[c].y-demiD,tailleCase/3,tailleCase/3);
	            	f++;

	            }
	            if(j==0 ){ // vers le bas
	            	drawable.drawImage(fb,listeCentre[c].x+(3*demiD)/4,listeCentre[c].y+demiD/2,tailleCase/3,tailleCase/3,null);
	            	if(f==5)
	            		f++;
	            	listeFleche[f]=new Rectangle(listeCentre[c].x+(3*demiD)/4,listeCentre[c].y+demiD/2,tailleCase/3,tailleCase/3);
	            	f++;

	            }
	            if(i==0){ // vers la gauche
	            	drawable.drawImage(fg,listeCentre[c].x-demiD,listeCentre[c].y+demiD-(demiD/3),tailleCase/3,tailleCase/3,null);
	            	if(f==5)
	            		f++;
	            	listeFleche[f]=new Rectangle(listeCentre[c].x-demiD,listeCentre[c].y+demiD-(demiD/3),tailleCase/3,tailleCase/3);
	            	f++;
	            }
        		c++;
        		
        		
        		}
        	}
        }
    
         }       
        
	}
	
	
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
    
  
    
    public void afficherFleche(){
    	for(int i=0;i<20;i++)
    		System.out.println(i+" "+ listeFleche[i]);
    	
    }
    
        
}
