package ija.ija2018.homework2.game;

import ija.ija2018.homework2.GameFactory;
import ija.ija2018.homework2.common.Field;
import ija.ija2018.homework2.common.Figure;

/**
 *
 * @author Bali
 */

public class Game extends GameFactory implements ija.ija2018.homework2.common.Game {

    public Game() {
        super();
    }

    private Board board;
    static Field destField;
    static Field aktualField;
    private String gameName;

    //Methods

    public Board getBoard() {
        return board;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    private boolean rights (Figure figure, Field field) {

        if (figure.getState().length()!=7) return false;
        int row = Integer.parseInt(figure.getState().substring(6,7));
        int col = Integer.parseInt(figure.getState().substring(4,5));
        String typ = figure.getState().substring(0,1);
        String color = figure.getState().substring(2,3);

        switch (gameName){
            case "Chess":
                switch (typ){
                    case "P":
                        if (color.equals("W")) return (col==field.getCol() && field.getRow()-row==1 )? true : false;
                        else return (col==field.getCol() && field.getRow()-row==-1 )? true : false;
                    case "V":
                        return (col==field.getCol() || row==field.getRow())? true : false;
                    default:
                        return false;
                }

            case "Checkers":
                if (typ.equals("P") && color.equals("W")){
                    return (col!=field.getCol() && field.getRow()-row==1 )? true : false;
                }

            default:
                return false;
        }
    }

    //Override Methods
    @Override
    public boolean move(Figure figure, Field field) {

//        if (!rights(figure, field)) return false;   //todo nahradit moveValidation(Field moveTo)

        if (figure == null) return false;   //prazdne pole
        aktualField = ((Disk) figure).getDiskField((Disk) figure,board);    //board.getField(field.getCol(), field.getRow());
        destField = field;
        if (!figure.moveValidation(field,this)) return false;

        return figure.move(field,board,true);
    }

    @Override
    public void undo() {
        Disk takeFigure;
        Figure figure;
        Field fieldPrev, field, takeField;

        takeField = aktualField;
        takeFigure = aktualField.getTakeDisk();

        figure = board.getField(aktualField.getCol(), aktualField.getRow()).get();
        fieldPrev = aktualField.getPrevField();
        field = board.getField(fieldPrev.getCol(), fieldPrev.getRow());
        figure.move(field,board,false);

        //come back take disk
        if (takeFigure != null) takeField.put(takeFigure);
        takeField.setTakeDisk(null);          //clear take disk
        takeField.setPrevField(null);         //clear previous field
    }
}
