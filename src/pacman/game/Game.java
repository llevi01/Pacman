package pacman.game;

import pacman.game.display.GameFrame;
import pacman.game.display.MapPanel;
import pacman.game.entity.Entity;
import pacman.game.entity.ghost.Blinky;
import pacman.game.entity.ghost.Clyde;
import pacman.game.entity.ghost.Inky;
import pacman.game.entity.ghost.Pinky;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.tile.edible.Fruit;
import pacman.game.util.MapLoader;
import pacman.game.util.SpriteLoader;

import java.util.ArrayList;

public class Game {
    public static GameFrame frame;
    private static MapPanel mapPanel;
    private static final GameThread gameThread;
    public static boolean running;
    public static ArrayList<ArrayList<Tile>> map = new ArrayList<>();
    public static ArrayList<Entity> entities = new ArrayList<>();
    public static ArrayList<Fruit> fruit = new ArrayList<>();
    private static int fruitTimer = 0;
    public static int remainingPellets = 0;
    private static int maxPellets;
    public static int score;
    private static Pacman pacman;

    static {
        gameThread = new GameThread();
    }

    public static void startGame() {
        initGame();
        initDisplay();
        initEntities();

        score = 0;
        running = true;
        gameThread.start();
    }

    /**
     * Frissíti a pályán lévő összes Entity állapotát
     */
    public static void update() {
        for (Entity entity : entities) {
            entity.update();
        }
        doFruitLogic();
        if (pacman.getLives() < 1 || remainingPellets < 1) {
            running = false;
        }
    }

    /**
     * Újrarajzolja a pályát
     */
    public static void render() {
        mapPanel.repaint();
    }

    public static void setMap(ArrayList<ArrayList<Tile>> map) {
        Game.map = map;
    }

    private static void initDisplay() {
        frame = new GameFrame();
        mapPanel = new MapPanel();
        frame.add(mapPanel);
        frame.pack();
    }
    private static void initGame() {
        SpriteLoader.loadSprites();
        MapLoader.loadMap();
        maxPellets = remainingPellets;
    }
    private static void initEntities() {
        entities = new ArrayList<>();
        pacman = new Pacman();
        Blinky blinky = new Blinky(pacman);
        Pinky pinky = new Pinky(pacman);
        Inky inky = new Inky(pacman, blinky);
        Clyde clyde = new Clyde(pacman);

        entities.add(pacman);
        entities.add(blinky);
        entities.add(pinky);
        entities.add(inky);
        entities.add(clyde);
    }

    /**
     * Gyümölcsök elhelyezéséért felelős metódus
     */
    private static void doFruitLogic() {
        int pelletsEaten = maxPellets - remainingPellets;
        // Első gyümölcs
        if (fruit.size() > 1 && pelletsEaten >= 70) {
            Fruit fruit1 = fruit.remove(0);
            Coordinate pos = fruit1.getMapPosition();
            map.get(pos.y).set(pos.x, fruit1);
        }
    }

    private static void placeFruit() {

    }

    private static void removeFruit() {

    }
}
