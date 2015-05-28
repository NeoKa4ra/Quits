
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPanel;


public class NouvellePartie extends JDialog {
	JRadioButton [] listeDifficulte;
	
	NouvellePartie(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		listeDifficulte = new JRadioButton [4];
		
		listeDifficulte [0]=new JRadioButton(" Joueur Vs Joueur ");
		listeDifficulte [1]=new JRadioButton(" Joueur Vs Ordi (Facile) ");
		listeDifficulte [2]=new JRadioButton(" Joueur Vs Ordi (Moyen) ");
		listeDifficulte [3]=new JRadioButton(" Joueur Vs Ordi (Difficile) ");
		
		
		JPanel panFich = new JPanel();
		panFich.setLayout(new GridLayout(4,1));
		for(int x=0;x<4;x++){
			listeDifficulte[x].addActionListener(new EcouteurDeDifficulte(x,this));
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
		setSize(400,400);
		setLocationRelativeTo(null);		
		setResizable(false);
		setVisible(true);
	}
}

