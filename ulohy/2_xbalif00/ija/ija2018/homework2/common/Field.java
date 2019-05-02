package ija.ija2018.homework2.common;

import ija.ija2018.homework2.game.Disk;

/**
 *
 * @author Bali
 */

public interface Field {

    void    addNextField(Field.Direction dirs, Field field);
    Field   nextField(Field.Direction dirs);
    boolean put(Disk figure);
    boolean remove(Figure figure);
    Field   getField(int col, int row);
    int getCol();
    int getRow();
    Field getPrevField();
    void  setPrevField(Field field);
    Disk  getTakeDisk();
    void  setTakeDisk(Disk disk);

    boolean isEmpty();                  //Test, zda je pole prázdné.
    Disk get();                         //Vrací kámen, který leží na poli. Pokud je pole prázdné, vrací null.

    enum Direction {
        D,      //Down
        L,      //Left
        LD,     //Left-Downe
        LU,     //Left-Up
        R,      //Right
        RD,     //Right-Down
        RU,     //Right-Up
        U       //Up
        ;
    }
}
