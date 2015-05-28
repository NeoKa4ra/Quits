import java.io.File;
import java.util.LinkedList;


public class Sauvegarde {

	LinkedList<String> listFichier;
	
	Sauvegarde(){
		listFichier=new LinkedList();	
	}
	
	public void sauv_Fich(String nom_fich){
		File f=new File(nom_fich);
		listFichier.add(nom_fich);
	}
	
	public void supp_Fiche(String nom_fich){
		int i;
		if(!listFichier.contains(nom_fich)){
			i=listFichier.indexOf(nom_fich);
			listFichier.remove(i);
		}
			
	}
	
	
}
