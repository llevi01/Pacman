package pacman.game.tile.edible;

import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.util.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Ehető elemeket reprezentáló osztály
 */
public abstract class Edible extends Tile {
    public Edible(Coordinate mapPosition, BufferedImage sprite) {
        super(mapPosition, sprite);
        state = EdibleState.ACTIVE;
    }

    /**
     * Az Edible aktuális állapota
     */
    protected EdibleState state;

    /**
     * Az Edible elfogyasztása ennyivel módosítja a pontszámot
     */
    protected int scoreModifier;

    public int getScoreModifier() {
        return scoreModifier;
    }

    public EdibleState getState() {
        return state;
    }

    public void toEatenState() {
        state = EdibleState.EATEN;
    }

    @Override
    public void render(Graphics2D graphics) {
        if (state == EdibleState.EATEN) {
            return;
        }

        Coordinate drawPosition = getDrawPosition();

        graphics.drawImage(sprite, drawPosition.x, drawPosition.y,
                Config.ON_SCREEN_TILE_SIZE, Config.ON_SCREEN_TILE_SIZE, null);
    }
}
