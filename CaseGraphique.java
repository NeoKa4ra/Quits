import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JPanel;


public class CaseGraphique extends JPanel {
	
	//Couleur couleur;
	boolean selectionnee;
	//int caseLargeur;
	//int caseHauteur;
	int contenu;
	public Point p;
	
	public CaseGraphique(int x, int y){
		setLayout(new GridLayout(1,0));
		//this.contenu=contenu;
		p = new Point(x,y);
	}

	public void paintComponent(Graphics g){
		
		Graphics2D drawable = (Graphics2D) g;
		//drawable.rotate(Math.toRadians(45));
		
		/*drawable.setPaint(Color.orange);
		drawable.fillRoundRect(0,0, getWidth(),getHeight(), 40, 40);
		drawable.setPaint(Color.black);
		drawable.drawRoundRect(0,0, getWidth(),getHeight(), 40, 40);*/
		
		int largeur = getSize().width;
	    int hauteur = getSize().height;
	    int dl = largeur/2, dh = hauteur/2;
	    int [] polx = { 0, dl, largeur, dl};
	    int [] poly = {dh, 0, dh, hauteur};
	    Polygon pol = new Polygon(polx,poly,4);
	    
	    drawable.setPaint(Color.orange);
	    drawable.fillPolygon(pol);
	    drawable.setPaint(Color.black);
	    drawable.drawPolygon(pol);
	    drawable.setPaint(Color.black);
	
	}
}
