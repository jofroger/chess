package ija.ija2018.homework2;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Game;
import ija.ija2018.homework2.game.Board;
import ija.ija2018.homework2.game.checkers.CheckersGame;
import ija.ija2018.homework2.game.checkers.Disk;
import ija.ija2018.homework2.game.chess.ChessGame;
import ija.ija2018.homework2.game.chess.Pawn;
import ija.ija2018.homework2.game.chess.Tower;


public abstract class GameFactory {
    public static Game createChessGame(Board board) {
        Field actField;

        // umiestnenie bielych figurok
        // veze
        actField = board.getField(1, 1);
        Tower newWLeftTover = new Tower(true);
        newWLeftTover.setPosition(new int[] {1, 1});
        actField.put(newWLeftTover);

        actField = board.getField(8, 1);
        Tower newWRightTover = new Tower(true);
        newWRightTover.setPosition(new int[] {8, 1});
        actField.put(newWRightTover);

        // pesiaci
        for (int coll = 1; coll <= 8; coll++) {
            Field act = board.getField(coll, 2);
            Pawn newPawn = new Pawn(true);
            newPawn.setPosition(new int[] {coll, 2});
            act.put(newPawn);
        }

        // umiestnenie ciernych figurok
        // veze
        actField = board.getField(1, 8);
        Tower newBLeftTover = new Tower(false);
        newBLeftTover.setPosition(new int[] {1, 8});
        actField.put(newBLeftTover);

        actField = board.getField(8, 8);
        Tower newBRightTover = new Tower(false);
        newBRightTover.setPosition(new int[] {8, 8});
        actField.put(newBRightTover);

        // pesiaci
        for (int coll = 1; coll <= 8; coll++) {
            Field act = board.getField(coll, 7);
            Pawn newPawn = new Pawn(false);
            newPawn.setPosition(new int[] {coll, 7});
            act.put(newPawn);
        }

        return new ChessGame();
    }

    public static Game createCheckersGame(Board board) {
        // umiestnenie LEN bielych dam
        for (int coll = 1; coll <= 8; coll+=2) {
            Field act = board.getField(coll, 1);
            Disk newDisk = new Disk(true);
            newDisk.setPosition(new int[] {coll, 1});
            act.put(newDisk);
        }

        for (int coll = 2; coll <= 8; coll+=2) {
            Field act = board.getField(coll, 2);
            Disk newDisk = new Disk(true);
            newDisk.setPosition(new int[] {coll, 2});
            act.put(newDisk);
        }

        return new CheckersGame();
    }
}
