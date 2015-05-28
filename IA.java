
public class IA {
	Plateau plateau;
	
	// constructeur
	IA(Plateau plateau){
		this.plateau=plateau;
	}
	
	/************************************* IA Facile *************************************/
	
	public CoupJouable easy(Plateau plateau){
		CoupJouable coupjouable=new CoupJouable();
		return coupjouable;
	}
	
	/************************************* IA Normale *************************************/
	
	public CoupJouable normal(Plateau plateau){
		CoupJouable coupjouable=new CoupJouable();
		return coupjouable;		
	}
	
	/************************************* IA Difficile *************************************/
	
	public CoupJouable hard(Plateau plateau){
		CoupJouable coupjouable=new CoupJouable();
		return coupjouable;
	}
	
	
}
