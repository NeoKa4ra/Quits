import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;


public class Sauvegarde {

	LinkedList<String> listFichier;
	
	Sauvegarde() throws IOException{
	File f=new File("sauvegarde");	
	listFichier=new LinkedList();
	if(!f.createNewFile()){
			FileReader fichier = new FileReader(f);	
			int longueur=(int) fichier.read();
			int i;
			String str;
			while(longueur!=-1){
				char []data=new char[longueur];
				for(i=0;i<longueur;i++){
					data[i]=(char) fichier.read();
				}
				longueur=(int) fichier.read();
				str=new String(data);
				listFichier.add(str);
			}
			fichier.close();
	}


	}
	
	public void sauv_Fich(String nom_fich) throws IOException{
		if(!listFichier.contains(nom_fich)){
			//File f=new File(nom_fich);
			listFichier.add(nom_fich);
			File f1=new File("sauvegarde");
			int longueur=nom_fich.length();
			int i;
			FileWriter fichier1=new FileWriter(f1,true);	
			fichier1.write(longueur);
			for(i=0;i<longueur;i++)
				fichier1.write(nom_fich.charAt(i));
			fichier1.close();
		}	
	}
	
	public void afficher(){
		String str;
		Iterator it=listFichier.iterator();
		while(it.hasNext()){
			str=(String)it.next();
			System.out.println(str);
		}
	}
	
	public void supp_Fiche(String nom_fich) throws IOException{
		int i;
		if(listFichier.contains(nom_fich)){
			i=listFichier.indexOf(nom_fich);
			listFichier.remove(i);
			File f1=new File("sauvegarde");
			FileWriter fichier1=new FileWriter(f1);
			String str;
			Iterator it=listFichier.iterator();
			while(it.hasNext()){
				str=(String)it.next();
				int longueur=str.length();
				fichier1.write(longueur);
				for(i=0;i<longueur;i++)
					fichier1.write(nom_fich.charAt(i));			
			}
			fichier1.close();
			
		}
			
	}
	
	
}
