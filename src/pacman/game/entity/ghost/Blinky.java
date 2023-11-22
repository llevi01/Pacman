package pacman.game.entity.ghost;

import pacman.game.entity.Direction;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

public class Blinky extends Ghost{
    /**
     * Blinky default konstruktor
     * @param pacman Pacman referenciája
     */
    public Blinky(Pacman pacman) {
        super("Blinky", pacman);
    }

    @Override
    protected void toStartingPos() {
        position = Config.BLINKY_STARTING_POS;
    }

    @Override
    protected void initSprites() {
        defaultSprites = SpriteLoader.entitySprites.get("Blinky");
        spriteList = defaultSprites.get(Direction.NONE);
        // TODO load frightened, eaten sprites
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
