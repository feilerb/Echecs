/* Le package jeu regroupe le jeu d'échecs dans sa globalité, que ce soit
le joueur, le plateau ou tout le principe de joueur */

package jeu;
import Joueur.*;
import piece.*;

/* La classe Initialisation est la classe qui fournit tout le début du jeu,
entre le choix du début de partie ou le choix des pièces */

public class Initialisation {

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet de créer une pièce précise selon ses attributs à un endroit précis
    @param num Le numéro du type de pièce (ex : 0 => roi)
    @param couleur La couleur de la pièce (true = blanc, false = noir)
    @param x Entier qui correspond à la longitude de la pièce
    @param y Entier qui correspond à la latitude de la pièce
    @return La pièce créée */

    public static Piece getPiece(int num, boolean couleur, int x, int y) {
        if (num == 0)
            return new Roi(x,y,couleur);
        if (num == 1)
            return new Reine(x,y,couleur);
        if (num == 2)
            return new Tour(x,y,couleur);
        if (num == 3)
            return new Fou(x,y,couleur);
        if (num == 4)
            return new Cavalier(x,y,couleur);
        if (num == 5)
            return new Pion(x,y,couleur);
        return new CaseVide(x,y);
        /* Une case vide permet de renvoyer un espace à l'endroit où il n'y a pas de pièces durant l'affichage */
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief S'occupe de l'affichage lors du lancement du programme et permet de choisir son mode de jeu */

    public static void debutDePartie() {
        java.util.Scanner entree = new java.util.Scanner(System.in);
        /* On utilise un scanner pour gérer les réponses du joueur */
        System.out.println("""
                Bonjour ! Bienvenue dans notre jeu d'échecs.

                Si vous voulez lancer une partie d'échecs normale, tapez 1
                Si vous souhaitez accéder aux différentes finales proposées, tapez 2
                Si vous voulez créer votre propre partie d'échecs, tapez 3
                """);
        int modeDeJeu = entree.nextInt(); /* On prend la valeur en entrée */

        if (modeDeJeu == 1) {
            System.out.println("\nVous avez lancé le mode Partie Classique ! Battez votre adversaire le plus vite possible !");
            System.out.println("Confirmez le début de partie ? (y/n)\n");
            String reponse = entree.next();
            if (reponse.equals("y"))
                debutPartieClassique();
            else if (reponse.equals("n"))
                debutDePartie(); /* Fait un retour en arrière */
            else
                System.out.println("Répondez par y ou par n s'il vous plaît...");
        }

        if (modeDeJeu == 2) {
            System.out.println("\nVous avez lancé le mode Finale Simple ! Choisissez la finale que vous souhaitez et essayez de gagner !");
            System.out.println("Confirmez le début de partie ? (y/n)\n");
            String reponse = entree.next();
            if (reponse.equals("y"))
                choisirFinale();
            else if (reponse.equals("n"))
                debutDePartie(); /* Fait un retour en arrière */
            else
                System.out.println("Répondez par y ou par n s'il vous plaît...");
        }

        if (modeDeJeu == 3) {
            System.out.println("\nVous avez lancé le mode Partie personnalisée ! " +
                    "C'est vous qui choisissez quelle pièce compose quelle case !");
            System.out.println("Confirmez le début de partie ? (y/n)\n");
            String reponse = entree.next();
            if (reponse.equals("y")) {
                System.out.println("""

                        À chaque fois, l'ordinateur vous présentera la case qu'il faudra compléter. Rentrez le nom de la place que vous voulez à chaque fois
                        """);
                System.out.println("Les blancs sont en majuscule et les noirs en minuscule.\n");
                System.out.println("Roi = R ou r         Reine = D ou d");
                System.out.println("Tour = T ou t        Fou = F ou f");
                System.out.println("Cavalier = C ou c    Pion = P ou p");
                System.out.println("Case vide = V ou v\n");
                partiePersonnalisee();
            }
            else if (reponse.equals("n"))
                debutDePartie(); /* Fait un retour en arrière */
            else
                System.out.println("Répondez par y ou par n s'il vous plaît...");
        }
        Plateau.EmplacementRoi();
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Lance une partie d'échecs classique */

    public static void debutPartieClassique(){

        /* Crée une pièce d'un certain type à un endroit à chaque fois */

        Plateau.plateau[7][0] = Initialisation.getPiece(2,true,7,0);
        Plateau.plateau[7][1] = Initialisation.getPiece(4,true,7,1);
        Plateau.plateau[7][2] = Initialisation.getPiece(3,true,7,2);
        Plateau.plateau[7][3] = Initialisation.getPiece(1,true,7,3);
        Plateau.plateau[7][4] = Initialisation.getPiece(0,true,7,4);
        Plateau.plateau[7][5] = Initialisation.getPiece(3,true,7,5);
        Plateau.plateau[7][6] = Initialisation.getPiece(4,true,7,6);
        Plateau.plateau[7][7] = Initialisation.getPiece(2,true,7,7);

        Plateau.plateau[6][0] = Initialisation.getPiece(5,true,6,0);
        Plateau.plateau[6][1] = Initialisation.getPiece(5,true,6,1);
        Plateau.plateau[6][2] = Initialisation.getPiece(5,true,6,2);
        Plateau.plateau[6][3] = Initialisation.getPiece(5,true,6,3);
        Plateau.plateau[6][4] = Initialisation.getPiece(5,true,6,4);
        Plateau.plateau[6][5] = Initialisation.getPiece(5,true,6,5);
        Plateau.plateau[6][6] = Initialisation.getPiece(5,true,6,6);
        Plateau.plateau[6][7] = Initialisation.getPiece(5,true,6,7);

        Plateau.plateau[0][0] = Initialisation.getPiece(2,false,0,0);
        Plateau.plateau[0][1] = Initialisation.getPiece(4,false,0,1);
        Plateau.plateau[0][2] = Initialisation.getPiece(3,false,0,2);
        Plateau.plateau[0][3] = Initialisation.getPiece(1,false,0,3);
        Plateau.plateau[0][4] = Initialisation.getPiece(0,false,0,4);
        Plateau.plateau[0][5] = Initialisation.getPiece(3,false,0,5);
        Plateau.plateau[0][6] = Initialisation.getPiece(4,false,0,6);
        Plateau.plateau[0][7] = Initialisation.getPiece(2,false,0,7);

        Plateau.plateau[1][0] = Initialisation.getPiece(5,false,1,0);
        Plateau.plateau[1][1] = Initialisation.getPiece(5,false,1,1);
        Plateau.plateau[1][2] = Initialisation.getPiece(5,false,1,2);
        Plateau.plateau[1][3] = Initialisation.getPiece(5,false,1,3);
        Plateau.plateau[1][4] = Initialisation.getPiece(5,false,1,4);
        Plateau.plateau[1][5] = Initialisation.getPiece(5,false,1,5);
        Plateau.plateau[1][6] = Initialisation.getPiece(5,false,1,6);
        Plateau.plateau[1][7] = Initialisation.getPiece(5,false,1,7);

    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet de choisir une finale d'échecs parmi plusieurs proposées */

    public static void choisirFinale() {
        java.util.Scanner entree = new java.util.Scanner(System.in);
        System.out.println("Choisissez la finale que vous préférez en entrant le numéro correspondant ! Blanc VS Noir.");
        System.out.println("1 : Roi, tour VS Roi");
        System.out.println("2 : Roi, tour VS Roi, pion, pion, pion");
        System.out.println("3 : Roi, reine VS Roi, tour");
        System.out.println("4 : Roi, pion, pion, pion VS Roi, pion, pion");
        System.out.println("5 : Roi, fou, pion VS Roi, fou");
        int numeroFinale = entree.nextInt();
        if (numeroFinale == 1) {
            Plateau.plateau[2][4] = Initialisation.getPiece(0,true,2,4);
            Plateau.plateau[1][1] = Initialisation.getPiece(2,true,1,1);
            Plateau.plateau[0][4] = Initialisation.getPiece(0,false,0,4);
        }

        else if (numeroFinale == 2) {
            Plateau.plateau[7][5] = Initialisation.getPiece(0,true,7,5);
            Plateau.plateau[1][6] = Initialisation.getPiece(2,true,1,6);
            Plateau.plateau[4][7] = Initialisation.getPiece(0,false,4,7);
            Plateau.plateau[5][5] = Initialisation.getPiece(5,false,5,5);
            Plateau.plateau[5][6] = Initialisation.getPiece(5,false,5,6);
            Plateau.plateau[5][7] = Initialisation.getPiece(5,false,5,7);
        }

        else if (numeroFinale == 3) {
            Plateau.plateau[2][5] = Initialisation.getPiece(0,true,2,5);
            Plateau.plateau[0][4] = Initialisation.getPiece(1,true,0,4);
            Plateau.plateau[1][7] = Initialisation.getPiece(0,false,1,7);
            Plateau.plateau[1][6] = Initialisation.getPiece(2,false,1,6);
        }

        else if (numeroFinale == 4) {
            Plateau.plateau[7][7] = Initialisation.getPiece(0,true,7,7);
            Plateau.plateau[3][0] = Initialisation.getPiece(5,true,3,0);
            Plateau.plateau[3][3] = Initialisation.getPiece(5,true,3,3);
            Plateau.plateau[6][6] = Initialisation.getPiece(5,true,6,6);
            Plateau.plateau[1][2] = Initialisation.getPiece(0,false,1,2);
            Plateau.plateau[5][6] = Initialisation.getPiece(5,false,5,6);
            Plateau.plateau[6][7] = Initialisation.getPiece(5,false,6,7);
        }

        else if (numeroFinale == 5) {
            Plateau.plateau[4][3] = Initialisation.getPiece(0,true,4,3);
            Plateau.plateau[5][6] = Initialisation.getPiece(3,true,5,6);
            Plateau.plateau[4][4] = Initialisation.getPiece(5,true,4,4);
            Plateau.plateau[2][4] = Initialisation.getPiece(0,false,2,4);
            Plateau.plateau[5][7] = Initialisation.getPiece(3,false,5,7);
        }

        else {
            System.out.println("\nCETTE FINALE N'EXISTE PAS !!! Veuillez choisir une finale valide...\n");
            choisirFinale();
        }
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet de créer sa propre disposition pour une partie d'échecs */

    public static void partiePersonnalisee() {
        java.util.Scanner entree = new java.util.Scanner(System.in);
        boolean roiblanc = false;
        boolean roinoir = false;
        /* Boucle qui pour chaque case demande quelle pièce le joueur veut mettre */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                String coordCase = Joueur.CoordCase(i, j);
                System.out.println("Quelle pièce voulez-vous mettre en " + coordCase + " ?");
                String nomPiece = entree.next();
                /* Selon ce que dit le joueur, le programme met une pièce à la case demandée */
                switch (nomPiece) {
                    case ("R") :
                        /* On vérifie s'il y a déjà un roi noir ou non */
                        if (!roiblanc) {
                            Plateau.plateau[i][j] = Initialisation.getPiece(0, true, i, j);
                            roiblanc = true;
                        }
                        else {
                            j -= 1;
                            System.out.println("Le roi blanc est déjà placé");
                        }
                        break;

                    case ("D") : Plateau.plateau[i][j] = Initialisation.getPiece(1, true, i, j);
                        break;
                    case ("T") : Plateau.plateau[i][j] = Initialisation.getPiece(2, true, i, j);
                        break;
                    case ("F") : Plateau.plateau[i][j] = Initialisation.getPiece(3, true, i, j);
                        break;
                    case ("C") : Plateau.plateau[i][j] = Initialisation.getPiece(4, true, i, j);
                        break;
                    case ("P") : Plateau.plateau[i][j] = Initialisation.getPiece(5, true, i, j);
                        break;
                    case ("r") :
                        /* On vérifie s'il y a déjà un roi noir ou non */
                        if (!roinoir) {
                            Plateau.plateau[i][j] = Initialisation.getPiece(0, false, i, j);
                            roinoir = true;
                        }
                        else {
                            j -= 1;
                            System.out.println("Le roi noir est déjà placé");
                        }
                        break;
                    case ("d") : Plateau.plateau[i][j] = Initialisation.getPiece(1, false, i, j);
                        break;
                    case ("t") : Plateau.plateau[i][j] = Initialisation.getPiece(2, false, i, j);
                        break;
                    case ("f") : Plateau.plateau[i][j] = Initialisation.getPiece(3, false, i, j);
                        break;
                    case ("c") : Plateau.plateau[i][j] = Initialisation.getPiece(4, false, i, j);
                        break;
                    case ("p") : Plateau.plateau[i][j] = Initialisation.getPiece(5, false, i, j);
                        break;
                    case ("V"), ("v") : Plateau.plateau[i][j] = Initialisation.getPiece(6, false, i, j);
                        break;
                    default : {
                        System.out.println("Ceci n'est pas une pièce...\n");
                        j = j - 1; /* Si l'entrée du joueur ne correspond pas à une pièce, le programme
                              lui redemande à la même case quelle pièce il veut mettre */
                    }
                }
            }
        }
        if (!roiblanc || !roinoir) {
            System.out.println("Un des rois (ou les deux) n'est pas placé)\n");
            partiePersonnalisee();
            /* S'il manque au moins un des deux rois, on redemande de faire le placement */
        }
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet de choisir de quel type sera le joueur noir ou blanc
    @param c La couleur du joueur
    @return Le joueur ou le bot créé */

    public static Joueur initialisationJoueur(boolean c) {
        java.util.Scanner entree = new java.util.Scanner(System.in);
        if (c) {
            System.out.println("\nLe joueur blanc sera : \n");
        }
        else {
            System.out.println("\nLe joueur noir sera : \n");
        }
        System.out.println("""
                1 : Un humain
                2 : Un bot
                """);
        int type = entree.nextInt();
        switch(type) {
            case(1):
                return new Joueur(c);
            case(2):
                return new Bot(c);
            default:
                System.out.println("\nIl faut taper soit 1 soit 2 !");
        }
        return initialisationJoueur(c);
    }
}
