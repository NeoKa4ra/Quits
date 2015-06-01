
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


public class Plateau {
	Case [][] echiquier;
	
	// le coup qu'a joué l'adversaire
	CoupJouable adv;
	
	int nbBlancSortis,nbMarronSortis;
	
	
	// le joueur blanc est en train de jouer
	boolean jBlanc;
	
	LinkedList<CoupJouable> historique;
	
	int position;
	
	public CoupJouable adv(){
		return adv;
	}
	
	public void inc_position(){
		position++;
	}
	
	public void dec_position(){
		position--;
	}
	
	public int position(){
		return position;
	}
	
	public void inverser_case(int i1,int j1,int i2,int j2){
		
		Case c=new Case(0,0);
		
		c.change(echiquier[i1][j1]);
		echiquier[i1][j1].change(echiquier[i2][j2]);
		echiquier[i2][j2].change(echiquier[i1][j1]);
		
	/*	if(echiquier[i1][j1].Contenu()==0)
			c=new Case(0);
		else if(echiquier[i1][j1].Contenu()==1)
			c=new Case(1);
		else
			c=new Case(2);
		
		if(echiquier[i2][j2].Contenu()==0)
			echiquier[i1][j1]=new Case(0);
		
		else if(echiquier[i2][j2].Contenu()==1)
			echiquier[i1][j1]=new Case(1);
		
		else
			echiquier[i1][j1]=new Case(2);
		
		
		if(c.Contenu()==0)
			echiquier[i2][j2]=new Case(0);
		
		else if(c.Contenu()==1)
			echiquier[i2][j2]=new Case(1);
		
		else
			echiquier[i2][j2]=new Case(2); */
		
		
	}
	
	public void inverser(){
		int i,j;
		
		for(j=0;j<5;j++){

			for(i=0;i<4-j;i++){
				
				inverser_case(i,j,4-i,4-j);

			}	
		}
		
		
	}
	
	
	public void ajoutHistorique(CoupJouable coupjouable){
		historique.add(position,coupjouable);
	}
	
	public LinkedList<CoupJouable> Historique(){
		return historique;
	}
	
	public void copie(Plateau plateau){
		nbBlancSortis=plateau.nbBlancSortis();
		nbMarronSortis=plateau.nbMarronSortis();
		
		jBlanc=plateau.jBlanc;
		historique= (LinkedList<CoupJouable>) plateau.Historique().clone();
		position=plateau.position();
		
		int i,j;
		for(i=0;i<5;i++){
			for(j=0;j<5;j++){
				if(plateau.echiquier[i][j].Contenu()==0)
					echiquier[i][j]=new Case(0,-1);
				else if(plateau.echiquier[i][j].Contenu()==1)
					echiquier[i][j]=new Case(1,-1);
				else
					echiquier[i][j]=new Case(2,-1);
			}
		}
		
	}
	
	
	Plateau(){
		// La matrice est 5*5
		echiquier=new Case[5][5];
		jBlanc=false;
		adv=new CoupJouable();
	}
	
	// retour de quel joueur joue
	public boolean jBlancjoue(){
		return jBlanc;
	}
	
	// change le joueur qui est en train de jouer
	public void changeJoueur(){
		jBlanc=!jBlanc;
	}
	
	// initialisation de toutes les cases � libres
	public void init_2_joueurs(){

		int i,j;
		// initialisation des cases � libre
		for(i=0;i<5;i++){
			for(j=0;j<5;j++){
					echiquier[i][j]=new Case(0,-1);					
			}
		
			
		}
		historique=new<CoupJouable> LinkedList();
		position=0;
		
		// initialisation des scores
		nbBlancSortis=0;
		nbMarronSortis=0;

		// mise en place des billes blanches
		echiquier[0][1]=new Case(1,2);
		echiquier[0][2]=new Case(1,3);
		echiquier[1][0]=new Case(1,0);
		echiquier[1][1]=new Case(1,4);
		echiquier[2][0]=new Case(1,1);
		
		// mise en place des billes marrons
		echiquier[2][4]=new Case(2,6);
		echiquier[3][4]=new Case(2,9);
		echiquier[3][3]=new Case(2,7);
		echiquier[4][3]=new Case(2,8);
		echiquier[4][2]=new Case(2,5);
		
	
	}
	// Getters
	public int nbMarronSortis(){
		return nbMarronSortis;
	}
	public int nbBlancSortis(){
		return nbBlancSortis;
	}
	
