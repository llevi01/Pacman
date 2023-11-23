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
        super("Pinky", pacman);
    }

    @Override
    protected void toStartingPos() {
        position = Config.PINKY_STARTING_POS;
        direction = Config.PINKY_STARTING_DIR;
    }

    @Override
    protected void initSprites() {
        spriteIndex = 0;
        defaultSprites = SpriteLoader.pinkySprites;
        frightenedSprites = SpriteLoader.ghostFrightenedSprites;
        eatenSprites = SpriteLoader.ghostEatenSprites;
        spriteList = defaultSprites.get(Direction.NONE);
        sprite = spriteList.get(0);
    }

    @Override
    protected Coordinate getChaseTarget() {
        Coordinate pacmanPos = pacman.getMapPosition();
        Direction pacmanDirection = pacman.getDirection();
        Coordinate offset = pacmanDirection.getVector().multiply(4);
        return pacmanPos.add(offset);
    }

    @Override
    protected Coordinate getScatterTarget() {
        return Config.PINKY_SCATTER_TARGET;
    }

}
