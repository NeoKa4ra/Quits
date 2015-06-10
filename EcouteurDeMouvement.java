import java.awt.Color;
import java.awt.event.*;

class EcouteurDeMouvement implements MouseMotionListener {
    MyGlassPane glass;
    PlateauGraphique p;

    EcouteurDeMouvement(MyGlassPane g,  PlateauGraphique p) {
    	this.p=p;
        glass = g;
    }

    public void mouseDragged(MouseEvent e) {

        glass.dessiner(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {}
}