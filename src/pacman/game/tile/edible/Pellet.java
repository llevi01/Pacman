package pacman.game.tile.edible;

import pacman.game.tile.Coordinate;

import java.awt.image.BufferedImage;

/**
 * Ehető pontot reprezentáló osztály
 */
public class Pellet extends Edible {
    /**
     * Pellet default konstruktor
     */
    public Pellet(Coordinate mapPosition, BufferedImage sprite, boolean isWalkable) {
        super(mapPosition, sprite, isWalkable);
        scoreModifier = PELLET_SCORE_MODIFIER;
    }
}
