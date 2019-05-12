package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;

public class Kral extends Disk{

    //Constructor
    /**
     * @param isWhite True/False Biela figurka
     * @param col Riadok
     * @param row Stlpec
     */
    public Kral(boolean isWhite, int col, int row) {
        super(isWhite, Type.K, col, row);
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

        String[][] cesty = {{up},{down},{left},{right},{leftUp},{leftDown},{rightUp},{rightDown}};
        setGoodWay(cesty);
    }

}
