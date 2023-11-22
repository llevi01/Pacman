package pacman.game.entity.ghost;

import pacman.game.entity.Direction;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

/**
 * Inky (kék) szellemet reprezentáló osztály
 */
public class Inky extends Ghost {
    /**
     * Inky default konstruktor
     * @param pacman Pacman referenciája
     */
    public Inky(Pacman pacman) {
        super("Inky", pacman);
    }

    @Override
    protected void toStartingPos() {
        position = Config.INKY_STARTING_POS;
    }

    @Override
    protected void initSprites() {
        defaultSprites = SpriteLoader.inkySprites;
        spriteList = defaultSprites.get(Direction.NONE);
        frightenedSprites = SpriteLoader.ghostFrightenedSprites;
        eatenSprites = SpriteLoader.ghostEatenSprites;
    }

    @Override
    protected Coordinate getChaseTarget() {
        return pacman.getMapPosition(); // TODO inky chase target
    }

    @Override
    protected Coordinate getScatterTarget() {
        return Config.INKY_SCATTER_TARGET;
    }
}
