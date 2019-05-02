package ija.ija2018.homework2.game;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;


import java.util.Arrays;


public class BoardField implements Field {

    private Field up;
    private Field rightUp;
    private Field right;
    private Field rightDown;
    private Field down;
    private Field leftDown;
    private Field left;
    private Field leftUp;

    private int[] coordinates;
    private Figure diskP;


    public BoardField(int col, int row) {
        coordinates = new int[2];
        coordinates[0] = col;
        coordinates[1] = row;

        down = null;
        left = null;
        leftDown = null;
        leftUp = null;
        right = null;
        rightDown = null;
        rightUp = null;
        up = null;
        diskP = null;
    }

    public void addNextField(Field.Direction dirs, Field field) {
        switch (dirs) {
            case D:
                down = field;
                break;
            case L:
                left = field;
                break;
            case LD:
                leftDown = field;
                break;
            case LU:
                leftUp = field;
                break;
            case R:
                right = field;
                break;
            case RD:
                rightDown = field;
                break;
            case RU:
                rightUp = field;
                break;
            case U:
                up = field;
                break;
            default:
                break;
        }
    }

    public Field nextField(Field.Direction dirs) {
        switch (dirs) {
            case D:
                return down;
            case L:
                return left;
            case LD:
                return leftDown;
            case LU:
                return leftUp;
            case R:
                return right;
            case RD:
                return rightDown;
            case RU:
                return rightUp;
            case U:
                return up;
            default:
                return null;
        }
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public boolean equals(BoardField obj) {
        int[] otherCord = obj.getCoordinates();
        if (Arrays.equals(otherCord, coordinates)) return true;

        return false;
    }

    public int hashCode() {
        return hashCode();
    }

    public boolean put(Figure disk) {
        if (diskP == null) {
            diskP = disk;
            disk.setPosition(coordinates);
            return true;
        }
        return false;
    }

    public boolean remove(Figure disk) {
        if (diskP.equals(disk) && diskP != null) {
            diskP = null;
            return true;
        }
        return false;
    }

    public Figure get() {
        if (diskP != null) {
            return diskP;
        }
        return null;
    }

    public boolean isEmpty() {
        return diskP == null;
    }
}
