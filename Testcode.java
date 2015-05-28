
import java.awt.Point;
import java.io.File;
import java.io.IOException;




public class Testcode {
	
	public static void main(String [] args) throws IOException {
		
		// affiche l'initialisation du tableau
		Plateau plateau=new Plateau();
		plateau.init_2_joueurs();
		plateau.afficher();

		int ia1 = 0 , ia2 = 0;
		IA bot = new IA();
		CoupJouable CJ=new CoupJouable();
		int nbParties = 10;
		while(ia1 != nbParties && ia2 != nbParties && plateau.position<2000){
			System.out.println("ia2 :");
			CJ = bot.normal(plateau);
			plateau.Joue(CJ,false);
			plateau.afficher();
			
			
			System.out.println("ia1 :");
			CJ = bot.hard(plateau,3);
			plateau.Joue(CJ,false);
			plateau.afficher();
			
			if (plateau.nbMarronSortis() >= 3){
				ia2++;
				plateau.init_2_joueurs();
			}
				
			if (plateau.nbBlancSortis() >= 3){
				ia1++;
				plateau.init_2_joueurs();
			}
		}
		System.out.println("score : ia1 " + ia1 + " - " + ia2 + " ia2");

		


	}
	
	
}
