package Tests;

import Joueur.Joueur;
import jeu.*;

public class JoueurTest {

    public boolean TestStringCase() throws Echec {
        Joueur j = new Joueur(true);
        return Joueur.StringCase("a1").x == 7 && Joueur.StringCase("a7").y == 1;
    }

    public boolean TestCoordCase(){
        Joueur j = new Joueur(true);
        return Joueur.CoordCase(0, 0).equals("a8");
    }

    public boolean TestPeutSeDeplacer(){
        Joueur j = new Joueur(true);
        Plateau p = new Plateau();
        Initialisation.getPiece(0,true,0,0);
        Initialisation.getPiece(0,false,0,2);
        Initialisation.getPiece(5,true,1,0);
        return j.PeutSeDeplacer();
    }

    public boolean TestMateriel(){
        Joueur j = new Joueur(true);
        Plateau p = new Plateau();
        Initialisation.getPiece(0,true,0,0);
        Initialisation.getPiece(0,false,0,2);
        Initialisation.getPiece(4,true,1,0);
        Initialisation.getPiece(4,false,7,7);
        return j.materiel();
    }

}
