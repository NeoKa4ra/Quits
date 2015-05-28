import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class EcouteurRetransmetteur implements MouseListener {
    MyGlassPane glass;
    Container content;
    Donnees d;

    public EcouteurRetransmetteur(MyGlassPane g, Container c, Donnees donnees) {
        glass = g;
        content = c;
        d = donnees;
    }

    // Le GlassPane interceptant tous les evenements, il nous faut une methode pour
    // retransmettre ceux qui concernent d'autres composants
    public void forwardEvent(MouseEvent e) {
        Point p = e.getPoint();
        Point cp = SwingUtilities.convertPoint(glass, p, content);
        Component c = SwingUtilities.getDeepestComponentAt(content, cp.x, cp.y);
        Point dp = SwingUtilities.convertPoint(content, p, c);
        c.dispatchEvent(new MouseEvent(c,
                                       e.getID(),
                                       e.getWhen(),
                                       e.getModifiers(),
                                       dp.x,
                                       dp.y,
                                       e.getClickCount(),
                                       e.isPopupTrigger()));
    }

    // Lors d'une pression de bouton, on trace l'image selectionnee
	// Dans les deux methodes ci-dessous, le d.selected = false sert a gerer le cas
	// ou le clic est en dehors des composants de l'application
    public void mousePressed(MouseEvent e) {
        d.selected = false;
        forwardEvent(e);
        glass.dessiner(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {
        forwardEvent(e);
        d.selected = false;
        glass.repaint();
    }

    // Il faut aussi une implementation pour les autres methodes de l'interface
    public void mouseEntered(MouseEvent e) {} //forwardEvent(e);}
    public void mouseExited(MouseEvent e) {} //forwardEvent(e);}
    public void mouseClicked(MouseEvent e) {} //forwardEvent(e);}
}
