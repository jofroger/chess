package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;


/**
 * @author Bali
 */
public class Board {

    private int boardSize = 0;

    public BoardField[][] array;
    private int cField;         //stlpec datoveho pola Field[][]
    private int rField;         //riadok datoveho pola Field[][]
    static int bSize;
    private BoardField field;

    /**
     * @param size Velkost hracieho pola
     */
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


    /**
     * @return Vrati prvok hracieho pola
     */
    //Methods
    public BoardField getField() {
        return field;
    }

    /**
     * @param field Nastavuje prvok hracieho pola
     */
    public void setField(BoardField field) {
        this.field = field;
    }


    /**
     * @param r Stlpec hracieho pola
     * @param c  Riadok hracieho pola
     * @param field Naplnane pole
     */
    public void setField(int c, int r, Field field) {
        this.array[c][r] = (BoardField) field;
    }

    /**
     * @param col Stlpec hracieho pola
     * @param row  Riadok hracieho pola
     * @return Vrati pole hracieho pola
     */
    public BoardField getField(int col, int row){
        this.rField = row-1;
        this.cField = col-1;
        return array[cField][rField];
    }

    /**
     * @return Vrati velkost hracieho pola
     */
    public int getSize(){
        return this.boardSize;
    }

}
