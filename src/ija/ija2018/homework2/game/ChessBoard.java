package ija.ija2018.homework2.game;


import java.awt.event.ActionEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/*
public class ChessBoard extends Application {

    public String B_Rook = "Icons\\Rook_b.gif";
    public String B_Knight = "Icons\\Knight_b.gif";
    public String B_Bishop = "Icons\\Bishop_b.gif";
    public String B_Queen = "Icons\\Queen_b.gif";
    public String B_king = "Icons\\king_b.gif";
    public String B_Pawn = "Icons\\Pawn_b.gif";
    //black pieces
    public String W_Rook = "Icons\\Rook_w.gif";
    public String W_Knight = "Icons\\Knight_w.gif";
    public String W_Bishop = "Icons\\Bishop_w.gif";
    public String W_Queen = "Icons\\Queen_w.gif";
    public String W_king = "Icons\\king_w.gif";
    public String W_Pawn = "Icons\\Pawn_w.gif";
    //white pieces

    private List model = new ArrayList<ChessPiece>();

    static GridPane root;
    static int size;
    static Button[][] tiles;
    //declares the array of buttons
    static Array[][] current;

    public void blankBoard(GridPane root, Button[][] tiles, int size) {

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                tiles[row][col] = new Button();
                String color;
                if ((row + col) % 2 == 0) {
                    color = "white";
                } else {
                    color = "black";
                }
                tiles[row][col].setStyle("-fx-background-color: " + color + ";");
                root.add(tiles[row][col], col, row);
                tiles[row][col].setPrefSize(50, 50);
            }
        }
        //set the cloure of buttons and thier size
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
    }

    public void start(Stage primaryStage) {
        root = new GridPane();
        size = 8;
        tiles = new Button[8][8];
        //declares the array of buttons

        blankBoard(root, tiles, size);
        //set up the board
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
        //loads the blank board

        if ("onePlayer".equals(Main_menu.gameType)) {
            set_up(tiles);
            //set up the game board
            //Add in a call to launch AI into game

        }
        if ("twoPlayer".equals(Main_menu.gameType)) {
            set_up(tiles);
            //set up game board

        }
//        model = new ArrayList<>();
    }

    public void set_up(Button[][] tiles) {
        Rook blackRookLeft = new Rook(new Coordinate(0, 0), ChessPiece.Color.BLACK);
        setUpPiece(tiles, B_Rook, blackRookLeft);

        Rook blackRookRight = new Rook(new Coordinate(0, 7), ChessPiece.Color.BLACK);
        setUpPiece(tiles, B_Rook, blackRookRight);
        //set up black rooks

        Knight blackKnightLeft = new Knight(new Coordinate(0, 1), ChessPiece.Color.BLACK);
        setUpPiece(tiles, B_Knight, blackKnightLeft);

        Knight blackKnightRight = new Knight(new Coordinate(0, 6), ChessPiece.Color.BLACK);
        setUpPiece(tiles, B_Knight, blackKnightRight);
        //set up black knight

        Bishop blackBishopLeft = new Bishop(new Coordinate(0, 2), ChessPiece.Color.BLACK);
        setUpPiece(tiles, B_Bishop, blackBishopLeft);

        Bishop blackBishopRight = new Bishop(new Coordinate(0, 5), ChessPiece.Color.BLACK);
        setUpPiece(tiles, B_Bishop, blackBishopRight);
        //set up black Bishop

        Queen blackQueen = new Queen(new Coordinate(0, 3), ChessPiece.Color.BLACK);
        setUpPiece(tiles, B_Queen, blackQueen);
        //set up black Queen

        King blackKing = new King(new Coordinate(0, 4), ChessPiece.Color.BLACK);
        setUpPiece(tiles, B_king, blackKing);
        //set up black King

        for (int i = 0; i < 8; i++) {
            Pawn blackPawn = new Pawn(new Coordinate(1, i), ChessPiece.Color.WHITE);
            setUpPiece(tiles, B_Pawn, blackPawn);
        }
        //set up the black pieces on the board with listerners

        //-------------------------------------------------------------------------//
        Rook whiteRookLeft = new Rook(new Coordinate(7, 0), ChessPiece.Color.WHITE);
        setUpPiece(tiles, W_Rook, whiteRookLeft);

        Rook whiteRookRight = new Rook(new Coordinate(7, 7), ChessPiece.Color.WHITE);
        setUpPiece(tiles, W_Rook, whiteRookRight);
        //set up black rooks

        Knight whiteKnightLeft = new Knight(new Coordinate(7, 1), ChessPiece.Color.WHITE);
        setUpPiece(tiles, W_Knight, whiteKnightLeft);

        Knight whiteKnightRight = new Knight(new Coordinate(7, 6), ChessPiece.Color.WHITE);
        setUpPiece(tiles, W_Knight, whiteKnightRight);
        //set up black knight

        Bishop whiteBishopLeft = new Bishop(new Coordinate(7, 2), ChessPiece.Color.WHITE);
        setUpPiece(tiles, W_Bishop, whiteBishopLeft);

        Bishop whiteBishopRight = new Bishop(new Coordinate(7, 5), ChessPiece.Color.WHITE);
        setUpPiece(tiles, W_Bishop, whiteBishopRight);
        //set up black Bishop

        King whiteKing = new King(new Coordinate(7, 3), ChessPiece.Color.WHITE);
        setUpPiece(tiles, W_king, whiteKing);
        //set up black King

        Queen whiteQueen = new Queen(new Coordinate(7, 4), ChessPiece.Color.WHITE);
        setUpPiece(tiles, W_Queen, whiteQueen);
        //set up black Queen

        for (int i = 0; i < 8; i++) {
            Pawn whitePawn = new Pawn(new Coordinate(6, i), ChessPiece.Color.WHITE);
            setUpPiece(tiles, W_Pawn, whitePawn);
        }
        //set up the white pieces on the board with listerners

        //Image imageOk = new Image(getClass().getResourceAsStream("ok.png"));
        Button backButton = new Button("Back", new ImageView());

    }

    private void setUpPiece(
            Button[][] tiles, String imageName, ChessPiece piece) {
        int x = piece.getCoordinate().getPositionX();
        int y = piece.getCoordinate().getPositionY();
        Button tile = tiles[x][y];
        tile.setGraphic(new ImageView(imageName));
        tile.setOnAction(foo
                -> showValidMoves(tiles, piece, imageName)
        );
        model.add(piece);
    }

    private void showValidMoves(Button[][] tiles, ChessPiece piece, String imageName) {
        List<Coordinate> validMoves = piece.getValidMoves();
        for (Coordinate coordinate : validMoves) {
            int x = coordinate.getPositionX();
            int y = coordinate.getPositionY();
            Button buttonToHighlight = tiles[x][y];
            highlightButton(buttonToHighlight, piece);
        }
    }

    private void highlightButton(Button button, ChessPiece piece) {
        button.setStyle(" -fx-background-color:#00ff00;");
        //sets the valid moves to be green
        button.setOnAction(foo
                -> movePiece(button, piece));
        //moves the piece to the users click
        blankBoard(root, tiles, size);
        //removes the green squares
    }

    private void movePiece(Button button, ChessPiece piece) {
        button.setGraphic(new ImageView(W_Pawn));

    }

    public static void main(String[] args) {
        Login_page login = new Login_page();
        login.setVisible(true);
    }

}

*/