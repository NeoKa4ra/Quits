import java.awt.*;
import java.awt.event.MouseListener;

import java.awt.event.*;
import javax.swing.*;

public class MonBouton extends JButton implements MouseListener {
	 String nom;
	 Image img, img2;
	 EcouteurDeBouton e;
	 boolean ok;
	 
	MonBouton(String nom, Image img, Image img2){
		this.nom=nom;
		this.img=img;
		this.img2=img2;
		ok=false;
		//this.e=e;
		//addActionListener(e);
		setBorderPainted(false);
		setPreferredSize(new Dimension(150,20));
	}
	
	public void paintComponent(Graphics g){ 
		Graphics2D drawable = (Graphics2D) g;
		
		if(ok)
			drawable.drawImage(img2,0,0,getSize().width, getSize().height, null);
		else
			drawable.drawImage(img,0,0,getSize().width, getSize().height, null);
	}

	 	public void mouseReleased(MouseEvent e) {	
	    }
	
	    // Il faut aussi une implementation pour les autres methodes de l'interface
	    public void mouseEntered(MouseEvent e) {
	    	
	    	ok=true;
	    	repaint();
	    }
	    public void mouseExited(MouseEvent e) {
	    }
	    public void mouseClicked(MouseEvent e) {
	    	
	    	ok=true;
	    	repaint();
	    	
	    }
	    public void mousePressed(MouseEvent e) {
	    	
	    	ok=true;
	    	repaint();
	    }
	
	
	
	
}
