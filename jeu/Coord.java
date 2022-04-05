/* Le package jeu regroupe le jeu d'échecs dans sa globalité, que ce soit
le joueur, le plateau ou tout le principe de joueur */

package jeu;

/* La classe Coord va permettre de prendre une longitude entière x et une latitude
entière y et de les assembler pour créer une coordonnée */

public class Coord {
    public int x;
    public int y;

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet d'assigner des coordonnées à partir de deux entiers
    @param x Entier qui correspond à la latitude
    @param y Entier qui correspond à la longitude */

    public Coord(int newx, int newy) {
        x = newx;
        y = newy;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Affiche les coordonnées
    @return Les deux coordonnées sous forme de string avec une barre entre
    les deux */

    public String toString(){
        return x + " | " + y;
    }
}
