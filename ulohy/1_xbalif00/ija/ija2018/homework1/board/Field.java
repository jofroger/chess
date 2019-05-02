package ija.ija2018.homework1.board;

/**
 *
 * @author Bali
 */

public interface Field{
    void addNextField(Field.Direction dirs, Field field);
    Field nextField(Field.Direction dirs);
    boolean put(Disk disk);
    boolean remove(Disk disk);
    boolean isEmpty();
    Disk get();
    Field getField(int col, int row);
    int getCol();
    int getRow();

    enum Direction {
        D,      //Down
        L,      //Left
        LD,     //Left-Down
        LU,     //Left-Up
        R,      //Right
        RD,     //Right-Down
        RU,     //Right-Up
        U       //Up
    }
}

