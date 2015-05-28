import java.awt.*;

import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Etats extends JPanel{

	Etats(){
		JLabel Etatjoueur1 = new JLabel("Joueur 1");
		JLabel Etatjoueur2 = new JLabel("Joueur 2");
		setLayout(new BorderLayout());
		add(Etatjoueur1,BorderLayout.WEST);
		add(Etatjoueur2,BorderLayout.EAST);
		
	}
	
	public void paintComponent(Graphics g) {
	}
	
}