package pacman.game.tile.edible;

import pacman.game.Game;
import pacman.game.tile.Coordinate;
import pacman.game.util.Config;

import java.awt.image.BufferedImage;

/**
 * Ehető pontot reprezentáló osztály
 */
public class Pellet extends Edible {
    /**
     * Pellet default konstruktor
     */
    public Pellet(Coordinate mapPosition, BufferedImage sprite) {
        super(mapPosition, sprite);
        scoreModifier = Config.PELLET_SCORE_MODIFIER;
        Game.remainingPellets++;
    }
}
