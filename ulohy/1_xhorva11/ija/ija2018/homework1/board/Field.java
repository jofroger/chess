package ija.ija2018.homework1.board;

import ija.ija2018.homework1.board.Disk;


public interface Field {

    void addNextField(Field.Direction dirs, Field field);
    Disk get();
    boolean isEmpty();
    Field nextField(Field.Direction dirs);
    boolean put(Disk disk);
    boolean remove(Disk disk);

    int[] getCoordinates();

    enum Direction {
        D,      // down
        L,      // left
        LD,     // left-down
        LU,     // left-up
        R,      // right
        RD,     // right-down
        RU,     // right-up
        U       // up
    }
}


