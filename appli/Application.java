/* Le package appli est le package qui contient le main, programme principal */

package appli;

import Joueur.Joueur;
import jeu.*;

/* La classe Application contient le main, qui est le programme principal du projet */

public class Application {
    public static void main(String[] args) {
        Plateau plateau = new Plateau(); /* On crée le plateau */
        Initialisation.debutDePartie(); /* On initialise la partie */
        Joueur[] Joueurs = new Joueur[2]; /* On crée deux joueurs */
        boolean fin = false;
        boolean echec = false;
        int tour = 1;
        int i = 0;
        boolean changement;
        SuperCoord input;
        Joueurs[0] = Initialisation.initialisationJoueur(true); /* On demande de quel type est le joueur blanc */
        Joueurs[1] = Initialisation.initialisationJoueur(false); /* On demande de quel type est le joueur noir */
        System.out.println("Debut de partie");
        while (true) { /* Boucle qui va permettre à la partie de commencer et d'être jouée */
            if (Joueurs[i].PeutSeDeplacer())
                break; /* Si le joueur ne peut pas se déplacer, on sort de la boucle */
            if (Joueurs[i].materiel()) {
                System.out.println(plateau.toString()); /* Affiche le plateau */
                System.out.println("Nulle par manque de materiel");
                fin = true;
                break; /* Si aucun des deux joueurs n'a plus assez de pièces pour gagner, la partie s'arrête
                (par exemple un roi de chaque côté) */
            }
            System.out.println("Trait au " + Joueurs[i].nom); /* Dit qui va jouer */

            System.out.println(plateau.toString()); /* Affiche le plateau */

            /* On vérifie qu'il n'y a pas d'erreur */
            try {
                input = Joueurs[i].Input();
            }
            catch (Echec e1) { /* Si un joueur demande partie nulle */
                if (Joueurs[i + tour].Nulle()) { /* Si le joueur adverse accepte */
                    System.out.println("Nulle par accord mutuel");
                    fin = true;
                    break;
                }
                else
                    continue;
            }
            catch (Abandon a) { /* Si le joueur abandonne */
                System.out.println("Le joueur " + Joueurs[i + tour].nom + " gagne par abandon");
                fin = true;
                break;
            }

            /* Si le joueur veut déplacer sa pièce */
            if (Plateau.plateau[input.Coord1.x][input.Coord1.y].MemeCouleur(Joueurs[i].couleurJo)) {
                if (Plateau.plateau[input.Coord1.x][input.Coord1.y].MouvPossible(input.Coord2, Plateau.plateau)) {
                    /* On essaye un déplacement sur le plateau */
                    try {
                        changement = Plateau.Deplacementplateau(input.Coord1,input.Coord2);
                        echec = false;
                    }
                    catch (Echec e) { /* Si on met l'adversaire en échec */
                        changement = true; /* On dit que le déplacement s'est bien passé */
                        echec = true; /* Et qu'il y a échec */
                    }
                }
                else {
                    changement = false;
                }
                if (!changement)
                    System.out.println("Deplacement impossible");
            }
            else {
                System.out.println("Vous n'avez pas pointé une de vos pièces");
                changement = false;
            }
            if (changement) {
                if (echec) {
                    System.out.println("\nÉchec"); /* Si le déplacement est possible et qu'il y a échec,
                    on avertit qu'il y a échec */
                }
                /* On change les variables pour que ça soit à l'autre joueur de jouer */
                i += tour;
                tour *= -1;
            }
        }

        if (!fin) { /* Si une fin quelconque se produit */
            System.out.println(plateau.toString());
            if (echec) { /* S'il y a échec */
                System.out.println("\nLe joueur " + Joueurs[i + tour].nom + " gagne par echec et mat");
                /* Le joueur qui a mis en échec son adversaire gagne */
            }
            else
                System.out.println("\nNulle par pat"); /* Sinon il y a match nul */
        }

    }
}
