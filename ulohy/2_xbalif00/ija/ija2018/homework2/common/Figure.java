package ija.ija2018.homework2.common;

import ija.ija2018.homework2.game.Board;

/**
 *
 * @author Bali
 */

public interface Figure {
    String getState();     //Vraci W white alebo B black
    boolean move(Field moveTo, Board board, boolean saveUndo);

    enum Color {
        B,      //Black
        W,      //White
    }

    enum Type {
        P,      //pesec
        V,      //vez
    }

}
