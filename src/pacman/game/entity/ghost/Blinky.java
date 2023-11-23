package pacman.game.entity.ghost;

import pacman.game.entity.Direction;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

/**
 * Blinky (piros) szellemet reprezentáló osztály
 */
public class Blinky extends Ghost{
    /**
     * Blinky default konstruktor
     * @param pacman Pacman referenciája
     */
    public Blinky(Pacman pacman) {
        super("Blinky", pacman);
    }

    @Override
    public void toStartingPos() {
        position = Config.BLINKY_STARTING_POS;
        direction = Config.BLINKY_STARTING_DIR;
    }

    @Override
    protected void initSprites() {
        spriteIndex = 0;
        defaultSprites = SpriteLoader.blinkySprites;
        frightenedSprites = SpriteLoader.ghostFrightenedSprites;
        eatenSprites = SpriteLoader.ghostEatenSprites;
        spriteList = defaultSprites.get(Direction.NONE);
        sprite = spriteList.get(spriteIndex);
    }

    @Override
    protected Coordinate getChaseTarget() {
        return pacman.getMapPosition();
    }

    @Override
    protected Coordinate getScatterTarget() {
        return Config.BLIKNY_SCATTER_TARGET;
    }
}
