package pacman.game.util;

import pacman.game.tile.Coordinate;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Konfigurációért felelős osztály
 */
public class Config {
    // Map
    public static final int COLUMNS = 28;
    public static final int ROWS = 31;


    // Display
    public static final int SCALE = 2;          // Nagyítás mértéke
    public static final int TILE_SIZE = 8;      // Egy tile egy oldalhossza pixelben
    public static final int ON_SCREEN_TILE_SIZE = TILE_SIZE * SCALE;
    public static final int ENTITY_SIZE = 14;   // Egy entity egy oldalhossza pixelben
    public static final int ON_SCREEN_ENTITY_SIZE = ENTITY_SIZE * SCALE;
    public static final int MAP_WIDTH = COLUMNS * ON_SCREEN_TILE_SIZE;
    public static final int MAP_HEIGHT = ROWS * ON_SCREEN_TILE_SIZE;
    public static final int DISPLAY_TARGET_FPS = 60;
    public static final int ENTITY_ANIMATION_FPS = 5;


    // Pacman
    public static final int PACMAN_LIVES = 3;
    public static final Coordinate PACMAN_STARTING_POS = new Coordinate(
            (13 * Config.TILE_SIZE + 3) * Config.SCALE,
            (23 * Config.TILE_SIZE + 3) * Config.SCALE
    );     // Képernyő koordináták!
    public static final int PACMAN_SPEED = 2 * SCALE;   // Pixel / frame


    // Ghost
    public static final int GHOST_DEFAULT_SPEED = PACMAN_SPEED;
    public static final int GHOST_FRIGHTENED_SPEED = GHOST_DEFAULT_SPEED / 2;
    public static final int GHOST_EATEN_SPEED  = GHOST_DEFAULT_SPEED * 2;
    public static final Coordinate IN_FRONT_OF_GHOST_HOUSE = new Coordinate(14, 11);
    public static final Coordinate INSIDE_GHOST_HOUSE = new Coordinate(14, 14);
    public static final Coordinate BLIKNY_SCATTER_TARGET = new Coordinate(25, -3);
    public static final Coordinate INKY_SCATTER_TARGET = new Coordinate(27, 33);
    public static final Coordinate PINKY_SCATTER_TARGET = new Coordinate(2, -3);
    public static final Coordinate CLYDE_SCATTER_TARGET = new Coordinate(0, 33);


    // Ghost state
    public static final int GHOST_CHASE_STATE_TIME = 20 * DISPLAY_TARGET_FPS;
    public static final int GHOST_SCATTER_STATE_TIME = 7 * DISPLAY_TARGET_FPS;
    public static final int GHOST_FRIGHTENED_STATE_TIME = 20 * DISPLAY_TARGET_FPS;
    public static final int GHOST_EATEN_STATE_TIME = -1;


    // Edibles
    public static final int PELLET_SCORE_MODIFIER = 10;
    public static final int POWER_PELLET_SCORE_MODIFIER = 50;
    public static final int CHERRY_SCORE_MODIFIER = 100;
    public static final int STRAWBERRY_SCORE_MODIFIER = 300;
    public static final int ORANGE_SCORE_MODIFIER = 500;
    public static final int APPLE_SCORE_MODIFIER = 700;
}
