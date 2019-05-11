package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;

public class Jezdec extends Disk{

    //Constructor
    public Jezdec(boolean isWhite, int col, int row) {
        super(isWhite, Type.J, col, row);
        init();
    }

    private void init () {
        String up    = String.valueOf(Field.Direction.U);
        String down  = String.valueOf(Field.Direction.D);
        String left  = String.valueOf(Field.Direction.L);
        String right = String.valueOf(Field.Direction.R);

        String[][] cesty = {{up,up,right},
                            {up,up,left},
                            {up,right,right},
                            {up,left,right},
                            {down,down,right},
                            {down,down,left},
                            {down,right,right},
                            {down,left,left},
                            {left,left,up},
                            {left,left,down},
                            {left,up,up},
                            {left,down,down},
                            {right,right,up},
                            {right,right,down},
                            {right,up,up},
                            {right,down,down}};
        setGoodWay(cesty);
    }

    @Override
    public boolean moveValidation(Field moveTo, Game game) {

////        Game game = null;
//        Field dirsField = null;
//        String[][] cesty = getGoodWay();
//
//        for (String[] strings : cesty) {
//            for (String str : strings) {
//                dirsField = game.getBoard().getField().nextField(Field.Direction.valueOf(str), game.getBoard());
//                if (dirsField == null) break;
//                else {
//                    if (!dirsField.isEmpty() && Game.destField.equals(dirsField))
//                        return dirsField.get().getColor().equals(Game.aktualField.get().getColor()) ? false:true;  //ak rovnaka farba tak FALSE
//                }
//            }
//        }

        Field dirsField = null;
        String[][] cesty = getGoodWay();

        for (String[] strings : cesty) {
            Field aktualField = Game.aktualField;
            for (String str : strings) {
                dirsField = aktualField.nextField(Field.Direction.valueOf(str), game.getBoard());
                if (dirsField == null) break;   //mimo sachovnice
                else {
                    if ( Game.destField.equals(dirsField)) {
                        //Nasiel policko kam chceme tahat
                        if (!dirsField.isEmpty()) return dirsField.get().getColor().equals(Game.aktualField.get().getColor()) ? false : true;  //ak rovnaka farba tak FALSE
                        else return true;       //ak je prazdne tak vzdy TRUE
                    }
                    aktualField = dirsField;
                }
            }
        }

        return false;
    }
}
