import java.awt.*;

import javax.swing.JComponent;

public class PlateauGraphique extends JComponent {
	Plateau plateau;
	
	// constructeur
	PlateauGraphique(Plateau plateau){
		this.plateau=plateau;
	}
	
    public void paintComponent(Graphics g) {

        // Graphics 2D est le vrai type de l'objet pass� en param�tre
        // Le cast permet d'avoir acces a un peu plus de primitives de dessin
        Graphics2D drawable = (Graphics2D) g;
        g.drawRoundRect (5, 15, 50, 75, 20, 15);

    }
	
	
	
}
