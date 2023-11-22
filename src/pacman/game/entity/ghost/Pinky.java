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
    }

    @Override
    protected void initSprites() {
        defaultSprites = SpriteLoader.pinkySprites;
        spriteList = defaultSprites.get(Direction.NONE);
        frightenedSprites = SpriteLoader.ghostFrightenedSprites;
        eatenSprites = SpriteLoader.ghostEatenSprites;
    }

    @Override
    protected Coordinate getChaseTarget() {
        return pacman.getMapPosition(); // TODO pinky chase target
    }

    @Override
    protected Coordinate getScatterTarget() {
        return Config.PINKY_SCATTER_TARGET;
    }

}
