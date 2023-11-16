package pacman.game.entity;

import pacman.game.tile.Coordinate;

/**
 * Irányt reprezentáló enum
 */
public enum Direction {
    UP(-1, 0, 1),
    LEFT(0, -1, 2),
    DOWN(1, 0, 3),
    RIGHT(0, 1, 4),
    NONE(0, 0, -1);

    private int xOffset;
    private int yOffset;
    private int priority;

    /**
     * Direction konstruktor
     * @param xOffset x tengely irányába való egységnyi eltérés
     * @param yOffset y tengely irányába való egységnyi eltérés
     * @param priority választási sorrendhez használt érték
     */
    Direction(int xOffset, int yOffset, int priority) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.priority = priority;
    }

    /**
     * Adott irányba való elmozdulás egységvektorát adja vissza
     * @return Elmozdulás egységvektora
     */
    public Coordinate getVector() {
        return new Coordinate(xOffset, yOffset);
    }
}
