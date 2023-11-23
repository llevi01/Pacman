package pacman.game.tile;

import java.awt.image.BufferedImage;

/**
 * Üres Tile-t reprezentáló osztály
 */
public class EmptyTile extends Tile{
    /**
     * EmptyTile default konstruktor
     * Megegyezik a Tile konstruktorával
     */
    public EmptyTile(Coordinate mapPosition, BufferedImage sprite) {
        super(mapPosition, sprite);
    }
}
