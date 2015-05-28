import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Fleche extends JPanel {
	Image img;
	//int x, y;
	Color c;
	
	Fleche(File f){
		//setLayout(new GridLayout(1,0));
		setOpaque(false);
		try {
			img = ImageIO.read(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//this.x = x;
		//this.y = y;
	}
	
	public void paintComponent(Graphics g){
		Graphics2D drawable = (Graphics2D) g;
		
		drawable.drawImage(img, 60, 5, null);
		//drawable.setPaint(Color.black);
		//System.out.println();
		//drawable.fillOval(getWidth()/3, getHeight()/3, 50, 50);
		
	}
}
