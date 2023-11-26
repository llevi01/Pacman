package pacman.game.tile;

/**
 * Koordinátákat és vektorokat kezelő osztály.
 * A koordinátákat a bal felső sarokból kezdve számoljuk,
 * jobbra nő az x, lefelé nő az y.
 */
public class Coordinate {
    public int x;
    public int y;

    /**
     * Coordinate default konstruktor
     * @param x koordináta az x tengelyen
     * @param y koordináta az y tengelyen
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Nullvektor
     */
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

    /**
     * equals metódus override
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate coord)) {
            return false;
        }
        return this.x == coord.x && this.y == coord.y;
    }

    /**
     * Visszaadja két koordináta távolságát
     * @param other másik koordináta
     * @return a két koordináta távolsága
     */
    public double getDistance(Coordinate other) {
        double a = this.x - other.x;
        double b = this.y - other.y;
        a *= a;
        b *= b;
        return Math.sqrt(a + b);
    }

    /**
     * Két lebegőpontos távolság összehasonlítása
     * @return 0, ha egyenlőek, 1, ha dist1 nagyobb, -1, ha dist1 kisebb
     */
    public static int compareDistances(double dist1, double dist2) {
        double delta = 0.0001d;
        if ((Math.abs(dist1 - dist2)) < delta) {
            return 0;
        }
        return dist1 < dist2 ? -1 : 1;
    }
}
