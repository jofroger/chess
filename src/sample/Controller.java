package sample;

import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;
import ija.ija2018.homework2.game.Board;
import ija.ija2018.homework2.game.BoardField;
import ija.ija2018.homework2.game.Disk;
import ija.ija2018.homework2.game.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ija.ija2018.homework2.GameFactory.createChessGame;
import static ija.ija2018.homework2.common.Figure.*;
import static java.lang.Thread.sleep;


public class Controller {

    // todo definovat triedu samotnej hry
    // bude musiet byt vytvoreny konstruktor v src
    // -- s parametrom suboru
    // -- s bez parametra ... cista hra
    // pri nacitani sa overi format zapisu a prevedie do zlozitej podoby
    Game chessGame; // na inicializovanie sa musi hra najprv nacitat

    private int actualMove = -1; // 0 je vzdy inicializacia dosky
    private List<Button> fieldList = new ArrayList<>();;
    private boolean paused = false;
    private List<String> moveListColors = new ArrayList<String>();

    // fxml elements
    @FXML private ListView<String> moveList;
    @FXML private GridPane board ;
    @FXML private Text warningBar ;
    @FXML private RadioButton playAutomaticaly ;
    @FXML private RadioButton playManualy ;
    @FXML private Button pauseOrPlay;



    // figure pictures
    private String figurePath = "/figuresimg/";

    private Image w_kral = new Image(getClass().getResourceAsStream(figurePath + "w_kral.png"));
    private Image w_dama = new Image(getClass().getResourceAsStream(figurePath +"w_dama.png"));
    private Image w_strelec = new Image(getClass().getResourceAsStream(figurePath +"w_strelec.png"));
    private Image w_kon = new Image(getClass().getResourceAsStream(figurePath +"w_kon.png"));
    private Image w_veza = new Image(getClass().getResourceAsStream(figurePath +"w_veza.png"));
    private Image w_pesiak = new Image(getClass().getResourceAsStream(figurePath +"w_pesiak.png"));

    private Image b_kral = new Image(getClass().getResourceAsStream(figurePath +"b_kral.png"));
    private Image b_dama = new Image(getClass().getResourceAsStream(figurePath +"b_dama.png"));
    private Image b_strelec = new Image(getClass().getResourceAsStream(figurePath +"b_strelec.png"));
    private Image b_kon = new Image(getClass().getResourceAsStream(figurePath +"b_kon.png"));
    private Image b_veza = new Image(getClass().getResourceAsStream(figurePath +"b_veza.png"));
    private Image b_pesiak = new Image(getClass().getResourceAsStream(figurePath +"b_pesiak.png"));

    // icon pictures
    private String iconPath = "/icons/";

    private Image playIcon = new Image(getClass().getResourceAsStream(iconPath + "play.png"));
    private Image pauseIcon = new Image(getClass().getResourceAsStream(iconPath +"pause.png"));




    private List<String> divByColor(String move) {
        String[] splitStr = move.split("\\s+");
        return Arrays.asList(splitStr[1], splitStr[2]);      // chceme to bez poradovaho cisla
    }

    @FXML protected void loadGame(ActionEvent event) {

        // todo metoda na nacitanie dat do ObservableList<String>
        /* priklad */
        ObservableList<String> moves = FXCollections.observableArrayList("1. h2h4 e7e6", "2. e2e4 e6e5", "3. Vh1h3 d7d6 ", "4. Sf1c4 Jb8c6");
        for (String move : moves) {
            moveList.getItems().add(move);
            List<String> list = divByColor(move);
            moveListColors.addAll(divByColor(move));
        }
        moveList.getSelectionModel().select(actualMove);

        // rozmiestnenie figurok
        chessGame = createChessGame(new Board(8));
        updateBoard();
    }


    protected int charToIntRow(char row) {
        switch (row) {
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            case 'f':
                return 6;
            case 'g':
                return 7;
            case 'h':
                return 8;
            default:
                warningBar.setText("ERROR Move: wrong format");
                return -1;
        }
    }

    protected Pair<Disk, BoardField> getFigureAndFieldFromMove(String move, boolean isWhite) {
        Type figureType;
        int srcCol, srcRow, destCol, destRow;
        // todo osetrit este dalsie vlastnosti ako sach, mat ...

        if (move.length() == 4) {
            figureType = Type.P;
            srcCol = charToIntRow(move.charAt(0));
            srcRow = Character.getNumericValue(move.charAt(1));
            destCol = charToIntRow(move.charAt(2));
            destRow = Character.getNumericValue(move.charAt(3));
        }
        else {
            switch (move.charAt(0)) {
                case 'K':
                    figureType = Type.K;
                    break;
                case 'D':
                    figureType = Type.D;
                    break;
                case 'V':
                    figureType = Type.V;
                    break;
                case 'S':
                    figureType = Type.S;
                    break;
                case 'J':
                    figureType = Type.J;
                    break;
                default:    // nikdy sa nevyuzile, len pre spravnu kompilaciu
                    figureType = Type.P;
                    break;
            }

            srcCol = charToIntRow(move.charAt(1));
            srcRow = Character.getNumericValue(move.charAt(2));
            destCol = charToIntRow(move.charAt(3));
            destRow = Character.getNumericValue(move.charAt(4));
        }


        Disk figureToMove = chessGame.getBoard().getField(srcCol, srcRow).get();
        if (figureToMove != null) {
            // kontrola ci figurka ma spravny typ a je biela
            if (figureToMove.isWhite() != isWhite) {
                warningBar.setText("ERR MoveList: Wrong color of moving figure");
                return null;
            }
            if (figureToMove.getTyp() != figureType) {
                warningBar.setText("ERR MoveList: Wrong type of moveing figure");
                return null;
            }
        }

        BoardField destField = chessGame.getBoard().getField(destCol, destRow);     //todo cisla poli musia byt skontrolovane pri nacitani

        return new Pair<Disk, BoardField>(figureToMove, destField);
    }


