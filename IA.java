import java.util.Random;
import java.awt.Point;

public class IA {
	final int largeurPt = 5;
	final int longueurPt = 5;
	final int nbCoupsPossibleMax = 7;
	final int nbBillesMax = 5;
	boolean IActuel;
	// constructeur
	IA(){
	
	}
	
	/************************************* IA 0 *************************************/
	
	public CoupJouable niveau0(Plateau pT, CoupJouable coupPrecAdv){
		CoupJouable CJ = new CoupJouable();
		CoupJouable [] coupJouables = new CoupJouable[nbBillesMax*nbCoupsPossibleMax];
		for (int k = 0; k < (nbBillesMax*nbCoupsPossibleMax);k++)
			coupJouables[k] = new CoupJouable();
		int cmpt = 0;
		Point pDep = new Point(-1,-1);
		Point pArr = new Point(-1,-1);
		for (int i=0;i<largeurPt;i++) {
			for (int j=0;j<longueurPt;j++) {
				if((pT.jBlancjoue() && pT.echiquier[i][j].estBlanc()) || (!pT.jBlancjoue() && pT.echiquier[i][j].estMarron())){
					pDep = new Point(i,j);
					for(int k=0; k<7; k++){
						// Choisit un coup
						if(k<3){		// test des points
							pArr = pT.pointLibre(pDep,k);
							CJ.joueCase(pDep,pArr);
						}
						else if(k<5)	// test des colonnes
							CJ.coup(k-3, i);
						else			// test des rangees
							CJ.coup(k-3, j);
						// Si le coup est valide
						if(CJ.estValide(pT,coupPrecAdv)){
							if(k<3)
								coupJouables[cmpt].joueCase(pDep,pArr);
							else if(k<5)
								coupJouables[cmpt].coup(k-3, i);
							else
								coupJouables[cmpt].coup(k-3, j);
							cmpt++;
						}
					}
				}
			}
		}
		
		Random r = new Random();
		if (cmpt == 1)
			CJ = coupJouables[0];
		else if (cmpt >= 1)
			CJ = coupJouables[r.nextInt(cmpt-1)];
		else
			System.out.println("Pas de coups possible, voir programmeur.");
			
		return CJ;
	}

