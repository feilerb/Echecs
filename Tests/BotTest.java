package Tests;

import Joueur.*;
import jeu.*;

public class BotTest {
    public static boolean TestInput() throws Abandon, Echec {
        Joueur j = new Bot(true);
        Plateau p = new Plateau();
        Initialisation.getPiece(4,true,1,0);
        return j.Input().Coord1.x==1;
    }

    public boolean TestNulle(){
        Joueur j = new Bot(true);
        Plateau p = new Plateau();
        Initialisation.getPiece(0,true,0,0);
        Initialisation.getPiece(0,false,0,2);
        Initialisation.getPiece(4,true,1,0);
        Initialisation.getPiece(4,false,7,7);
        return !j.Nulle();
    }

}
