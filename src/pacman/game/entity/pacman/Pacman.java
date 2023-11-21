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

    /**
     * Pacman sebessége (pixel / frame)
     */
    private static final int DEFAULT_SPEED = 4;

    /**
     * A következő lehetőségnél Pacman ebbe az írányba fordul
     * Akkor van értelme, amikor a játékos kiválaszt egy olyan irányt,
     * amerre Pacman éppen nem tud menni (ez kereszteződések előtt fordul elő)
     */
    private Direction nextDirection = Direction.NONE;

    /**
     * Inicializáló metódus
     */
    protected void init() {
        STARTING_POS = new Coordinate(
                (13 * Config.TILE_SIZE + 3) * Config.SCALE,
                (23 * Config.TILE_SIZE + 3) * Config.SCALE
        );

        speed = DEFAULT_SPEED;
        position = STARTING_POS;
        direction = Direction.RIGHT; // TODO none
    }

    /**
     * Pacman elveszít egy életet
     * Az a szellem hívja meg, aki megsebezte
     */
    public void hurt() {

    }

    /**
     * Belső logika végrehajtása
     */
    @Override
    public void update() {
        updateDirection();
        position = position.add(direction.getVector().multiply(speed));
        checkOutOfFrame();
        checkWallCollisions();

        updateCurrentTile();
        interactWithTile();
    }

    /**
     * Frissíti Pacman irányát
     */
    private void updateDirection() {
        Direction chosenDirection = getChosenDirection();
        Coordinate mapPos = getMapPosition();
        Coordinate chosenTilePos = mapPos.add(chosenDirection.getVector());
        boolean turningAround = direction.getVector().add(chosenDirection.getVector()).equals(Coordinate.NULLVECTOR);
        Tile chosenTile;

        if (chosenDirection.equals(Direction.NONE)) {
            return;
        }

        try {
            chosenTile = Game.map.get(chosenTilePos.y).get(chosenTilePos.x);
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        // Pacman csak akkor fordulhat, ha egy Tile közepén van éppen
        Coordinate midTile = new Coordinate(
                (mapPos.x * Config.TILE_SIZE + 3) * Config.SCALE,
                (mapPos.y * Config.TILE_SIZE + 3) * Config.SCALE
        );

        // El akar fordulni a játékos, de még nem ért a Tile közepére
        if (!turningAround && !position.equals(midTile)) {
            nextDirection = chosenDirection;
            return;
        }

        // A kiválasztott Tile fal, így nem változtatunk irányt
        if (!chosenTile.isWalkable()) {
            return;
        }

        direction = chosenDirection;
        nextDirection = Direction.NONE;
    }

    /**
     * A jelenlegi Tile-el való interakció
     * A rajta lévő Edible elfogyasztása,
     * szellemekkel való interakció
     */
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

        // Ha más Entity is van ezen a Tile-n az csak szellem lehet, ekkor veszítünk egy életet TODO ez így nem jó
        if (currentTile.entities.size() > 1) {
            Game.lives--;
        }
    }

    /**
     * PowerPellet elfogyasztásakor meghívott függvény
     */
    private void powerPelletEaten() {
        for (Entity entity : Game.entities) {
            if (entity.getName().equals("Pacman")) {
                continue;
            }

            // TODO frightened
        }
    }

    /**
     * Visszaadja a felhasználó által éppen kiválasztott irányt
     */
    private Direction getChosenDirection() {
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
        return nextDirection;
    }
}
