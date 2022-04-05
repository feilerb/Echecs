/* Le package piece regroupe toutes les pièces qui héritent de la classe Piece */

package piece;
import jeu.Plateau;
import jeu.Coord;

/* La tour est une pièce assez forte, qui peut se déplacer en ligne,
mais ne peut pas sauter au dessus d'une pièce. Elle permet de mater le roi adverse
assez facilement (comme avec par exemple le mat du couloir) */

public class Tour extends Piece {


    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur Tour va permettre de créer une pièce de type tour
    Pour ça il nous faut ses coordonnées et sa couleur */

    public Tour(int i, int j, boolean t) {
        super(i, j);
        aBouge = false;
        couleur = t;
        valeur = 5;
        if (t)
            nom = "T";
        else
            nom = "t";
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Pour le cas de la tour, il faut vérifier que la pièce se déplace en colonne ou en ligne, ne passe pas au dessus d'une pièce déjà présente
    ou qu'il essaye de manger une pièce de sa couleur */

    public boolean MouvPossible(Coord fin, Piece[][] p) {

        if (fin.x < 0 || fin.x > 7 || fin.y < 0 || fin.y > 7|| p[fin.x][fin.y].MemeCouleur(couleur))
            return false;
        int direction = 1;
        int vecteurx = fin.x - coord.x;
        int vecteury = fin.y - coord.y;
        if (vecteurx < 0) {
            vecteurx *= -1;
            direction = -1;
        }

        if (vecteury < 0) {
            vecteury *= -1;
            direction = -1;
        }


        if ((vecteurx > 0 && vecteury == 0)||(vecteurx == 0 && vecteury > 0)) {
            if (vecteurx == 0) {
                for(int i = 1; i < vecteury; i++) {
                    if (p[coord.x][coord.y + (i * direction)].VPiece())
                        return false;
                }
            }
            if (vecteury == 0) {
                for(int i = 1; i < vecteurx; i++) {
                    if (p[coord.x + (i * direction)][coord.y].VPiece())
                        return false;
                }
            }
            return true;
        }
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* On vérifie donc quand on l'appelle si la pièce peut se déplacer au moins à
    un endroit, et ici dans le cas de la tour */

    public boolean DeplacementPossible() {
        int x, y;
        boolean boucle = true;
        x =- 1;
        y =- 1;
        while (coord.x+x > 0 && boucle){
            if(Plateau.plateau[coord.x+x][coord.y].VPiece()){
                if(Plateau.plateau[coord.x+x][coord.y].MemeCouleur(couleur)){
                    break;
                }
                else
                    boucle = false;
            }
            x -= 1;
        }
        boucle = true;
        if(x == 0){
            x += 1;
        }
        while (coord.x + x < 8){
            if(coord.x + x < 0)
                x += 1;
            if (x != 0){
                if(Plateau.plateau[coord.x+x][coord.y].VPiece()){
                    if(Plateau.plateau[coord.x+x][coord.y].MemeCouleur(!couleur)) {
                        if (Plateau.FauxDeplacement(coord,new Coord(coord.x+x,coord.y)))
                            return true;
                    }
                    break;
                }
                else
                    if (Plateau.FauxDeplacement(coord,new Coord(coord.x+x,coord.y)))
                        return true;
            }
            x += 1;
        }
        while (coord.y + y > 0 && boucle){
            if(Plateau.plateau[coord.x][coord.y+y].VPiece()){
                if(Plateau.plateau[coord.x][coord.y+y].MemeCouleur(couleur)){
                    break;
                }
                else
                    boucle = false;
            }
            y -= 1;
        }
        if(y == 0){
            y += 1;
        }
        while (coord.y + y < 8){
            if(coord.y + y < 0)
                y += 1;
            if(y != 0){
                if(Plateau.plateau[coord.x][coord.y+y].VPiece()){
                    if(Plateau.plateau[coord.x][coord.y+y].MemeCouleur(!couleur)) {
                        if (Plateau.FauxDeplacement(coord,new Coord(coord.x,coord.y+y)))
                            return true;
                    }
                    break;
                }
                else{
                    if (Plateau.FauxDeplacement(coord,new Coord(coord.x,coord.y+y)))
                        return true;
                }
            }
            y += 1;
        }
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Etant donné que nous sommes dans la classe Tour, VTour devient true */

    public boolean VTour (){
        return true;
    }
}
