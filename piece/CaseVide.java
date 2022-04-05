/* Le package piece regroupe toutes les pièces qui héritent de la classe Piece */

package piece;

/* La classe CaseVide va permettre des cases où il n'y a aucune pièce */

public class CaseVide extends Piece {

    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur CaseVide permet de créer une case vide
    Les entiers i et j correspondent aux coordonnées de cette case */

    public CaseVide(int i, int j) {
        super(i, j);
        nom = " ";
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* La case vide n'a pas de couleur, donc elle sera toujours d'une couleur différente à une pièce */

    public boolean MemeCouleur (boolean c) {
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* La case vide n'étant pas une pièce, la vérification de pièce retournera toujours faux */

    public boolean VPiece () {
        return false;
    }
}