	// afficher les valeurs de la matrice
	public void afficheMatrice(){
		int i,j;
		for(j=4;j>=0;j--){
			for(i=0;i<5;i++){
				if(echiquier[i][j].estLibre())
					System.out.printf("0");
				else if(echiquier[i][j].estMarron())
					System.out.printf("2");	
				else
					System.out.printf("1");
				
				System.out.printf(" ");		
			}
			System.out.println();				
		}
	}
	
	// afficher les cases de l'echiquier
	public void afficher(){
		int i,j;
		for(j=4;j>=0;j--){
			for(i=0;i<5;i++){
				if(echiquier[i][j].estLibre())
					System.out.printf("("+ 0+",");
				else if(echiquier[i][j].estMarron())
					System.out.printf("("+"2"+",");	
				else
					System.out.printf("("+1+",");
				
				System.out.printf(echiquier[i][j].num_bille+ ") ");		
			}
			System.out.printf("\n");				
		}
		
		if(jBlanc)
			System.out.println("jblanc :"+"1");
		else
			System.out.println("jblanc :"+"0");
		
		
		System.out.println("nbmarronsortis :"+nbBlancSortis);
		System.out.println("nbblancsortis :"+nbMarronSortis);
		
		System.out.println("position :"+position);
		
		LinkedList<CoupJouable> Lcopie;
		Lcopie= (LinkedList<CoupJouable>) historique.clone();
		CoupJouable coupjouable=new CoupJouable();
		coupjouable.copie(adv);
		
		i=-1;
		while(i<position){

			System.out.print(coupjouable.Colonne()+" ");
			System.out.print(coupjouable.Rangee()+" ");
			Point parr=coupjouable.PointArr();
			Point pdep=coupjouable.PointDep();
			
			if(coupjouable.Sens())
				System.out.print(1+" ");
			else
				System.out.print(0+" ");
			
			System.out.print(pdep.x+" ");
			System.out.print(pdep.y+" ");
			System.out.print(parr.x+" ");
			System.out.print(parr.y+" ");	
			
			if(coupjouable.blancSorti())
				System.out.print("1" +" ");	
			else
				System.out.print("0" +" ");				
			
			if(coupjouable.marronSorti())
				System.out.print("1" +" ");	
			else
				System.out.print("0" +" ");	
			
			System.out.printf("\n");
			i++;
			if(i!=position)
				coupjouable=(CoupJouable) Lcopie.get(i);
		}
		
	}
	

	// on calcul le poitnde sortie pour une bille blanche
	public Point sortieblanche(){
		return new Point(4,4);
	}
	// on calcul le poitn de sortie pour une bille marron
	public Point sortiemarron(){
		return new Point(0,0);
	}
	
	
	// on calcule le nouveau tableau en fonction du coupjouable
	public void Joue(CoupJouable coupjouable,boolean refaire){
		// premier cas c'est une case
		if(coupjouable.estCase()){
			Point p1,p2;
			p1=coupjouable.PointDep();
			p2=coupjouable.PointArr();
			echiquier[p2.x][p2.y].change(echiquier[p1.x][p1.y]);
			echiquier[p1.x][p1.y].libere();
		}
		// deuxieme cas c'est une colonne
		else if(coupjouable.estColonne()){
			int c=coupjouable.Colonne();
			int i;
			Case anciennecase=new Case(0,0);
			Case nouvellecase=new Case(0,0);
			// bas haut
			if(coupjouable.Sens()){
				nouvellecase.change(echiquier[c][0]);			
				for(i=0;i<=3;i++){
					anciennecase.change(echiquier[c][i+1]);
					echiquier[c][i+1].change(nouvellecase);
					nouvellecase.change(anciennecase);
					
				}
				echiquier[c][0].change(nouvellecase);
			}
			// haut bas
			else{
				nouvellecase.change(echiquier[c][4]);			
				for(i=4;i>=1;i--){
					anciennecase.change(echiquier[c][i-1]);
					echiquier[c][i-1].change(nouvellecase);
					nouvellecase.change(anciennecase);
				}
				echiquier[c][4].change(nouvellecase);
			}
		}
		// 3 eme cas c'est une rangee
		else{
				int r=coupjouable.Rangee();
				int i;
				Case anciennecase=new Case(0,0);
				Case nouvellecase=new Case(0,0);
				// gauche droite
				if(coupjouable.Sens()){
					nouvellecase.change(echiquier[0][r]);			
					for(i=0;i<=3;i++){
						anciennecase.change(echiquier[i+1][r]);
						echiquier[i+1][r].change(nouvellecase);
						nouvellecase.change(anciennecase);
					}
					echiquier[0][r].change(nouvellecase);
				}
				// droite gauche
				else{
					nouvellecase.change(echiquier[4][r]);			
					for(i=4;i>=1;i--){
						anciennecase.change(echiquier[i-1][r]);
						echiquier[i-1][r].change(nouvellecase);
						nouvellecase.change(anciennecase);
					}
					echiquier[4][r].change(nouvellecase);
				}
				
		}
		//maj des scores
		//liberation de la sortie si elle etait occupee par une bille de la mauvaise couleur
		Point sortiemarron=sortiemarron();
		Point sortieblanc=sortieblanche();
		if(echiquier[sortiemarron.x][sortiemarron.y].estMarron()){
			nbMarronSortis++;
			echiquier[sortiemarron.x][sortiemarron.y].libere();
			coupjouable.marronSort(1);
		}
		if(echiquier[sortieblanc.x][sortieblanc.y].estBlanc()){
			nbBlancSortis++;
			echiquier[sortieblanc.x][sortieblanc.y].libere();
			coupjouable.blancSort(1);
		}
		changeJoueur();
		if(!refaire)
			ajoutHistorique(coupjouable);
		
		inc_position();
		adv.copie(coupjouable);
	}
	
