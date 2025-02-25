package sample;

import ija.ija2018.homework2.game.Board;
import ija.ija2018.homework2.game.BoardField;
import ija.ija2018.homework2.game.Disk;
import ija.ija2018.homework2.game.Game;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ija.ija2018.homework2.GameFactory.createChessGame;
import static ija.ija2018.homework2.common.Figure.*;


public class Controller {
    private Game chessGame; // na inicializovanie sa musi hra najprv nacitat

    private int actualMove = -1; // 0 je vzdy inicializacia dosky
    private List<Button> fieldList = new ArrayList<>();
    private boolean paused = false;
    private List<String> moveListColors = new ArrayList<>();
    private int tabCnt = 2;

    // fxml elements
    @FXML private ListView<String> moveList;
    @FXML private GridPane board ;
    @FXML private Text warningBar ;
    @FXML private RadioButton playAutomaticaly ;
    @FXML private RadioButton playManualy ;
    @FXML private Button pauseOrPlay;
    @FXML private TextField delay;
    @FXML private TabPane root;
    @FXML private TextField showPosition;


    // figure pictures
    private String figurePath = "/img/figuresimg/";

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
    private String iconPath = "/img/icons/";

    private Image playIcon = new Image(getClass().getResourceAsStream(iconPath + "play.png"));
    private Image pauseIcon = new Image(getClass().getResourceAsStream(iconPath +"pause.png"));



    private List<String> divByColor(String move) {
        String[] splitStr = move.split("\\s+");
        return Arrays.asList(splitStr[1], splitStr[2]);      // chceme to bez poradovaho cisla
    }

