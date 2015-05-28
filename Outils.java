import java.awt.*;

import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Outils extends JPanel{
	

	Outils(Sauvegarde sauvegarde){

		JButton aide=new JButton("aide");
		JButton coupdepouce=new JButton ("coupdepouce");
		JButton annuler=new JButton("annuler");
		JButton refaire=new JButton("refaire");
		
		aide.addActionListener(new EcouteurDeBouton(sauvegarde));
		coupdepouce.addActionListener(new EcouteurDeBouton(sauvegarde));
		annuler.addActionListener(new EcouteurDeBouton(sauvegarde));
		refaire.addActionListener(new EcouteurDeBouton(sauvegarde));

		
		add(aide);
		add(coupdepouce);
		add(annuler);
		add(refaire);
		
	}
	
	public void paintComponent(Graphics g) {
	}
	
}
