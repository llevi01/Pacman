package pacman.game.tile;

import pacman.game.config.Config;

public abstract class Tile {
    /**
     * A tile-t reprezentáló sprite középpontjának helye a képernyőn
     */
    private Coordinate position;
    public abstract void render();
    public abstract void update(double step);

    /**
     * Átkonvertálja a képernyő koordinátákat pálya koordinátákra
     * @return A tile helye a pályán
     */
    public Coordinate getMapPosition() {
        return new Coordinate(
                position.x / Config.ON_SCREEN_TILE_SIZE,
                position.y / Config.ON_SCREEN_TILE_SIZE
        );
    }
}
