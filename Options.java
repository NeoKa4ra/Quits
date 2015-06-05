
import java.awt.*;
import javax.swing.*;


public class Options extends JDialog {
	JRadioButton [] listeDifficulte;
	int niveau;  //initialiser à 0 pour le niveau par défaut
	ButtonGroup listeBouton;
	Moteur m;
	JComboBox j1;
	JComboBox j2;
	
	
	Options(JFrame parent, String title, boolean modal,Moteur m, PlateauGraphique p, int i){
		
		
		super(parent, title, modal);
		//setUndecorated(true);
		niveau=i;
		listeDifficulte = new JRadioButton [4];
		listeDifficulte [0]=new JRadioButton(" Joueur Vs Joueur ");
		listeDifficulte [1]=new JRadioButton(" Joueur Vs Ordi (Facile) ");
		listeDifficulte [2]=new JRadioButton(" Joueur Vs Ordi (Moyen) ");
		listeDifficulte [3]=new JRadioButton(" Joueur Vs Ordi (Difficile) ");
		
		listeDifficulte[niveau].setSelected(true);		//System.out.println(m.niveau);
		
		listeBouton = new ButtonGroup();
		JPanel panDifficulte = new JPanel();
		 panDifficulte.setBackground(Color.white);
		panDifficulte.setLayout(new GridLayout(4,1));
		panDifficulte.setBorder(BorderFactory.createTitledBorder("Choix du Niveau"));
	   

		for(int x=0;x<4;x++){
			listeDifficulte[x].addActionListener(new EcouteurDeDifficulte(x,this,m));
			listeBouton.add(listeDifficulte[x]);
			panDifficulte.add(listeDifficulte[x]);
		}
		
		
		JPanel panbouton = new JPanel();		
		JButton annuler = new JButton("Annuler");
		JButton ok = new JButton("OK");	
		annuler.addActionListener(new EcouteurDeBouton3(this,p));
		ok.addActionListener(new EcouteurDeBouton3(this,p));
		panbouton.add(annuler);
		panbouton.add(ok);
		
		
	    JPanel choixJoueur = new JPanel();
	    choixJoueur.setLayout(new GridLayout(2,1));
	    choixJoueur.setPreferredSize(new Dimension(220, 70));
	    choixJoueur.setBorder(BorderFactory.createTitledBorder("Couleur des pions"));
	    j1 = new JComboBox();
	    j1.addItem("Noir");
	    j1.addItem("Blanc");

	    j2 = new JComboBox();
	    j2.addItem("Noir");
	    j2.addItem("Blanc");
	    
	    JLabel pionLabel1 = new JLabel("Joueur 1:");
	    JLabel pionLabel2 = new JLabel("Joueur 2:(Ordi)");
	    choixJoueur.add(pionLabel1);
	    choixJoueur.add(j1);
	    choixJoueur.add(pionLabel2);
	    choixJoueur.add(j2);
		
		
		j1.setSelectedIndex(0);
		j2.setSelectedIndex(1);
		
		
		setLayout(new BorderLayout());
		add(panbouton, BorderLayout.SOUTH);
		add(panDifficulte, BorderLayout.CENTER);
		add(choixJoueur, BorderLayout.NORTH);
		setSize(400,250);
		setLocation(200,200);		
		setResizable(false);
		setVisible(true);
	}
}
