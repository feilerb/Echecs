package jeu;

public interface IJoueur {

    static Coord StringCase(String Case) throws Echec {
        return null;
    }

    static String CoordCase(int x, int y) {
        return null;
    }

    SuperCoord Input() throws Echec, Abandon;

    boolean PeutSeDeplacer();

    boolean Nulle();

    boolean materiel();

}
