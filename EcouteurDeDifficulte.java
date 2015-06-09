import javax.swing.*;
import java.awt.event.*;

public class EcouteurDeDifficulte implements ActionListener{

	Options opt;
	int x;
	Moteur m;
	
	EcouteurDeDifficulte(int x, Options opt, Moteur m){
		this.x=x;
		this.opt=opt;
		this.m=m;
	}
	
	public void actionPerformed( ActionEvent e){
		if(e.getActionCommand().equals(" Joueur Vs Joueur ")){
			m.niveau=0;
			m.dernier_niveau=0;
			
		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Facile) ")){
			m.niveau=1;
			m.dernier_niveau=1;

		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Moyen) ")){
			m.niveau=2;
			m.dernier_niveau=2;

		
		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Difficile) ")){
			m.niveau=3;
			m.dernier_niveau=3;
		}
	}
		
}