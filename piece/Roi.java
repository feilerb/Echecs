/* Le package piece regroupe toutes les pièces qui héritent de la classe Piece */

package piece;
import jeu.Plateau;
import jeu.Coord;

/* Le roi est la pièce la plus importante de chaque côté de l'échequier.
Elle est unique pour chaque joueur à tout moment de la partie.
Si l'un des deux rois ne peut plus se déplacer pour quelconque raison, la partie s'arrête instantanément */

public class Roi extends Piece {


    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur Roi va permettre de créer une pièce de type roi
    Pour ça il nous faut ses coordonnées et sa couleur */

    public Roi(int i, int j, boolean c) {

        super(i, j);
        aBouge = false;
        valeur = 0;
        couleur = c;
        if (c)
            nom= "R";
        else
            nom= "r";
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Pour le cas du roi, il faut vérifier que la pièce se déplace à une case,
     qu'il essaye de manger une pièce de sa couleur ou roque */

    public boolean MouvPossible(Coord fin, Piece[][] p){
        if (fin.x < 0 || fin.x > 7 || fin.y < 0 || fin.y > 7||p[fin.x][fin.y].MemeCouleur(couleur))
            return false;
        boolean versh = true;
        int vecteurx = fin.x - coord.x;
        int vecteury = fin.y - coord.y;
        if (vecteurx < 0)
            vecteurx *= -1;
        if (vecteury < 0){
            vecteury *= -1;
            versh = false;
        }
        if(!aBouge && vecteury == 2 && vecteurx == 0){
            if(versh){
                if (Plateau.plateau[coord.x][7].VTour()){
                    if(!Plateau.plateau[coord.x][7].aBouge && !Plateau.plateau[coord.x][5].VPiece() && !Plateau.plateau[coord.x][6].VPiece()){
                        return true;
                    }
                }
            }
            else {
                if (Plateau.plateau[coord.x][0].VTour()){
                    if(!Plateau.plateau[coord.x][0].aBouge && !Plateau.plateau[coord.x][3].VPiece() && !Plateau.plateau[coord.x][2].VPiece() && !Plateau.plateau[coord.x][1].VPiece()){
                        return true;
                    }
                }
            }
        }
        return vecteurx < 2 && vecteury < 2;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Etant donné que nous sommes dans la classe Roi, VRoi devient true */

    public boolean VRoi (){
        return true;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* On vérifie donc quand on l'appelle si la pièce peut se déplacer au moins à
    un endroit, et ici dans le cas du roi */

    public boolean DeplacementPossible(){
        for(int i = -1; i < 2; i++){
            if(coord.x + i < 0)
                continue;
            if(coord.x + i > 7)
                break;
            for(int j = -1; j < 2; j++){
                if(coord.y + j < 0)
                    continue;
                if(coord.y + j > 7)
                    break;
                if(!Plateau.plateau[coord.x + i][coord.y + j].MemeCouleur(couleur))
                    if (Plateau.FauxDeplacement(coord,new Coord(coord.x + i,coord.y + j)))
                        return true;
            }
        }
        return false;
    }
}
