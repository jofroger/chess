package ija.ija2018.homework1.board;

/**
 *
 * @author Bali
 */

public class BoardField implements Field {

    private int col;            //stlpec hracieho pola
    private int row;            //riadok hracieho pola
    private int c;              //stlpec hracieho pola v smere dirs
    private int r;              //riadok hracieho pola v smere dirs
    private int cField;         //stlpec datoveho pola Field[][]
    private int rField;         //riadok datoveho pola Field[][]
    private Disk disk;

    //    Constructor
    public BoardField(int col, int row){
        this.row = row;
        this.col = col;
        this.rField = row-1;
        this.cField = col-1;
    }

    private int newRow (Direction dirs, int r) {
        String s;
        for (int i = 0; i < dirs.toString().length(); i++) {
            s = dirs.toString().substring(i,i+1);
            switch (s){
                case "D":
                    r--;
                    break;
                case "U":
                    r++;
                    break;
            }
        }
        return r;
    }

    private int newCol (Direction dirs, int c) {
        String s;
        for (int i = 0; i < dirs.toString().length(); i++) {
            s = dirs.toString().substring(i,i+1);
            switch (s){
                case "L":
                    c--;
                    break;
                case "R":
                    c++;
                    break;
            }
        }
        return c;
    }

    @Override
    public int getCol() {
        return cField;
    }

    @Override
    public int getRow() {
        return rField;
    }

    @Override
    public Field getField(int col, int row) {
        this.rField = row-1;
        this.cField = col-1;
        return Board.array[cField][rField];
    }

    @Override
    public void addNextField(Direction dirs, Field field) {
            c = newCol(dirs, this.cField);
            r = newRow(dirs, this.rField);

        if (c>=0 || c<= Board.bSize-1 || r>=0 || r<= Board.bSize-1 ) Board.array[c][r] = field;
    }

    @Override
    public Field nextField(Direction dirs) {

        c = newCol(dirs, this.cField);
        r = newRow(dirs, this.rField);

        if (c>=0 || c<= Board.bSize-1 || r>=0 || r<= Board.bSize-1 ) return Board.array[c][r];

        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.disk == null ? true : false;
    }


    @Override
    public Disk get() {
        if (!isEmpty()) {
            return disk;
        } else return null;
    }

    @Override
    public boolean put(Disk disk) {
        if (isEmpty()) {
            this.disk = disk;
            return true;
        } else return false;
    }

    @Override
    public boolean remove(Disk disk) {
        if (!isEmpty() && this.disk.equals(disk)) {
            this.disk = null;
            return true;
        } else return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BoardField && this.getClass() == obj.getClass()) {
            final BoardField other = (BoardField) obj;
            return other.row == this.row && other.col == this.col;
        }
        return false;
    }
}
