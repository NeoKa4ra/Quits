import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.*;


public class EcouteurDeFichier implements ActionListener {
	int numerofichier;
	ChargerPartie cp;
	
	EcouteurDeFichier(int n, ChargerPartie cp){
		this.cp = cp;
		numerofichier=n;	
	}
	
	public void actionPerformed(ActionEvent e){
	
			Iterator i = cp.sauvegarde.listFichier.iterator();
			int x=0;
			while(x!=numerofichier && x<cp.taille){
				i.next();
				x++;
			}
			//appel d'une fonction de joris du genre recupere le nom du fichier et dans ecouteur debouton2 quand on appuie sur ok, la il faut charger le fichier
			
			cp.fichierSelectionne=(String) i.next();
	}
	
	
}
