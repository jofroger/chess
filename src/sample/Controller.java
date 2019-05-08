package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    // todo definovat triedu samotnej hry
    // bude musiet byt vytvoreny konstruktor v src
    // -- s parametrom suboru
    // -- s bez parametra ... cista hra

    private int actualMove = 0; // 0 je vzdy inicializacia dosky
    private List<Button> fieldList = new ArrayList<>();;


    // fxml elements
    @FXML private ListView<String> moveList;
    @FXML private GridPane board ;
    @FXML private GridPane game ;
    @FXML private Text warningBar ;

    // figure pictures
    private String path = "/figuresimg/";

    private Image w_kral = new Image(getClass().getResourceAsStream(path + "w_kral.png"));
    private Image w_dama = new Image(getClass().getResourceAsStream(path +"w_dama.png"));
    private Image w_strelec = new Image(getClass().getResourceAsStream(path +"w_strelec.png"));
    private Image w_kon = new Image(getClass().getResourceAsStream(path +"w_kon.png"));
    private Image w_veza = new Image(getClass().getResourceAsStream(path +"w_veza.png"));
    private Image w_pesiak = new Image(getClass().getResourceAsStream(path +"w_pesiak.png"));

    private Image b_kral = new Image(getClass().getResourceAsStream(path +"b_kral.png"));
    private Image b_dama = new Image(getClass().getResourceAsStream(path +"b_dama.png"));
    private Image b_strelec = new Image(getClass().getResourceAsStream(path +"b_strelec.png"));
    private Image b_kon = new Image(getClass().getResourceAsStream(path +"b_kon.png"));
    private Image b_veza = new Image(getClass().getResourceAsStream(path +"b_veza.png"));
    private Image b_pesiak = new Image(getClass().getResourceAsStream(path +"b_pesiak.png"));


    @FXML protected void loadGame(ActionEvent event) {

        // todo metoda na nacitanie dat do ObservableList<String>
        /* priklad */
        ObservableList<String> move = FXCollections.observableArrayList("1. h4 e6", "2. e4 Be7", "3. Rh3 h5 ", "4. Bc4 Nf6");
        for (String o : move) {
            moveList.getItems().add(o);
        }
        moveList.getSelectionModel().select(actualMove);

        //todo rozmiestnenie figurok
        //zavolanie konstruktoru s parametrom suboru
        //inicializovanie gui dosky
        updateBoard();
    }



    @FXML protected void nextMove(ActionEvent event) {
        actualMove++;
        String rawNextMove = moveList.getItems().get(actualMove);

        // todo parsing nextmove
        String destCoord = rawNextMove;

        if (true/*move(destCoord[0], destCoord[1])*/) {
            moveList.getSelectionModel().select(actualMove);
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
            actualMove--;
            String rawNextMove = moveList.getItems().get(actualMove);

            // todo parsing nextmove
            String destCoord = rawNextMove;

            if (true/*move(destCoord[0], destCoord[1])*/) {
                moveList.getSelectionModel().select(actualMove);
                updateBoard();
            }
            else {
                warningBar.setText("Non-valid move");
                actualMove++;
                // todo zablokovat tlacitka???
            }
        }
    }

    protected void updateBoard() {
        int butIdx = 0;
/*
        for (cez vsetky polia) {

            Button actButton = fieldList.get(butIdx);
            switch (nazov postavy) {
                case Type.K:
                    if (figure.isWhite()) actButton.setGraphic(new ImageView(w_kral));
                    else actButton.setGraphic(new ImageView(b_kral));
                    break;
                case Type.D:
                    if (figure.isWhite()) actButton.setGraphic(new ImageView(w_dama));
                    else actButton.setGraphic(new ImageView(b_dama));
                    break;
                case Type.V:
                    if (figure.isWhite()) actButton.setGraphic(new ImageView(w_veza));
                    else actButton.setGraphic(new ImageView(b_veza));
                    break;
                case Type.J:
                    if (figure.isWhite()) actButton.setGraphic(new ImageView(w_kon));
                    else actButton.setGraphic(new ImageView(b_kon));
                    break;
                case Type.S:
                    if (figure.isWhite()) actButton.setGraphic(new ImageView(w_strelec));
                    else actButton.setGraphic(new ImageView(b_strelec));
                    break;
                case Type.P:
                    if (figure.isWhite()) actButton.setGraphic(new ImageView(w_pesiak));
                    else actButton.setGraphic(new ImageView(b_pesiak));
                    break;
                deafult:
                    actButton.setGraphic(null);
                    break;
            }
            butIdx++;
        }*/
    }




    public void initialize() {
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

                //set img
                if (x == 0) {
                    if (y == 0 || y == 7) {
                        button.setGraphic(new ImageView(b_veza));
                    }
                    if (y == 1 || y == 6) {
                        button.setGraphic(new ImageView(b_kon));
                    }
                    if (y == 2 || y == 5) {
                        button.setGraphic(new ImageView(b_strelec));
                    }
                    if (y == 3) {
                        button.setGraphic(new ImageView(b_dama));
                    }
                    if (y == 4) {
                        button.setGraphic(new ImageView(b_kral));
                    }
                }
                if (x == 1) {
                    button.setGraphic(new ImageView(b_pesiak));
                }
                if (x == 6) {
                    button.setGraphic(new ImageView(w_pesiak));
                }
                if (x == 7) {
                    if (y == 0 || y == 7) {
                        button.setGraphic(new ImageView(w_veza));
                    }
                    if (y == 1 || y == 6) {
                        button.setGraphic(new ImageView(w_kon));
                    }
                    if (y == 2 || y == 5) {
                        button.setGraphic(new ImageView(w_strelec));
                    }
                    if (y == 3) {
                        button.setGraphic(new ImageView(w_dama));
                    }
                    if (y == 4) {
                        button.setGraphic(new ImageView(w_kral));
                    }
                }

                fieldList.add(button);
                board.getChildren().add(button);

                //ColumnConstraints column1 = new ColumnConstraints();
                //column1.setPercentWidth(10);
                //board.getColumnConstraints().addAll(column1);
            }
        }
/*
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(70);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(30);
        game.getColumnConstraints().addAll(column1, column2); // each get 50% of width*/
    }






}