    @FXML protected void nextMove(ActionEvent event) {
        actualMove++;
        String rawNextMove = moveList.getItems().get(actualMove / 2);   // moveList obsahuje cierneho a bieleho tahy spolocne

        Pair<Disk, BoardField> figureAndField = getFigureAndFieldFromMove(moveListColors.get(actualMove), actualMove % 2 == 0); // kazdy parny tah ide biely

        if (chessGame.move(figureAndField.getKey(), figureAndField.getValue())) {   // key = Disk, value = Boardfield
            moveList.getSelectionModel().select(actualMove/2);
            updateBoard();
        }
        else {
            warningBar.setText("Non-valid move");
            actualMove--;
            // todo zablokovat tlacitka???
        }
    }

    @FXML protected void previousMove(ActionEvent event) {
        if (actualMove > 0) {
            chessGame.undo();
            actualMove--;
            moveList.getSelectionModel().select(actualMove/2);
            updateBoard();
        }
    }

    // change move by clicking to item in listview
    @FXML public void handleMouseClick(MouseEvent arg0) {
        actualMove = moveList.getSelectionModel().getSelectedIndex();
        actualMove--;
        nextMove(null);
    }

    @FXML protected void restartGame(ActionEvent event) {
        actualMove = 0;
        moveList.getSelectionModel().select(actualMove);

        // for () game.undo(); alebo inak zavisi od funkcie
        updateBoard();
    }

    @FXML protected void playGameAutomatically(ActionEvent event) throws InterruptedException {
        //while (true) {
            for (int i = 0; i < 4; i++) {
                if (!paused) {
                    nextMove(null);
                    sleep(2000);
                }
            }
        //}
    }



    protected int getInvertedRow(int row) {
        switch (row) {
            case 1:
                return 8;
            case 2:
                return 7;
            case 3:
                return 6;
            case 4:
                return 5;
            case 5:
                return 4;
            case 6:
                return 3;
            case 7:
                return 2;
            case 8:
                return 1;
            default:
                return -1;
        }
    }

    protected void updateField(Button button, Disk figure, Image wFigureImg, Image bFigureImg) {
        if (figure.isWhite()) button.setGraphic(new ImageView(wFigureImg));
        else button.setGraphic(new ImageView(bFigureImg));
    }

    protected void updateBoard() {
        Board board = chessGame.getBoard();
        int butIdx = 0;
        int boardSize = board.getSize();

        for (int row = 1; row <= boardSize; row++) {
            for (int col = 1; col <= boardSize; col++) {
                Button actButton = fieldList.get(butIdx);
                Disk actFigure = board.getField(col, getInvertedRow(row)).get();

                if (actFigure != null) {
                    switch (actFigure.getTyp()) {
                        case K:
                            updateField(actButton, actFigure, w_kral, b_kral);
                            break;
                        case D:
                            updateField(actButton, actFigure, w_dama, b_dama);
                            break;
                        case V:
                            updateField(actButton, actFigure, w_veza, b_veza);
                            break;
                        case J:
                            updateField(actButton, actFigure, w_kon, b_kon);
                            break;
                        case S:
                            updateField(actButton, actFigure, w_strelec, b_strelec);
                            break;
                        case P:
                            updateField(actButton, actFigure, w_pesiak, b_pesiak);
                            break;
                    }
                }
                else actButton.setGraphic(null);

                butIdx++;
            }
        }
    }

    @FXML protected void setPauseOrPlay(ActionEvent event) {
        if (paused) pauseOrPlay.setGraphic(new ImageView(pauseIcon));
        else  pauseOrPlay.setGraphic(new ImageView(playIcon));

        paused = !paused;   // obrati hodnotu
    }
/*
    @FXML protected void addNewTab(ActionEvent event) {

        TabPane tabPane = new TabPane();
        Tab tab = new Tab();
        tab.setText("new tab");
        tab.setContent(new Rectangle(200, 200, Color.LIGHTSTEELBLUE));
        tabPane.getTabs().add(tab);
    }
*/

        public void initialize() {
            chessGame = createChessGame(new Board(8));  // create normal chess game

            // only automatic or manual can be selected at once
            ToggleGroup playGroup = new ToggleGroup();
            playAutomaticaly.setToggleGroup(playGroup);
            playManualy.setToggleGroup(playGroup);

            playManualy.setSelected(true);  // turn on by default

            // set to paused
            setPauseOrPlay(null);


            // init board buttons
            for (int x = 0; x < 8; x++) {
                for (int y = 0; y < 8; y++) {
                    Button button = new Button();
                    GridPane.setConstraints(button, y, x);  // y = column, x = row

                    // set color
                    Color fieldColor = Color.web("#00000000");
                    if ((x + y) % 2 == 0 ) fieldColor = Color.web("#FFFFFF");
                    button.setBackground(new Background(new BackgroundFill(fieldColor, CornerRadii.EMPTY, Insets.EMPTY)));
                    button.setMinWidth(100);
                    button.setMinHeight(100);

                    fieldList.add(button);
                    board.getChildren().add(button);

                    //ColumnConstraints column1 = new ColumnConstraints();
                    //column1.setPercentWidth(10);
                    //board.getColumnConstraints().addAll(column1);
                }
            }
            updateBoard();

            /*
            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(70);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(30);
            game.getColumnConstraints().addAll(column1, column2); // each get 50% of width*/
        }






}
