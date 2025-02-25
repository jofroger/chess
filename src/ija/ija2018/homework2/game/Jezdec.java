package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;

public class Jezdec extends Disk{

    //Constructor
    /**
     * @param isWhite True/False Biela figurka
     * @param col Riadok
     * @param row Stlpec
     */
    public Jezdec(boolean isWhite, int col, int row) {
        super(isWhite, Type.J, col, row);
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

        String[][] cesty = {{up,up,right},
                            {up,up,left},
                            {up,right,right},
                            {up,left,left},
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

    /**
     * @param moveTo Presun na policko
     * @param game Hra
     * @return Vrati ci dany pohyb figurky je realizovatelny True/False
     */
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
        String str = "";

        Game.sach = false;

        for (String[] strings : cesty) {
            Field aktualField = Game.aktualField;
//            for (String str : strings) {
            for (int i = 0; i < strings.length; i++) {
                str = strings[i];
                dirsField = aktualField.nextField(Field.Direction.valueOf(str), game.getBoard());
                if (dirsField == null) break;   //mimo sachovnice
                else {
                    if ( Game.destField.equals(dirsField) && i==2) {
                        //Nasiel policko kam chceme tahat
//                        if (!dirsField.isEmpty()) return dirsField.get().getColor().equals(Game.aktualField.get().getColor()) ? false : true;  //ak rovnaka farba tak FALSE
//                        else return true;       //ak je prazdne tak vzdy TRUE
                        if (!dirsField.isEmpty()) {
                            //pole NIE je prazdne
                            if (dirsField.get().getColor().equals(Game.aktualField.get().getColor())) return false; //ak rovnaka farba tak FALSE
//                            else return true;
                            else {
                                if (dirsField.get().getTyp().equals(Figure.Type.K)) {
                                    Game.sach = true;
                                    return false;
                                }else return true;
                            }

                        }else return true; //pole JE prazdne

                    }
                    aktualField = dirsField;
                }
            }
        }

        return false;
    }
}
