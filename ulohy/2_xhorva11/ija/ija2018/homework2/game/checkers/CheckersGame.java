package ija.ija2018.homework2.game.checkers;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.common.Game;

import java.util.HashMap;
import java.util.Stack;

public class CheckersGame implements Game {
    private Stack<HashMap<String, Object>> undoStack;

    public CheckersGame() {
        undoStack = new Stack<>();
    }

    public boolean move(Figure figure, Field field) {
        int[] destCord = field.getCoordinates();
        int[] startCord = figure.getPosition();

        if ( (startCord[0] + 1 == destCord[0]) && ((startCord[1] + 1) == destCord[1]) && field.isEmpty() ) {
            addMoveToUndo(figure, null, field.nextField(Field.Direction.LD), field);

            field.put(figure);
            figure.setPosition(field.getCoordinates());
            field.nextField(Field.Direction.LD).remove(figure);
            return true;
        }
        else if ( (startCord[0] - 1 == destCord[0]) && ((startCord[1] + 1) == destCord[1]) && field.isEmpty()){
            addMoveToUndo(figure, null, field.nextField(Field.Direction.RD), field);

            field.put(figure);
            figure.setPosition(field.getCoordinates());
            field.nextField(Field.Direction.RD).remove(figure);
            return true;
        }
        else {
            return false;
        }
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
