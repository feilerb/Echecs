package Tests;

import jeu.*;

public class InitialisationTest {
    public boolean getPieceTest(){
        Plateau p = new Plateau();
        Initialisation.getPiece(0,true,0,0);
        return Plateau.plateau[0][0].VRoi();
    }

    public boolean debutPartieClassiqueTest(){
        Plateau p = new Plateau();
        Initialisation.debutPartieClassique();
        return Plateau.plateau[7][4].VRoi();
    }
}
