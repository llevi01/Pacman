package pacman.game.tile;

/**
 * Koordinátákat kezelő osztály.
 * A koordinátákat a bal felső sarokból kezdve számoljuk,
 * jobbra nő az x, lefelé nő az y.
 */
public class Coordinate {
    public int x;
    public int y;
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Coordinate NULLVECTOR = new Coordinate(0, 0);

    /**
     * Összead két koordinátát
     * A két koordinátát vektorként kezelhetjük,
     * ez alapján adjuk össze
     * @param other Az a koordináta, amit ehhez adunk hozzá
     * @return Összeg koordináta
     */
    public Coordinate add(Coordinate other) {
        return new Coordinate(
                this.x + other.x,
                this.y + other.y
        );
    }

    /**
     * Kivon két koordinátát egymásból
     */
    public Coordinate subtract(Coordinate other) {
        return new Coordinate(
                this.x - other.x,
                this.y - other.y
        );
    }

    /**
     * Megszoroz egy koordinátát egy egész számmal
     */
    public Coordinate multiply(int n) {
        return new Coordinate(
                this.x * n,
                this.y * n
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate coord)) {
            return false;
        }
        return this.x == coord.x && this.y == coord.y;
    }

}
