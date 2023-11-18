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
    public Edible(Coordinate mapPosition, BufferedImage sprite, boolean isWalkable) {
        super(mapPosition, sprite, isWalkable);
        state = EdibleState.ACTIVE;
    }

    /**
     * Az Edible elfogyasztása ennyivel módosítja a pontszámot
     */
    protected int scoreModifier;

    public int getScoreModifier() {
        return scoreModifier;
    }

    public void toEatenState() {
        state = EdibleState.EATEN;
    }

    @Override
    public void render(Graphics2D graphics) {
        if (state == EdibleState.EATEN) {
            return;
        }

        Coordinate drawPosition = getDrawPos();

        graphics.drawImage(sprite, drawPosition.x, drawPosition.y,
                Config.ON_SCREEN_TILE_SIZE, Config.ON_SCREEN_TILE_SIZE, null);
    }

    /**
     * Az Edible aktuális állapota
     */
    public EdibleState state;

    protected static final int PELLET_SCORE_MODIFIER = 10;
    protected static final int POWER_PELLET_SCORE_MODIFIER = 50;
    protected static final int CHERRY_SCORE_MODIFIER = 100;
    protected static final int STRAWBERRY_SCORE_MODIFIER = 300;
    protected static final int ORANGE_SCORE_MODIFIER = 500;
    protected static final int APPLE_SCORE_MODIFIER = 700;
}
