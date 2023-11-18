package pacman.game.tile.edible;

import pacman.game.tile.Coordinate;

import java.awt.image.BufferedImage;

/**
 * Ehető pontot reprezentáló osztály
 * Ezt elfogyasztva a szellemek Frightened állapotba kerülnek
 */
public class PowerPellet extends Edible {
    public PowerPellet(Coordinate mapPosition, BufferedImage sprite, boolean isWalkable) {
        super(mapPosition, sprite, isWalkable);
        scoreModifier = POWER_PELLET_SCORE_MODIFIER;
    }
}
