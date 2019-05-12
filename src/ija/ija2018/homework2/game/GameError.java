package ija.ija2018.homework2.game;

public class GameError {

    String name;
    Integer index;

    public GameError(String name, Integer index) {
        this.name = name;
        this.index = index;
    }

    public static final GameError ERR0 = new GameError("OK", 0);
    public static final GameError ERR1 = new GameError("String Step nema dva kroky", 1);
    public static final GameError ERR2 = new GameError("Figurka Biela policko je mimo sachovnice", 2);
    public static final GameError ERR3 = new GameError("Figurka Cierna policko je mimo sachovnice", 3);
    public static final GameError ERR4 = new GameError("Na policku tahu nie je figurka", 4);
    public static final GameError ERR5 = new GameError("Procedura move skoncila chybou", 5);
    public static final GameError ERR6 = new GameError("Na policku je iny typ figurky", 6);

    @Override
    public String toString() {
        return name;
    }

}
