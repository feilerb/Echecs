package Tests;

import piece.Piece;
import piece.CaseVide;

public class CaseVideTest {

    public boolean VPieceTest(){
        Piece p2 = new CaseVide(0,0);
        return !p2.VPiece();
    }

    public boolean MemeCouleurTest(){
        Piece p2 = new CaseVide(0,0);
        return !p2.MemeCouleur(true);
    }

}
