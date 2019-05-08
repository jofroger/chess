package sample;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;


public class Controller {

    @FXML
    private GridPane board ;
    @FXML
    private GridPane game ;
    //private List<Button> fields ;

    public void initialize() {
        //fields = new ArrayList<>();
        for (int x = 1; x <= 8; x++) {
            for (int y = 1; y <= 8; y++) {
                Button button = new Button("" + x+y);
                GridPane.setConstraints(button, y, x);  // y = column, x = row

                // set color
                Color fieldColor = Color.web("#00000000");
                if ((x + y) % 2 == 0 ) fieldColor = Color.web("#FFFFFF");
                button.setBackground(new Background(new BackgroundFill(fieldColor, CornerRadii.EMPTY, Insets.EMPTY)));
                button.setMinWidth(100);
                button.setMinHeight(100);

                //Image figure = new Image(getClass().getResourceAsStream("2d.png"));
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
