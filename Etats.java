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
	Image bb,bn,flecheN, flecheJ,scr;
	boolean debut;
	int H,i;
	
	
	Etats(int i, int h){
		this.H=h;
		setPreferredSize(new Dimension(100, H));
		debut=true;
		score=0;
		this.i=i;
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
			scr=ImageIO.read(new File("Score.png"));
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}	
	}
	
	
	public void paintComponent(Graphics g) {
		Graphics2D drawable = (Graphics2D) g;
		//drawable.rotate(Math.toRadians(45));
		
		
		//setPreferredSize(new Dimension(100,100));
		int w=getSize().width;
		int h=getSize().height;
		drawable.setPaint(new Color(132, 46, 27));
		drawable.fillRect(0, 0, getSize().width, getSize().height);
		drawable.setPaint(Color.black);
	
		int x=w-h;
		int y=0;
		if(debut==false){
			if(i==1) //c'est mon tour
				drawable.drawImage(flecheN,w/5,0,w/10,h,null);
		
			if(i==2) //c'est mon tour
				drawable.drawImage(flecheJ,w/5,0,w/10,h,null);
			
			for(int i=0;i<3;i++){
				drawable.setPaint(new Color(136, 66, 29));
				drawable.fillOval(x,y,h-(h/8),h-(h/8));
				x=x-h;
			}
			
			
			//affichage image score
			
			drawable.drawImage(scr,w-(3*h)-w/7,h/4,w/15,h/2,null);
			
			
			if(tour==1) {//c'est mon tour
				if(joueur==1) //c'est mon tour
					drawable.drawImage(bn,w/5+w/10+w/50,0,h,h,null);
			
				if(joueur==2) //c'est mon tour
					drawable.drawImage(bb,w/5+w/10+w/50,0,h,h,null);
			}
			
			
			x=w-h;
			for(int i=0;i<score;i++){
				if(joueur==1)
					drawable.drawImage(bn,x,y,h,h,null);
				if(joueur==2)
					drawable.drawImage(bb,x,y,h,h,null);
				x=x-h;
			}
		}else{
			if(joueur==1) //c'est mon tour
				drawable.drawImage(flecheN,w/5,0,w/8,h,null);
		
			if(joueur==2) //c'est mon tour
				drawable.drawImage(flecheJ,w/5,0,w/8,h,null);
			
			for(int i=0;i<5;i++){
				if(joueur==1)
					drawable.drawImage(bn,x,y,h,h,null);
				if(joueur==2)
					drawable.drawImage(bb,x,y,h,h,null);
				x=x-h;
			}
			
		}
		
	}
	
}