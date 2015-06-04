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
			System.out.println("choix 0");
			System.out.println(m.niveau);
			m.niveau=0;
			opt.niveau=0;

			
		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Facile) ")){
			System.out.println("choix 1");
			m.niveau=1;
			opt.niveau=1;
		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Moyen) ")){
			System.out.println("choix 2");
			m.niveau=2;
			opt.niveau=2;
		}
		if(e.getActionCommand().equals(" Joueur Vs Ordi (Difficile) ")){
			System.out.println("choix 3");
			m.niveau=3;
			opt.niveau=3;
		}
	}
		
}