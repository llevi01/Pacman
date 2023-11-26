package pacman.game.entity.ghost;

import pacman.game.entity.Direction;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

/**
 * Pinky (rózsaszín) szellemet reprezentáló osztály
 */
public class Pinky extends Ghost {
    /**
     * Pinky default konstruktor
     * @param pacman Pacman referenciája
     */
    public Pinky(Pacman pacman) {
        super(pacman);
    }

    /**
     * Kezdőállapotba állítja a szellemet
     */
    @Override
    protected void toStartingPos() {
        position = Config.PINKY_STARTING_POS;
        direction = Config.PINKY_STARTING_DIR;
    }

    /**
     * Sprite-okat inicializáló metódus
     */
    @Override
    protected void initSprites() {
        spriteIndex = 0;
        defaultSprites = SpriteLoader.pinkySprites;
        frightenedSprites = SpriteLoader.ghostFrightenedSprites;
        eatenSprites = SpriteLoader.ghostEatenSprites;
        spriteList = defaultSprites.get(Direction.NONE);
        sprite = spriteList.get(0);
    }

    /**
     * @return A szellem célja CHASE állapotban
     */
    @Override
    protected Coordinate getChaseTarget() {
        Coordinate pacmanPos = pacman.getMapPosition();
        Direction pacmanDirection = pacman.getDirection();
        Coordinate offset = pacmanDirection.getVector().multiply(4);
        return pacmanPos.add(offset);
    }

    /**
     * @return A szellem célja SCATTER állapotban
     */
    @Override
    protected Coordinate getScatterTarget() {
        return Config.PINKY_SCATTER_TARGET;
    }

}
