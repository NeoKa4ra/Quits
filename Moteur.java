
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter ;
import java.io.IOException ;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

public class Moteur {

	
	Moteur(){
	
	}
	
	public static void sauvegarde(String nom,Plateau plateau,Sauvegarde sauvegarde) throws IOException {
		sauvegarde.sauv_Fich(nom);
		File f=new File(nom);
		int i,j;
		FileWriter fichier = new FileWriter(f);
		for(j=4;j>=0;j--){
			for(i=0;i<5;i++){
				if(plateau.echiquier[i][j].estLibre())
					fichier.write(0);
				else if(plateau.echiquier[i][j].estMarron())
					fichier.write(2);	
				else
					fichier.write(1);
				
				if(plateau.echiquier[i][j].num_bille!=-1)
					fichier.write(plateau.echiquier[i][j].num_bille);
				else
					fichier.write(5);
			}
		}
		
		if(plateau.jBlancjoue())
			fichier.write(1);
		else
			fichier.write(0);
		
		fichier.write(plateau.nbBlancSortis());
		fichier.write(plateau.nbMarronSortis());
		
		
		LinkedList<CoupJouable> L,Lcopie;
		L=plateau.Historique();
		Lcopie= (LinkedList<CoupJouable>) L.clone();
		int position=plateau.position();
		CoupJouable coupjouable=new CoupJouable();
		
		coupjouable.copie(plateau.adv());
		i=-1;
		while(i<position){

			if(coupjouable.Colonne()==-1)
				fichier.write(5);	
			else
				fichier.write(coupjouable.Colonne());
			if(coupjouable.Rangee()==-1)
				fichier.write(5);
			else
				fichier.write(coupjouable.Rangee());
			
			Point pdep=coupjouable.PointDep();
			Point parr=coupjouable.PointArr();
			
			if(coupjouable.Sens())
				fichier.write(1);
			else
				fichier.write(0);
			
			
			if(pdep.x==-1 && pdep.y==-1){
				fichier.write(5);
				fichier.write(5);
			}
			else{
				fichier.write(pdep.x);
				fichier.write(pdep.y);
			}	
			if(parr.x==-1 && parr.y==-1){
				fichier.write(5);
				fichier.write(5);
			}
			else{
				fichier.write(parr.x);
				fichier.write(parr.y);
			}
			
			if(coupjouable.blancSorti())
				fichier.write(1);	
			else
				fichier.write(0);
			
			if(coupjouable.marronSorti())
				fichier.write(1);	
			else
				fichier.write(0);
			i++;
			if(i!=position)
				coupjouable=(CoupJouable) L.get(i);
	
		}
		
		
		fichier.close();
	}
	
	public static void charger(String nom,Plateau plateau) throws IOException{
		File f=new File(nom);
		plateau.lecture(f);
		
	}
	
	public static void supp_sauv(String nom,Sauvegarde sauvegarde) throws IOException{
		File f=new File(nom);
		sauvegarde.supp_Fiche(nom);
		f.delete();
	}
	
	
	
	public static boolean pointJouable(Point dep, Point arr, Plateau plateau){
		CoupJouable adv=new CoupJouable();
		boolean res=false;
		CoupJouable coupjouable = new CoupJouable();
		coupjouable.joueCase(dep,arr);
		if(coupjouable.estValide(plateau)){
			plateau.Joue(coupjouable,false);
			res = true;
		}
		
		return res;		
	}
	
	public static boolean rangeeJouableD(int rangee, Plateau plateau){
		
		boolean res = false;
		CoupJouable coupjouable = new CoupJouable();
		coupjouable.joueRDroite(rangee);
		if(coupjouable.estValide(plateau)){
			plateau.Joue(coupjouable,false);
			res = true;
		}
	
		return res;	
	}
	
	
	public static boolean rangeeJouableG(int rangee, Plateau plateau){
		
		boolean res = false;
		CoupJouable coupjouable = new CoupJouable();
		coupjouable.joueRGauche(rangee);
		if(coupjouable.estValide(plateau)){
			plateau.Joue(coupjouable,false);
			res = true;
		}
	
		return res;	
	}
	
	
	public static boolean colonneJouableB(int colonne, Plateau plateau){
		
		boolean res = false;
		CoupJouable coupjouable= new CoupJouable();
		coupjouable.joueCBas(colonne);
		if(coupjouable.estValide(plateau)){
			plateau.Joue(coupjouable,false);
			res = true;
		}
		
		return res;	
	}
	
	public static boolean colonneJouableH(int colonne, Plateau plateau){
		
		boolean res = false;
		CoupJouable coupjouable= new CoupJouable();
		coupjouable.joueCHaut(colonne);
		if(coupjouable.estValide(plateau)){
			plateau.Joue(coupjouable,false);
			res = true;
		}
		
		return res;	
	}
	
	public static void annuler(Plateau plateau){
		CoupJouable coupjouable=new CoupJouable();
		int position=plateau.position();
		LinkedList<CoupJouable> L=(LinkedList<CoupJouable>) plateau.Historique().clone();
		if(position!=0){
			coupjouable.copie(L.get(position-1));
			plateau.Annuler(coupjouable);
		}	
		
	}
	
	public static void refaire(Plateau plateau){
		CoupJouable coupjouable=new CoupJouable();
		int position=plateau.position();

			LinkedList<CoupJouable> L=(LinkedList<CoupJouable>) plateau.Historique().clone();
			if(position!=L.size()){
				coupjouable=L.get(position);
				plateau.Joue(coupjouable,true);
			}	
		
	}
	
	public static void inverser(Plateau plateau){
		plateau.inverser();
		
	}
	
	
	
	
	

	
	
}	
