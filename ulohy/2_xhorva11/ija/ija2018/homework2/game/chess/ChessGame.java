package ija.ija2018.homework2.game.chess;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;

import java.util.HashMap;
import java.util.Stack;

public class ChessGame implements ija.ija2018.homework2.common.Game {

    private Stack< HashMap<String, Object> > undoStack;

    public ChessGame() {
        undoStack = new Stack<>();
    }

    public boolean move(Figure figure, Field field) {
        if (figure.getType().equals("pawn")) return this.movePawn(figure, field);
        if (figure.getType().equals("tower")) return this.moveTower(figure, field);
        else return false;
    }

    private boolean movePawn(Figure figure, Field field) {
        int[] destCord = field.getCoordinates();
        int[] startCord = figure.getPosition();
        boolean pawnColor = figure.isWhite();

        if (pawnColor) {
            if ( (startCord[0] == destCord[0]) && ((startCord[1] + 1) == destCord[1]) && field.isEmpty() ) {
                addMoveToUndo(figure, null, field.nextField(Field.Direction.D), field);

                field.put(figure);
                figure.setPosition(field.getCoordinates());
                field.nextField(Field.Direction.D).remove(figure);
                return true;
            }
        }
        else {
            if ( (startCord[0] == destCord[0]) && ((startCord[1] - 1) == destCord[1]) && field.isEmpty() ) {
                addMoveToUndo(figure, null, field.nextField(Field.Direction.U), field);

                field.put(figure);
                figure.setPosition(field.getCoordinates());
                field.nextField(Field.Direction.U).remove(figure);
                return true;
            }
        }

        return false;
    }

    private boolean moveTower(Figure figure, Field field) {
        int[] destCord = field.getCoordinates();
        int [] startCord = figure.getPosition();
        boolean direction;
        Field act = field;

        if (destCord[0] == startCord[0]) {
            if (destCord[1] > startCord[1]) {
                //hore
                for (int i = 0; i < destCord[1] - startCord[1]; i++) {
                    if (!act.isEmpty() && i != 0) return false;
                    act = act.nextField(Field.Direction.D);
                }
                return placeTowerToNewPosition(figure, field, act);
            }
            else {
                for (int i = 0; i < startCord[1] - destCord[1]; i++) {
                    if (!act.isEmpty() && i != 0) return false;
                    act = act.nextField(Field.Direction.U);
                }

                return placeTowerToNewPosition(figure, field, act);
            }
        }
        else if (destCord[1] == startCord[1]) {
            if (destCord[0] > startCord[0]) {
                //dolava
                for (int i = 0; i < destCord[0] - startCord[0]; i++) {
                    if (!act.isEmpty() && i != 0) return false;
                    act = act.nextField(Field.Direction.L);
                }

                return placeTowerToNewPosition(figure, field, act);
            }
            else {
                for (int i = 0; i < startCord[0] - destCord[0]; i++) {
                    if (!act.isEmpty() && i != 0) return false;
                    act = act.nextField(Field.Direction.R);
                }

                return placeTowerToNewPosition(figure, field, act);
            }
        }
        else {
            return false;
        }
    }

    private boolean placeTowerToNewPosition(Figure figure, Field field, Field act) {
        if (field.isEmpty()) {
            addMoveToUndo(figure, null, act, field);

            field.put(figure);
            figure.setPosition(field.getCoordinates());
            act.remove(figure);
            return true;
        }
        else return this.takeFigure(figure, field, act);
    }

    private boolean takeFigure(Figure figure, Field fieldWithRmFigure, Field fieldWithAttackingFig) {
        Figure figureForRm = fieldWithRmFigure.get();

        if (figureForRm.isWhite() != figure.isWhite()) {
            addMoveToUndo(figure, figureForRm, fieldWithAttackingFig, fieldWithRmFigure);

            fieldWithRmFigure.remove(figureForRm);           // odobranie figurky z aktualneho pola

            fieldWithRmFigure.put(figure);                   // nahradenie figurkou, ktora vybija
            figure.setPosition(fieldWithRmFigure.getCoordinates());
            fieldWithAttackingFig.remove(figure);
            return true;
        }

        return false;
    }

    private void addMoveToUndo(Figure movedFig, Figure takenFig, Field start, Field dest) {
        HashMap<String, Object> undoMove = new HashMap<>();
        undoMove.put("movedFig", movedFig);
        undoMove.put("takenFig", takenFig);
        undoMove.put("start", start);
        undoMove.put("dest", dest);

        this.undoStack.push(undoMove);
    }

    public void undo() {
        HashMap<String, Object> undoMove = this.undoStack.pop();
        Figure movedFig = (Figure) undoMove.get("movedFig");
        Figure takenFig = (Figure) undoMove.get("takenFig");
        Field start = (Field) undoMove.get("start");
        Field dest = (Field) undoMove.get("dest");

        movedFig.setPosition(start.getCoordinates());
        Figure oldFigS = start.get();
        if (oldFigS != null) start.remove(oldFigS);
        start.put(movedFig);

        if (takenFig != null) {
            Figure oldFigD = dest.get();
            if (oldFigD != null) dest.remove(oldFigD);
            dest.put(takenFig);
        }
    }
}
