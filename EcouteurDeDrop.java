import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class EcouteurDeDrop implements MouseListener {
    Donnees d;
    Plateau matrice;
   // AireDeDessin aire;
    PlateauGraphique plateau;

    // Ecouteur de souris a besoin de connaitre l'aire pour y tracer des choses
    public EcouteurDeDrop(Donnees donnees, PlateauGraphique plateau) {
        d = donnees;
        matrice = d.matrice;
        this.plateau = plateau;
    }
	

    // Lors d'un relachement de bouton, on trace l'image selectionnee, s'il y
    // en a une
    public void mouseReleased(MouseEvent e) {
    	//int i = 2;
    	//if(i == 2){
        if (d.selected) {
        	CaseGraphique c = (CaseGraphique) e.getSource();
          
            d.selected = false;
            d.depart=d.billes[d.selection].p;
            d.arrivee=c.p;

            
            if(Moteur.pointJouable(d.depart,d.arrivee,d.matrice)){
            	  c.add(d.billes[d.selection]);
                  d.billes[d.selection].Change(d.arrivee);
            }      
            

            


        }
    	//}
    }

    // Il faut aussi une implementation pour les autres methodes de l'interface
    public void mouseEntered(MouseEvent e) {
        System.out.println("toto");
    }
    public void mouseExited(MouseEvent e) {
        System.out.println("toto1");
    }
    public void mouseClicked(MouseEvent e) {
        System.out.println("toto2");
    }
    public void mousePressed(MouseEvent e) {
        System.out.println("toto3");
    }
}

