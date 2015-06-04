import java.awt.*;
import javax.swing.*;

class MyGlassPane extends JComponent {
	
PlateauGraphique plateau;
    int selection, x, y;
    Graphics2D drawable;
    

    MyGlassPane(PlateauGraphique plateau) {
    	this.plateau = plateau;
    }

    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;

        if (plateau.selectionBille) {
        	if(plateau.matrice.echiquier[plateau.depart.x][plateau.depart.y].contenu==1){
    			drawable.drawImage(plateau.bb,x,y,plateau.tailleCase/2,plateau.tailleCase/2,null);
    		}
    		if(plateau.matrice.echiquier[plateau.depart.x][plateau.depart.y].contenu==2){
    			drawable.drawImage(plateau.bn,x,y,plateau.tailleCase/2,plateau.tailleCase/2,null);
    		}
        }
       
    }

    public void dessiner(int x, int y) {
        if (plateau.selectionBille) {
        	this.x=x-plateau.tailleCase/4;
        	this.y=y-plateau.tailleCase/4;
            repaint();
        }
    }
}