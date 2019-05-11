package ija.ija2018.homework2.common;

import ija.ija2018.homework2.game.Board;
import ija.ija2018.homework2.game.Game;
/**
 *
 * @author Bali
 */

public interface Figure {

    String getState();     //Vraci W white alebo B black
    boolean move(Field moveTo, Board board, boolean saveUndo);
    boolean moveValidation(Field moveTo, Game game);

    enum Color {
        B,      //Black
        W,      //White
    }

    enum Type {
        K,      //kral
        D,      //dama
        V,      //vez
        S,      //strelec
        J,      //jezdec
        P,      //pesec
    }

}
