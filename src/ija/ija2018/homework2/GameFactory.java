package ija.ija2018.homework2;

import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.game.*;

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
        // vytvor vsetky policka na hracej doske pre hruSach
        for (int i = 1; i <= board.getSize(); i++) {
            for (int j = 1; j <= board.getSize(); j++) {
                BoardField boardField = new BoardField(j,i);

    //                switch (i) {
    //                    case 1:
    //                        if (j==1 || j==8) disk = new Disk(true, Figure.Type.V);
    //                        break;
    //                    case 2:
    //                        disk = new Disk(true, Figure.Type.P);
    //                        break;
    //                    case 7:
    //                        disk = new Disk(false, Figure.Type.P);
    //                        break;
    //                    case 8:
    //                        if (j==1 || j==8) disk = new Disk(false, Figure.Type.V);
    //                        break;
    //                }
    //                boardField.setDisk(disk);
//                 board.array[j-1][i-1] = boardField;
    //                disk = null;

                switch (i) {
                    case 1:
                        switch (j) {
                            case 1:
                                Vez Va1 = new Vez(true, j, i);
                                disk = Va1;
                                break;
                            case 2:
                                Jezdec Jb1 = new Jezdec(true, j, i);
                                disk = Jb1;
                                break;
                            case 3:
                                Strelec Sc1 = new Strelec(true, j, i);
                                disk = Sc1;
                                break;
                            case 4:
                                Dama Dd1 = new Dama(true, j, i);
                                disk = Dd1;
                                break;
                            case 5:
                                Kral Ke1 = new Kral(true, j, i);
                                disk = Ke1;
                                break;
                            case 6:
                                Strelec Sf1 = new Strelec(true, j, i);
                                disk = Sf1;
                                break;
                            case 7:
                                Jezdec Jg1 = new Jezdec(true, j, i);
                                disk = Jg1;
                                break;
                            case 8:
                                Vez Vh1 = new Vez(true, j, i);
                                disk = Vh1;
                                break;
                        }
                        break;
                    case 2:
                        switch (j) {
                            case 1:
                                Pesec Pa2 = new Pesec(true, j, i);
                                disk = Pa2;
                                break;
                            case 2:
                                Pesec Pb2 = new Pesec(true, j, i);
                                disk = Pb2;
                                break;
                            case 3:
                                Pesec Pc2 = new Pesec(true, j, i);
                                disk = Pc2;
                                break;
                            case 4:
                                Pesec Pd2 = new Pesec(true, j, i);
                                disk = Pd2;
                                break;
                            case 5:
                                Pesec Pe2 = new Pesec(true, j, i);
                                disk = Pe2;
                                break;
                            case 6:
                                Pesec Pf2 = new Pesec(true, j, i);
                                disk = Pf2;
                                break;
                            case 7:
                                Pesec Pg2 = new Pesec(true, j, i);
                                disk = Pg2;
                                break;
                            case 8:
                                Pesec Ph2 = new Pesec(true, j, i);
                                disk = Ph2;
                                break;
                        }
                        break;
                    case 7:
                        switch (j) {
                            case 1:
                                Pesec Pa7 = new Pesec(false, j, i);
                                disk = Pa7;
                                break;
                            case 2:
                                Pesec Pb7 = new Pesec(false, j, i);
                                disk = Pb7;
                                break;
                            case 3:
                                Pesec Pc7 = new Pesec(false, j, i);
                                disk = Pc7;
                                break;
                            case 4:
                                Pesec Pd7 = new Pesec(false, j, i);
                                disk = Pd7;
                                break;
                            case 5:
                                Pesec Pe7 = new Pesec(false, j, i);
                                disk = Pe7;
                                break;
                            case 6:
                                Pesec Pf7 = new Pesec(false, j, i);
                                disk = Pf7;
                                break;
                            case 7:
                                Pesec Pg7 = new Pesec(false, j, i);
                                disk = Pg7;
                                break;
                            case 8:
                                Pesec Ph7 = new Pesec(false, j, i);
                                disk = Ph7;
                                break;
                        }
                        break;
                    case 8:
                        switch (j) {
                            case 1:
                                Vez Va8 = new Vez(false, j, i);
                                disk = Va8;
                                break;
                            case 2:
                                Jezdec Jb8 = new Jezdec(false, j, i);
                                disk = Jb8;
                                break;
                            case 3:
                                Strelec Sc8 = new Strelec(false, j, i);
                                disk = Sc8;
                                break;
                            case 4:
                                Dama Dd8 = new Dama(false, j, i);
                                disk = Dd8;
                                break;
                            case 5:
                                Kral Ke8 = new Kral(false, j, i);
                                disk = Ke8;
                                break;
                            case 6:
                                Strelec Sf8 = new Strelec(false, j, i);
                                disk = Sf8;
                                break;
                            case 7:
                                Jezdec Jg8 = new Jezdec(false, j, i);
                                disk = Jg8;
                                break;
                            case 8:
                                Vez Vh8 = new Vez(false, j, i);
                                disk = Vh8;
                                break;
                        }
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
