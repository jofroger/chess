package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;

public class Strelec extends Disk{

    /**
     * @param isWhite True/False Biela figurka
     * @param col Riadok
     * @param row Stlpec
     */
    //Constructor
    public Strelec(boolean isWhite, int col, int row) {
        super(isWhite, Type.S, col, row);
        init();
    }
    /**
     * @note Pre dany typ figurky inicializuje zoznam moznych smerov
     */
    private void init () {
        String leftUp    = String.valueOf(Field.Direction.LU);
        String leftDown  = String.valueOf(Field.Direction.LD);
        String rightUp  = String.valueOf(Field.Direction.RU);
        String rightDown = String.valueOf(Field.Direction.RD);

        String[][] cesty = {{leftUp,leftUp,leftUp,leftUp,leftUp,leftUp,leftUp,leftUp},
                            {leftDown,leftDown,leftDown,leftDown,leftDown,leftDown,leftDown,leftDown},
                            {rightUp,rightUp,rightUp,rightUp,rightUp,rightUp,rightUp,rightUp},
                            {rightDown,rightDown,rightDown,rightDown,rightDown,rightDown,rightDown,rightDown}};
        setGoodWay(cesty);
    }

}
