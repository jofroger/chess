package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class Controller {

    @FXML
    private GridPane board ;
    @FXML
    private GridPane game ;
    //private List<Button> fields ;

    public void initialize() {
        //fields = new ArrayList<>();
        String path = "/figuresimg/";

        Image w_kral = new Image(getClass().getResourceAsStream(path + "w_kral.png"));
        Image w_dama = new Image(getClass().getResourceAsStream(path +"w_dama.png"));
        Image w_strelec = new Image(getClass().getResourceAsStream(path +"w_strelec.png"));
        Image w_kon = new Image(getClass().getResourceAsStream(path +"w_kon.png"));
        Image w_veza = new Image(getClass().getResourceAsStream(path +"w_veza.png"));
        Image w_pesiak = new Image(getClass().getResourceAsStream(path +"w_pesiak.png"));

        Image b_kral = new Image(getClass().getResourceAsStream(path +"b_kral.png"));
        Image b_dama = new Image(getClass().getResourceAsStream(path +"b_dama.png"));
        Image b_strelec = new Image(getClass().getResourceAsStream(path +"b_strelec.png"));
        Image b_kon = new Image(getClass().getResourceAsStream(path +"b_kon.png"));
        Image b_veza = new Image(getClass().getResourceAsStream(path +"b_veza.png"));
        Image b_pesiak = new Image(getClass().getResourceAsStream(path +"b_pesiak.png"));

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

                //fields.add(button);
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
