import java.awt.*;

import javax.swing.*;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Outils extends JPanel{
	JButton aide;
	JButton coupdepouce;
	JButton annuler;
	JButton refaire;

	Outils(Sauvegarde sauvegarde,PlateauGraphique plateau, Etats j1,  Etats j2, Moteur m){

		ImageIcon icon = new ImageIcon(new ImageIcon("aide.jpeg").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		aide=new JButton(icon);
		aide.setPreferredSize(new Dimension(30, 30));
		
		icon = new ImageIcon(new ImageIcon("aide.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		coupdepouce=new JButton(icon);
		coupdepouce.setPreferredSize(new Dimension(30, 30));
		
		icon = new ImageIcon(new ImageIcon("refaire.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		refaire=new JButton(icon);
		refaire.setPreferredSize(new Dimension(30, 30));
		
		icon = new ImageIcon(new ImageIcon("annuler.png").getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		annuler=new JButton(icon);
		annuler.setPreferredSize(new Dimension(30, 30));
		
		
		aide.addActionListener(new EcouteurDeBouton(0,sauvegarde,plateau,m,j1,j2,aide,annuler));
		coupdepouce.addActionListener(new EcouteurDeBouton(1,sauvegarde,plateau,m,j1,j2,coupdepouce,annuler));
		annuler.addActionListener(new EcouteurDeBouton(2,sauvegarde,plateau,m,j1,j2,annuler,refaire));
		refaire.addActionListener(new EcouteurDeBouton(3,sauvegarde,plateau,m,j1,j2,annuler,refaire));
		

		
		add(aide);
		add(coupdepouce);
		add(annuler);
		add(refaire);
		
		annuler.setEnabled(false);
		refaire.setEnabled(false);
		
		plateau.annuler=annuler;
	
		
	}
	
	public void paintComponent(Graphics g) {
	}
	
}