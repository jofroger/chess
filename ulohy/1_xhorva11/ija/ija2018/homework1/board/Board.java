package ija.ija2018.homework1.board;

import ija.ija2018.homework1.board.Field;

import java.util.Dictionary;
import java.util.HashMap;


public class Board {

    private HashMap<String, Field> boardPlan;
    private int boardSize;


    public Board(int size) {
        boardSize = size;
        boardPlan = new HashMap<String, Field>();

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {

                if (i == 1 && j == 1) {
                    Field newField = getConnectedField(i, j);

                    Field newU = getConnectedField(i+1, j);
                    Field newRU = getConnectedField(i+1, j+1);
                    Field newR = getConnectedField(i, j+1);

                    newField.addNextField(Field.Direction.U, newU);
                    newField.addNextField(Field.Direction.RU, newRU);
                    newField.addNextField(Field.Direction.R, newR);
                }
                else if (i == size && j == 1) {
                    Field newField = getConnectedField(i, j);

                    Field newU = getConnectedField(i+1, j);
                    Field newLU = getConnectedField(i+1, j-1);
                    Field newL = getConnectedField(i, j-1);

                    newField.addNextField(Field.Direction.U, newU);
                    newField.addNextField(Field.Direction.LU, newLU);
                    newField.addNextField(Field.Direction.L, newL);
                }
                else if (i == 1 && j == size) {
                    Field newField = getConnectedField(i, j);

                    Field newR = getConnectedField(i, j+1);
                    Field newRD = getConnectedField(i-1, j+1);
                    Field newD = getConnectedField(i-1, j);

                    newField.addNextField(Field.Direction.R, newR);
                    newField.addNextField(Field.Direction.RD, newRD);
                    newField.addNextField(Field.Direction.D, newD);
                }
                else if (i == size && j == size) {
                    Field newField = getConnectedField(i, j);

                    Field newD = getConnectedField(i-1, j);
                    Field newLD = getConnectedField(i-1, j-1);
                    Field newL = getConnectedField(i, j-1);

                    newField.addNextField(Field.Direction.D, newD);
                    newField.addNextField(Field.Direction.LD, newLD);
                    newField.addNextField(Field.Direction.L, newL);
                }
                else if (j > 1 && j < size && i == 1){
                    Field newField = getConnectedField(i, j);

                    Field newL = getConnectedField(i, j-1);
                    Field newLU = getConnectedField(i+1, j-1);
                    Field newU = getConnectedField(i+1, j);
                    Field newRU = getConnectedField(i+1, j+1);
                    Field newR = getConnectedField(i, j+1);

                    newField.addNextField(Field.Direction.L, newL);
                    newField.addNextField(Field.Direction.LU, newLU);
                    newField.addNextField(Field.Direction.U, newU);
                    newField.addNextField(Field.Direction.RU, newRU);
                    newField.addNextField(Field.Direction.R, newR);
                }
                else if (j > 1 && j < size && i == size) {
                    Field newField = getConnectedField(i, j);

                    Field newR = getConnectedField(i, j+1);
                    Field newRD = getConnectedField(i-1, j+1);
                    Field newD = getConnectedField(i-1, j);
                    Field newLD = getConnectedField(i-1, j-1);
                    Field newL = getConnectedField(i, j-1);

                    newField.addNextField(Field.Direction.R, newR);
                    newField.addNextField(Field.Direction.RD, newRD);
                    newField.addNextField(Field.Direction.D, newD);
                    newField.addNextField(Field.Direction.LD, newLD);
                    newField.addNextField(Field.Direction.L, newL);
                }
                else if (i > 1 && i < size && j == 1){
                    Field newField = getConnectedField(i, j);

                    Field newU = getConnectedField(i+1, j);
                    Field newRU = getConnectedField(i+1, j+1);
                    Field newR = getConnectedField(i, j+1);
                    Field newRD = getConnectedField(i-1, j+1);
                    Field newD = getConnectedField(i-1, j);

                    newField.addNextField(Field.Direction.U, newU);
                    newField.addNextField(Field.Direction.RU, newRU);
                    newField.addNextField(Field.Direction.R, newR);
                    newField.addNextField(Field.Direction.RD, newRD);
                    newField.addNextField(Field.Direction.D, newD);
                }
                else if (i > 1 && i < size && j == size) {
                    Field newField = getConnectedField(i, j);

                    Field newD = getConnectedField(i-1, j);
                    Field newLD = getConnectedField(i-1, j-1);
                    Field newL = getConnectedField(i, j-1);
                    Field newLU = getConnectedField(i+1, j-1);
                    Field newU = getConnectedField(i+1, j);

                    newField.addNextField(Field.Direction.D, newD);
                    newField.addNextField(Field.Direction.LD, newLD);
                    newField.addNextField(Field.Direction.L, newL);
                    newField.addNextField(Field.Direction.LU, newLU);
                    newField.addNextField(Field.Direction.U, newU);
                }
                else {
                    Field newField = getConnectedField(i, j);

                    Field newU = getConnectedField(i+1, j);
                    Field newRU = getConnectedField(i+1, j+1);
                    Field newR = getConnectedField(i, j+1);
                    Field newRD = getConnectedField(i-1, j+1);
                    Field newD = getConnectedField(i-1, j);
                    Field newLD = getConnectedField(i-1, j-1);
                    Field newL = getConnectedField(i, j-1);
                    Field newLU = getConnectedField(i+1, j-1);

                    newField.addNextField(Field.Direction.U, newU);
                    newField.addNextField(Field.Direction.RU, newRU);
                    newField.addNextField(Field.Direction.R, newR);
                    newField.addNextField(Field.Direction.RD, newRD);
                    newField.addNextField(Field.Direction.D, newD);
                    newField.addNextField(Field.Direction.LD, newLD);
                    newField.addNextField(Field.Direction.L, newL);
                    newField.addNextField(Field.Direction.LU, newLU);
                }
            }
        }
    }


    private Field getConnectedField(int i, int j) {
        String key = getKey(i, j);

        Field newField = boardPlan.get(key);
        if (newField == null) {
            newField = new BoardField(j, i);
            boardPlan.put(key, newField);
        }
        return newField;
    }

    private String getKey(int i, int j) {
        String row = String.valueOf(i);
        String col = String.valueOf(j);
        return col + row;
    }

    public Field getField(int col, int row) {
        String key = getKey(row, col);
        return boardPlan.get(key);
    }

    public int getSize() {
        return boardSize;
    }
}
