import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel{

	MonBouton NouvellePartie;
	MonBouton SauvegarderPartie;
	MonBouton ChargerPartie;
	MonBouton Options;
	Moteur m;
	Image img1,img2,img3,img4;
	
	Menu (Sauvegarde sauvegarde, PlateauGraphique p, Moteur m, Etats j1, Etats j2){
	
		this.m=m;
		//setPreferredSize(new Dimension(120,40));
		try { 
			img1=ImageIO.read(new File("NouvPartie.png"));
			img2=ImageIO.read(new File("SauvPartie.png"));
			img3=ImageIO.read(new File("ChargerPartie.png"));
			img4=ImageIO.read(new File("Options.png"));
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}	
		
		//b=new MonBouton("Sauvegarder Partie2");
		NouvellePartie = new MonBouton ("Nouvelle Partie",img1,img2);
		SauvegarderPartie = new MonBouton ("Sauvegarder Partie",img2,img3);
		ChargerPartie = new MonBouton ("Charger Partie",img3,img3);
		Options = new MonBouton ("Options",img4,img3); 
	
		
		NouvellePartie.addActionListener(new EcouteurDeBouton(-1,sauvegarde,p,m,j1,j2,SauvegarderPartie,ChargerPartie));
		SauvegarderPartie.addActionListener(new EcouteurDeBouton(-2,sauvegarde,p,m,j1,j2,SauvegarderPartie,ChargerPartie));
		ChargerPartie.addActionListener(new EcouteurDeBouton(-3,sauvegarde,p,m,j1,j2,SauvegarderPartie,ChargerPartie));
		Options.addActionListener(new EcouteurDeBouton(-4,sauvegarde,p,m,j1,j2,SauvegarderPartie,ChargerPartie));
		
		this.setLayout (new GridLayout (16,1));
		for(int i=0;i<2;i++)
			this.add(new JLabel());
		this.add (NouvellePartie );
		this.add (SauvegarderPartie);
		this.add (ChargerPartie);
		this.add (Options);
		//this.add (b);
		
	
	}
	
	
	public void paintComponent(Graphics g) {
		setBackground(Color.white);
		Graphics2D drawable = (Graphics2D) g;
		drawable.setPaint(Color.white);
		drawable.fillRect(0,0, getSize().width, getSize().height);
	}
}
