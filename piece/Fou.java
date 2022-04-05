/* Le package piece regroupe toutes les pièces qui héritent de la classe Piece */

package piece;
import jeu.Coord;
import jeu.Plateau;

/* Le fou est une pièce qui se déplace en diagonale, dans toutes les directions possibles.
En revanche, il ne peut pas passer au dessus des autres pièces, et arrivera difficilement
voire jamais à mater le roi adverse à lui seul */

public class Fou extends Piece {

    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur Fou va permettre de créer une pièce de type fou
    Pour ça il nous faut ses coordonnées et sa couleur */

    public Fou(int i, int j, boolean c) {
        super(i, j);
        couleur = c;
        valeur = 3;
        if (c)
            nom = "F";
        else
            nom = "f";
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Pour le cas du fou, il faut vérifier que la pièce se déplace en diagonale, ne passe pas au dessus d'une pièce déjà présente
    ou qu'il essaye de manger une pièce de sa couleur */

    public boolean MouvPossible(Coord fin, Piece[][] p) {
        if (fin.x < 0 || fin.x > 7 || fin.y < 0 || fin.y > 7 || p[fin.x][fin.y].MemeCouleur(couleur))
            return false;
        int directionx = 1;
        int directiony = 1;
        int vecteurx = fin.x - coord.x;
        int vecteury = fin.y - coord.y;
        if (vecteurx < 0) {
            vecteurx *= -1;
            directionx = -1;
        }
        if (vecteury < 0) {
            vecteury *= -1;
            directiony = -1;
        }
        if (vecteurx == vecteury && vecteurx != 0) {
            for (int i = 1; i < vecteurx; i++) {
                if (p[coord.x + (i * directionx)][coord.y + (i * directiony)].VPiece())
                    return false;
            }
            return true;
        }
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* On vérifie donc quand on l'appelle si la pièce peut se déplacer au moins à
    un endroit, et ici dans le cas du fou */

    public boolean DeplacementPossible(){
        int x,y;
        boolean boucle = true;
        x = -1;
        y = -1;
        while (coord.x + x > 0 && coord.y + y > 0 && boucle) {
            if (Plateau.plateau[coord.x + x][coord.y + y].VPiece()) {
                if (Plateau.plateau[coord.x + x][coord.y + y].MemeCouleur(couleur)) {
                    x += 1;
                    y += 1;
                    break;
                }
                else {
                    if (Plateau.FauxDeplacement(coord,new Coord(coord.x + x,coord.y + y)))
                        return true;
                    boucle = false;
                    x += 1;
                    y += 1;
                }
            }
            x -= 1;
            y -= 1;
        }
        boucle = true;
        while (coord.x + x < 8 && coord.y + y < 8) {
            if(coord.x + x < 0 || coord.y + y < 0) {
                x += 1;
                y += 1;
            }
            if (x != 0) {
                if (Plateau.plateau[coord.x + x][coord.y + y].VPiece()) {
                    if (!Plateau.plateau[coord.x + x][coord.y + y].MemeCouleur(couleur)) {
                        if (Plateau.FauxDeplacement(coord,new Coord(coord.x + x,coord.y + y)))
                            return true;
                    }
                break;
                }
                else {
                    if (Plateau.FauxDeplacement(coord,new Coord(coord.x + x,coord.y + y)))
                        return true;
                }
            }
            x += 1;
            y += 1;
        }
        x =- 1;
        y = 1;
        while (coord.x + x > 0 && coord.y + y < 7 && boucle) {

                if (Plateau.plateau[coord.x + x][coord.y + y].VPiece()) {
                    if (Plateau.plateau[coord.x + x][coord.y + y].MemeCouleur(couleur)) {
                    x += 1;
                    y -= 1;
                    break;
                }
                else {
                    if (Plateau.FauxDeplacement(coord,new Coord(coord.x + x,coord.y + y)))
                        return true;
                    x += 1;
                    y -= 1;
                    boucle = false;
                }
            }
            x -= 1;
            y += 1;
            }

        while (coord.x + x < 8 && coord.y + y > -1) {
            if (coord.x + x < 0 || coord.y + y > 7) {
                x += 1;
                y -= 1;
            }
            if (x != 0) {
                if (Plateau.plateau[coord.x + x][coord.y + y].VPiece()) {
                    if (!Plateau.plateau[coord.x + x][coord.y + y].MemeCouleur(couleur)) {
                        if (Plateau.FauxDeplacement(coord,new Coord(coord.x + x,coord.y + y)))
                            return true;
                    }
                    break;
                }
                else {
                    if (Plateau.FauxDeplacement(coord,new Coord(coord.x + x,coord.y + y)))
                        return true;
                }
            }
            x += 1;
            y -= 1;
        }
        return false;
    }
}
