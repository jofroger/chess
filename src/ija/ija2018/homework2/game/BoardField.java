package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;

/**
 *
 * @author Bali
 */

public class BoardField implements Field, Cloneable {

    private int col;            //stlpec hracieho pola
    private int row;            //riadok hracieho pola
    private int c;              //stlpec hracieho pola v smere dirs
    private int r;              //riadok hracieho pola v smere dirs
    private int cField;         //stlpec datoveho pola Field[][]
    private int rField;         //riadok datoveho pola Field[][]
    private Disk disk;
    private Disk takeDisk;      //vyhodena figurka
    public Field prevField;     //predchadzajuce pole
    public Field prevAktualField;     //predchadzajuce aktualne pole

    /**
     * @param col Stlpec hracej dosky
     * @param row Riadok hracej dosky
     */
    //Constructor
    public BoardField(int col, int row){
        this.row = row;
        this.col = col;
        this.rField = row-1;
        this.cField = col-1;
    }

    /**
     * @param dirs Smer pohybu
     * @param r Aktualny riadok
     * @return Vrati novy riadok v zavislosti od smeru pohybu
     */
    //Methods Homework1
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

    /**
     * @param dirs Smer pohybu
     * @param c Aktualny stlpec
     * @return Vrati novy stlpec v zavislosti od smeru pohybu
     */
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

    /**
     * @return Vrati stlpec
     */
    @Override
    public int getCol() {
        return this.col;
    }

    /**
     * @return Vrati riadok
     */
    @Override
    public int getRow() {
        return this.row;
    }

    /**
     * @param col Stlpec
     * @param row Riadok
     * @param board Hracia doska
     * @return Vrati hracie pole
     */
    @Override
    public Field getField(int col, int row, Board board) {
        this.rField = row-1;
        this.cField = col-1;
        return board.getField(cField, rField);
    }

    /**
     * @param dirs Smer pohybu
     * @param field Pridava nove pole
     * @param board Konkretna hracia doska
     */
    @Override
    public void addNextField(Direction dirs, Field field, Board board) {
        c = newCol(dirs, this.cField);
        r = newRow(dirs, this.rField);

        if (c>=0 || c<= Board.bSize-1 || r>=0 || r<= Board.bSize-1 ) board.setField(c,r,field);
    }


    /**
     * @param dirs Smer pohybu
     * @param board Hracia doska
     * @return Vrati nasledujuce pole
     */
    @Override
    public Field nextField(Direction dirs, Board board) {

//        c = newCol(dirs, this.cField);
//        r = newRow(dirs, this.rField);

//        if (c>=0 || c<= Board.bSize-1 || r>=0 || r<= Board.bSize-1 ) return board.getField(c, r);

        c = newCol(dirs, this.col);
        r = newRow(dirs, this.row);

//        if (c>=1 || c<= Board.bSize || r>=1 || r<= Board.bSize ) return board.getField(c, r);
        if (c>=1 && c<= Board.bSize && r>=1 && r<= Board.bSize ) return board.getField(c, r);

        return null;
    }


    /**
     * @return Vrati ci hracie pole je prazdne
     */
    @Override
    public boolean isEmpty() {
        return this.disk == null ? true : false;
    }


    /**
     * @return Vrati figurku hracieho pola
     */
    //Methods
    @Override
    public Disk get() {
        if (!isEmpty()) {
            disk.setColRow(col,row);
            return disk;
        } else return null;
    }

    /**
     * @param disk Figurka
     * @return Polozi figurku na hracie pole
     */
    @Override
    public boolean put(Disk disk) {
        if (isEmpty()) {
            this.disk = disk;
            disk.setColRow(this.col,this.row);
            return true;
        } else return false;
    }

    /**
     * @param figure Vymaze figurku z hracieho pola
     * @return
     */
    @Override
    public boolean remove(Figure figure) {
        if (!isEmpty()) {
            this.disk = null;
            return true;
        } else return false;
    }

    public Disk getDisk() {
        return disk;
    }

    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    //Override Methods
    @Override
    public Disk getTakeDisk() {
        return this.takeDisk;
    }
    @Override
    public void setTakeDisk(Disk takeDisk) {
        this.takeDisk = takeDisk;
    }

    @Override
    public Field getPrevField() {
        return this.prevField;
    }

    @Override
    public void setPrevField(Field field) {
        this.prevField = field;
    }

    @Override
    public Field getPrevAktualField() {
        return this.prevAktualField;
    }

    @Override
    public void setPrevAktualField(Field field) {
        this.prevAktualField = field;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
