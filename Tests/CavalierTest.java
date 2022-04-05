package Tests;

import jeu.Coord;
import jeu.Initialisation;
import piece.Piece;
import jeu.Plateau;
import piece.Cavalier;

public class CavalierTest {

    public boolean MouvPossibleTest(){
        Plateau p = new Plateau();
        Piece p2 = new Cavalier(0,0,true);
        Initialisation.getPiece(0,true,5,4);
        Initialisation.getPiece(0,false,7,3);
        Plateau.EmplacementRoi();
        return p2.MouvPossible(new Coord(2,1), Plateau.plateau);
    }

    public boolean DeplacementPossibleTest(){
        Plateau p = new Plateau();
        Piece p2 = new Cavalier(0,0,true);
        Initialisation.getPiece(0,true,5,4);
        Initialisation.getPiece(0,false,7,3);
        Plateau.EmplacementRoi();
        return p2.DeplacementPossible();
    }

}
