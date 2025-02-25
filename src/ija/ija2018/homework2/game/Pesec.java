package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;

public class Pesec extends Disk{

    //Constructor
    /**
     * @param isWhite True/False Biela figurka
     * @param col Riadok
     * @param row Stlpec
     */
    public Pesec(boolean isWhite, int col, int row) {
        super(isWhite, Type.P, col, row);
        init();
    }
    /**
     * @note Pre dany typ figurky inicializuje zoznam moznych smerov
     */
    private void init () {
        String up        = String.valueOf(Field.Direction.U);
        String down      = String.valueOf(Field.Direction.D);
        String leftUp    = String.valueOf(Field.Direction.LU);
        String leftDown  = String.valueOf(Field.Direction.LD);
        String rightUp   = String.valueOf(Field.Direction.RU);
        String rightDown = String.valueOf(Field.Direction.RD);

        String[][] cesty = {{up,up},
                {leftUp},
                {rightUp},
                {down,down},
                {leftDown},
                {rightDown}};
        setGoodWay(cesty);

    }

    /**
     * @param moveTo Presun na policko
     * @param game Hra
     * @return Vrati ci dany pohyb figurky je realizovatelny True/False
     */
    @Override
    public boolean moveValidation(Field moveTo, Game game) {

//        Game game = new Game(); //null;
        Field dirsField = null;
        String[][] cesty = getGoodWay();
        Disk pesec = game.getBoard().getField(Game.aktualField.getCol(), Game.aktualField.getRow()).get();

        int k = pesec.getColor().equals(Color.W)?0:3;
        int twoSteps = pesec.getColor().equals(Color.W)?2:7;

        Game.sach = false;

        for (int i = k; i < k+3; i++) {
            Field aktualField = Game.aktualField;
            String[] strings = cesty[i];
//            twoSteps = i==0 || i==3 && Game.aktualField.getRow()==2 || Game.aktualField.getRow()==7 ? true:false;
            for (String str : strings) {
//                dirsField = game.getBoard().getField().nextField(Field.Direction.valueOf(str), game.getBoard());
                dirsField = aktualField.nextField(Field.Direction.valueOf(str), game.getBoard());
                if (dirsField == null) break;
                else {
//                    if (!dirsField.isEmpty() && Game.destField.equals(dirsField)) {
                    if ( Game.destField.equals(dirsField)) {
                        if (!dirsField.isEmpty()) {
                            //pole NIE je prazdne
                            if (i != 0 && i != 3) {             // ide do boku
                                if (dirsField.get().getColor().equals(Game.aktualField.get().getColor())) return false; //ak rovnaka farba tak FALSE
//                                else return true;
                                else {
                                    if (dirsField.get().getTyp().equals(Figure.Type.K)) {
                                        Game.sach = true;
                                        return false;
                                    }else return true;
                                }

                            }else return false;                 // ide priamo

                        }else {
                            //pole JE prazdne
                            if (i != 0 && i != 3) return false; // ide do boku
                            else return true;                   // ide priamo
                        }
                    }
                    aktualField = dirsField;
                    if (!dirsField.isEmpty()) break; // v ceste je figurka
//                    if (i != 0 && i != 3) break;     // umozni dva kroky pesiaka
                    if (Game.aktualField.getRow() != twoSteps) break;     // umozni dva kroky pesiaka
                }
            }
        }

        return false;
    }
}
