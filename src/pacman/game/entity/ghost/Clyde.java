package pacman.game.entity.ghost;

import pacman.game.entity.Direction;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

/**
 * Clyde (narancssárga) szellemet reprezentáló osztály
 */
public class Clyde extends Ghost {
    /**
     * Clyde default konstruktor
     * @param pacman Pacman referenciája
     */
    public Clyde(Pacman pacman) {
        super("Clyde", pacman);
    }

    @Override
    protected void toStartingPos() {
        position = Config.CLYDE_STARTING_POS;
    }

    @Override
    protected void initSprites() {
        defaultSprites = SpriteLoader.clydeSprites;
        spriteList = defaultSprites.get(Direction.NONE);
        frightenedSprites = SpriteLoader.ghostFrightenedSprites;
        eatenSprites = SpriteLoader.ghostEatenSprites;
    }

    @Override
    protected Coordinate getChaseTarget() {
        return pacman.getMapPosition(); // TODO clyde chase target
    }

    @Override
    protected Coordinate getScatterTarget() {
        return Config.CLYDE_SCATTER_TARGET;
    }

}
