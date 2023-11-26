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
    /**
     * Edible default konstruktor
     * @param mapPosition az Edible helye a pályám
     * @param sprite az Edible-t reprezentáló sprite a képernyőn
     */
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

    /**
     * Visszaadja azt, hogy az edible elfogyasztása hány pontot ér
     */
    public int getScoreModifier() {
        return scoreModifier;
    }

    /**
     * @return True, ha az Edible el van fogyasztva
     */
    public boolean isEaten() {
        return state.equals(EdibleState.EATEN);
    }

    /**
     * EATEN állapotba állítja az Edible-t
     */
    public void toEatenState() {
        state = EdibleState.EATEN;
    }

    /**
     * A képernyőre rajzolja az Edible-t
     * @param graphics Erre történik a festés
     */
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
