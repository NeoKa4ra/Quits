import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import java.awt.event.*;

public class Menu extends JPanel{
	
	JButton NouvellePartie;
	JButton SauvegarderPartie;
	JButton ChargerPartie;
	JButton Options;
	
	
	Menu (Sauvegarde sauvegarde){
			

		NouvellePartie = new JButton ("Nouvelle Partie");
		SauvegarderPartie = new JButton ("Sauvegarder Partie");
		ChargerPartie = new JButton ("Charger Partie");
		Options = new JButton ("Options"); 
	
		
		NouvellePartie.addActionListener(new EcouteurDeBouton(sauvegarde));
		SauvegarderPartie.addActionListener(new EcouteurDeBouton(sauvegarde));
		ChargerPartie.addActionListener(new EcouteurDeBouton(sauvegarde));
		Options.addActionListener(new EcouteurDeBouton(sauvegarde));
		
		this.setLayout (new GridLayout (20,1));
		this.add (NouvellePartie );
		this.add (SauvegarderPartie);
		this.add (ChargerPartie);
		this.add (Options);
		
	}
	
	
	public void paintComponent(Graphics g) {
	
	}
}

