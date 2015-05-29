import javax.swing.*;

import java.awt.event.*;
import java.io.IOException;


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
			if(np.listeBouton.getSelection()!=null){
				np.dispose();  
			}
			else{
				 JOptionPane jop = new JOptionPane();
				 jop.showMessageDialog(null, "Veuillez choisir un niveau!", "Erreur Nouvelle Partie", JOptionPane.ERROR_MESSAGE);
			}
			 
		}
		
	}
}
