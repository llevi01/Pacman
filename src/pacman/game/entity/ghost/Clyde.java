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
        super(pacman);
    }

    /**
     * Kezdőállapotba állítja a szellemet
     */
    @Override
    protected void toStartingPos() {
        position = Config.CLYDE_STARTING_POS;
        direction = Config.CLYDE_STARTING_DIR;
    }

    /**
     * Sprite-okat inicializáló metódus
     */
    @Override
    protected void initSprites() {
        spriteIndex = 0;
        defaultSprites = SpriteLoader.clydeSprites;
        frightenedSprites = SpriteLoader.ghostFrightenedSprites;
        eatenSprites = SpriteLoader.ghostEatenSprites;
        spriteList = defaultSprites.get(Direction.NONE);
        sprite = spriteList.get(spriteIndex);
    }

    /**
     * @return A szellem célja CHASE állapotban
     */
    @Override
    protected Coordinate getChaseTarget() {
        Coordinate pacmanPos = pacman.getMapPosition();
        double distance = pacmanPos.getDistance(this.getMapPosition());

        if (distance > 8) {
            return pacmanPos;
        }
        return Config.CLYDE_SCATTER_TARGET;
    }

    /**
     * @return A szellem célja SCATTER állapotban
     */
    @Override
    protected Coordinate getScatterTarget() {
        return Config.CLYDE_SCATTER_TARGET;
    }

}
