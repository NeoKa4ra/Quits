import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

class EcouteurBille implements MouseListener {
    Donnees d;
    int selection;
    int x, y;

    // Ecouteur d'action a besoin de connaitre les donnees pour effectuer la
    // selection
    public EcouteurBille(int i, Donnees donnees) {
        selection = i;
        d = donnees;
        //this.x = x;
        //this.y = y;

    }

    public void mousePressed(MouseEvent e) {
        d.selection = selection;
        d.selected = true;
        
        
    }

    // Il faut aussi une implementation pour les autres methodes de l'interface
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
