import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class EcouteurFleche implements MouseListener {
	Donnees d;
	int selectionF;
	
	EcouteurFleche(int i, Donnees donnees){
		d = donnees;
		selectionF = i;
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("Survolle sur la fleche");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Clic sur la fleche" + selectionF);
		d.selectionF = selectionF;
		
		
		switch(selectionF){
		case 19:
			Moteur.rangeeJouableG(0, d.matrice);
			break;
		case 18:
			Moteur.rangeeJouableG(1, d.matrice);
			break;
		case 17:
			Moteur.rangeeJouableG(2, d.matrice);
			break;
		case 16:
			Moteur.rangeeJouableG(3, d.matrice);
			break;
		case 15:
			Moteur.rangeeJouableG(4, d.matrice);
			break;
		case 14:
			Moteur.colonneJouableB(4, d.matrice);
			break;
		case 13:
			Moteur.colonneJouableB(3, d.matrice);
			break;
		case 12:
			Moteur.colonneJouableB(2, d.matrice);
			break;
		case 11:
			Moteur.colonneJouableB(1, d.matrice);
			break;
		case 10:
			Moteur.colonneJouableB(0, d.matrice);
			break;
		case 9:
			Moteur.colonneJouableH(0, d.matrice);
			break;
		case 8:
			Moteur.colonneJouableH(1, d.matrice);
			break;
		case 7:
			Moteur.colonneJouableH(2, d.matrice);
			break;
		case 6:
			Moteur.colonneJouableH(3, d.matrice);
			break;
		case 5:
			Moteur.colonneJouableH(4, d.matrice);
			break;
		case 4:
			Moteur.rangeeJouableD(0, d.matrice);
			break;
		case 3:
			Moteur.rangeeJouableD(1, d.matrice);
			break;
		case 2:
			Moteur.rangeeJouableD(2, d.matrice);
			break;
		case 1:
			Moteur.rangeeJouableD(3, d.matrice);
			break;
		case 0:
			Moteur.rangeeJouableD(4, d.matrice);
			break;
			
		
		
		}
			
	}

}
