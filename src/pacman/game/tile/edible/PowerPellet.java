package pacman.game.tile.edible;

import pacman.game.tile.Coordinate;
import pacman.game.util.Config;

import java.awt.image.BufferedImage;

/**
 * Ehető pontot reprezentáló osztály
 * Ezt elfogyasztva a szellemek Frightened állapotba kerülnek
 */
public class PowerPellet extends Edible {
    /**
     * PowerPellet default konstruktor
     * @param mapPosition a PowerPellet helye a pályán
     * @param sprite a PowerPellet-et reprezentáló sprite
     */
    public PowerPellet(Coordinate mapPosition, BufferedImage sprite) {
        super(mapPosition, sprite);
        scoreModifier = Config.POWER_PELLET_SCORE_MODIFIER;
    }
}
