/* Le package jeu regroupe le jeu d'échecs dans sa globalité, que ce soit
le joueur, le plateau ou tout le principe de joueur */

package jeu;

/* On crée une classe plateau qui est composé d'un double tableau de pièces,
des coordonnées des deux rois et des coordonnées des échecs s'ils existent*/

import piece.Piece;

public class Plateau {
    public static Piece[][] plateau;
    public static Coord RoiBlanc;
    public static Coord RoiNoir;
    public static Coord Echec;

    /*---------------------------------------------------------------------------------------------------*/

    /*On crée un constructeur nommé plateau qui crée une surface de jeu de 8 cases sur 8 cas,
    qui pour chaque case crée une case vide et crée le principe d'échec */

    public Plateau() {
        plateau = new Piece[8][8];
        Echec = new Coord(-1,0);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                plateau[i][j] = Initialisation.getPiece(6,true,i,j);
            }
        }
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Cette méthode va permettre l'affichage du plateau
    @return L'affichage du plateau */

    public String toString() {
        StringBuilder affichage;
        String ligneLettres = "    a   b   c   d   e   f   g   h";
        String sautLigne = System.getProperty("line.separator"); /* Permet un retour à la ligne */
        String interLigne = "   --- --- --- --- --- --- --- ---";
        String colonne = " | ";
        affichage = new StringBuilder(ligneLettres + sautLigne);
        for (int i = 0; i < 8; i++) {
            affichage.append(interLigne).append(sautLigne);
            String chiffreLigne = String.valueOf(8 - i);
            affichage.append(chiffreLigne);
            for (int j = 0; j < 9; j++) {
                affichage.append(colonne);
                if (j != 8)
                    affichage.append(plateau[i][j].to_String());
                /* On affiche les pièces dans les cases du plateau */
            }
            affichage.append(chiffreLigne).append(sautLigne);
        }
        affichage.append(interLigne).append(sautLigne).append(ligneLettres);
        return affichage.toString();
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Sert à savoir où est le roi blanc et le roi noir */

    public static void EmplacementRoi() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j<8; j++) {
                if (plateau[i][j].VRoi()) { /* Regarde sur la case [i][j] si c'est un Roi */
                    if (plateau[i][j].MemeCouleur(true)) { /* Regarde la couleur du roi */
                        RoiBlanc = new Coord(i,j); /* S'il est blanc, il prend ses coordonnées */
                    }
                    else {
                        RoiNoir = new Coord(i,j); /* De même mais avec le roi noir */
                    }
                }
            }
        }
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Vérifie s'il y a échec en fonction de la couleur
    @param c La couleur du roi
    @return Un booléen pour savoir s'il y a échec ou non */

    public static boolean VEchec(boolean c) {
        if (Echec.x != -1) { /* S'il y avait déjà échec */
            if (plateau[Echec.x][Echec.y].MemeCouleur(!c)) { /* Si la pièce qui met en échec n'est
                                                           pas de même couleur que le roi */
                if (c) { /* Si c'est le roi blanc qui est en échec */
                    if (plateau[Echec.x][Echec.y].MouvPossible(RoiBlanc, plateau)) {
                        /* Si c'est toujours le même échec*/
                        return true; /* Alors il y a échec sur le roi blanc */
                    }
                }
                else {
                    if (plateau[Echec.x][Echec.y].MouvPossible(RoiNoir, plateau)) {
                        return true; /* Alors il y a échec sur le roi noir */
                    }
                }
            }
        }
        for (int i = 0; i < 8; i++) { /* On parcourt toutes les cases du tableau */
            for (int j = 0; j < 8; j++) {
                if (plateau[i][j].MemeCouleur(!c)) { /* Si la couleur est différente de celle du roi */
                    if (c) {
                        if (plateau[i][j].MouvPossible(RoiBlanc,plateau)) { /* Si la pièce peut se
                                                                            déplacer sur le roi */
                            Echec.x = i;
                            Echec.y = j; /* On enregistre les coordonnées de la pièce qui met en échec */
                            return true;
                        }
                    }
                    else {
                        if (plateau[i][j].MouvPossible(RoiNoir,plateau)) { /* Si la pièce peut se
                                                                            déplacer sur le roi */
                            Echec.x = i;
                            Echec.y = j; /* On enregistre les coordonnées de la pièce qui met en échec */
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Permet le déplacement d'une pièce sur le plateau
    @param depart Coordonnées initiales de la pièce
    @param arrivee Coordonnées de la case où veut aller la pièce */

    public static boolean Deplacementplateau (Coord depart, Coord arrivee) throws Echec {
        boolean roque = false;
        boolean versh = false; /* De quel côté est le roque (vers la colonne h) */
        if (depart.y - arrivee.y == 2 ) {
            roque = true; /* Possibilité de roque vers la colonne a */
        }
        else if (depart.y - arrivee.y == -2) {
            roque = true;
            versh = true; /* Possibilité de roque vers la colonne h */
        }

        /* On déplace la pièce en changeant les coordonnées et en mettant une case vide à la case de départ */
        Coord act = new Coord(depart.x, depart.y);
        Piece f = plateau[arrivee.x][arrivee.y];
        plateau[arrivee.x][arrivee.y] = plateau[depart.x][depart.y];
        plateau[arrivee.x][arrivee.y].changePlace(arrivee.x,arrivee.y);
        plateau[act.x][act.y] = Initialisation.getPiece(6,true,act.x,act.y);

        /* On vérifie si le déplacement était pour un roi.
        En fonction de la couleur, on modifie les coordonnées du roi correspondant et on les garde en mémoire */
        if (plateau[arrivee.x][arrivee.y].VRoi()) {
            if (plateau[arrivee.x][arrivee.y].couleur) {
                RoiBlanc.x = arrivee.x;
                RoiBlanc.y = arrivee.y;
            }
            else {
                RoiNoir.x = arrivee.x;
                RoiNoir.y = arrivee.y;
            }

            /* Si jamais il y a roque, on applique les caractéristiques du déplacement  */
            if (roque) {
                if (versh) {
                    plateau[arrivee.x][5] = plateau[arrivee.x][7];
                    plateau[arrivee.x][5].changePlace(arrivee.x,7);
                    plateau[arrivee.x][7] = Initialisation.getPiece(6,true,arrivee.x,7);
                }
                else {
                    plateau[arrivee.x][3] = plateau[arrivee.x][0];
                    plateau[arrivee.x][3].changePlace(arrivee.x,0);
                    plateau[arrivee.x][0] = Initialisation.getPiece(6,true,arrivee.x,0);
                }
            }
        }

        if (VEchec(plateau[arrivee.x][arrivee.y].couleur)) {
        /* S'il y avait échec sur le roi de sa couleur alors on annule le coup*/
            plateau[act.x][act.y] = plateau[arrivee.x][arrivee.y];
            plateau[act.x][act.y].changePlace(act.x,act.y);
            plateau[arrivee.x][arrivee.y] = f; /* Il replace la pièce mangée */

            /* On annule le roque s'il y en avait eu un */
            if (roque && plateau[arrivee.x][arrivee.y].VRoi()) {
                if (versh) {
                    plateau[arrivee.x][7] = plateau[arrivee.x][5];
                    plateau[arrivee.x][7].changePlace(arrivee.x,5);
                    plateau[arrivee.x][5] = Initialisation.getPiece(6,true,arrivee.x,5);
                }
                else {
                    plateau[arrivee.x][0] = plateau[arrivee.x][3];
                    plateau[arrivee.x][0].changePlace(arrivee.x,3);
                    plateau[arrivee.x][3] = Initialisation.getPiece(6,true,arrivee.x,3);
                }
            }

            /* Si c'est un roi qui avait été joué, on remet ses coordonnées de départ */
            if (plateau[act.x][act.y].VRoi()) {
                if (plateau[act.x][act.y].couleur) {
                    RoiBlanc.x = act.x;
                    RoiBlanc.y = act.y;
                }
                else {
                    RoiNoir.x = act.x;
                    RoiNoir.y = act.y;
                }
            }
            return false; /* Permet de rejouer */
        }

        /* Si un pion s'était transformé en reine, on le retransforme en pion et on le remet là où il était */
        if (Plateau.plateau[arrivee.x][arrivee.y].VPion() &&
           ((arrivee.x == 0 && Plateau.plateau[arrivee.x][arrivee.y].MemeCouleur(true)) ||
           (arrivee.x == 7 && Plateau.plateau[arrivee.x][arrivee.y].MemeCouleur(false)))) {
             Plateau.plateau[arrivee.x][arrivee.y] = Initialisation.getPiece(1,Plateau.plateau[arrivee.x][arrivee.y].couleur,arrivee.x,arrivee.y);
        }

        if (plateau[arrivee.x][arrivee.y].VTour() || plateau[arrivee.x][arrivee.y].VRoi())
            plateau[arrivee.x][arrivee.y].aBouge = true;
        if (VEchec(!plateau[arrivee.x][arrivee.y].couleur)) { /* S'il y a échec sur le roi de la couleur adverse */
            throw new Echec();
        }
        Echec.x = -1; /* Il n'y a pas d'échecs */
        return true;
    }

    /*---------------------------------------------------------------------------------------------------*/

    /* @brief Vérifie par un faux déplacement si la pièce mettrait en échec le roi adverse
    @param depart Les coordonnées de départ de la pièce
    @param arrivee Les coordonnées d'arrivée de la pièce
    @return Booléen qui indique s'il y aurait échec ou non */

    public static boolean FauxDeplacement (Coord depart, Coord arrivee) {
        boolean retour = true;
        Coord act = new Coord(depart.x, depart.y); /* On duplique les coordonnées de départ */
        Piece f = plateau[arrivee.x][arrivee.y];
        plateau[arrivee.x][arrivee.y] = plateau[depart.x][depart.y];
        plateau[arrivee.x][arrivee.y].changePlace(arrivee.x,arrivee.y);
        plateau[act.x][act.y] = Initialisation.getPiece(6,true,act.x,act.y);
        if (plateau[arrivee.x][arrivee.y].VRoi()) {
            if (plateau[arrivee.x][arrivee.y].couleur) {
                RoiBlanc.x = arrivee.x;
                RoiBlanc.y= arrivee.y;
            }
            else {
                RoiNoir.x = arrivee.x;
                RoiNoir.y = arrivee.y;
            }
        }
        /* Création d'une case vide */
        if (VEchec(plateau[arrivee.x][arrivee.y].couleur)) { /* S'il y a échec */
            retour = false;
        }
        plateau[act.x][act.y] = plateau[arrivee.x][arrivee.y];
        plateau[act.x][act.y].changePlace(act.x,act.y);
        plateau[arrivee.x][arrivee.y] = f; /* Il replace la pièce mangée */
        if (plateau[act.x][act.y].VRoi()) {
            if (plateau[act.x][act.y].couleur) {
                RoiBlanc.x = act.x;
                RoiBlanc.y = act.y;
            }
            else {
                RoiNoir.x = act.x;
                RoiNoir.y = act.y;
            }
        }
        return retour;
    }

}
