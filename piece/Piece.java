/* Le package jeu regroupe le jeu d'échecs dans sa globalité, que ce soit
le joueur, le plateau ou tout le principe de joueur */

package piece;

/* La classe Piece est une classe mère qui va permettre de transmettre ses
méthodes à tous les différents types de pièce du jeu. Pour ça, chaque piece
est accompagnée de coordonnées, d'un nom et d'une couleur */

import jeu.Coord;
import jeu.IPiece;

public class Piece implements IPiece {
    public Coord coord;
    public String nom;
    public boolean couleur;
    public boolean aBouge;
    public int valeur;

    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur pièce va permettre deux prendre deux entiers et de les assigner aux
    coordonnées de la pièce */

    public Piece(int i, int j) {
        coord = new Coord(i,j);
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Cette méthode booléenne nous indique si le mouvement que
    le joueur veut faire est possible
    @param fin Les coordonnées d'où veut se déplacer la pièce
    @param p Le plateau
    @return Si le mouvement est possible */

    public boolean MouvPossible(Coord fin, Piece[][] p){
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Cette méthode permet le changement de position d'une pièce
    @param newx Numéro de la colonne sur laquelle la piece va être deplacée
    @param newy Numéro de la ligne sur laquelle la piece va être deplacée */

    public void changePlace(int newx, int newy) {
        coord.x = newx;
        coord.y = newy;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Vérifie si c'est une pièce, ou une case vide
    @return Booléen qui indique si c'est une pièce ou non */

    public boolean VPiece () {
        return true;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet d'inscrire sur l'affichage le symbole qui représente la pièce
    @return Un string qui correspond à la pièce */

    public String to_String(){
        return nom;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Vérifie si la pièce est un pion ou non
    @return Booléen qui indique s'il s'agit d'un pion */

    public boolean VPion (){
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief  Vérifie si la pièce est un roi ou non
    @return Booléen qui indique s'il s'agit d'un roi */

    public boolean VRoi (){
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet de savoir si deux pièces sont de même couleur
    @return Booléen qui indique si elles sont de même couleur */

    public boolean MemeCouleur (boolean c) {
        return c == couleur;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Vérifie si la pièce peut se déplacer au moins une fois
    @return Si au moins un déplacement est possible, true est retourné */

    public boolean DeplacementPossible() { return false; }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief  Vérifie si la pièce est une tour ou non
    @return Booléen qui indique s'il s'agit d'une tour */

    public boolean VTour (){
        return false;
    }


}
