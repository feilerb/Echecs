/* Le package jeu regroupe le jeu d'échecs dans sa globalité, que ce soit
le joueur, le plateau ou tout le principe de joueur */

package jeu;

/* La classe SuperCoord va nous permettre d'associer deux coordonnées */

public class SuperCoord {
    public Coord Coord1;
    public Coord Coord2;

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet de créer un couple de deux coordonnées
    @param newx La première coordonnée
    @param newy La seconde coordonnée */

    public SuperCoord(Coord newx, Coord newy){
        Coord1 = newx;
        Coord2 = newy;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Affiche le couple de coordonnées
    @return Affiche le couple de coordonnées sous forme de string
    avec une double barre entre les deux */

    public String toString(){
        return Coord1.toString() + " || " + Coord2.toString();
    }
}
