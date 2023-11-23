package pacman.game.util;

import pacman.game.entity.Direction;
import pacman.game.tile.Coordinate;

import java.util.ArrayList;

/**
 * Konfigurációért felelős osztály
 */
public class Config {
    // Map
    public static final int MAP_COLUMNS = 28;
    public static final int MAP_ROWS = 31;


    // Display
    public static final int SCALE = 3;          // Nagyítás mértéke
    public static final int TILE_SPRITE_SIZE = 8;      // Egy tile egy oldalhossza (pixel)
    public static final int ON_SCREEN_TILE_SIZE = TILE_SPRITE_SIZE * SCALE;
    public static final int ENTITY_SPRITE_SIZE = 16;   // Egy entity sprite egy oldalhossza (pixel)
    public static final int ON_SCREEN_ENTITY_SIZE = ENTITY_SPRITE_SIZE * SCALE;
    public static final int MAP_WIDTH = MAP_COLUMNS * ON_SCREEN_TILE_SIZE;
    public static final int MAP_HEIGHT = MAP_ROWS * ON_SCREEN_TILE_SIZE;
    public static final int DISPLAY_TARGET_FPS = 60;
    public static final int ENTITY_ANIMATION_FPS = 5;


    // Pacman
    public static final int PACMAN_LIVES = 3;
    public static final Coordinate PACMAN_STARTING_POS = new Coordinate(
            (13 * Config.TILE_SPRITE_SIZE + 3 + 4) * Config.SCALE,
            (23 * Config.TILE_SPRITE_SIZE + 3) * Config.SCALE
    );     // Képernyő koordináták!
    public static final int PACMAN_SPEED = 2 * SCALE;   // Pixel / frame


    // Ghost
    // speed
    public static final int GHOST_DEFAULT_SPEED = PACMAN_SPEED;
    public static final int GHOST_FRIGHTENED_SPEED = GHOST_DEFAULT_SPEED / 2;
    public static final int GHOST_EATEN_SPEED  = GHOST_DEFAULT_SPEED * 2;

    //target
    public static final Coordinate IN_FRONT_OF_GHOST_HOUSE = new Coordinate(14, 11);
    public static final Coordinate INSIDE_GHOST_HOUSE = new Coordinate(14, 14);
    public static final Coordinate BLIKNY_SCATTER_TARGET = new Coordinate(25, -3);
    public static final Coordinate PINKY_SCATTER_TARGET = new Coordinate(2, -3);
    public static final Coordinate INKY_SCATTER_TARGET = new Coordinate(27, 33);
    public static final Coordinate CLYDE_SCATTER_TARGET = new Coordinate(0, 33);

    // starting position
    public static final Coordinate BLINKY_STARTING_POS = new Coordinate(
            (13 * TILE_SPRITE_SIZE + 3 + 4) * SCALE,
            (11 * TILE_SPRITE_SIZE + 3) * SCALE);
    public static final Coordinate PINKY_STARTING_POS = new Coordinate(
            (13 * TILE_SPRITE_SIZE + 3 + 4) * SCALE,
            (14 * TILE_SPRITE_SIZE + 3) * SCALE);
    public static final Coordinate INKY_STARTING_POS = new Coordinate(
            (11 * TILE_SPRITE_SIZE + 3 + 4) * SCALE,
            (14 * TILE_SPRITE_SIZE + 3) * SCALE);
    public static final Coordinate CLYDE_STARTING_POS = new Coordinate(
            (15 * TILE_SPRITE_SIZE + 3 + 4) * SCALE,
            (14 * TILE_SPRITE_SIZE + 3) * SCALE);

    // starting direction
    public static final Direction BLINKY_STARTING_DIR = Direction.LEFT;
    public static final Direction PINKY_STARTING_DIR = Direction.LEFT;
    public static final Direction INKY_STARTING_DIR = Direction.RIGHT;
    public static final Direction CLYDE_STARTING_DIR = Direction.RIGHT;


    // Ghost state
    public static final int GHOST_CHASE_STATE_TIME = 10 * DISPLAY_TARGET_FPS;
    public static final int GHOST_SCATTER_STATE_TIME = 6 * DISPLAY_TARGET_FPS;
    public static final int GHOST_FRIGHTENED_STATE_TIME = 5 * DISPLAY_TARGET_FPS;
    public static final int GHOST_STATE_INFINITE_TIME = -1;


    // Edibles
    public static final int PELLET_SCORE_MODIFIER = 10;
    public static final int POWER_PELLET_SCORE_MODIFIER = 50;
    public static final int CHERRY_SCORE_MODIFIER = 100;
    public static final int STRAWBERRY_SCORE_MODIFIER = 300;
    public static final int ORANGE_SCORE_MODIFIER = 500;
    public static final int APPLE_SCORE_MODIFIER = 700;
    public static final ArrayList<String> FRUIT_TYPES = new ArrayList<>();
    static {
        FRUIT_TYPES.add("cherry");
        FRUIT_TYPES.add("strawberry");
        FRUIT_TYPES.add("orange");
        FRUIT_TYPES.add("apple");
    }
}
