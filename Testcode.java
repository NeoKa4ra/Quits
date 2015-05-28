
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

		// on joue une case de p1 vers p2
		CoupJouable coupjouable=new CoupJouable();
		Point p1=new Point(4,2);
		Point p2=new Point(4,1);
		
		
		coupjouable.joueCase(p1,p2);
		
		Moteur m=new Moteur();
		File f;
		System.out.printf("\n");
		
		plateau.Joue(coupjouable,false);
		plateau.afficher();
		
		
		coupjouable=new CoupJouable();
		
		coupjouable.joueCHaut(3);
		plateau.Joue(coupjouable,false);
		plateau.afficher();	
		
		coupjouable=new CoupJouable();
		coupjouable.joueCHaut(3);
		

		
		
		Sauvegarde sauvegarde=new Sauvegarde();
		
		m.sauvegarde("sauvegarde",plateau,sauvegarde);
		m.charger("sauvegarde",plateau);
		plateau.afficher();
		
		
		m.annuler(plateau);
		m.annuler(plateau);
		m.annuler(plateau);
		plateau.afficher();
		/*
		coupjouable=new CoupJouable();
		
		coupjouable.joueCHaut(4);
		plateau.Joue(coupjouable,false);
		
		System.out.println("CH 4");
		
		m.sauvegarde("sauvegarde",plateau,sauvegarde);
		m.charger("sauvegarde",plateau);		
		plateau.afficher();
		
		m.annuler(plateau);
		m.annuler(plateau);
		m.annuler(plateau);
		m.annuler(plateau);
		m.annuler(plateau);
		
		plateau.afficher();

		m.refaire(plateau);
		m.refaire(plateau);

		plateau.afficher();
		

		m.inverser(plateau);
		m.inverser(plateau);		

		plateau.afficher();
		
		
			
		/*System.out.printf("\n");
		
		// on regarde si le premiercoup est valide
		if(coupjouable.estValide(plateau, coupAdv))
			//System.out.printf("yolo");
			// on joue le coup
			plateau.Joue(coupjouable);
		plateau.afficher();
	
		CoupJouable coupjouable2=new CoupJouable();
		p1=new Point(1,0);
		p2=new Point(1,1);
		coupjouable2.JoueCase(p1,p2);
		
		if(!coupjouable2.estValide(plateau,coupjouable))
			System.out.println("fail"); // affiche un fail car la case 1,1 est occupee
		
		System.out.printf("\n");
		
		coupjouable2=new CoupJouable();
		coupjouable2.joueCHaut(3);
		if(!coupjouable2.estValide(plateau,coupjouable))
			System.out.println("fail2");
		plateau.afficher();
		
		coupjouable.copie(coupjouable2);
		coupjouable2=new CoupJouable();
		coupjouable2.joueCBas(3);
		if(!coupjouable2.estValide(plateau,coupjouable))
			// affiche un fail car on a jou� un coup inverse
			System.out.println("fail");
		
		coupjouable=new CoupJouable();
		coupjouable.joueRGauche(1);
		plateau.Joue(coupjouable);
		plateau.afficher();	
		
		coupjouable=new CoupJouable();
		coupjouable.joueRGauche(0);
		plateau.Joue(coupjouable);
		
		coupjouable=new CoupJouable();
		coupjouable.joueRGauche(0);
		plateau.Joue(coupjouable);
		
		coupjouable=new CoupJouable();
		coupjouable.joueRGauche(0);
		plateau.Joue(coupjouable);
		
		// affiche le nbre_de_marron sorti(s)
		System.out.println(plateau.nbMarronSortis());
		
		// il n'y a plus de deux sur la rangee 0 car il a �t� �limin�
		plateau.afficher();
		
		coupjouable=new CoupJouable();
		p1=new Point(4,3);
		p2=new Point(4,4);
		coupjouable.JoueCase(p1,p2);
		if(!coupjouable.estValide(plateau, coupAdv))
			System.out.println("fail");
		coupjouable.joueCHaut(1);
		if(!coupjouable.estValide(plateau, coupAdv))
			System.out.printf("fail4");	
		/*System.out.printf("\n");
		plateau.Joue(coupjouable);
		plateau.afficher();*/
		

		int ia1 = 0 , ia2 = 0;
		IA bot = new IA();
		CoupJouable CJ=new CoupJouable();
		while(ia1 != 100 && ia2 != 100){
			System.out.println("ia2 :");
			CJ = bot.normal(plateau, coupAdv);
			plateau.Joue(CJ,false);
			plateau.afficher();
			coupAdv.copie(CJ);
			
			
			System.out.println("ia1 :");
			CJ = bot.hard(plateau,3, coupAdv);
			plateau.Joue(CJ,false);
			plateau.afficher();
			coupAdv.copie(CJ);
			
			if (plateau.nbMarronSortis() >= 3){
				ia2++;
				plateau.init_2_joueurs();
			}
				
			if (plateau.nbBlancSortis() >= 3){
				ia1++;
				plateau.init_2_joueurs();
			}
		}
		System.out.println("score ia1 : " + ia1 + " *** score ia2 : " + ia2);

		


	}
	
	
}
