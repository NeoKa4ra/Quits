import javax.swing.*;

import java.awt.event.*;


public class EcouteurDeBouton3 implements ActionListener{
	NouvellePartie np;
	
	EcouteurDeBouton3(NouvellePartie np){
		this.np=np;
	}

	public void actionPerformed( ActionEvent e){
		if(e.getActionCommand().equals("Annuler")){
			 np.dispose();
		}
		
		if(e.getActionCommand().equals("OK")){
			 System.out.println("joris!!");
			 np.dispose();
		}
		
	}
}