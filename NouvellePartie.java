
import java.awt.*;
import javax.swing.*;


public class NouvellePartie extends JDialog {
	JRadioButton [] listeDifficulte;
	int niveau;  //initialiser à 0 pour le niveau par défaut
	ButtonGroup listeBouton;
	Moteur m;
	
	NouvellePartie(JFrame parent, String title, boolean modal,Moteur m){
		super(parent, title, modal);
		listeDifficulte = new JRadioButton [4];
		niveau=0;
		listeDifficulte [0]=new JRadioButton(" Joueur Vs Joueur ");
		listeDifficulte [1]=new JRadioButton(" Joueur Vs Ordi (Facile) ");
		listeDifficulte [2]=new JRadioButton(" Joueur Vs Ordi (Moyen) ");
		listeDifficulte [3]=new JRadioButton(" Joueur Vs Ordi (Difficile) ");
	
		//System.out.println(m.niveau);
		
		listeBouton = new ButtonGroup();
		JPanel panFich = new JPanel();
		panFich.setLayout(new GridLayout(4,1));
		for(int x=0;x<4;x++){
			listeDifficulte[x].addActionListener(new EcouteurDeDifficulte(x,this,m));
			listeBouton.add(listeDifficulte[x]);
			panFich.add(listeDifficulte[x]);
		}
		
		
		JPanel panbouton = new JPanel();
		
		JButton annuler = new JButton("Annuler");
		JButton ok = new JButton("OK");
		
		
		annuler.addActionListener(new EcouteurDeBouton3(this));
		ok.addActionListener(new EcouteurDeBouton3(this));
		
		panbouton.add(annuler);
		panbouton.add(ok);
		
		
		setLayout(new BorderLayout());
		add(panbouton, BorderLayout.SOUTH);
		add(panFich, BorderLayout.NORTH);
		setSize(400,170);
		setLocationRelativeTo(null);		
		setResizable(false);
		setVisible(true);
	}
}