	public boolean VerifPionC(int colonne,boolean jblanc){
		boolean res=false;
		int i;
		if(jblanc){
			for(i=0;i<=4;i++){
				if(echiquier[colonne][i].estBlanc())
					res=true;
			}		
		}
		else{
			for(i=0;i<=4;i++){
				if(echiquier[colonne][i].estMarron())
					res=true;
			}
		}
		return res;		
	
	}
	
	public boolean VerifPionR(int rangee,boolean jblanc){
		boolean res=false;
		int i;
		if(jblanc){
			for(i=0;i<=4;i++){
				if(echiquier[i][rangee].estBlanc())
					res=true;
			}		
		}
		else{
			for(i=0;i<=4;i++){
				if(echiquier[i][rangee].estMarron())
					res=true;
			}
		}
		return res;		
	
	}
	
	
	public boolean estJouableCHaut(int colonne,boolean jblanc){
		
		return echiquier[colonne][4].estLibre() && VerifPionC(colonne,jblanc);
	
	}
	
	public boolean estJouableCBas(int colonne,boolean jblanc){
		
		return echiquier[colonne][0].estLibre() && VerifPionC(colonne,jblanc);
	
	}
	
	public boolean estJouableRGauche(int rangee,boolean jblanc){
		
		return echiquier[0][rangee].estLibre() && VerifPionR(rangee,jblanc);
	
	}
	
	public boolean estJouableRDroite(int rangee,boolean jblanc){
		
		return echiquier[4][rangee].estLibre() && VerifPionR(rangee,jblanc);
	
	}
	
