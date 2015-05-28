import java.awt.*;
import java.io.File;


class Donnees {
    Bille [] billes;
    Fleche [] fleches;
    Plateau matrice;
    Point depart,arrivee;
    
    
    int selection, selectionF;
    boolean selected;

    Donnees() {
        billes = new Bille[10];
        fleches = new Fleche[20];
        matrice = new Plateau();
        matrice.init_2_joueurs();
        
        depart=new Point(-1,-1);
        arrivee=new Point(-1,-1);
        
        billes[0] = new Bille(Color.black,1,0);
        billes[1] = new Bille(Color.black,2,0);
        billes[2] = new Bille(Color.black,0,1);
        billes[3] = new Bille(Color.black,0,2);
        billes[4] = new Bille(Color.black,1,1);
            
        billes[5] = new Bille(Color.gray,4,2);
        billes[6] = new Bille(Color.gray,2,4);
        billes[7] = new Bille(Color.gray,3,3);
        billes[8] = new Bille(Color.gray,4,3);
        billes[9] = new Bille(Color.gray,3,4);
        
        for(int i = 0; i < 5; i++)
        	fleches[i] = new Fleche(new File("t.png"));
        
        for(int i = 5; i < 10; i++)
        	fleches[i] = new Fleche(new File("g.png"));
        
        for(int i = 10; i < 15; i++)
        	fleches[i] = new Fleche(new File("bd.png"));
        
        for(int i = 15; i < 20; i++)
        	fleches[i] = new Fleche(new File("bg.png"));
        
        
        
        
        
        selection = 0;
        selectionF = 0;
        selected = false;
    }
}
