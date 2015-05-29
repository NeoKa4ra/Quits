import java.awt.*;
import java.io.File;


class Donnees {
    Bille [] billes;
    Fleche [] fleches;
    Plateau matrice;
    Point depart,arrivee;
    
    
    int selection, selectionF;
    boolean selected;

    Donnees( Plateau matrice) {
        billes = new Bille[10];
        fleches = new Fleche[20];
        this.matrice = matrice;
        
        depart=new Point(-1,-1);
        arrivee=new Point(-1,-1);
        
        billes[0] = new Bille(Color.black);
        billes[1] = new Bille(Color.black);
        billes[2] = new Bille(Color.black);
        billes[3] = new Bille(Color.black);
        billes[4] = new Bille(Color.black);
            
        billes[5] = new Bille(Color.gray);
        billes[6] = new Bille(Color.gray);
        billes[7] = new Bille(Color.gray);
        billes[8] = new Bille(Color.gray);
        billes[9] = new Bille(Color.gray);
        
        for(int i = 0; i < 5; i++)
        	fleches[i] = new Fleche(new File("/home/j/jaozande/workspace/Quits/src/t.png"));
        
        for(int i = 5; i < 10; i++)
        	fleches[i] = new Fleche(new File("/home/j/jaozande/workspace/Quits/src/g.png"));
        
        for(int i = 10; i < 15; i++)
        	fleches[i] = new Fleche(new File("/home/j/jaozande/workspace/Quits/src/bd.png"));
        
        for(int i = 15; i < 20; i++)
        	fleches[i] = new Fleche(new File("/home/j/jaozande/workspace/Quits/src/bg.png"));
        
        
        
        
        
        selection = 0;
        selectionF = 0;
        selected = false;
    }
}

