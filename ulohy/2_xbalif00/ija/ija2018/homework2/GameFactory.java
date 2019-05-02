package ija.ija2018.homework2;

import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.game.Board;
import ija.ija2018.homework2.game.BoardField;
import ija.ija2018.homework2.game.Disk;
import ija.ija2018.homework2.game.Game;

/**
 *
 * @author Bali
 */

public abstract class GameFactory {

    //Constructor
    public GameFactory() {
    }

    public static Game createChessGame(Board board) {

        Disk disk = null;
        // vytvor vsetky policka na hracej doske pre Sach
        for (int i = 1; i <= board.getSize(); i++) {
            for (int j = 1; j <= board.getSize(); j++) {
                BoardField boardField = new BoardField(j,i);
                switch (i) {
                    case 1:
                        if (j==1 || j==8) disk = new Disk(true, Figure.Type.V);
                        break;
                    case 2:
                        disk = new Disk(true, Figure.Type.P);
                        break;
                    case 7:
                        disk = new Disk(false, Figure.Type.P);
                        break;
                    case 8:
                        if (j==1 || j==8) disk = new Disk(false, Figure.Type.V);
                        break;
                }
                boardField.setDisk(disk);
                board.array[j-1][i-1] = boardField;
                disk = null;
            }
        }

        Game game = new Game();
        game.setGameName("Chess");
        game.setBoard(board);
        return game;
    }

    public static Game createCheckersGame(Board board) {
        Disk disk = null;
        // vytvor vsetky policka na hracej doske pre Damu
        for (int i = 1; i <= board.getSize(); i++) {
            for (int j = 1; j <= board.getSize(); j++) {
                BoardField boardField = new BoardField(j,i);
                switch (i) {
                    case 1:
                        if (j%2==1) disk = new Disk(true, Figure.Type.P);
                        break;
                    case 2:
                        if (j%2==0) disk = new Disk(true, Figure.Type.P);
                        break;
//                    case 7:
//                        if (j%2==1) disk = new Disk(false, Figure.Type.P);
//                        break;
//                    case 8:
//                        if (j%2==0) disk = new Disk(false, Figure.Type.P);
//                        break;
                }
                boardField.setDisk(disk);
                board.array[j-1][i-1] = boardField;
                disk = null;
            }
        }

        Game game = new Game();
        game.setGameName("Checkers");
        game.setBoard(board);
        return game;
    }
}
