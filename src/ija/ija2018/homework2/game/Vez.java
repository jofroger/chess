package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;

public class Vez extends Disk{

    //Constructor
    public Vez(boolean isWhite, int col, int row) {
        super(isWhite, Type.V, col, row);
        init();
    }

    private void init () {
        String up    = String.valueOf(Field.Direction.U);
        String down  = String.valueOf(Field.Direction.D);
        String left  = String.valueOf(Field.Direction.L);
        String right = String.valueOf(Field.Direction.R);

        String[][] cesty = {{up,up,up,up,up,up,up,up},
                            {down,down,down,down,down,down,down,down},
                            {left,left,left,left,left,left,left,left},
                            {right,right,right,right,right,right,right,right}};
        setGoodWay(cesty);

    }

}
