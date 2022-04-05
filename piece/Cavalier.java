/* Le package piece regroupe toutes les pièces qui héritent de la classe Piece */

package piece;
import jeu.Coord;
import jeu.Plateau;

/* Le cavalier est une pièce qui se déplace en L, c'est-à-dire soit de 2 en longitude et 1 en latitude,
ou inversement. De plus, le cavalier est la seule pièce qui peut sauter au dessus les autres */

public class Cavalier extends Piece {

    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur Cavalier va permettre de créer une pièce de type cavalier
    Pour ça il nous faut ses coordonnées et sa couleur */

    public Cavalier(int i, int j, boolean c) {
        super(i, j);
        couleur = c;
        valeur = 3;
        if(c)
            nom = "C"; /* S'il est blanc, son nom sera C */
        else
            nom = "c"; /* Sinon, son nom sera c */
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Pour le cas du cavalier, il faut vérifier que le mouvement en L ne fait pas sortir la pièce
    du plateau ou n'atterit pas sur une pièce de même couleur */

    public boolean MouvPossible(Coord fin, Piece[][] p) {
        if (fin.x < 0 || fin.x > 7 || fin.y < 0 || fin.y > 7 || p[fin.x][fin.y].MemeCouleur(couleur))
            return false;
        int vecteurx = fin.x - coord.x;
        int vecteury = fin.y - coord.y;
        if (vecteurx < 0)
            vecteurx *= -1;
        if (vecteury < 0)
            vecteury *= -1;
        return (vecteurx == 1 && vecteury == 2) || (vecteurx == 2 && vecteury == 1);
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* On vérifie donc quand on l'appelle si la pièce peut se déplacer au moins à
    un endroit, et ici dans le cas du cavalier */

    public boolean DeplacementPossible() {

        int x,y;
        int m = 1;
        for(int i = 0; i < 8; i++) {
            if (i == 4)
                m = -1;
            if (i % 2 == 0) {
                x = m;
                y = 2;
            }
            else {
                x = 2 * m;
                y = 1;
            }
            if (i % 4 > 1) {
                y = y * -1;
            }
            if (coord.x + x < 0 || coord.x + x > 7 || coord.y + y < 0 || coord.y + y > 7)
                continue;
            if (!Plateau.plateau[coord.x + x][coord.y + y].MemeCouleur(couleur))
                if (Plateau.FauxDeplacement(coord, new Coord(coord.x + x,coord.y + y))) {
                    return true;
            }
        }
        return false;
    }
}
