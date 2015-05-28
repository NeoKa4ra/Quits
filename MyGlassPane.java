import java.awt.*;
import javax.swing.*;

class MyGlassPane extends JComponent {
    Donnees d;
    int selection, x, y;
    Graphics2D drawable;

    MyGlassPane(Donnees donnees) {
       d = donnees;
    }

    public void paintComponent(Graphics g) {
        drawable = (Graphics2D) g;

        if (d.selected) {
            //drawable.drawImage(d.billes[d.selection], x, y, null);
        	if(d.selection >= 0 && d.selection <=4)
        		drawable.setPaint(Color.black);
        	else
        		drawable.setPaint(Color.gray);
        	
            drawable.fillOval(x, y, d.billes[d.selection].getWidth()-90, d.billes[d.selection].getHeight()-90);
        }
       
    }

    public void dessiner(int x, int y) {
        if (d.selected) {
    		
            this.x = x;
            this.y = y;
            repaint();
        }
    }
}