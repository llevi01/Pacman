package pacman.game.entity.pacman;

import pacman.game.Game;
import pacman.game.entity.Direction;
import pacman.game.entity.Entity;
import pacman.game.input.InputHandler;
import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;

/**
 * Pacmant reprezentáló osztály
 */
public class Pacman extends Entity {
    /**
     * Pacman default konstruktor
     */
    public Pacman(String id, Coordinate position, Direction direction) {
        super(id, position, direction);
        speed = DEFAULT_SPEED;
    }

    private static final int DEFAULT_SPEED = 10;

    /**
     * A következő lehetőségnél Pacman ebbe az írányba fordul
     * Akkor van értelme, amikor a játékos kiválaszt egy olyan irányt,
     * amerre Pacman éppen nem tud menni (ez kereszteződések előtt fordul elő)
     */
    private Direction next_direction = Direction.NONE;

    @Override
    public void update(double step) {
        updateDirection();


    }

    private void updateDirection() {
        Direction choosenDirection = getChoosenDirection();
        Coordinate mapPos = getMapPosition();
        Coordinate tilePos = mapPos.add(choosenDirection.getVector());
        Tile choosenTile = Game.map.get(tilePos.y).get(tilePos.x);

        // Ha a kiválasztott Tile-n nem lehet átmenni, visszatérünk
        if (!choosenTile.isWalkable) {
            return;
        }



        // Frissíteni kell az irányt, vagy a next_direction-t
        if (choosenDirection != direction) {

        }
    }

    /**
     * Visszaadja a felhasználó által éppen kiválasztott irányt
     */
    private Direction getChoosenDirection() {
        if (InputHandler.upPressed) {
            return Direction.UP;
        }
        if (InputHandler.leftPressed) {
            return Direction.LEFT;
        }
        if (InputHandler.downPressed) {
            return Direction.DOWN;
        }
        if (InputHandler.rightPressed) {
            return Direction.RIGHT;
        }
        return Direction.NONE;
    }
}