	public void lecture(File f) throws IOException{
		int i,j;
		FileReader fichier = new FileReader(f);	
		int contenu,num_bille;
		for(j=4;j>=0;j--){
			for(i=0;i<5;i++){
				contenu = (int) fichier.read();
				num_bille = (int) fichier.read();
				if(num_bille==5)
					num_bille=-1;
				Case c=new Case(contenu,num_bille);
				echiquier[i][j]=c;
			}
		}
		
		if((int) fichier.read()==1)
			jBlanc=true;
		else
			jBlanc=false;
		
		nbBlancSortis=(int) fichier.read();
		nbMarronSortis=(int) fichier.read();
		
		
		historique=new<CoupJouable> LinkedList();
		

		Point p1,p2;
		int colonne=(int) fichier.read();
		position=0;
		boolean estadv=true;

		while(colonne!=-1){
			CoupJouable coupjouable=new CoupJouable();
			int rangee=(int) fichier.read();
			int bool=(int) fichier.read();
			int x1=(int) fichier.read();
			int y1=(int) fichier.read();
			int x2=(int) fichier.read();
			int y2=(int) fichier.read();
			
			int blancsort=(int) fichier.read();
			int marronsort=(int) fichier.read();
			
			p1=new Point(x1,y1);
			p2=new Point(x2,y2);
			
			if(p1.x==5 && p1.y==5){
				p1=new Point(-1,-1);
			}	
			if(p2.x==5 && p2.y==5){
				p2=new Point(-1,-1);
			}
			if(colonne==5)
				colonne=-1;	
			
			if(rangee==5)
				rangee=-1;
			

			if(colonne!=-1){
				if(bool==1)
					coupjouable.joueCHaut(colonne);
				else
					coupjouable.joueCBas(colonne);
			}	
			else if(rangee!=-1){
				if(bool==1)
					coupjouable.joueRDroite(rangee);
				else
					coupjouable.joueRGauche(rangee);				
			}	
			else
				coupjouable.joueCase(p1,p2);
			
			
			if(blancsort==1)
				coupjouable.blancSort(1);
			else
				coupjouable.blancSort(0);
			
			if(marronsort==1)
				coupjouable.marronSort(1);
			else
				coupjouable.marronSort(0);
			
			
				
			
			if(estadv)
				adv.copie(coupjouable);
			else{
				historique.add(position,coupjouable);
				inc_position();
			}	
			
			
			colonne=(int) fichier.read();
			estadv=false;
				
		}
		
		
		fichier.close();
		
	}
	
	public void Annuler(CoupJouable coupjouable){
				//retour a l'ancien score
				//occupation de la sortie si elle etait occupee par une bille de la mauvaise couleur
				Point sortiemarron=sortiemarron();
				Point sortieblanc=sortieblanche();
				if(coupjouable.marronSorti()){
					nbMarronSortis--;
					echiquier[sortiemarron.x][sortiemarron.y].noircit();
				}
				if(coupjouable.blancSorti()){
					nbBlancSortis--;
					echiquier[sortieblanc.x][sortieblanc.y].blanchit();
				}
				changeJoueur();
				dec_position();
		
				// premier cas c'est une case
				if(coupjouable.estCase()){
					Point p1,p2;
					p1=coupjouable.PointDep();
					p2=coupjouable.PointArr();
					echiquier[p1.x][p1.y].change(echiquier[p2.x][p2.y]);
					echiquier[p2.x][p2.y].libere();
				}
				// deuxieme cas c'est une colonne
				else if(coupjouable.estColonne()){
					int c=coupjouable.Colonne();
					int i;
					Case anciennecase=new Case(0,0);
					Case nouvellecase=new Case(0,0);
					// inversement du bas haut
					if(!coupjouable.Sens()){
						nouvellecase.change(echiquier[c][0]);			
						for(i=0;i<=3;i++){
							anciennecase.change(echiquier[c][i+1]);
							echiquier[c][i+1].change(nouvellecase);
							nouvellecase.change(anciennecase);
							
						}
						echiquier[c][0].change(nouvellecase);
					}
					// inversement du haut bas
					else{
						nouvellecase.change(echiquier[c][4]);			
						for(i=4;i>=1;i--){
							anciennecase.change(echiquier[c][i-1]);
							echiquier[c][i-1].change(nouvellecase);
							nouvellecase.change(anciennecase);
						}
						echiquier[c][4].change(nouvellecase);
					}
				}
				// 3 eme cas c'est une rangee
				else{
						int r=coupjouable.Rangee();
						int i;
						Case anciennecase=new Case(0,0);
						Case nouvellecase=new Case(0,0);
						// inversement gauche droite
						if(!coupjouable.Sens()){
							nouvellecase.change(echiquier[0][r]);			
							for(i=0;i<=3;i++){
								anciennecase.change(echiquier[i+1][r]);
								echiquier[i+1][r].change(nouvellecase);
								nouvellecase.change(anciennecase);
							}
							echiquier[0][r].change(nouvellecase);
						}
						// inversement droite gauche
						else{
							nouvellecase.change(echiquier[4][r]);			
							for(i=4;i>=1;i--){
								anciennecase.change(echiquier[i-1][r]);
								echiquier[i-1][r].change(nouvellecase);
								nouvellecase.change(anciennecase);
							}
							echiquier[4][r].change(nouvellecase);
						}
						
				}
				if(position!=0)
					adv.copie(historique.get(position-1));
				else
					adv=new CoupJouable();

			}
	
