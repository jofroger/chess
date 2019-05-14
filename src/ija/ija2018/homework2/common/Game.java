package ija.ija2018.homework2.common;

/**
 *
 * @author Bali
 */

public interface Game {   //extends Figure, Field {

    boolean move(Figure figure, Field field);   //Přesune figuru na zadané polícko, pokud je to možné.
    void  undo();                               //Provede reverzní operaci (tah) nad hrou.
    int oneStep(String s);

}
