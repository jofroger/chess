package ija.ija2018.homework2.game;

import ija.ija2018.homework2.GameFactory;
import ija.ija2018.homework2.common.Game;

import javax.swing.*;

public class Sach {


    public static void main(String[] args) {

        //GUI Swing + Awt
        GridLayoutManager gui = new GridLayoutManager();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        gui.setSize(700,700);

        //Hra sach
        Board board = new Board(8);
        Game game = GameFactory.createChessGame(board);


        gui.synchronizeGUI(board);

        Integer cisloChyby = 0;
        cisloChyby = game.oneStep("b2b4 Jg8f6");
        if (cisloChyby != 0) System.out.println(GameError.ERR1);
        gui.synchronizeGUI(board);

        cisloChyby = game.oneStep("a2a4 e7e5");
        gui.synchronizeGUI(board);
        cisloChyby = game.oneStep("Va1a3 Ke8e7");
        gui.synchronizeGUI(board);
        cisloChyby = game.oneStep("Va3e3 Jf6g4");
        gui.synchronizeGUI(board);
        cisloChyby = game.oneStep("h2h3 b7b6");
        gui.synchronizeGUI(board);
        cisloChyby = game.oneStep("f2f4 h7h5");
        gui.synchronizeGUI(board);
        cisloChyby = game.oneStep("Ve3e5 Ke7d6");
        gui.synchronizeGUI(board);

        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);
        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);

        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);
        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);

        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);
        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);

        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);
        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);

        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);
        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);

        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);
        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);

        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);
        // jeden krok zpet
        game.undo();
        gui.synchronizeGUI(board);







        //Priklady tahov
        //W TAH1 Pesec Pb2 z b2 na b4
        game.move(board.getField(2, 2).get(), board.getField(2, 4));
        gui.synchronizeGUI(board);

        //B TAH2 Jazdec Jg8 z g8 na f6
        game.move(board.getField(7, 8).get(), board.getField(6, 6));
        gui.synchronizeGUI(board);

        //W TAH3 Pesec Pa2 z a2 na a4
        game.move(board.getField(1, 2).get(), board.getField(1, 4));
        gui.synchronizeGUI(board);

        //B TAH4 Pesec Pe7 z e7 na e5
        game.move(board.getField(5, 7).get(), board.getField(5, 5));
        gui.synchronizeGUI(board);

        //W TAH5 Veza Va1 z a1 na a3
        game.move(board.getField(1, 1).get(), board.getField(1, 3));
        gui.synchronizeGUI(board);

        //W TAH6 Veza Va1 z a1 na a3    PRAZDNE POLE
    if (!game.move(board.getField(1, 1).get(), board.getField(1, 3))) System.out.printf("TAH6 Chyba Pole 1,1 je uz prazdne");
        gui.synchronizeGUI(board);

        //B TAH6+ Kral Ke8 z e8 na e7
        game.move(board.getField(5, 8).get(), board.getField(5, 7));
        gui.synchronizeGUI(board);

        //W TAH7 Jazdec Jg8 z f6 na g4
        game.move(board.getField(6, 6).get(), board.getField(7, 4));
        gui.synchronizeGUI(board);

        //B TAH8 Pesec Pb7 z b7 na b6
        game.move(board.getField(2, 7).get(), board.getField(2, 6));
        gui.synchronizeGUI(board);


        //W TAH9 Veza Va1 z a3 na e3
        game.move(board.getField(1, 3).get(), board.getField(5, 3));
        gui.synchronizeGUI(board);

        //B TAH10 Pesec Ph7 z h7 na h5
        game.move(board.getField(8, 7).get(), board.getField(8, 5));
        gui.synchronizeGUI(board);


        //W TAH11 Veza Va1 z e3 na e5 ZOBER pesca a SACH
        game.move(board.getField(5, 3).get(), board.getField(5, 5));
        gui.synchronizeGUI(board);

        //B TAH12 Kral Ke8 z e7 na d6
        game.move(board.getField(5, 7).get(), board.getField(4, 6));
        gui.synchronizeGUI(board);



        // OK synchronizovat pole JButton[][] squares = new JButton[8][8] a BoardField[][] array  asi po kazdom platnom tahu

        //todo GUI bude dodavat 1, aktualnu poziciu = ktorou figurkou chcem tahat   Napr. prvy klik -> actionPerformed(ActionEvent e) -> processClick(int i, int j)
        //                      2, poziciu kde chcem tahat                          Napr. druhy klik -> actionPerformed(ActionEvent e) -> processClick(int i, int j)
        //

        //todo asi GUI (alebo trieda spolupracujuca s GUI) by malo zabezpecit
        //             striedanie tahov biely hrac, cierny hrac, ...
        //             kde ulozit vyhodene figurky
        //             (vsetky vyhodene figurky by sa mali dat vydy zistit nacitanim vsetkych naplnenych atributov BoardField.takeDisk)
        //

    }

}
