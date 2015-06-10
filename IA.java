import java.util.Random;
import java.awt.Point;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class IA {
	final int largeurPt = 5;
	final int longueurPt = 5;
	final int nbCoupsPossibleMax = 7;
	final int nbBillesMax = 5;
	boolean isMax;
	
	IA(){
	
	}
	
	/************************************* IA 0 *************************************/
	
	public CoupJouable niveau0(Plateau pT){
		CoupJouable CJ = new CoupJouable();
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		
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
	/************************************* IA 1 *************************************/
	
	public CoupJouable niveau1(Plateau pT){
		CoupJouable coupJouable = new CoupJouable();

		Plateau pTemp = new Plateau();
		pTemp.copie(pT);

		CoupJouable[] coupJouables = new CoupJouable[1000];
		for (int k = 0; k < (1000);k++)
			coupJouables[k] = new CoupJouable();
		int cmpt=0;	

		Point pDep = new Point(-1,-1);
		Point pArr = new Point(-1,-1);
		
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();

		for(CoupJouable CJ : list){
			pTemp.Joue(CJ,false);
			if ((pT.jBlanc && pT.nbBlancSortis() == pTemp.nbBlancSortis()-1) || (!pT.jBlanc && pT.nbMarronSortis() == pTemp.nbMarronSortis()-1))
				return CJ;
		}
		for(CoupJouable CJ : list){
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
		if(cmpt == 0)
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
		int indiceMax = -100;

		Plateau pTemp = new Plateau();
		pTemp.copie(pT);
		CoupJouable coupJouable = new CoupJouable();
		CoupJouable [] coupJouables = new CoupJouable[nbBillesMax*nbCoupsPossibleMax];
		for (int k = 0; k < (nbBillesMax*nbCoupsPossibleMax);k++)
			coupJouables[k] = new CoupJouable();
		int cmpt = 0;
		Point pDep = new Point(-1,-1);
		Point pArr = new Point(-1,-1);
		
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();

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
		this.isMax = true;
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
	
	private int Max(Plateau pT, int profondeur){
		this.isMax = true;
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT, isMax);
		int max, tmp;
		max = -100000;
		Plateau pTemp= new Plateau();
		pTemp.copie(pT);
		
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		for(CoupJouable CJ : list){
			pTemp.Joue(CJ,false);
			tmp = Min(pTemp, profondeur-1);
			if(tmp > max){
				max = tmp;
			}
			pTemp.copie(pT);
		}
		return max;
	}
	
	private int Min(Plateau pT, int profondeur){
		this.isMax = false;
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT, isMax);
		int min, tmp;
		min = 100000;
		Plateau pTemp= new Plateau();
		pTemp.copie(pT);
		
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		for(CoupJouable CJ : list){
			pTemp.Joue(CJ,false);
			tmp = Max(pTemp, profondeur-1);
			if(tmp < min){
				min = tmp;
			}
			pTemp.copie(pT);
		}
		return min;
	}
	private int eval(Plateau pT, boolean max){
		int res = 0;
		if((!pT.jBlancjoue() && max) || (pT.jBlancjoue() && !max)){
			res = poidsM(pT) - poidsB(pT); 
		}
		else
			res = poidsB(pT) - poidsM(pT);
		return res;
		
	}

	private boolean partieFinie(Plateau pT){
		return pT.nbMarronSortis()>2 || pT.nbBlancSortis()>2;
	}

	private int poidsB(Plateau pT){
		int res=0;
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
				if(pT.echiquier[i][j].estBlanc())
					res += coutB()[i][j];
			}
		}
		res += pT.nbBlancSortis()*100;
		if (pT.nbBlancSortis() >= 3)
			res += 10000;
		return res;
	}
	
	private int poidsM(Plateau pT){
		int res=0;
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
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
		int [][] mat = new int[5][5];
	mat[0][4] = 4;		mat[1][4] = 5;		mat[2][4] = 6;		mat[3][4] = 7;		mat[4][4] = 0; 
	mat[0][3] = 3;		mat[1][3] = 4;		mat[2][3] = 5;		mat[3][3] = 6;		mat[4][3] = 7;
	mat[0][2] = 2;		mat[1][2] = 3;		mat[2][2] = 4;		mat[3][2] = 5;		mat[4][2] = 6;
	mat[0][1] = 1;		mat[1][1] = 2;		mat[2][1] = 3;		mat[3][1] = 4;		mat[4][1] = 5;
	mat[0][0] = 15;		mat[1][0] = 1;		mat[2][0] = 2;		mat[3][0] = 3;		mat[4][0] = 4;
		return mat;
	}
	
	// Inverse du cout precedent
	private int[][] coutM(){
		int [][] mat = new int[5][5];
		int [][] temp = new int[5][5];
		temp = coutB();
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
				mat[i][j]=temp[4-i][4-j];
			}
		}
		return mat;
	}
	
	private int alphaBetaMax(Plateau pT, int Alpha, int Beta, int profondeur){
		this.isMax = true;
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT, isMax);
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
		this.isMax = false;
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT, isMax);
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
	
	private int minimum(int A, int B){
		if(A<B)
			return A;
		return B;
	}
	private int maximum(int A, int B){
		if(A>=B)
			return A;
		return B;
	}
}