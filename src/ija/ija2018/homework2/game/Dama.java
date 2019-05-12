package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;

public class Dama extends Disk{

    //Constructor
    /**
     * @param isWhite True/False Biela figurka
     * @param col Riadok
     * @param row Stlpec
     */
    public Dama(boolean isWhite, int col, int row) {
        super(isWhite, Type.D, col, row);
        init();
    }

    /**
     * @note Pre dany typ figurky inicializuje zoznam moznych smerov
     */
    private void init () {
        String up    = String.valueOf(Field.Direction.U);
        String down  = String.valueOf(Field.Direction.D);
        String left  = String.valueOf(Field.Direction.L);
        String right = String.valueOf(Field.Direction.R);
        String leftUp    = String.valueOf(Field.Direction.LU);
        String leftDown  = String.valueOf(Field.Direction.LD);
        String rightUp  = String.valueOf(Field.Direction.RU);
        String rightDown = String.valueOf(Field.Direction.RD);

        String[][] cesty = {{up,up,up,up,up,up,up,up},
                            {down,down,down,down,down,down,down,down},
                            {left,left,left,left,left,left,left,left},
                            {right,right,right,right,right,right,right,right},
                            {leftUp,leftUp,leftUp,leftUp,leftUp,leftUp,leftUp,leftUp},
                            {leftDown,leftDown,leftDown,leftDown,leftDown,leftDown,leftDown,leftDown},
                            {rightUp,rightUp,rightUp,rightUp,rightUp,rightUp,rightUp,rightUp},
                            {rightDown,rightDown,rightDown,rightDown,rightDown,rightDown,rightDown,rightDown}};
        setGoodWay(cesty);

    }

}
