package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;

import static ija.ija2018.homework2.game.Board.bSize;

/**
 *
 * @author Bali
 */

public class Disk implements Figure, Cloneable {

    private boolean diskWhite;
    private Type typ;
    private Color color;
    private int col;            //stlpec hracieho pola na ktorom je Disk
    private int row;            //riadok hracieho pola na ktorom je Disk
    private String[][] goodWay;



    //Constructor
    public Disk(boolean isWhite) {
        this.diskWhite = isWhite;
    }

    public Disk(boolean isWhite, Type typ) {
        this.diskWhite = isWhite;
        this.color = isWhite ?  Color.W: Color.B;
        this.typ = typ;
    }

    public Disk(boolean isWhite, Type typ, int col, int row) {
        this.diskWhite = isWhite;
        this.color = isWhite ?  Color.W: Color.B;
        this.typ = typ;
        this.col = col;
        this.row = row;
    }

    public Color getColor() {
        return color;
    }

    public Type getTyp() {
        return typ;
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
//                    if (!dirsField.isEmpty()) break; // v ceste je figurka
//                }
//            }
//        }

        Field dirsField = null;
        String[][] cesty = getGoodWay();

        Game.sach = false;

        for (String[] strings : cesty) {
            Field aktualField = Game.aktualField;
            for (String str : strings) {
                dirsField = aktualField.nextField(Field.Direction.valueOf(str), game.getBoard());
                if (dirsField == null) break;
                else {
                    if ( Game.destField.equals(dirsField)) {
                        //Nasiel policko kam chceme tahat
//                        if (!dirsField.isEmpty()) return dirsField.get().getColor().equals(Game.aktualField.get().getColor()) ? false : true;  //ak rovnaka farba tak FALSE
//                        else return true;       //ak je prazdne tak vzdy TRUE
                        if (!dirsField.isEmpty()) {
                            //pole NIE je prazdne
                            if (dirsField.get().getColor().equals(Game.aktualField.get().getColor())) return false; //ak rovnaka farba tak FALSE
                            else {
                                if (dirsField.get().getTyp().equals(Figure.Type.K)) {
                                    Game.sach = true;
                                    return false;
                                }else return true;
                            }

                        }else return true; //pole JE prazdne

                    }
                    aktualField = dirsField;
                    if (!dirsField.isEmpty()) break; // v ceste je figurka
                }
            }
        }

        return false;
    }

    public String[][] getGoodWay() {
        return goodWay;
    }

    public void setGoodWay(String[][] goodWay) {
        this.goodWay = goodWay;
    }

    //Methods
    public boolean saveHistory(Field fieldFrom, Field fieldTo, Board board ){
        Disk moveToFigure = board.getField(fieldTo.getCol(),fieldTo.getRow()).get();

        // - ZOBRATIE FIGURKY
        if (moveToFigure != null) {
            fieldTo.setTakeDisk(moveToFigure);
        }

        try {
            BoardField f = (BoardField) fieldFrom;
            fieldTo.setPrevField((Field) f.clone());
            f = (BoardField) Game.prevAktualField;
            if (f!=null)fieldTo.setPrevAktualField((Field) f.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean checkTrase (Field fieldFrom, Field fieldTo, Board board ) {
        int k = 0;
        if (fieldFrom.getCol() == fieldTo.getCol()) {
            k = fieldFrom.getRow()>fieldTo.getRow()? -1 : 1;
            for (int i = fieldFrom.getRow()+k; i != fieldTo.getRow() ; i=i+k) {
                if (board.array[fieldFrom.getCol()-1][i-1].get()!=null) return false;
            }
            return true;
        }

        if (fieldFrom.getRow() == fieldTo.getRow()) {
            k = fieldFrom.getCol()>fieldTo.getCol()? -1 : 1;
            for (int i = fieldFrom.getCol()+k; i != fieldTo.getCol() ; i=i+k) {
                if (board.array[i-1][fieldFrom.getRow()-1].get()!=null) return false;
            }
        }
        return true;
    }

    public Field getDiskField (Disk disk, Board board) {

        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                Field f = board.array[j][i];
                if ( f.get() != null && f.get().equals(disk)) return f;
            }
        }
        return null;
    }

    public void setColRow(int col,int row) {
        this.col = col;
        this.row = row;
    }

    public boolean isWhite(){
        return diskWhite;
    }

    //Override Methods
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean move(Field moveTo, Board board, boolean saveUndo){
        Disk moveToFigure = board.getField(moveTo.getCol(),moveTo.getRow()).get();

        Field ff = getDiskField(this, board);
        if (ff == null) return false;                   //Not found Disk
        if (saveUndo) saveHistory(ff,moveTo,board);

        if(!moveTo.isEmpty()) {  //bolo return false;
            //fieldTo is not Empty

            // Nevyhod figurku rovnakej farby
            if (moveToFigure.color.equals(this.color)) return false;

            // zmaz zobranu figurku
            if(!moveTo.remove(moveToFigure)) return false;  //Not Remove Disk from old position
        }

        if (saveUndo) if (!checkTrase(ff,moveTo,board)) return false;       //Trace is not free
        Disk f = ff.get();
        if(!ff.remove(f)) return false;            //Not Remove Disk from old position
        Game.aktualField = moveTo;
        return moveTo.put(f);
    }


    @Override
    public String getState() {
        return String.valueOf(typ)+"["+String.valueOf(color)+"]"+col+":"+row;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
