/* Le package piece regroupe toutes les pièces qui héritent de la classe Piece */

package piece;
import jeu.Coord;
import jeu.Plateau;

/* Le pion est une pièce assez faible mais pourtant très spéciale.
Elle peut se déplacer d'au maximum deux cases vers l'avant lors de
son premier déplacement mais seulement d'une case par la suite.
De plus, elle mange exclusivement les pièces dans les premières cases
en diagonales en avançant. Enfin, si elle arrive au bord opposé du
plateau, le pion se transormera en reine */

public class Pion extends Piece {

    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur Pion va permettre de créer une pièce de type pion
    Pour ça il nous faut ses coordonnées et sa couleur */

    public Pion(int i, int j, boolean c) {
        super(i, j);
        couleur = c;
        valeur = 1;
        if (c)
            nom = "P";
        else
            nom = "p";
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Pour le cas du pion, il faut vérifier que la pièce respecte les conditions de déplacement, de manger et
    de transformation du pion */

    public boolean MouvPossible(Coord fin, Piece[][] p) {
        if (fin.x < 0 || fin.x > 7 || fin.y < 0 || fin.y > 7 || p[fin.x][fin.y].MemeCouleur(couleur))
            return false;
        int vecteurx = fin.x - coord.x;
        int vecteury = fin.y - coord.y;
        if (vecteury < 0)
            vecteury *= -1;
        if ((couleur && vecteurx == -1) || (!couleur && vecteurx == 1))
            return (vecteury == 0 && !p[fin.x][fin.y].VPiece()) || (vecteury == 1 && p[fin.x][fin.y].MemeCouleur(!couleur));
        if ((couleur && vecteurx == -2 && !p[coord.x-1][coord.y].VPiece() && coord.x == 6) || (!couleur && vecteurx == 2 && !p[coord.x+1][coord.y].VPiece() && coord.x == 1)){
            return vecteury == 0 && !p[fin.x][fin.y].VPiece();
        }
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Etant donné que nous sommes dans la classe Pion, VPion devient true */

    public boolean VPion (){
        return true;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* On vérifie donc quand on l'appelle si la pièce peut se déplacer au moins à
    un endroit, et ici dans le cas du pion */

    public boolean DeplacementPossible() {
        if (!couleur) {
            for (int i = -1; i < 2; i++) {
                if (coord.y + i > 7)
                    break;
                if (coord.y + i < 0 || ((i != 0)&&!Plateau.plateau[coord.x + 1][coord.y + i].MemeCouleur(couleur))
                || (i == 0&&Plateau.plateau[coord.x + 1][coord.y + i].VPiece()))
                    continue;

                if (Plateau.FauxDeplacement(coord,new Coord(coord.x + 1,coord.y + i))) {
                    if (i != 0) {
                        if (Plateau.plateau[coord.x + 1][coord.y + i].MemeCouleur(!couleur))
                            return true;
                    }
                    else
                        return true;
                }
            }
            if (coord.x == 1 && !Plateau.plateau[coord.x + 2][coord.y].VPiece()
            && !Plateau.plateau[coord.x + 1][coord.y].VPiece()) {
                return Plateau.FauxDeplacement(coord, new Coord(coord.x + 2, coord.y));
            }
        }
        else {
            for(int i = -1; i < 2; i++) {
                if (coord.y + i > 7)
                    break;
                if (coord.y + i < 0 || ((i != 0) && !Plateau.plateau[coord.x - 1][coord.y + i].MemeCouleur(couleur)) ||
                (i == 0&&Plateau.plateau[coord.x - 1][coord.y + i].VPiece()))
                    continue;

                if (Plateau.FauxDeplacement(coord,new Coord(coord.x-1,coord.y + i))) {
                    if (i != 0) {
                        if (Plateau.plateau[coord.x - 1][coord.y + i].MemeCouleur(!couleur))
                            return true;
                    }
                    else
                        return true;
                }
            }
            if (coord.x == 6 && !Plateau.plateau[coord.x - 2][coord.y].VPiece() &&
            !Plateau.plateau[coord.x - 1][coord.y].VPiece()) {
                return Plateau.FauxDeplacement(coord, new Coord(coord.x - 2, coord.y));
            }
        }
        return false;
    }
}
