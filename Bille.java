import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Bille extends JPanel {
	Color c;
	public Point p;
	
	Bille(Color c,int x,int y){
		setOpaque(false);
		this.c = c;
		p=new Point(x,y);
	}
	
	public void Change(Point p){
		this.p=new Point(p.x,p.y);
	}
	
	public void paintComponent(Graphics g){
		Graphics2D drawable = (Graphics2D) g;
		
		//drawable.drawImage(img, getWidth()/3, getWidth()/3, null);
		drawable.setPaint(c);

			drawable.fillOval((getWidth()/3)-5, getWidth()/3, getWidth()-90, getHeight()-90);
		
	}
}
