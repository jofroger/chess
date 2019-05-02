package ija.ija2018.homework2.common;


public interface Field {

    void addNextField(Field.Direction dirs, Field field);
    Figure get();
    boolean isEmpty();
    Field nextField(Field.Direction dirs);
    boolean put(Figure disk);
    boolean remove(Figure disk);

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


