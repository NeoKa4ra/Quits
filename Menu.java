import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;

public class Menu extends JPanel{
	
	//MonBouton b;
	JButton NouvellePartie;
	JButton SauvegarderPartie;
	JButton ChargerPartie;
	JButton Options;
	Moteur m;
	
	Menu (Sauvegarde sauvegarde, PlateauGraphique p, Moteur m, Etats j1, Etats j2){
	
		this.m=m;
		
		//b=new MonBouton("Sauvegarder Partie2");
		NouvellePartie = new JButton ("Nouvelle Partie");
		SauvegarderPartie = new JButton ("Sauvegarder Partie");
		ChargerPartie = new JButton ("Charger Partie");
		Options = new JButton ("Options"); 
	
		
		NouvellePartie.addActionListener(new EcouteurDeBouton(-1,sauvegarde,p,m,j1,j2,SauvegarderPartie,ChargerPartie));
		SauvegarderPartie.addActionListener(new EcouteurDeBouton(-2,sauvegarde,p,m,j1,j2,SauvegarderPartie,ChargerPartie));
		ChargerPartie.addActionListener(new EcouteurDeBouton(-3,sauvegarde,p,m,j1,j2,SauvegarderPartie,ChargerPartie));
		Options.addActionListener(new EcouteurDeBouton(-4,sauvegarde,p,m,j1,j2,SauvegarderPartie,ChargerPartie));
		
		this.setLayout (new GridLayout (20,1));
		this.add (NouvellePartie );
		this.add (SauvegarderPartie);
		this.add (ChargerPartie);
		this.add (Options);
		//this.add (b);
		
	}
	
	
	public void paintComponent(Graphics g) {
	
	}
}
