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
     * Blinky referenciája
     */
    Blinky blinky;

    /**
     * Inky default konstruktor
     * @param pacman Pacman referenciája
     */
    public Inky(Pacman pacman, Blinky blinky) {
        super("Inky", pacman);
        this.blinky = blinky;
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
        Coordinate pacmanPos = pacman.getMapPosition();
        Coordinate pacmanDirVector = pacman.getDirection().getVector();
        Coordinate blinkyPos = blinky.getMapPosition();

        Coordinate temp = pacmanPos.add(pacmanDirVector.multiply(2));
        Coordinate vector = temp.subtract(blinkyPos);
        vector = vector.multiply(2);
        return blinkyPos.add(vector);
    }

    @Override
    protected Coordinate getScatterTarget() {
        return Config.INKY_SCATTER_TARGET;
    }
}
