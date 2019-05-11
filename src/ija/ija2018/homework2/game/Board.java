package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;

/**
 *
 * @author Bali
 */

public class Board {

    private int boardSize = 0;

    public BoardField[][] array;
    private int cField;         //stlpec datoveho pola Field[][]
    private int rField;         //riadok datoveho pola Field[][]
    static int bSize;
    private BoardField field;

    //Constructor
    public Board(int size){
        bSize = size;
        this.boardSize = size;
        this.array = new BoardField[size][size];
    }

//    public static void main(String[] args) {
//
//        GridLayoutManager gui = new GridLayoutManager();
//        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//    }


    //Methods
    public BoardField getField() {
        return field;
    }

    public void setField(BoardField field) {
        this.field = field;
    }
    public void setField(int c, int r, Field field) {
        this.array[c][r] = (BoardField) field;
    }

    public BoardField getField(int col, int row){
        this.rField = row-1;
        this.cField = col-1;
        return array[cField][rField];
    }

    public int getSize(){
        return this.boardSize;
    }

}
