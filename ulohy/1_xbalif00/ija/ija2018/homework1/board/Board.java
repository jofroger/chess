package ija.ija2018.homework1.board;

/**
 *
 * @author Bali
 */

public class Board {
    private int boardSize = 0;
    static Field[][] array;
    private int cField;         //stlpec datoveho pola Field[][]
    private int rField;         //riadok datoveho pola Field[][]

    static int bSize;

    //Constructor
    public Board(int size){
        bSize = size;
        this.boardSize = size;
        this.array = new Field[size][size]; //bolo

        // vytvor vsetky policka na hracej doske
        for (int i = 1; i <= boardSize; i++) {
            for (int j = 1; j <= boardSize; j++) {
                this.array[j-1][i-1] = new BoardField(j,i);
            }
        }
    }

    //Methods
    public Field getField(int col, int row){
        this.rField = row-1;
        this.cField = col-1;
        return array[cField][rField];
    }

    public int getSize(){
        return this.boardSize;
    }

}
