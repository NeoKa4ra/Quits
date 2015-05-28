import javax.swing.*;
import java.awt.event.*;

public class EcouteurDeDifficulte implements ActionListener{
	NouvellePartie np;
	int x;
	
	EcouteurDeDifficulte(int x, NouvellePartie np){
		this.x=x;
		this.np=np;
	}
	
	public void actionPerformed( ActionEvent e){
		if(e.getActionCommand().equals(" Joueur Vs Joueur ")){
			System.out.println("choix 0");
		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Facile) ")){
			System.out.println("choix 1");
		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Moyen) ")){
			System.out.println("choix 2");
		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Difficile) ")){
			System.out.println("choix 3");
		}
	}
		
}