	/************************************* IA DE MERDE *************************************/
	public CoupJouable normal(Plateau pT, CoupJouable coupPrecAdv){
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
					if(CJ.estValide(pT,coupPrecAdv)){
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
					if(CJ.estValide(pT,coupPrecAdv)){
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
					if(CJ.estValide(pT,coupPrecAdv)){
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
					if(CJ.estValide(pT,coupPrecAdv)){
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
					if(CJ.estValide(pT,coupPrecAdv)){
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
					if(CJ.estValide(pT,coupPrecAdv)){
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
					if(CJ.estValide(pT,coupPrecAdv)){
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
	/************************************* IA Difficile *************************************/
	
	public CoupJouable hard(Plateau pT, int profondeur, CoupJouable coupPrecAdv){
		this.IActuel = true;
		CoupJouable CJ=new CoupJouable();
		CoupJouable CJmax=new CoupJouable();
		int tmp, max = -100000;
		Plateau pTemp = new Plateau();
		pTemp.copie(pT);
		Point pDep = new Point(-1,-1);
		Point pArr = new Point(-1,-1);
		for(int i=0; i<largeurPt; i++){
			for(int j=0; j<longueurPt;j++){
				if((pT.jBlancjoue() && pT.echiquier[i][j].estBlanc()) || (!pT.jBlancjoue() && pT.echiquier[i][j].estMarron())){
					pDep = new Point(i,j);
					// Pour les 7 coups possibles de la bille
					for(int k=0; k<7; k++){
						// Choisit un coup
						if(k<3){		// test des points
							pArr = pT.pointLibre(pDep,k);
							CJ.joueCase(pDep,pArr);
						}
						else if(k<5)	// test des colonnes
							CJ.coup(k-3, i);
						else			// test des rangees
							CJ.coup(k-3, j);
						// Si le coup est valide
						if(CJ.estValide(pT,coupPrecAdv)){
							pTemp.Joue(CJ,false);
							tmp = Max(pTemp, profondeur-1,coupPrecAdv);
							
							
							if(k<3){		// test des points
								System.out.println("jouer : " + i + j +" Coup : " + k + " valeur : " + tmp);
							}
							else if(k<5)	// test des colonnes
								CJ.coup(k-3, i);
							else			// test des rangees
								CJ.coup(k-3, j);
							System.out.println("case : " + i + j +" Coup : " + k + " valeur : " + tmp);
							if(tmp > max){
								
								max = tmp;
								CJmax.copie(CJ);
							}
							pTemp.copie(pT);
						}
					}
				}
			}
		}
		return CJmax;
	}
	
	private int Max(Plateau pT, int profondeur, CoupJouable coupPrecAdv){
		CoupJouable CJ = new CoupJouable();
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT,true);
		int max, tmp;
		max = -100000;

		Plateau pTemp= new Plateau();
		pTemp.copie(pT);
		Point pDep = new Point(-1,-1);
		Point pArr = new Point(-1,-1);
		for (int i=0;i<largeurPt;i++){
			for(int j=0;j<longueurPt;j++){
				if((pT.jBlancjoue() && pT.echiquier[i][j].estBlanc()) || (!pT.jBlancjoue() && pT.echiquier[i][j].estMarron())){
					pDep = new Point(i,j);
					// Pour les 7 coups possibles de la bille
					for(int k=0; k<7; k++){
						// Choisit un coup
						if(k<3){		// test des points
							pArr = pT.pointLibre(pDep,k);
							CJ.joueCase(pDep,pArr);
						}
						else if(k<5)	// test des colonnes
							CJ.coup(k-3, i);
						else			// test des rangees
							CJ.coup(k-3, j);
						// Si le coup est valide
						if(CJ.estValide(pT,coupPrecAdv)){
							pTemp.Joue(CJ,false);
							tmp = Min(pTemp, profondeur-1,coupPrecAdv);
							if(tmp > max)
								max = tmp;
							pTemp.copie(pT);
						}
					}
				}
			}
		}
		return max;
	}
	
	private int Min(Plateau pT, int profondeur, CoupJouable coupPrecAdv){
		CoupJouable CJ = new CoupJouable();
		if(profondeur == 0 || partieFinie(pT))
			return eval(pT,false);
		int min, tmp;
		min = 100000;

		Plateau pTemp= new Plateau();
		pTemp.copie(pT);
		Point pDep = new Point(-1,-1);
		Point pArr = new Point(-1,-1);
		for (int i=0;i<largeurPt;i++){
			for(int j=0;j<longueurPt;j++){
				if((pT.jBlancjoue() && pT.echiquier[i][j].estBlanc()) || (!pT.jBlancjoue() && pT.echiquier[i][j].estMarron())){
					pDep = new Point(i,j);
					// Pour les 7 coups possibles de la bille
					for(int k=0; k<7; k++){
						// Choisit un coup
						if(k<3){		// test des points
							pArr = pT.pointLibre(pDep,k);
							CJ.joueCase(pDep,pArr);
						}
						else if(k<5)	// test des colonnes
							CJ.coup(k-3, i);
						else			// test des rangees
							CJ.coup(k-3, j);
						// Si le coup est valide
						if(CJ.estValide(pT,coupPrecAdv)){
							pTemp.Joue(CJ,false);
							tmp = Max(pTemp, profondeur-1,coupPrecAdv);
							if(tmp < min)
								min = tmp;
							pTemp.copie(pT);
						}
					}
				}
			}
		}
		return min;
	}
	private int eval(Plateau pT, boolean IActuel){
		int res = 0;
		//System.out.println(" Poids blanc : " + poidsB(pT) + " Poids Marron : " + poidsM(pT));
		if(pT.jBlancjoue())
			res = poidsB(pT) - poidsM(pT);
		else
			res = poidsM(pT) - poidsB(pT);
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
		res += pT.nbBlancSortis()*150;
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
		res += pT.nbMarronSortis()*150;
		return res;
	}


	private int[][] coutB(){
		int [][] mat = new int[5][5];
		mat[0][0] = 1000;
		mat[0][1] = 10;
		mat[0][2] = 20;
		mat[0][3] = 30;
		mat[0][4] = 40;
		mat[1][0] = 0;
		mat[1][1] = 10;
		mat[1][2] = 20;
		mat[1][3] = 35;
		mat[1][4] = 50;
		mat[2][0] = 0;
		mat[2][1] = 0;
		mat[2][2] = 35;
		mat[2][3] = 40;
		mat[2][4] = 90;
		mat[3][0] = 0;
		mat[3][1] = 0;
		mat[3][2] = 0;
		mat[3][3] = 100;
		mat[3][4] = 110;
		mat[4][0] = 0;
		mat[4][1] = 0;
		mat[4][2] = 0;
		mat[4][3] = 0;
		mat[4][4] = 1000; 
		return mat;
	}

	private int[][] coutM(){
		int [][] mat = new int[5][5];
		int [][] temp = new int[5][5];
		int [] tab = new int[25];
		temp = coutB();
		int cpt = 0;
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
				tab[cpt] = temp[i][j];
				cpt++;
			}
		}
		cpt = 0;
		for (int i=4;i>=0;i--){
			for (int j=4;j>=0;j--){
				mat[i][j] = tab[cpt];
				cpt++;
			}
		}
		
		return mat;
	}
}
