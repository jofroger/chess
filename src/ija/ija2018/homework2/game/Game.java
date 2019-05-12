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

    @Override
    public int oneStep (String step) {

        String delims = "[ ]+";
        String[] tokens = step.split(delims);

        String figureTyp = "";
        int rowAkt = 0;     // aktual row
        int colAkt = 0;     // aktual col
        int rowDest = 0;    // destination row
        int colDest = 0;    // destination col
        int f = 0;          // figurka cislo

        if (tokens.length == 2) {

            Figure figure;
            Field field;
            f = 0;
            for (String token : tokens) {
                f++;
                if (Character.isUpperCase(token.codePointAt(0))) {
                   figureTyp = token.substring(0,1);
                   if (Character.isLowerCase(token.codePointAt(1)))
                        colAkt = token.codePointAt(1)-96;
                   if (Character.isDigit(token.codePointAt(2)))
                        rowAkt = token.codePointAt(2)-48;

                   if (Character.isLowerCase(token.codePointAt(3)))
                        colDest = token.codePointAt(3)-96;
                   if (Character.isDigit(token.codePointAt(4)))
                        rowDest = token.codePointAt(4)-48;

                }else {
                    figureTyp = "P";
                    if (Character.isLowerCase(token.codePointAt(0))) {
//                        colAkt = Character.getNumericValue(Integer.parseInt(token.substring(0,1)))-96;
                        colAkt = token.codePointAt(0)-96;
                    }
                    if (Character.isDigit(token.codePointAt(1)))
                        rowAkt = token.codePointAt(1)-48;

                    if (Character.isLowerCase(token.codePointAt(2)))
                        colDest = token.codePointAt(2)-96;
                    if (Character.isDigit(token.codePointAt(3)))
                        rowDest = token.codePointAt(3)-48;

                }

                if (colAkt >= 1 && colAkt <=8 && rowAkt >= 1 && rowAkt <=8 &&
                    colDest >= 1 && colDest <=8 && rowDest >= 1 && rowDest <=8) {

                    figure = board.getField(colAkt, rowAkt).get();
                    Disk d = (Disk) figure;
                    if (figure == null) return 4;
                    if (!d.getTyp().toString().equals(figureTyp)) return 6;
                    field = board.getField(colDest, rowDest);
                    if (!move(figure,field)) return 5;

                }else if (f==1) return 2;
                      else return 3;

            }
        } else return 1;

        return 0;
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
