package Tests;

import jeu.*;
import piece.Piece;

public class PieceTest {
    public boolean MouvPossibleTest(){
        Plateau p = new Plateau();
        Piece p2 = new Piece(0,0);
        return !p2.MouvPossible(new Coord(0,0), Plateau.plateau);
    }

    public boolean changePlaceTest(){
        Plateau p = new Plateau();
        Piece p2 = new Piece(0,0);
        p2.changePlace(3,7);
        return p2.coord.x == 3;
    }

    public boolean VPieceTest(){
        Piece p2 = new Piece(0,0);
        return p2.VPiece();
    }

    public boolean VPionTest(){
        Piece p2 = new Piece(0,0);
        return !p2.VPion();
    }

    public boolean VRoiTest(){
        Piece p2 = new Piece(0,0);
        return !p2.VRoi();
    }

    public boolean DeplacementPossibleTest(){
        Piece p2 = new Piece(0,0);
        return !p2.DeplacementPossible();
    }

    public boolean VTourTest(){
        Piece p2 = new Piece(0,0);
        return !p2.VTour();
    }
}
