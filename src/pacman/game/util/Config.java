package pacman.game.util;

public class Config {
    public static final int TILE_SIZE = 8;      // Egy tile egy oldalhossza pixelben
    public static final int ENTITY_SIZE = 14;   // Egy entity egy oldalhossza pixelben
    public static final int SCALE = 3;          // Nagyítás mértéke
    public static final int ON_SCREEN_TILE_SIZE = TILE_SIZE * SCALE;
    public static final int ON_SCREEN_ENTITY_SIZE = ENTITY_SIZE * SCALE;
    public static final int COLUMNS = 28;
    public static final int ROWS = 31;
    public static final int MAP_WIDTH = COLUMNS * ON_SCREEN_TILE_SIZE;
    public static final int MAP_HEIGHT = ROWS * ON_SCREEN_TILE_SIZE;
}
