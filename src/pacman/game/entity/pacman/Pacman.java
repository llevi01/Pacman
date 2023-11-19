package pacman.game.entity.pacman;

import pacman.game.Game;
import pacman.game.entity.Direction;
import pacman.game.entity.Entity;
import pacman.game.input.InputHandler;
import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.tile.edible.Edible;
import pacman.game.tile.edible.EdibleState;
import pacman.game.tile.edible.Pellet;
import pacman.game.tile.edible.PowerPellet;
import pacman.game.util.Config;

/**
 * Pacmant reprezentáló osztály
 */
public class Pacman extends Entity {
    /**
     * Pacman default konstruktor
     */
    public Pacman() {
        super("Pacman");
        init();
        updateCurrentTile();
    }

    private static final int DEFAULT_SPEED = 10;
    private static final Coordinate STARTING_POS = new Coordinate(
            (13 * Config.TILE_SIZE + 3) * Config.SCALE,
            (23 * Config.TILE_SIZE + 3) * Config.SCALE
    );

    /**
     * A következő lehetőségnél Pacman ebbe az írányba fordul
     * Akkor van értelme, amikor a játékos kiválaszt egy olyan irányt,
     * amerre Pacman éppen nem tud menni (ez kereszteződések előtt fordul elő)
     */
    private Direction next_direction = Direction.NONE;

    public void init() {
        speed = DEFAULT_SPEED;
        position = STARTING_POS;
        direction = Direction.RIGHT;
    }

    @Override
    public void update(double step) {
        updateDirection();
        position = position.add(direction.getVector().multiply(speed).multiply(step));
        checkOutOfFrame();

        updateCurrentTile();
        interactWithTile();
    }

    private void updateDirection() {
        Direction chosenDirection = getChoosenDirection();
        Coordinate mapPos = getMapPosition();
        Coordinate chosenTilePos;
        chosenTilePos = mapPos.add(chosenDirection.getVector());
        Tile chosenTile;
        boolean turningAround = direction.getVector().add(chosenDirection.getVector()).equals(Coordinate.NULLVECTOR);

        if (chosenDirection.equals(Direction.NONE)) {
            return;
        }

        try {
            chosenTile = Game.map.get(chosenTilePos.y).get(chosenTilePos.x);
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        // Pacman csak akkor fordulhat, ha egy Tile közepén van éppen
        Coordinate turnPos = new Coordinate(
                (mapPos.x * Config.TILE_SIZE + 3) * Config.SCALE,
                (mapPos.y * Config.TILE_SIZE + 3) * Config.SCALE
        );

        // El akar fordulni a játékos, de még nem ért a Tile közepére
        if (!turningAround && !position.equals(turnPos)) {
            next_direction = chosenDirection;
            return;
        }

        if (!chosenTile.isWalkable()) {
            return;
        }

        direction = chosenDirection;
        next_direction = Direction.NONE;
    }

    private void interactWithTile() {
        if (currentTile == null) {
            return;
        }

        if (currentTile instanceof Edible) {
            Edible edible = (Edible) currentTile;
            if (edible.getState() == EdibleState.EATEN) {
                return;
            }

            edible.toEatenState();
            Game.score += edible.getScoreModifier();

            if (edible instanceof Pellet) {
                Game.remainingPellets--;
            } else if (edible instanceof PowerPellet) {
                powerPelletEaten();
            }
        }

        // Ha más Entity is van ezen a Tile-n az csak szellem lehet, ekkor veszítünk egy életet
        if (currentTile.entities.size() > 1) {
            Game.lives--;
        }
    }

    private void checkOutOfFrame() {
        if (position.x < -Config.ON_SCREEN_ENTITY_SIZE) {
            position.x += Config.MAP_WIDTH + Config.ON_SCREEN_ENTITY_SIZE;
        } else if (position.x > Config.MAP_WIDTH + Config.ON_SCREEN_ENTITY_SIZE) {
            position.x -= Config.MAP_WIDTH + 2 * Config.ON_SCREEN_ENTITY_SIZE;
        }
    }

    private void powerPelletEaten() {
        for (Entity entity : Game.entities) {
            if (entity.id.equals("Pacman")) {
                continue;
            }

            // TODO frightened
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
        return next_direction;
    }
}
