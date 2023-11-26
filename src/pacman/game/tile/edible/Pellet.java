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
     * @param mapPosition a Pellet helye a pályán
     * @param sprite a Pellet-et reprezentáló sprite
     */
    public Pellet(Coordinate mapPosition, BufferedImage sprite) {
        super(mapPosition, sprite);
        scoreModifier = Config.PELLET_SCORE_MODIFIER;
        Game.maxPellets++;
    }
}