    /**
     * Nacitava hru zo suboru
     * @throws IOException
     */
    @FXML protected void loadGame() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (selectedFile != null) {
            moveList.getItems().clear();
            moveListColors.clear();

            BufferedReader in;
            in = new BufferedReader(new FileReader(selectedFile));
            String line = in.readLine();
            while (line != null) {
                moveList.getItems().add(line);
                moveListColors.addAll(divByColor(line));
                line = in.readLine();
            }

            moveList.getSelectionModel().select(actualMove);

            // rozmiestnenie figurok
            chessGame = createChessGame(new Board(8));
            updateBoard();
        }
    }

    /**
     *  Uklada hru do suboru
     * @throws IOException
     */
    @FXML protected void saveGame() throws IOException {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showSaveDialog(root.getScene().getWindow());

        if (selectedFile != null) {
            PrintWriter writer;
            writer = new PrintWriter(selectedFile);
            for (String line : moveList.getItems()) {
                writer.println(line);
            }
            writer.close();
        }
    }

    private int charToIntRow(char row) {
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

    private char intToCharRow(int row) {
        switch (row) {
            case 1:
                return 'a';
            case 2:
                return 'b';
            case 3:
                return 'c';
            case 4:
                return 'd';
            case 5:
                return 'e';
            case 6:
                return 'f';
            case 7:
                return 'g';
            case 8:
                return 'h';
            default:
                warningBar.setText("ERROR Move: wrong format");
                return 'X';
        }
    }

    /**
     *
     * @param move
     * @param isWhite
     * @return
     */
    private Pair<Disk, BoardField> getFigureAndFieldFromMove(String move, boolean isWhite) {
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

        return new Pair<>(figureToMove, destField);
    }

    /**
     * Vykona dalsi tah
     */
    @FXML protected void nextMove() {
        if (actualMove < moveListColors.size() - 1) {   // pocitanie sa zacina od -1
            actualMove++;

            Pair<Disk, BoardField> figureAndField = getFigureAndFieldFromMove(moveListColors.get(actualMove), actualMove % 2 == 0); // kazdy parny tah ide biely

            if (chessGame.move(figureAndField.getKey(), figureAndField.getValue())) {   // key = Disk, value = Boardfield
                moveList.getSelectionModel().select(actualMove / 2);
                updateBoard();
            } else {
                warningBar.setText("Non-valid move");
                actualMove--;
                // todo zablokovat tlacitka???
            }
        }
    }

    /**
     * Vrati posledny tah
     */
    @FXML protected void previousMove() {
        if (actualMove >= 0) {
            actualMove--;
            chessGame.undo();
            moveList.getSelectionModel().select(actualMove/2);
            updateBoard();
        }
    }

    /**
     * Premiestni sa na vybrany tah
     */
    @FXML public void handleMouseClick() {
        int destMove = moveList.getSelectionModel().getSelectedIndex() * 2;

        if (actualMove < destMove) {
            while (actualMove < destMove) nextMove();      // aktualizacia prebieha v nextMove a previousMove
        } else {
            while (actualMove > destMove) previousMove();
        }
    }


    /**
     * Restartuje hru
     */
    @FXML protected void restartGame() {
        while (actualMove >= 0) previousMove();
    }

    private void play() {
        Task task = new Task<Void>() {
            @Override
            public Void call() throws InterruptedException {
                while (actualMove < moveListColors.size()) {

                    EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent e) {
                            setPauseOrPlay();
                        }
                    };

                    if (!paused) {
                        Platform.runLater(() -> nextMove());
                        Thread.sleep(Long.valueOf(delay.getText()));
                    }
                }
                return null;
            }
        };
        new Thread(task).start();
    }

    /**
     * Zacne automaticky prehravat hru
     */
    @FXML protected void playGameAutomatically() {
        if (paused) setPauseOrPlay();
        play();
    }


    private int getInvertedRow(int row) {
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

    private void updateField(Button button, Disk figure, Image wFigureImg, Image bFigureImg) {
        if (figure.isWhite()) button.setGraphic(new ImageView(wFigureImg));
        else button.setGraphic(new ImageView(bFigureImg));
    }

    private void updateBoard() {
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

    @FXML protected void redo() {
        redo();
    }


    /**
     * Stopne alebo spusti automaticke prehravanie hry
     */
    @FXML protected void setPauseOrPlay() {
        if (paused) pauseOrPlay.setGraphic(new ImageView(pauseIcon));
        else  pauseOrPlay.setGraphic(new ImageView(playIcon));

        paused = !paused;   // obrati hodnotu
    }

    /**
     * Prida novy tab
     * @throws IOException
     */
    @FXML protected void addNewTab() throws IOException {
        TabPane pane = FXMLLoader.load(this.getClass().getResource("gui.fxml"));

        Tab newTab = new Tab("Game " + tabCnt);
        newTab.setContent(pane.getTabs().get(0).getContent());
        root.getTabs().addAll(newTab);

        tabCnt++;
    }

    private boolean canDoMove = false;
    private int srcCol;
    private int srcRow;
    private char srcColChar;
    private Button selectedField;

    private void playerMove(Button field) {
        if (playAutomaticaly.isSelected() && paused || playManualy.isSelected()) {
            String coord = field.getAccessibleText();

            if (canDoMove) {
                int destCol = Character.getNumericValue(coord.charAt(0));
                int destRow = getInvertedRow(Character.getNumericValue(coord.charAt(1)));
                char dstColChar = intToCharRow(destCol);

                Disk actFigure = chessGame.getBoard().getField(srcCol, srcRow).get();
                if (actFigure != null) {
                    actualMove++;
                    BoardField destField = chessGame.getBoard().getField(destCol, destRow);

                    String moveNonation = "" + (actFigure.getTyp() == Type.P ? "" : actFigure.getTyp()) + srcColChar + srcRow + dstColChar + destRow;
                    String simpleMoveNotation = moveNonation;
                    if (actFigure.isWhite()) moveNonation = "" + ((actualMove / 2) + 1) + ". " + moveNonation + " ";

                    if (chessGame.move(actFigure, destField)) {
                        warningBar.setText(""+chessGame.getSach());
                        if (actFigure.isWhite()) {
                            moveList.getItems().remove(actualMove/2, moveList.getItems().size());
                            moveList.getItems().add(actualMove / 2, moveNonation);
                        }
                        else {
                            String newRecord;

                            String record = moveList.getItems().get(actualMove / 2);
                            moveList.getItems().remove(actualMove/2, moveList.getItems().size());

                            String[] splitRec = record.split("\\s+");
                            if (splitRec.length == 3) {
                                newRecord = splitRec[0] + " " + splitRec[1] + " " + moveNonation;
                            }
                            else {
                                newRecord = record + moveNonation;
                            }
                            moveList.getItems().add(actualMove / 2, newRecord);
                        }
                        moveListColors.subList(actualMove, moveListColors.size()).clear();
                        moveListColors.add(simpleMoveNotation);

                        moveList.getSelectionModel().select(actualMove / 2);
                        updateBoard();
                        canDoMove = !canDoMove;
                        showPosition.setText("");
                    } else {
                        warningBar.setText("Non-valid move");
                        warningBar.setText(""+chessGame.getSach());

                        actualMove--;
                    }
                }
            } else {
                srcCol = Character.getNumericValue(coord.charAt(0));
                srcRow = getInvertedRow(Character.getNumericValue(coord.charAt(1)));
                srcColChar = intToCharRow(srcCol);

                Disk figureForCheck = chessGame.getBoard().getField(srcCol, srcRow).get();
                if (figureForCheck != null) {
                    if ((((actualMove + 1) % 2) == 0) && !figureForCheck.isWhite()) {
                        warningBar.setText("It is White's turn");
                        return;
                    }
                    if ((((actualMove + 1) % 2) == 1) && figureForCheck.isWhite()) {
                        warningBar.setText("It is Black's turn");
                        return;
                    }
                    showPosition.setText("" + srcColChar + srcRow);
                    canDoMove = !canDoMove;
                }
            }
        }
        else {
            warningBar.setText("You can not make moves when Automatic play is not paused");
        }
    }

    /**
     * Je mozne opakovat tah
     */
    @FXML protected void resetPosition() {
        if (canDoMove) {
            showPosition.setText("");
            canDoMove = !canDoMove;
        }
    }


    public void initialize() {
        chessGame = createChessGame(new Board(8));  // create normal chess game

        // only automatic or manual can be selected at once
        ToggleGroup playGroup = new ToggleGroup();
        playAutomaticaly.setToggleGroup(playGroup);
        playManualy.setToggleGroup(playGroup);

        playManualy.setSelected(true);  // turn on by default

        // set to paused
        setPauseOrPlay();

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
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        playerMove(button);
                    }
                });
                button.setAccessibleText(""+ (y+1) + (x+1));
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
