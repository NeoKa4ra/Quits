import java.util.Random;
import java.awt.Point;
import java.util.LinkedList;
import java.util.NoSuchElementException;

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
			CJ = list.get(r.nextInt(list.size()-1));
		} catch(NoSuchElementException e) {
			System.out.println("Probleme : Coup IA Interdit");
		}
		return CJ;
	}
	/************************************* IA 1 *************************************/
	public CoupJouable niveau1(Plateau plateau){
		CoupJouable coupJouable = new CoupJouable();
		boolean res = false; 
		CoupJouable temp = new CoupJouable();
		Plateau plateau2;
		Point as = new Point(-1,-1);
			
		Point s = new Point(-1,-1);
		CoupJouable[] coupJouables = new CoupJouable[1000];
		for (int k = 0; k < (1000);k++)
			coupJouables[k] = new CoupJouable();
		int cmpt=0;	
		
		
		Point pDep = new Point(-1,-1);
		Point pArr = new Point(-1,-1);
		
		if (plateau.jBlanc){
			s = plateau.sortieblanche();
			if(plateau.echiquier[s.x][s.y].estLibre()){
				if(plateau.echiquier[s.x-1][s.y].estBlanc()){
					as.x = s.x-1;
					as.y = s.y;
					coupJouable.joueRDroite(s.y);
					res = true;
				} else if(plateau.echiquier[s.x][s.y-1].estBlanc()){
					as.x = s.x;
					as.y = s.y-1;
					coupJouable.joueCHaut(s.x);
					res = true;
				}
				else if(plateau.echiquier[s.x-1][s.y-1].estBlanc()){
					as.x = s.x-1;
					as.y = s.y-1;
					coupJouable.joueCase(as,s);
					res = true;
					}
			}
			else{
				if(!plateau.echiquier[s.x][s.y].estLibre()){
					for (int i=0;i<largeurPt;i++) {
						for (int j=0;j<longueurPt;j++) {
							pDep = new Point(i,j);
							for(int k=0; k<7; k++){
								// Choisit un coup
								
								if(k<3){		// test des points
									pArr = plateau.pointLibre(pDep,k);
									coupJouable.joueCase(pDep,pArr);
								}
								else if(k==4)	// test des colonnes
									coupJouable.coup(k-3, i);
								else if(k==6)			// test des rangees
									coupJouable.coup(k-3, j);
								// Si le coup est valide
								if(coupJouable.estValide(plateau) && k!= 3 && k!=5){
									//System.out.println("nan");
									if(k<3)
										coupJouables[cmpt].joueCase(pDep,pArr);
									else if(k==4)
										coupJouables[cmpt].coup(k-3, i);
									else if(k==6)
										coupJouables[cmpt].coup(k-3, j);
									cmpt++;
									res = true;
								}
								
							}
						}
					}
				}
				Random r = new Random();
				if (cmpt == 1)
					coupJouable = coupJouables[0];
				else if (cmpt >= 1)
					coupJouable = coupJouables[r.nextInt(cmpt-1)];
				else
					System.out.println("Pas de coups possible, voir programmeur." + cmpt);
			}	
		}
		else if (!plateau.jBlanc){
			s = plateau.sortiemarron();
			if(plateau.echiquier[s.x][s.y].estLibre()){
				
				if(plateau.echiquier[s.x][s.y+1].estMarron()){
					as.x = s.x;
					as.y = s.y+1;
					coupJouable.joueCBas(s.x);
					res = true;
				}
				else if(plateau.echiquier[s.x+1][s.y].estMarron()){
					as.x = s.x+1;
					as.y = s.y;
					coupJouable.joueRGauche(s.y);
					res = true;
				}
				else if(plateau.echiquier[s.x+1][s.y+1].estMarron()){
					as.x = s.x+1;
					as.y = s.y+1;
					coupJouable.joueCase(as,s);
					res = true;
				}
			}
			
			else{
				if(!plateau.echiquier[s.x][s.y].estLibre()){
					for (int i=0;i<largeurPt;i++) {
						for (int j=0;j<longueurPt;j++) {
							pDep = new Point(i,j);
							for(int k=0; k<7; k++){
								// Choisit un coup
								
								if(k<3){		// test des points
									pArr = plateau.pointLibre(pDep,k);
									coupJouable.joueCase(pDep,pArr);
								}
								else if(k==3)	// test des colonnes
									coupJouable.coup(k-3, i);
								else if(k==5)			// test des rangees
									coupJouable.coup(k-3, j);
								// Si le coup est valide
								if(coupJouable.estValide(plateau) && k!=4 && k!=6){
									if(k<3)
										coupJouables[cmpt].joueCase(pDep,pArr);
									else if(k==3)
										coupJouables[cmpt].coup(k-3, i);
									else if(k==5)
										coupJouables[cmpt].coup(k-3, j);
									cmpt++;
									res = true;
								}
							}
						}
					}
				}
				Random r = new Random();
				if (cmpt == 1)
					coupJouable = coupJouables[0];
				else if (cmpt >= 1)
					coupJouable = coupJouables[r.nextInt(cmpt-1)];
				else
					System.out.println("Pas de coups possible, voir programmeur." + cmpt);
			}	
		}
		if(res == false){
			 coupJouable = niveau0(plateau);
			 
		}
		return coupJouable;
	}

	/************************************* IA Normal *************************************/
	public CoupJouable normal(Plateau pT){
		int indiceInitial = pT.calculIndicePoid();
		int indiceMax = -100;

		Plateau pTemp;
		pTemp = new Plateau();
		pTemp.copie(pT);

		CoupJouable CJ = new CoupJouable();
		CoupJouable [] coupJouables = new CoupJouable[nbBillesMax*nbCoupsPossibleMax];
		for (int k = 0; k < (nbBillesMax*nbCoupsPossibleMax);k++){
			coupJouables[k] = new CoupJouable();
		}
		int cmpt = 0;
		Point pDep = new Point(-1,-1);
		Point pArr = new Point(-1,-1);

		for(int i=0; i<largeurPt; i++){
			for(int j=0; j<longueurPt;j++){
				if((pT.jBlanc && pT.echiquier[i][j].estBlanc()) || (!pT.jBlanc && pT.echiquier[i][j].estMarron())){
					pDep = new Point(i,j);
					pArr = pT.pointLibre(pDep,0);
					CJ.joueCase(pDep,pArr);
					if(CJ.estValide(pT)){
						pTemp.Joue(CJ,false);
						if (pTemp.calculIndicePoid()>indiceMax){ 
							cmpt = 0;
							coupJouables[cmpt].joueCase(pDep,pArr);
							cmpt++;
							indiceMax =pTemp.calculIndicePoid();
						}
						else if (pTemp.calculIndicePoid()==indiceMax){ 
							coupJouables[cmpt].joueCase(pDep,pArr);
							cmpt++;
						}
						pTemp.copie(pT);
					}
					pArr = pT.pointLibre(pDep,1);
					CJ.joueCase(pDep,pArr);
					if(CJ.estValide(pT)){
							pTemp.Joue(CJ,false);
						if (pTemp.calculIndicePoid()>indiceMax){ 
							cmpt = 0;
							coupJouables[cmpt].joueCase(pDep,pArr);
							cmpt++;
							indiceMax =pTemp.calculIndicePoid();
						}
						else if (pTemp.calculIndicePoid()== indiceMax){ 
							coupJouables[cmpt].joueCase(pDep,pArr);
							cmpt++;
						}
						pTemp.copie(pT);
					}
					pArr = pT.pointLibre(pDep,2);
					CJ.joueCase(pDep,pArr);
					if(CJ.estValide(pT)){
						pTemp.Joue(CJ,false);
						if (pTemp.calculIndicePoid()>indiceMax){ 
							cmpt = 0;
							coupJouables[cmpt].joueCase(pDep,pArr);
							cmpt++;
							indiceMax =pTemp.calculIndicePoid();
						}
						else if (pTemp.calculIndicePoid()==indiceMax){ 
							coupJouables[cmpt].joueCase(pDep,pArr);
							cmpt++;
						}
						pTemp.copie(pT);
					}
					CJ.joueCHaut(i);
					if(CJ.estValide(pT)){
						pTemp.Joue(CJ,false);
						if (pTemp.calculIndicePoid()>indiceMax){ 
							cmpt = 0;
							coupJouables[cmpt].joueCHaut(i);
							cmpt++;
							indiceMax =pTemp.calculIndicePoid();
						}
						else if (pTemp.calculIndicePoid()==indiceMax){ 
							coupJouables[cmpt].joueCHaut(i);
							cmpt++;
						}
						pTemp.copie(pT);
					}
					CJ.joueCBas(i);
					if(CJ.estValide(pT)){
						pTemp.Joue(CJ,false);
						if (pTemp.calculIndicePoid()>indiceMax){ 
							cmpt = 0;
							coupJouables[cmpt].joueCBas(i);
							cmpt++;
							indiceMax =pTemp.calculIndicePoid();
						}
						else if (pTemp.calculIndicePoid()==indiceMax){ 
							coupJouables[cmpt].joueCBas(i);
							cmpt++;
						}
						pTemp.copie(pT);
					}
					CJ.joueRDroite(j);
					if(CJ.estValide(pT)){
						pTemp.Joue(CJ,false);
						if (pTemp.calculIndicePoid()>indiceMax){ 
							cmpt = 0;
							coupJouables[cmpt].joueRDroite(j);
							cmpt++;
							indiceMax =pTemp.calculIndicePoid();
						}
						else if (pTemp.calculIndicePoid()==indiceMax){ 
							coupJouables[cmpt].joueRDroite(j);
							cmpt++;
						}
						pTemp.copie(pT);
					}
					CJ.joueRGauche(j);
					if(CJ.estValide(pT)){
						pTemp.Joue(CJ,false);
						if (pTemp.calculIndicePoid()>indiceMax){ 
							cmpt = 0;
							coupJouables[cmpt].joueRGauche(j);
							cmpt++;
							indiceMax =pTemp.calculIndicePoid();
						}
						else if (pTemp.calculIndicePoid()==indiceMax){ 
							coupJouables[cmpt].joueRGauche(j);
							cmpt++;
						}
						pTemp.copie(pT);
					}
				}
			}
		}
		Random r = new Random();
		if (cmpt == 1)
			CJ = coupJouables[0];
		else
			CJ = coupJouables[r.nextInt(cmpt-1)];
		return CJ;
	}
	
	/*
	if(k == 0){
								System.out.println("Bille Gauche 		x : " + i + "	y : " + j + "	valeur = " + tmp);
							}
							else if(k == 1){
								System.out.println("Bille Droite 		x : " + i + "	y : " + j + "	valeur = " + tmp);
							}
							else if(k == 2){
								System.out.println("Bille Diagonal 		x : " + i + "	y : " + j + "	valeur = " + tmp);
							}
							else if(k == 3){
								System.out.println("Colonne Haut		x : " + i + "		valeur = " + tmp);
							}
							else if(k == 4){
								System.out.println("Colonne Bas		x : " + i + "		valeur = " + tmp);
							}
							else if(k == 5){
								System.out.println("Ligne Droite		y : " + j + "		valeur = " + tmp);
							}
							else if(k == 6){
								System.out.println("Ligne Gauche		y : " + j + "		valeur = " + tmp);
							}
	*/
	/************************************* IA Difficile *************************************/
	
	public CoupJouable hard(Plateau pT, int profondeur){
		this.isMax = true;
		int alpha = -10000;
		int beta = 10000;
		CoupJouable CJmax=new CoupJouable();
		int tmp, max = -100000;
		Plateau pTemp = new Plateau();
		pTemp.copie(pT);
		
		LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
		list = pT.listeCoupsPossibles();
		for(CoupJouable CJ : list){
			pTemp.Joue(CJ,false);
			/*if(faireAlpha)
				tmp = alphaBeta(pTemp, alpha, beta, profondeur-1);
			else*/
				tmp = Min(pTemp, profondeur-1);
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
		//System.out.println(" Poids blanc : " + poidsB(pT) + " Poids Marron : " + poidsM(pT));
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
	// alpha est toujours inférieur à beta 
	private int alphaBeta(Plateau pT, int A, int B, int profondeur){
		this.isMax = !isMax;
		int val;
		Plateau pTemp= new Plateau();
		pTemp.copie(pT);
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT, isMax);
		else{
			LinkedList<CoupJouable> list = new<CoupJouable> LinkedList();
			list = pT.listeCoupsPossibles();
			if(!isMax){
				val = 100000;
				for(CoupJouable CJ : list){
					pTemp.Joue(CJ,false);
					val = minimum(val, alphaBeta(pTemp, A, B, profondeur-1));
					pTemp.copie(pT);
				}
				if(A >= val) // coupure alpha 
					return val;
				B = minimum(B, val);
				
			}
			else{
				val = -100000;
				for(CoupJouable CJ : list){
					pTemp.Joue(CJ,false);
					val = maximum(val, alphaBeta(pTemp, A, B ,profondeur-1));
					pTemp.copie(pT);
				}
				if(val >= B) // coupure beta 
					return val;
				A = maximum(A, val);
			}
		}
		return val;
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