	public boolean estCoupgagnant(CoupJouable coupjouable,CoupJouable anciencoup){
		boolean res=false;
		if(coupjouable.estValide(this)){
			Plateau plateaucopie=new Plateau();
			plateaucopie.copie(this);
			plateaucopie.Joue(coupjouable, false);
			res=plateaucopie.nbBlancSortis()==3 || plateaucopie.nbMarronSortis()==3;
		}
		return res;
		
	}
	// Retourne le point aux coordonnees ciblant la direction dir : 0 = Gauche, 1 = Droite, 2 = Biais(diagonale)
	// p = position de la bille
	// Si le point vaut (-1,-1), c'est qu'il est inaccessible ou occupe ou erreur
	public Point pointLibre(Point p, int dir){
		Point pRes = new Point(-1,-1);
		Point s = new Point(-1,-1);
		// Coordonnees a ajouter
		int x = 0, y = 0;
		if(dir == 0)
			y = 1;
		else if(dir == 1)
			x = 1;
		else if(dir == 2){
			x = 1;
			y = 1;
		}
		else
			System.out.println("dir non valide (0 : Gauche, 1 : Droite, 2 : Biais(Diagonale)");
		
		// Si la bille donnee est blanche
		if(echiquier[p.x][p.y].estBlanc()){
			s = sortieblanche();
			if(s.x >= p.x && s.y >= p.y && p.x+x <= 4 && p.y+y <= 4 && echiquier[p.x+x][p.y+y].estLibre())
				pRes = new Point (p.x+x, p.y+y);
			else if (s.x <= p.x && s.y <= p.y && p.x-x >= 0 && p.y-y >= 0 && echiquier[p.x-x][p.y-y].estLibre())
				pRes = new Point (p.x-x, p.y-y);
		}
		else{
			s = sortiemarron();
			if(s.x >= p.x && s.y >= p.y && p.x+x <= 4 && p.y+y <= 4 && echiquier[p.x+x][p.y+y].estLibre())
				pRes = new Point (p.x+x, p.y+y);
			else if (s.x <= p.x && s.y <= p.y && p.x-x >= 0 && p.y-y >= 0 && echiquier[p.x-x][p.y-y].estLibre())
				pRes = new Point (p.x-x, p.y-y);
		}
		return pRes;
	}
	
	public int poidBlanc()
    {
        int poidB=0, nbB=0;
        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                if(echiquier[i][j].estBlanc())
                {
                poidB = poidB + (i+j);
                nbB++;
                }
        }}
                poidB = poidB + ((5-nbB)*8);
                return poidB;
    }
     
    public int poidMarron()
    {
        int poidM=0, nbM=0;
        for (int i=0;i<5;i++) {
            for (int j=0;j<5;j++) {
                if(echiquier[i][j].estMarron())
                {
                poidM = poidM + (8-i-j);
                nbM++;
                }
        }}
                poidM = poidM + ((5-nbM)*8);
                return poidM;
    }
     
    public int calculIndicePoid()
    {
        if(jBlancjoue())
        {
            return poidMarron()-poidBlanc();
        }
        else
        {
            return poidBlanc()-poidMarron();
        }         
    }
    
    public LinkedList<CoupJouable> ListeCoujouable(){
		int i,j;
		
		LinkedList<CoupJouable> L = new LinkedList<CoupJouable>();
		//Iterator<CoupJouable> it= L.iterator();
		CoupJouable CJ = new CoupJouable();
	
		int largeurPt = 5;
		int longueurPt = 5;
		
			
			Point pDep = new Point(-1,-1);
			Point pArr = new Point(-1,-1);
			
			for (i=0;i<largeurPt;i++) {
				for (j=0;j<longueurPt;j++) {
					if((jBlancjoue() && echiquier[i][j].estBlanc()) || (!jBlancjoue() && echiquier[i][j].estMarron())){
						pDep = new Point(i,j);
						for(int k=0; k<7; k++){
							// Choisit un coup
							if(k<3){		// test des points
								pArr = pointLibre(pDep,k);
								CJ.joueCase(pDep,pArr);
							}
							else if(k<5)	// test des colonnes
								CJ.coup(k-3, i);
							else			// test des rangees
								CJ.coup(k-3, j);
							// Si le coup est valide
							if(CJ.estValide(this)){
								if(k<3)
									L.add(CJ);
								else if(k<5)
									L.add(CJ);
								else
									L.add(CJ);
							}
						}
					}
				}
			}
			return L;
	}
}
	
	
	

