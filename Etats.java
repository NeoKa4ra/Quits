import java.awt.*;

import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Etats extends JComponent{
	String message;
	int score;
	int joueur;
	int tour;
	Image bb,bn,flecheN, flecheJ;
	boolean debut;
	
	Etats(int i){
		debut=true;
		message="oui";
		score=0;
		joueur=i;
		if(joueur==1)
			tour=1;
		else 
			tour=0;
		try { 
			bb=ImageIO.read(new File("Boule-Blanche.png")); 
			bn=ImageIO.read(new File("Boule-Noire.png"));
			flecheN=ImageIO.read(new File("Joueur1.png"));
			flecheJ=ImageIO.read(new File("Joueur2.png"));
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}	
	}
	
	public void inverserJoueur(){
		joueur=3-joueur;
		tour=1-tour;
	}
	

	
	
	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;
		//drawable.rotate(Math.toRadians(45));
		
		int w=getSize().width;
		int h=getSize().height;
		drawable.setPaint(new Color(187, 174, 152));
		drawable.fillRect(0, 0, getSize().width, getSize().height);
		drawable.setPaint(Color.black);
		drawable.drawString(message,0,0);
		int x=w-h;
		int y=0;
		if(debut==false){
			if(tour==1 && joueur==1) //c'est mon tour
				drawable.drawImage(flecheN,w/10,0,w/10,h,null);
		
			if(tour==1 && joueur==2) //c'est mon tour
				drawable.drawImage(flecheJ,w/10,0,w/10,h,null);
		
			drawable.drawString(message,w/10+10,0);
			for(int i=0;i<score;i++){
				if(joueur==1)
					drawable.drawImage(bn,x,y,h,h,null);
				if(joueur==2)
					drawable.drawImage(bb,x,y,h,h,null);
				x=x-h;
			}
		}
		
	}
	
}