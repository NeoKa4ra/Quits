
public class Case {
	// 0 pour case libre
	// 1 pour case occupee par blanc
	// 2 pour case occupee par marron
	int contenu;
	
	// constructeur
	public Case(int contenu){
		
		this.contenu=contenu;
	}
	
	// la case est libre
	public boolean estLibre(){
		return contenu==0;
	}
	
	// la case est occupee
	public boolean estOcc(){
		return (contenu==1 || contenu==2);
	}
	
	// la case est occupe par marron
	public boolean estMarron(){
		return contenu==2;
	}
	
	// la case est occupe par blanc
	public boolean estBlanc(){
		return contenu==1;
	}
	
	// libere la case
	public void libere(){
		contenu=0;
	}
	
	// change le contenu de la case avec pion blanc
	public void blanchit(){
		contenu=1;
	}
	
	// change le contenu de la case avec pion marron
	public void noircit(){
		contenu=2;
	}
	
	// change le contenu de la case en fonctin du contenu d'une case
	public void change(Case c){
		contenu=c.contenu;
	}
	
	public void afficher(){
		
		if(estLibre())
			System.out.printf("0"+" ");
		else if(estMarron())
			System.out.printf("2"+" ");	
		else
			System.out.printf("1"+" ");	
	}
	
	public int Contenu(){
		return contenu;
	}
	
	
	
	
}
