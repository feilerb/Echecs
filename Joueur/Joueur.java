/* Le package jeu regroupe le jeu d'échecs dans sa globalité, que ce soit
le joueur, le plateau ou tout le principe de joueur */

package Joueur;

import jeu.*;

/* La classe Joueur est définie par les différentes actions qu'un joueur peut faire.
Elle se compose d'un nom et de la couleur du joueur */
public class Joueur implements IJoueur{
    public String nom;
    public boolean couleurJo;

    /*---------------------------------------------------------------------------------------------------*/

    /* Le constructeur Joueur crée un joueur à partir d'unc couleur donnée (noir ou blanc) */

    public Joueur (boolean c){
        couleurJo = c;
        if (c)
            nom = "blanc";
        else
            nom = "noir";
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet de transformer le nom de la case entré par le joueur
    en coordonnées comprises par la machine (exemple : a1 => (0,7)
    @param Case Le nom de la case
    @return Les coordonnées de la case */

    public static Coord StringCase(String Case) throws Echec {

        /* On sépare le nom de la case en deux en laissant d'un côté la lettre de
        la colonne dans charx et de l'autre le numéro de la ligne dans chary */

        if ( Case.length()!=2)
            throw new Echec();
        char charx = Case.charAt(0);
        char chary = Case.charAt(1);
        int x;
        int y;

        if (chary < 49 || chary > 56 || charx < 97 || charx > 104) /* On vérifie que la case donnée
                                                                      est bien existante en voyant si
                                                                      y est bien compris entre 1 et 8
                                                                      et si x est bien compris entre
                                                                      a et h en utilisant la table ASCII */
            throw new Echec(); /* Erreur */

        else { /* On prend la valeur numérique de charx et chary,
                  c'est-à-dire leur position dans la table ASCII */

            y = ((int)charx - 97); /* On enlève 97 à cette position pour que
                                    le 1 de la table ASCII soit bien égal à
                                    1 en numérique */

            x = 7 - ((int)chary - 49); /* De même avec y, mais on soustrait
                                        ce résultat à 7 puisque la colonne a
                                        est la plus éloignée (7) en terme de
                                        latitude et h la plus proche (0) */
        }

        return new Coord(x,y);
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Contrairement à StringCase, CoordCase permet de transformer des
    coordonnées machines en coordonnées case
    @param x Entier qui correspond à la longitude
    @param y Entier qui correpond à la latitude
    @return Le nom de la case */

    public static String CoordCase(int x, int y) {
        assert (x > -1 && x < 8 && y >- 1 && y < 8); /* On vérifie bien que x ET y soient
                                                tous les deux compris entre ou
                                                égaux 0 et 7 */
        char charx = 0;
        char chary = 0;
        if(x == 0)
            charx = '8';
        if(x == 1)
            charx = '7';
        if(x == 2)
            charx = '6';
        if(x == 3)
            charx = '5';
        if(x == 4)
            charx = '4';
        if(x == 5)
            charx = '3';
        if(x == 6)
            charx = '2';
        if(x == 7)
            charx = '1';
        if(y == 0)
            chary = 'a';
        if(y == 1)
            chary = 'b';
        if(y == 2)
            chary = 'c';
        if(y == 3)
            chary = 'd';
        if(y == 4)
            chary = 'e';
        if(y == 5)
            chary = 'f';
        if(y == 6)
            chary = 'g';
        if(y == 7)
            chary = 'h';
        String retour = "";
        retour = retour + chary + charx;
        return retour;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Récupère le mouvement que veux faire le joueur en entrant la case
    de la pièce qu'il veut déplacer et la case d'arrivée
    @return Renvoie les coordonnées d'arrivée et de départ */

    public SuperCoord Input() throws Echec, Abandon {
        boolean reussite = false;
        Coord a = new Coord(-1,0);
        Coord b = new Coord(-1,0);
        java.util.Scanner entree = new java.util.Scanner(System.in);
        /* On utilise un scanner pour récupérer les écrits du joueur */
        while (!reussite){
            System.out.println("Entrez la case de la pièce que vous voulez déplacer : \n" +
                    "(ou entrez \"nulle\" si vous voulez proposer la nulle ou bien \"abandon\" si vous souhaitez abondonner)");
            String coordDepart = entree.next();
            if (coordDepart.equals("nulle")) /* Proposition de nulle */
                throw new Echec();
            if (coordDepart.equals("abandon")) /* Abandon du joueur */
                throw new Abandon();

            /* On vérifie que la case existe bien via le try catch ci-dessous */
            try {
                a = StringCase(coordDepart);
                reussite = true; /* Si la saisie est réussie, on sort du while */
            }
            catch (Echec e) {
                System.out.println("La case que vous demandez n'existe pas, veuillez réessayer");
                /* Sinon on retourne au début de la boucle */
            }
        }
        reussite = false;

        while (!reussite){
            System.out.println("Entrez la case d'arrivée :");
            String coordArrivee = entree.next();

            /* On vérifie là encore que la case existe bien via le try catch ci-dessous */
            try {
                b = StringCase(coordArrivee);
                reussite = true;
            }
            catch (Echec e){
                System.out.println("La case que vous demandez n'existe pas, veuillez réessayer");
            }
        }
        return new SuperCoord(a,b);
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Vérifie si au moins une pièce du joueur peut être déplacée
    @return Un booléen, true si une pièce peut se déplacer, sinon false et c'est la fin de la partie */

    public boolean PeutSeDeplacer() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0;j < 8; j++) {
                if (Plateau.plateau[i][j].MemeCouleur(this.couleurJo))
                    if (Plateau.plateau[i][j].DeplacementPossible())
                        return false;
            }
        }
        return true;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet de répondre à une proposition de nulle adverse
    @return Un booléen, qui accepte ou non la nulle */

    public boolean Nulle() {
        java.util.Scanner entree = new java.util.Scanner(System.in);
        System.out.println("""

                 Votre adversaire propose la nulle acceptez-vous :
                1 : oui
                2 : non""");

        int numero = entree.nextInt();
        switch (numero){
            case (1): return true;
            case (2): return false;
            default:
                System.out.println("Ce n'est ni 1 ni 2");
                Nulle(); /* On refait la méthode jusqu'à il y ait une réponse valide */
        }
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Compte la valeur de chaque pièce de chaque joueur pour vérifier
    s'il y a encore assez de matériel pour jouer (dans le cas contraire,
    c'est la nulle par manque de matériel)
    @return Un booléen, qui confirme ou non s'il reste assez de matériel pour un joueur */

    public boolean materiel(){
        int ami = 0; /* Valeur des pièces du premier joueur */
        int nbamis = 0; /* Nombre de pièce du même joueur */
        int ennemi = 0; /* Valeur des pièces du second joueur */
        int nbennemis = 0; /* Nombre de pièce de ce même joueur */
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(Plateau.plateau[i][j].VPiece()){
                    if(Plateau.plateau[i][j].MemeCouleur(couleurJo)){
                        ami += Plateau.plateau[i][j].valeur;
                        nbamis += 1;
                    }
                    else{
                        ennemi += Plateau.plateau[i][j].valeur;
                        nbennemis +=1;
                    }
                    /* On parcourt toutes les cases du plateau et en fonction des pièces, on modifie les compteurs */
                }
            }
        }
        return ((nbamis == 2 && ami == 3) || nbamis == 1) && ((nbennemis == 2 && ennemi == 3) || nbennemis == 1);
        /* Situation qui sont en manque de matériel */
    }
}
