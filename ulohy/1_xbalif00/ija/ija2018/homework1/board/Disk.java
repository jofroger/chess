package ija.ija2018.homework1.board;

import static ija.ija2018.homework1.board.Board.bSize;

/**
 *
 * @author Bali
 */

public class Disk{

    private boolean diskWhite;

    //Constructor
    public Disk(boolean isWhite){
        this.diskWhite = isWhite;
    }

    //Methods
    public boolean isWhite(){
        return diskWhite;
    }
    public boolean move(Field moveTo){
        if(!moveTo.isEmpty()) return false;             //Is not Empty
        Field ff = getDiskField(this);
        if (ff == null) return false;                   //Not found Disk
        if (!checkTrase(ff,moveTo)) return false;       //Trace is not free
        Disk d = ff.get();
        if (!ff.remove(d)) return false;            //Not Remove Disk from old position
        return moveTo.put(d);
    }

    private boolean checkTrase (Field source, Field destination ) {
        int k = 0;
        if (source.getCol() == destination.getCol()) {
            k = source.getRow()>destination.getRow()? -1 : 1;
            for (int i = source.getRow()+k; i != destination.getRow()-k ; i=i+k) {
                if (Board.array[source.getCol()][i].get()!=null) return false;
            }
        }

        if (source.getRow() == destination.getRow()) {
            k = source.getCol()>destination.getCol()? -1 : 1;
            for (int i = source.getCol()+k; i != destination.getCol()-k ; i=i+k) {
                if (Board.array[i][source.getRow()].get()!=null) return false;
            }
        }
        return true;
    }

    private Field getDiskField (Disk disk) {

        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                Field f = Board.array[j][i];
                if ( f.get() != null && f.get().equals(disk)) return f;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Disk && this.getClass() == obj.getClass()) {
            final Disk other = (Disk) obj;
            return other.diskWhite == this.diskWhite;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
