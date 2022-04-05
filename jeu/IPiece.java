package jeu;

import piece.Piece;

public interface IPiece {

    boolean MouvPossible(Coord fin, Piece[][] p);

    void changePlace(int newx, int newy);

    boolean VPiece ();

    String to_String();

    boolean VPion ();

    boolean VRoi ();

    boolean MemeCouleur (boolean c);

    boolean DeplacementPossible();

    boolean VTour ();


}
