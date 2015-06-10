import java.util.Random;
import java.awt.Point;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class IA {
	// Constantes 
	final int largeurPt = 5;
	final int longueurPt = 5;
	final int nbCoupsPossibleMax = 7;
	final int nbBillesMax = 5;
	
	IA(){}
	
	/************************************* IA random *************************************/
	
	public CoupJouable niveau0(Plateau pT){
		// Renvoie un coupJouable aleatoire
		CoupJouable CJ = new CoupJouable();
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		// Parmi la liste, prendre un coup au hasard
		Random r = new Random();
		try{
			if (list.size() == 1)
				CJ = list.getFirst();
			else
				CJ = list.get(r.nextInt(list.size()-1));
		} catch(NoSuchElementException e) {
			System.out.println("Probleme : Coup IA Interdit");
		}
		return CJ;
	}
	/************************************* IA facile *************************************/
	
	public CoupJouable niveau1(Plateau pT){
		// Renvoie un coup jouable qui avance vers l'objectif s'il le peut.
		CoupJouable coupJouable = new CoupJouable();
		CoupJouable[] coupJouables = new CoupJouable[nbBillesMax*nbCoupsPossibleMax]; // Tableau de coups jouables retenus
		for (int k = 0; k < nbBillesMax*nbCoupsPossibleMax;k++)
			coupJouables[k] = new CoupJouable();
		int cmpt=0;	
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		Plateau pTemp = new Plateau(); // Plateau qui servira a jouer un coup
		pTemp.copie(pT);
		for(CoupJouable CJ : list){ // Validation d'un coup qui sort une bille
			pTemp.Joue(CJ,false);
			if ((pT.jBlanc && pT.nbBlancSortis() == pTemp.nbBlancSortis()-1) || (!pT.jBlanc && pT.nbMarronSortis() == pTemp.nbMarronSortis()-1))
				return CJ;
		}
		for(CoupJouable CJ : list){ // Validation d'un coup qui sort
			if(CJ.pDep.x != -1)
				coupJouables[cmpt++].joueCase(CJ.pDep,CJ.pArr);
			if(!pT.jBlanc){
				if (CJ.sens == false && CJ.colonne != -1)
					coupJouables[cmpt++].joueCBas(CJ.colonne);
				else if (CJ.sens == false && CJ.rangee != -1)
					coupJouables[cmpt++].joueRGauche(CJ.rangee);
			}
			else{
				if (CJ.sens == true && CJ.colonne != -1)
					coupJouables[cmpt++].joueCHaut(CJ.colonne);
				else if (CJ.sens == true && CJ.rangee != -1)
					coupJouables[cmpt++].joueRDroite(CJ.rangee);
			}
		}
		Random r = new Random();
		if(cmpt == 0) // Parmi la liste, prendre un coup au hasard
			 coupJouable = niveau0(pT);
		else if (cmpt == 1)
			coupJouable = coupJouables[0];
		else if (cmpt > 1)
			coupJouable = coupJouables[r.nextInt(cmpt-1)];
		else
			System.out.println("Pas de coups possible, voir programmeur." + cmpt);
			
		return coupJouable;
	}
	/************************************* IA Normal *************************************/

	public CoupJouable normal(Plateau pT){
		// Renvoie un coup à partir de poids ponderes
		int indiceMax = -100;
		CoupJouable coupJouable = new CoupJouable();
		CoupJouable [] coupJouables = new CoupJouable[nbBillesMax*nbCoupsPossibleMax];// Tableau de coups jouables retenus
		for (int k = 0; k < (nbBillesMax*nbCoupsPossibleMax);k++)
			coupJouables[k] = new CoupJouable();
		int cmpt = 0;
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		Plateau pTemp = new Plateau(); // Plateau qui servira a jouer un coup
		pTemp.copie(pT);
		for(CoupJouable CJ : list){
			pTemp.Joue(CJ,false);
			if (pTemp.calculIndicePoid() >= indiceMax){ 
				if (pTemp.calculIndicePoid() > indiceMax){
					cmpt = 0;
					indiceMax = pTemp.calculIndicePoid();
				}
				if(CJ.pDep.x != -1)
					coupJouables[cmpt++].joueCase(CJ.pDep,CJ.pArr);
				else if (CJ.sens == false && CJ.colonne != -1)
					coupJouables[cmpt++].joueCBas(CJ.colonne);
				else if (CJ.sens == false && CJ.rangee != -1)
					coupJouables[cmpt++].joueRGauche(CJ.rangee);
				else if (CJ.sens == true && CJ.colonne != -1)
					coupJouables[cmpt++].joueCHaut(CJ.colonne);
				else if (CJ.sens == true && CJ.rangee != -1)
					coupJouables[cmpt++].joueRDroite(CJ.rangee);
			}
			pTemp.copie(pT);
		}

		Random r = new Random();
		if (cmpt == 1)
			coupJouable = coupJouables[0];
		else
			coupJouable = coupJouables[r.nextInt(cmpt-1)];
		return coupJouable;
	}
	

	/************************************* IA Difficile *************************************/
	
	public CoupJouable hard(Plateau pT, int profondeur){
		// Renvoie un coup à partir d'un min max elagage alpha beta
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		CoupJouable CJmax=new CoupJouable();
		int tmp, max = -100000;
		Plateau pTemp = new Plateau();
		pTemp.copie(pT);
		
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		for(CoupJouable CJ : list){
			pTemp.Joue(CJ,false);
			tmp = alphaBetaMin(pTemp, alpha, beta, profondeur-1);
			//tmp = Min(pTemp, profondeur);
			if(tmp > max){
				max = tmp;
				CJmax.copie(CJ);
			}
			pTemp.copie(pT);
		}
		return CJmax;
	}
	
	private int alphaBetaMax(Plateau pT, int Alpha, int Beta, int profondeur){
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT, true);
		int max, tmp;
		Plateau pTemp= new Plateau();
		pTemp.copie(pT);
		
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		for(CoupJouable CJ : list){
			pTemp.Joue(CJ,false);
			tmp = alphaBetaMin(pTemp, Alpha, Beta, profondeur-1);
			if(tmp > Alpha)
				Alpha = tmp;
			if (Alpha >= Beta)
				return Alpha;
			pTemp.copie(pT);
		}
		return Alpha;
	}
	
	private int alphaBetaMin(Plateau pT, int Alpha, int Beta, int profondeur){
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT, false);
		int min, tmp;
		Plateau pTemp= new Plateau();
		pTemp.copie(pT);
		
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		for(CoupJouable CJ : list){
			pTemp.Joue(CJ,false);
			tmp = alphaBetaMax(pTemp, Alpha, Beta, profondeur-1);
			if(tmp < Beta)
				Beta = tmp;
			if (Alpha >= Beta)
				return Beta;
			pTemp.copie(pT);
		}
		return Beta;
	}
	
	private int eval(Plateau pT, boolean max){ // Evaluation du min max
		int res = 0;
		if((!pT.jBlancjoue() && max) || (pT.jBlancjoue() && !max)){
			res = poidsM(pT) - poidsB(pT); 
		}
		else
			res = poidsB(pT) - poidsM(pT);
		return res;
		
	}

	private boolean partieFinie(Plateau pT){ // Vrai si partie terminee
		return pT.nbMarronSortis()>2 || pT.nbBlancSortis()>2;
	}

	private int poidsB(Plateau pT){	// Poids du plateau blanc
		int res=0;
		for (int i=0;i<largeurPt;i++){
			for (int j=0;j<longueurPt;j++){
				if(pT.echiquier[i][j].estBlanc())
					res += coutB()[i][j];
			}
		}
		res += pT.nbBlancSortis()*100;
		if (pT.nbBlancSortis() >= 3)
			res += 10000;
		return res;
	}
	
	private int poidsM(Plateau pT){ // Poids du plateau marron
		int res=0;
		for (int i=0;i<largeurPt;i++){
			for (int j=0;j<longueurPt;j++){
				if(pT.echiquier[i][j].estMarron())
					res += coutM()[i][j];
			}
		}
		res += pT.nbMarronSortis()*100;
		if (pT.nbMarronSortis() >= 3)
			res += 10000;
		return res;
	}

	// Donner une valeur a chaque case
	private int[][] coutB(){
		int [][] mat = new int[largeurPt][longueurPt];
	mat[0][4] = 4;		mat[1][4] = 5;		mat[2][4] = 6;		mat[3][4] = 7;		mat[4][4] = 0; 
	mat[0][3] = 3;		mat[1][3] = 4;		mat[2][3] = 5;		mat[3][3] = 6;		mat[4][3] = 7;
	mat[0][2] = 2;		mat[1][2] = 3;		mat[2][2] = 4;		mat[3][2] = 5;		mat[4][2] = 6;
	mat[0][1] = 1;		mat[1][1] = 2;		mat[2][1] = 3;		mat[3][1] = 4;		mat[4][1] = 5;
	mat[0][0] = 15;		mat[1][0] = 1;		mat[2][0] = 2;		mat[3][0] = 3;		mat[4][0] = 4;
		return mat;
	}
	
	// Inverse du cout precedent
	private int[][] coutM(){
		int [][] mat = new int[largeurPt][longueurPt];
		int [][] temp = new int[largeurPt][longueurPt];
		temp = coutB();
		for (int i=0;i<largeurPt;i++){
			for (int j=0;j<5;j++)
				mat[i][j]=temp[4-i][4-j];
		}
		return mat;
	}
}