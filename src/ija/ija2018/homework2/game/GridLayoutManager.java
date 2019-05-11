package ija.ija2018.homework2.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GridLayoutManager extends JFrame {

    private Container contents;

    //Components:
    private JButton[][] squares = new JButton[8][8];
    private JTextField textField = new JTextField();
    private JButton button = new JButton();
    
    //Colors
        private  Color colorBlack = Color.BLACK;  //Black squares
        private  Color color = Color.ORANGE;  //Zlta squares

    //Current position:
    //Upper left corner of board is (0,0)
    private  int row = 6;
    private  int col = 1;

    //Images
    private ImageIcon berolina = new ImageIcon("pieces\\WBerolina.png");

    //biele
    private ImageIcon pesecW = new ImageIcon("pieces\\WBerolina.png");
    private ImageIcon vezW = new ImageIcon("pieces\\WCastle.png");
    private ImageIcon jezdecW = new ImageIcon("pieces\\WHorse.png");
    private ImageIcon strelecW = new ImageIcon("pieces\\WBishop.png");
    private ImageIcon damaW = new ImageIcon("pieces\\WQueen.png");
    private ImageIcon kralW = new ImageIcon("pieces\\WKing.png");
    //cierne
    private ImageIcon pesecB = new ImageIcon("pieces\\BBerolina.png");
    private ImageIcon vezB = new ImageIcon("pieces\\BCastle.png");
    private ImageIcon jezdecB = new ImageIcon("pieces\\BHorse.png");
    private ImageIcon strelecB = new ImageIcon("pieces\\BBishop.png");
    private ImageIcon damaB = new ImageIcon("pieces\\BQueen.png");
    private ImageIcon kralB = new ImageIcon("pieces\\BKing.png");

    public GridLayoutManager () {

//        super("GUI GridLayout Manager - (click a valid squere to move knight)");
        super("xHorvat xBali SACH");

        contents = getContentPane();
        contents.setLayout(new GridLayout(8,8));
//        contents.getWidth();


        //Create event handlers:
        ButtonHandler buttonHandler = new ButtonHandler();

        //Create and add board components
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = new JButton();
//                if ((i+j)%2!=0) squares[i][j].setBackground(colorBlack);
                if ((i+j)%2!=0) squares[i][j].setBackground(color);
                contents.add(squares[i][j]);
                squares[i][j].addActionListener(buttonHandler);
            }
        }

//        squares[row][col].setIcon(berolina);

        //Size and display window
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null); //Centers window
        setVisible(true);
    }

    public void synchronizeGUI (Board board) {

        ImageIcon icon = null;
        for (BoardField[] boardRow : board.array) {
            for (BoardField boardField : boardRow) {
                icon = null;
                if (boardField.getDisk() != null) {

                    switch (boardField.getDisk().getColor()) {
                        case W:
                            switch (boardField.getDisk().getTyp()) {
                                case P:
                                    icon = pesecW;
                                    break;
                                case V:
                                    icon = vezW;
                                    break;
                                case J:
                                    icon = jezdecW;
                                    break;
                                case S:
                                    icon = strelecW;
                                    break;
                                case D:
                                    icon = damaW;
                                    break;
                                case K:
                                    icon = kralW;
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case B:
                            switch (boardField.getDisk().getTyp()) {
                                case P:
                                    icon = pesecB;
                                    break;
                                case V:
                                    icon = vezB;
                                    break;
                                case J:
                                    icon = jezdecB;
                                    break;
                                case S:
                                    icon = strelecB;
                                    break;
                                case D:
                                    icon = damaB;
                                    break;
                                case K:
                                    icon = kralB;
                                    break;
                                default:
                                    break;

                            }
                            break;
                        default:
                            icon = null;
                            break;
                    }
                }

                squares[8-boardField.getRow()][boardField.getCol()-1].setIcon(icon);
            }
        }
    }

    private boolean isValidMove(int i, int j) {

        int rowDelta = Math.abs(i - row);
        int colDelta = Math.abs(j - col);

        if ((rowDelta == 1) && (colDelta == 2)) return true;
        if ((rowDelta == 2) && (colDelta == 1)) return true;
        return false;
    }

    private void processClick(int i, int j) {

        if (isValidMove(i,j) == false) return;
        squares[row][col].setIcon(null);
        squares[i][j].setIcon(berolina);
        row = i;
        col = j;
    }

    private class ButtonHandler implements ActionListener {

      public void actionPerformed(ActionEvent e) {
          Object source = e.getSource();
          for (int i = 0; i < 8; i++) {

              for (int j = 0; j < 8; j++) {
                  if (source == squares[i][j]) {
                      processClick(i,j);
                      return;
                  }
              }
          }
      }
    }

}
