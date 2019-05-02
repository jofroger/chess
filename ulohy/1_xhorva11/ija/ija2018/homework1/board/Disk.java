package ija.ija2018.homework1.board;

import ija.ija2018.homework1.board.Field;


public class Disk {

    private boolean diskColor;       // true = white, false = black
    private int[] position;


    public Disk(boolean isWhite) {
        diskColor = isWhite;
    }

    public boolean isWhite() {
        return diskColor;
    }

    public boolean move(Field moveTo) {
        int[] destCord = moveTo.getCoordinates();
        boolean direction;
        Field act = moveTo;

        if (destCord[0] == position[0]) {
            if (destCord[1] > position[1]) {
                //hore
                for (int i = 0; i < destCord[1] - position[1]; i++) {
                    if (!act.isEmpty()) return false;
                    act = act.nextField(Field.Direction.D);
                }
                moveTo.put(this);
                this.setPosition(moveTo.getCoordinates());
                act.remove(this);
                return true;
            }
            else {
                for (int i = 0; i < position[1] - destCord[1]; i++) {
                    if (!act.isEmpty()) return false;
                    act = act.nextField(Field.Direction.U);
                }
                moveTo.put(this);
                this.setPosition(moveTo.getCoordinates());
                act.remove(this);
                return true;
            }
        }
        else if (destCord[1] == position[1]) {
            if (destCord[0] > position[0]) {
                //dolava
                for (int i = 0; i < destCord[0] - position[0]; i++) {
                    if (!act.isEmpty()) return false;
                    act = act.nextField(Field.Direction.L);
                }
                moveTo.put(this);
                this.setPosition(moveTo.getCoordinates());
                act.remove(this);
                return true;
            }
            else {
                for (int i = 0; i < position[0] - destCord[0]; i++) {
                    if (!act.isEmpty()) return false;
                    act = act.nextField(Field.Direction.R);
                }
                moveTo.put(this);
                this.setPosition(moveTo.getCoordinates());
                act.remove(this);
                return true;
            }
        }
        else {
            return false;
        }
    }

    public void setPosition(int[] coord) {
        position = coord;
    }

    public boolean equals(Disk obj) {
        return obj.isWhite() == diskColor;
    }

    public int hashCode() {
        return hashCode();
    }
}
