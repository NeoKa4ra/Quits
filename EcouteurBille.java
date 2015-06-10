import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class EcouteurBille implements MouseListener{
    
    PlateauGraphique plateau;

    
    public EcouteurBille(PlateauGraphique plateau) {
    	this.plateau=plateau;

    }

    public void mousePressed(MouseEvent e) {
     
      int x=e.getX();
      int y=e.getY();
       
      int i= plateau.calculNUmeroCase(x, y);
      Point p = plateau.calculIndice(i);
      if(plateau.clicBille(p,i,x,y)){
    	  plateau.selectionBille=true;
    	  plateau.depart=p;
      }
      plateau.coupPossible=(plateau.matrice.jBlanc && plateau.matrice.echiquier[plateau.depart.x][plateau.depart.y].estBlanc()) || (!plateau.matrice.jBlanc && plateau.matrice.echiquier[plateau.depart.x][plateau.depart.y].estMarron());
      plateau.repaint();
    }

    // Il faut aussi une implementation pour les autres methodes de l'interface
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
