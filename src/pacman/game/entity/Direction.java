package pacman.game.entity;

import pacman.game.tile.Coordinate;

/**
 * Irányt reprezentáló enum
 */
public enum Direction {
    UP(0, -1, 1),
    LEFT(-1, 0, 2),
    DOWN(0, 1, 3),
    RIGHT(1, 0, 4),
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
