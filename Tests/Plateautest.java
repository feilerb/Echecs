package Tests;

import jeu.*;

public class Plateautest {


    public final boolean TestEmplacementRoi(){
        Plateau p = new Plateau();
        Initialisation.getPiece(0,true,5,4);
        Initialisation.getPiece(0,false,7,3);
        Plateau.EmplacementRoi();
        return Plateau.RoiBlanc.x==5 && Plateau.RoiBlanc.y==4 && Plateau.RoiNoir.x==7 && Plateau.RoiNoir.y==3;
    }

    public final boolean TestVEchec(){
        Plateau p = new Plateau();
        Initialisation.getPiece(0,true,5,4);
        Initialisation.getPiece(2,false,7,4);
        return Plateau.VEchec(true);
    }

    public final boolean TestDeplacementplateau() throws Echec {
        Plateau p = new Plateau();
        Initialisation.getPiece(0,true,0,0);
        Initialisation.getPiece(0,false,0,2);
        Plateau.EmplacementRoi();
        Initialisation.getPiece(2,false,7,4);
        Plateau.Deplacementplateau(Plateau.plateau[7][4].coord, new Coord(5,4));
        return Plateau.plateau[5][4].VTour();
    }


    public final boolean TestFauxDeplacement(){
        Plateau p = new Plateau();
        Initialisation.getPiece(0,true,0,0);
        Initialisation.getPiece(0,false,0,2);
        Plateau.EmplacementRoi();
        Initialisation.getPiece(2,false,7,4);
        return Plateau.FauxDeplacement(Plateau.plateau[7][4].coord, new Coord(5,4));
    }

}
