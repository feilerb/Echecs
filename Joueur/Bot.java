/* Le package jeu regroupe le jeu d'échecs dans sa globalité, que ce soit
le joueur, le plateau ou tout le principe de joueur */

package Joueur;

/* Va permettre de faire des coups aléatoires */


import jeu.Coord;
import jeu.Plateau;
import jeu.SuperCoord;

import java.util.Random;

/* La classe Bot est une classe qui hérite de Joueur, qui permettra de faire jouer un algorithme */

public class Bot extends Joueur {
    public static boolean couleurJo;

    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur Bot permet de créer un algorithme qui jouera */
    public Bot(boolean c) {
        super(c);
        couleurJo = c;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Ici, la classe Input diffère de joueur car l'algorithme fait des coups aléatoires
    (dans le respect des règles du jeu) */

    public SuperCoord Input() {
        Random random = new Random(); /* On crée une classe random */
        boolean fin = false;
        int nbalea = 0; /* Le nombre de pièces de sa couleur qui pourront se déplacer */
        Coord a = new Coord(-1,0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Plateau.plateau[i][j].MemeCouleur(couleurJo))
                    if (Plateau.plateau[i][j].DeplacementPossible())
                        nbalea += 1; /* On vérifie pour chaque case du plateau quelle pièce
                                        de sa couleur peut se déplacer */
            }
        }
        assert (nbalea != 0);

        int nb = random.nextInt(nbalea); /* On choisit un entier entre 0 et nbalea - 1 */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Plateau.plateau[i][j].MemeCouleur(couleurJo))
                    if (Plateau.plateau[i][j].DeplacementPossible()) {
                        if (nb == 0) {
                            /* On parcourt toutes les cases du plateau, si la pièce sur laquelle
                            il se trouve est de la même couleur et qu'elle peut se déplacer, et
                            si nb est égal à 0, il prend les coordonnées de cette pièce */
                            a = new Coord(i,j);
                            fin = true;
                            break;
                        }
                        nb -= 1;
                    }
            }
            if (fin)
                break;
        }
        assert (a.x != -1);
        fin = false;
        nb = random.nextInt(5); /* On choisit un entier entre 0 et 4 */
        Coord b = new Coord(-1,0);
        Coord boucle = new Coord(0,0);
        while (!fin) {
            for (int i = 0; i < 8; i++) {
                boucle.x = i;
                for (int j = 0; j < 8; j++) {
                    boucle.y = j;
                    if(Plateau.plateau[a.x][a.y].MouvPossible(boucle,Plateau.plateau))
                        if (Plateau.FauxDeplacement(a,boucle)) {
                            if (nb == 0) {
                                /* Il déplace aléatoirement une des 5 premières pièces qu'il peut déplacer */
                                b = new Coord(i,j);
                                fin = true;
                               break;
                            }
                            nb -= 1;
                        }

                }
               if (fin)
                   break;
            }
        }
        assert (b.x != -1);
        return new SuperCoord(a,b);
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* Permet à l'algortihme de choisir s'il accepte le nul ou pas */

    public boolean Nulle() {
        int ami = 0;
        int ennemi = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                /* Pour chaque case, il vérifie de quelle couleur est la pièce qui s'y trouve
                (s'il y a une pièce) */
                if (Plateau.plateau[i][j].VPiece()) {
                    if (Plateau.plateau[i][j].MemeCouleur(couleurJo))
                        ami += Plateau.plateau[i][j].valeur;
                        /* Si c'est une pièce de sa couleur, il rajoute la valeur de sa pièce à son compteur */
                    else
                        ennemi += Plateau.plateau[i][j].valeur;
                        /* Si c'est une pièce adverse, il rajoute la valeur de la pièce au compteur adverse */
                }
            }
        }
        return ami < ennemi;
        /* Si la valeur de son compteur est moins élevée que celle de son ennemi, il accepte la nulle */
    }
}
