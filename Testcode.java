import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


public class Testcode {
	
	public static void main(String [] args) throws IOException {
		
		// affiche l'initialisation du tableau
		Plateau plateau=new Plateau();
		plateau.init_2_joueurs();
		plateau.afficheMatrice();
		LinkedList<CoupJouable> L = new LinkedList<CoupJouable>();
		L = plateau.ListeCoujouable();
		plateau.Joue(L.get(7),false);
		System.out.println();
		plateau.afficheMatrice();
		int ia1 = 0 , ia2 = 0;
		IA bot = new IA();
		CoupJouable CJ=new CoupJouable();
		int nbParties = 1;
		while(ia1 != nbParties && ia2 != nbParties && plateau.position < 400){
			/*//*********** VERSION AVEC AFFICHAGE ***********
			System.out.println();
			System.out.println("Tour " + (plateau.position/2) + " : ");
			System.out.println();
			//*********** JOUEUR 1 ***********
			System.out.println("Joueur 1 (pions 2) :");
			CJ = bot.hard(plateau,1);
			plateau.Joue(CJ,false);
			plateau.afficheMatrice();

			System.out.println();
			//*********** JOUEUR 2 ***********
			System.out.println("Joueur 2 (pions 1) :");
			CJ = bot.niveau1(plateau);
			plateau.Joue(CJ,false);
			plateau.afficheMatrice();
			
			System.out.println();
			*/
			//*********** VERSION SANS AFFICHAGE ***********
			//*********** JOUEUR 1 ***********
			CJ = bot.niveau0(plateau);
			plateau.Joue(CJ,false);
			//*********** JOUEUR 2 ***********
			CJ = bot.hard(plateau,1);
			plateau.Joue(CJ,false);
			
			//*********** Test Fin Partie ***********
			if (plateau.nbMarronSortis() >= 3){
				ia2++;
				plateau.init_2_joueurs();
				System.out.println("Score :");
				System.out.println("(2) Premier joueur   " + ia2 + " - " + ia1 + "   Deuxieme joueur (1)");
			}
				
			if (plateau.nbBlancSortis() >= 3){
				ia1++;
				plateau.init_2_joueurs();
				System.out.println("Score :");
				System.out.println("(2) Premier joueur   " + ia2 + " - " + ia1 + "   Deuxieme joueur (1)");
			}
			
		}
	}
}
