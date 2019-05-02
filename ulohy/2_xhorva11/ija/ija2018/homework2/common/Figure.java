package ija.ija2018.homework2.common;

public interface Figure {
    boolean isWhite();
    void setPosition(int[] coord);
    int[] getPosition();
    String getState();
    String getType();
}
