
import java.awt.Point;
import java.io.File;
import java.io.IOException;




public class Testcode {
	
	public static void main(String [] args) throws IOException {
		
		// affiche l'initialisation du tableau
		Plateau plateau=new Plateau();
		plateau.init_2_joueurs();
		plateau.afficher();
		// Coup adversaire
		CoupJouable coupAdv=new CoupJouable();

		int ia1 = 0 , ia2 = 0;
		IA bot = new IA();
		CoupJouable CJ=new CoupJouable();
		int lim = 1;
		while(ia1 != lim && ia2 != lim){
			System.out.println("ia2 :");
			CJ = bot.hard(plateau,1, coupAdv);
			plateau.Joue(CJ,false);
			plateau.afficher();
			coupAdv.copie(CJ);
			
			
			System.out.println("ia1 :");
			CJ = bot.niveau0(plateau, coupAdv);
			plateau.Joue(CJ,false);
			plateau.afficher();
			coupAdv.copie(CJ);
			
			if (plateau.nbMarronSortis() >= 3){
				ia2++;
				plateau.init_2_joueurs();
				coupAdv=new CoupJouable();
			}
				
			if (plateau.nbBlancSortis() >= 3){
				ia1++;
				plateau.init_2_joueurs();
				coupAdv=new CoupJouable();
			}
			if(plateau.position%2000 == 0){
				System.out.println("ia1 : " + ia1 + " ia2 : " + ia2);
				plateau.init_2_joueurs();
				coupAdv=new CoupJouable();
			}
		}
		System.out.println("ia1 : " + ia1 + " ia2 : " + ia2);
	}
	
	
}
