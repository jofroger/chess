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
    private int cField;         //stlpec datoveho pola Field[][]
    private int rField;         //riadok datoveho pola Field[][]
    private Disk disk;
    private Disk takeDisk;
    public Field prevField;

    //Constructor
    public BoardField(int col, int row){
        this.row = row;
        this.col = col;
        this.rField = row-1;
        this.cField = col-1;
    }

    //Methods
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
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean put(Disk disk) {
        if (isEmpty()) {
            this.disk = disk;
            disk.setColRow(this.col,this.row);
            return true;
        } else return false;
    }

    @Override
    public boolean remove(Figure figure) {
        if (!isEmpty()) {
            this.disk = null;
            return true;
        } else return false;
    }

    @Override
    public void addNextField(Direction dirs, Field field) {

    }

    @Override
    public Field nextField(Direction dirs) {
        return null;
    }


    @Override
    public Field getField(int col, int row) {
        return null;
    }

    @Override
    public int getCol() {
        return this.col;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public boolean isEmpty() {
        return this.disk == null ? true : false;
    }

    @Override
    public Disk get() {
        if (!isEmpty()) {
            disk.setColRow(col,row);
            return disk;
        } else return null;
    }
}
