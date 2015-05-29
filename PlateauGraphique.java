import java.awt.*;

import javax.swing.*;

//import java.io.File;
//import java.io.IOException;

//import javax.imageio.ImageIO;
//import javax.swing.*;

public class PlateauGraphique extends JComponent{
	
	Donnees d;
	Graphics2D drawable;
	Plateau matrice;
	CaseGraphique [][] plateauGraphique;

	PlateauGraphique(Donnees donnees){
		d = donnees;
		this.matrice = d.matrice;
		setLayout(null);
		
		plateauGraphique=new CaseGraphique[5][5];
	
	}

	public void init () {

				plateauGraphique[0][0]=ajouterCase(380, 650, matrice.echiquier[0][0].numBille(),0,0);
				plateauGraphique[1][0]=ajouterCase(460, 570, matrice.echiquier[1][0].numBille(),1,0);
				plateauGraphique[2][0]=ajouterCase(540, 490, matrice.echiquier[2][0].numBille(),2,0);
				plateauGraphique[3][0]=ajouterCase(620, 410, matrice.echiquier[3][0].numBille(),3,0);
				plateauGraphique[4][0]=ajouterCase(700, 330, matrice.echiquier[4][0].numBille(),4,0); //droite
				
				plateauGraphique[0][1]=ajouterCase(300, 570, matrice.echiquier[0][1].numBille(),0,1);
				plateauGraphique[0][2]=ajouterCase(220, 490, matrice.echiquier[0][2].numBille(),0,2);
				plateauGraphique[0][3]=ajouterCase(140, 410, matrice.echiquier[0][3].numBille(),0,3);
				plateauGraphique[0][4]=ajouterCase(60, 330, matrice.echiquier[0][4].numBille(),0,4); // gauche
				
				plateauGraphique[1][1]=ajouterCase(380, 490, matrice.echiquier[1][1].numBille(),1,1);
				
				plateauGraphique[2][1]=ajouterCase(460, 410, matrice.echiquier[2][1].numBille(),2,1);
				plateauGraphique[3][1]=ajouterCase(540, 330, matrice.echiquier[3][1].numBille(),3,1);
				plateauGraphique[4][1]=ajouterCase(620, 250, matrice.echiquier[4][1].numBille(),4,1); //d
				
				plateauGraphique[1][2]=ajouterCase(300, 410, matrice.echiquier[1][2].numBille(),1,2);
				plateauGraphique[1][3]=ajouterCase(220, 330, matrice.echiquier[1][3].numBille(),1,3);
				plateauGraphique[1][4]=ajouterCase(140, 250, matrice.echiquier[1][4].numBille(),1,4); //g
				
				
				plateauGraphique[2][2]=ajouterCase(380, 330, matrice.echiquier[2][2].numBille(),2,2);
				
				plateauGraphique[3][2]=ajouterCase(460, 250, matrice.echiquier[3][2].numBille(),3,2);
				plateauGraphique[4][2]=ajouterCase(540, 170, matrice.echiquier[4][2].numBille(),4,2); // d
				
				plateauGraphique[2][3]=ajouterCase(300, 250, matrice.echiquier[2][3].numBille(),2,3);
				plateauGraphique[2][4]=ajouterCase(220, 170, matrice.echiquier[2][4].numBille(),2,4); //g
				
				plateauGraphique[3][3]=ajouterCase(380, 170,matrice.echiquier[3][3].numBille(),3,3); //m
				
				plateauGraphique[4][3]=ajouterCase(460, 90,matrice.echiquier[4][3].numBille(),4,3); //d
				
				plateauGraphique[3][4]=ajouterCase(300, 90, matrice.echiquier[3][4].numBille(),3,4); //g
				
				plateauGraphique[4][4]=ajouterCase(380, 10,matrice.echiquier[4][4].numBille(),4,4); // s

				ajouterFleche(455, 0, 0);
				ajouterFleche(530, 70, 1);
				ajouterFleche(605, 140, 2);
				ajouterFleche(690, 220, 3);
				ajouterFleche(760, 290, 4);
				
				ajouterFleche(290, 0, 5);
				ajouterFleche(210, 70, 6);
				ajouterFleche(135, 145, 7);
				ajouterFleche(65, 220, 8);
				ajouterFleche(-20, 300, 9);
				
				ajouterFleche(450, 750, 10);
				ajouterFleche(530, 680, 11);
				ajouterFleche(600, 610, 12);
				ajouterFleche(680, 530, 13);
				ajouterFleche(760, 450, 14);
				
				ajouterFleche(-20, 460, 15);
				ajouterFleche(50, 530, 16);
				ajouterFleche(120, 600, 17);
				ajouterFleche(210, 690, 18);
				ajouterFleche(290, 760, 19);
				
	}
	

	public CaseGraphique ajouterCase(int x, int y, int num_bille, int l, int h){
		CaseGraphique c = new CaseGraphique(l,h);
		
		c.addMouseListener(new EcouteurDeDrop(d, this));
		if(num_bille!=-1){
			
			d.billes[num_bille].addMouseListener(new EcouteurBille(num_bille, d));
			c.add(d.billes[num_bille]);
			
		}
	    c.setBounds(x, y, 150, 150);
	    add(c);
		return c;
	}
	
	


	
	public void paintComponent(Graphics g) {
	       

		// Graphics 2D est le vrai type de l'objet passe en parametre
		// Le cast permet d'avoir acces a un peu plus de primitives de dessin
		drawable = (Graphics2D) g;
		//drawable.rotate(Math.toRadians(45));
		
		drawable.setPaint(Color.white);
		drawable.fillRect(0, 0, getSize().width, getSize().height);
        	
		
   
        
	}
	
	public void ajouterFleche(int x, int y, int i){
		d.fleches[i].setBounds(x, y, 110, 110);
		d.fleches[i].addMouseListener(new EcouteurFleche(i,d,this));
		add(d.fleches[i]);
	}
	
        
        
	
        
        
}

