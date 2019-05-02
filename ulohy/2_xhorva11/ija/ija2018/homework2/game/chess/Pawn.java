package ija.ija2018.homework2.game.chess;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;


public class Pawn implements Figure {

    private boolean diskColor;       // true = white, false = black
    private int[] position;


    public Pawn(boolean isWhite) {
        diskColor = isWhite;
    }

    public boolean isWhite() {
        return diskColor;
    }

    public String getState() {
            String figure = "P";    // pesiak
            String figureColor = diskColor ? "[W]" : "[B]";
            String position = this.position[0] + ":" + this.position[1];

            return figure + figureColor + position;
        }

        public void setPosition(int[] coord) {
            position = coord;
        }

        public int[] getPosition() {
            return this.position;
    }

    public String getType() {
        return "pawn";
    }

    public boolean equals(Pawn obj) {
        return obj.isWhite() == diskColor;
    }

    public int hashCode() {
        return hashCode();
    }
}
