import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.*;

import javax.swing.*;

public class ChargerPartie extends JDialog{
	Plateau p;
	Sauvegarde sauvegarde;
	JRadioButton [] listeFich;
	String fichierSelectionne;
	int taille;
	ButtonGroup listeBouton;	

	ChargerPartie(JFrame parent, String title, boolean modal, EcouteurDeBouton b, Sauvegarde sauvegarde, Plateau p){
		super(parent, title, modal);
		this.p=p;
		fichierSelectionne="riendutoutpourlinstant";
		this.sauvegarde=sauvegarde;
		taille = sauvegarde.listFichier.size();
		System.out.println(taille);
		Iterator i = sauvegarde.listFichier.iterator();
		
		
		listeBouton = new ButtonGroup();
		listeFich = new JRadioButton[taille];
		int x=0;
		while(i.hasNext()){
			listeFich[x]=new JRadioButton((String) i.next());
			listeBouton.add(listeFich[x]);
			x++;
		}
		
		JPanel panFich = new JPanel();
		panFich.setLayout(new GridLayout(taille,1));
		for(x=0;x<taille;x++){
			listeFich[x].addActionListener(new EcouteurDeFichier(x,this));
			panFich.add(listeFich[x]);
		}
		
		JPanel panbouton = new JPanel();
		JButton supp = new JButton("Supprimer");
		JButton renommer = new JButton("Renommer");
		JButton annuler = new JButton("Annuler");
		JButton charger = new JButton("Charger");
		
		supp.addActionListener(new EcouteurDeBouton2(this,sauvegarde));	
		renommer.addActionListener(new EcouteurDeBouton2(this,sauvegarde));
		annuler.addActionListener(new EcouteurDeBouton2(this,sauvegarde));
		charger.addActionListener(new EcouteurDeBouton2(this,sauvegarde));
		
		panbouton.add(supp);
		panbouton.add(renommer);
		panbouton.add(annuler);
		panbouton.add(charger);
		
		
		setLayout(new BorderLayout());
		add(panbouton, BorderLayout.SOUTH);
		add(panFich, BorderLayout.NORTH);
		setSize(500,taille*20+200);
		setLocationRelativeTo(null);		
		setResizable(false);
		setVisible(true);
	}
	
	
}
