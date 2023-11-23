package pacman.game;

import pacman.game.display.GameFrame;
import pacman.game.display.MapPanel;
import pacman.game.entity.Entity;
import pacman.game.entity.ghost.Blinky;
import pacman.game.entity.ghost.Clyde;
import pacman.game.entity.ghost.Inky;
import pacman.game.entity.ghost.Pinky;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Tile;
import pacman.game.util.MapLoader;
import pacman.game.util.SpriteLoader;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Game {
    private static GameFrame frame;
    private static MapPanel mapPanel;
    private static GameThread gameThread;
    public static boolean running;
    public static ArrayList<ArrayList<Tile>> map;
    public static ArrayList<Entity> entities;
    public static int remainingPellets = 0;
    public static int score = 0;
    public static int lives = 3; // TODO ezt átgondolni

    static {
        gameThread = new GameThread();
    }

    public static void startGame() {
        initGame();
        initDisplay();
        initEntities();

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
        if (lives < 1 || remainingPellets < 1) {
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
    }
    private static void initEntities() {
        entities = new ArrayList<>();
        Pacman pacman = new Pacman();
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
}
