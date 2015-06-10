import java.awt.Point;


public class CoupJouable {
	
	// Avec le sens et la valeur de colonne et rangee, nous savons quel type de coup est joue
	// Colonne != -1 ou Rangee != -1 lorsque l'un d'entre eux est joue
	boolean sens;
	int colonne,rangee;
	
	// Coordonnees points departs et arrivee
	Point pDep;
	Point pArr;
	
	// booleen pour savoir si une bille marrron est sorti avec le coup joué
	boolean marronSort;
	
	// booleen pour savoir si une bille blanc est sorti avec le coup joué
	boolean blancSort;	
	
	CoupJouable(){
		init();
	}
	
	// maj des booleens pour savoir si une bille est sortie
	public void marronSort(int i){
		if(i==1)
			marronSort=true;
		else
			marronSort=false;		
	}
	
	public void blancSort(int i){
		if(i==1)
			blancSort=true;
		else
			blancSort=false;
	}
	
	// return si une bille est sortie
	public boolean marronSorti(){
		return marronSort;
	}
	
	public boolean blancSorti(){
		return blancSort;
	}
	
	
	public void init(){
		colonne = -1;
		rangee = -1;
		pDep = new Point(-1,-1);
		pArr = new Point(-1,-1);
		sens = false;
		marronSort=false;
		blancSort=false;
	}
	
	// copie d'un coup joue
	public void copie(CoupJouable coupjouable){
		colonne = coupjouable.Colonne();
		rangee = coupjouable.Rangee();
		pDep = coupjouable.PointDep();
		pArr = coupjouable.PointArr();
		sens = coupjouable.Sens();
		marronSort=coupjouable.marronSorti();
		blancSort=coupjouable.blancSorti();		
	}
	
	// On joue une case
	public void joueCase(Point pDep,Point pArr){
		init();
		this.pDep = pDep;
		this.pArr = pArr;
	}
	
	// Si on ne joue pas une case, on joue soit une colonne soit une ligne dans un sens donne
	// R = rangee jouee, C = colonne jouee
	public void joueCHaut(int colonne){
		init();
		this.colonne = colonne;
		sens = true;
	}
	
	public void joueCBas(int colonne){
		init();
		this.colonne = colonne;
		sens = false;
	}

	public void joueRDroite(int rangee){
		init();
		this.rangee = rangee;
		sens = true;
	}

	public void joueRGauche(int rangee){
		init();
		this.rangee = rangee;
		sens = false;
	}
	
	public boolean estCase(){
		return colonne == -1 && rangee == -1;
	}
	public boolean estColonne(){
		return colonne!= -1;
	}
	public boolean estRangee(){
		return rangee!= -1;
	}
	
	// Methode recuperant les attributs (getters)
	public Point PointDep(){
		return pDep;
	}
	public Point PointArr(){
		return pArr;
	}
	public int Colonne(){
		return colonne;
	}
	public int Rangee(){
		return rangee;
	}
	public boolean Sens(){
		return sens;
	}
	
	public boolean estCoupInverse(CoupJouable coupjouable){
		boolean res = false;
		if(!estCase() && !coupjouable.estCase()){
			if(estRangee() && coupjouable.estRangee())
				res = sens == !coupjouable.Sens() && Rangee() == coupjouable.Rangee();
			else if(estColonne() && coupjouable.estColonne())
				res = sens == !coupjouable.Sens() && Colonne() == coupjouable.Colonne();
		}
		return res;
	}
	
	// Verifie si ce coup joue est un deplacement gauche adjacent
	public boolean estDepGauche(Plateau plateau){
		boolean res;
		if (plateau.echiquier[pDep.x][pDep.y].estBlanc())
			res = (pDep.x == pArr.x && pDep.y == pArr.y-1);
		else
			res = (pDep.x == pArr.x+1 && pDep.y == pArr.y);
		return res;
	}
	
	
	
	
	// Verifie si ce coup joue est un deplacement droite adjacent
	public boolean estDepDroite(Plateau plateau){
		boolean res;
		if (plateau.echiquier[pDep.x][pDep.y].estBlanc())
			res = (pDep.x == pArr.x-1 && pDep.y == pArr.y);
		else
			res = (pDep.x == pArr.x && pDep.y == pArr.y+1);
		return res;
		
	}
	// Verifie si ce coup joue est un deplacement diagonale adjacent
	public boolean estDepDiagonale(Plateau plateau){
		boolean res;
		if (plateau.echiquier[pDep.x][pDep.y].estBlanc())
			res = (pDep.x == pArr.x-1 && pDep.y == pArr.y-1 );
		else
			res = (pDep.x == pArr.x+1 && pDep.y == pArr.y+1);
		return res;
		
	}
	
	public boolean estDepPossible(Plateau plateau){
		return estDepDiagonale(plateau) || estDepDroite(plateau) || estDepGauche(plateau);
	}
	
	//renvoit si le coup jouer est valide
	public boolean estValide(Plateau plateau){
		boolean res = false;
		boolean jBlanc=plateau.jBlancjoue();
		//on verifie que le coup joue ne correspond pas au coup inverse de l'adersaire
		if(!estCoupInverse(plateau.adv())){
			// on joue une rengee ou colonne
			if(estColonne() && ( (sens && plateau.estJouableCHaut(colonne,jBlanc)) || (!sens && plateau.estJouableCBas(colonne,jBlanc))) )
				res = colonne>= 0 && colonne<5;
			else if(estRangee() && ( (sens && plateau.estJouableRDroite(rangee,jBlanc)) || (!sens && plateau.estJouableRGauche(rangee,jBlanc)) ))
				res = rangee>= 0 && rangee<5;			
			else{
			// on joue une case
				//on verifie qu'elle est bien dans l'�chiquier
				//if(pDep != null && pArr != null){
				if(pDep.x>= 0 && pDep.y<5 && pArr.x>= 0 && pArr.y<5 && CaseJouableParJ(plateau))
					// on verifie que la case d'arriv�e est libre et que la case de depart est occupee
					if(plateau.echiquier[pArr.x][pArr.y].estLibre() && plateau.echiquier[pDep.x][pDep.y].estOcc())
						res = estDepPossible(plateau);
			}
			//}
		}
		return res;		 
	}
	public boolean CaseJouableParJ(Plateau plateau){
		boolean res;
		boolean jBlanc=plateau.jBlancjoue();
		res= (jBlanc && plateau.echiquier[pDep.x][pDep.y].estBlanc()) || (!jBlanc && plateau.echiquier[pDep.x][pDep.y].estMarron());
		return res;
	}
	
	// 0 = colonne haut, 1 = colonne bas, 2 = rangee droite, 3 = rangee gauche
	public void coup(int cp, int coordonnee){
		if(cp == 0)
			joueCHaut(coordonnee);
		else if(cp == 1)
			joueCBas(coordonnee);
		else if(cp == 2)
			joueRDroite(coordonnee);
		else if(cp == 3)
			joueRGauche(coordonnee);
		else
			System.out.println("Mauvais cp lors de l'appel a coup");
			
	}
	
}
