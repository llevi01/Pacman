package pacman.game.tile.wall;

import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;

import java.awt.image.BufferedImage;

/**
 * Falat reprezentáló osztály
 */
public class Wall extends Tile {
    /**
     * Wall defatult konstruktor
     */
    public Wall(Coordinate mapPosition, BufferedImage sprite) {
        super(mapPosition, sprite);
    }

    /**
     * @return False, mivel a falon nem lehet átmenni
     */
    @Override
    public boolean isWalkable() {
        return false;
    }
}
