package pacman.game;

import pacman.game.display.GameFrame;
import pacman.game.entity.Entity;
import pacman.game.entity.ghost.*;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Tile;
import pacman.game.tile.edible.Fruit;
import pacman.game.util.Config;
import pacman.game.util.MapLoader;
import pacman.game.util.SpriteLoader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    public static GameFrame frame;
    public static GameThread thread;
    public static volatile GameState state;
    public static ArrayList<ArrayList<Tile>> map = new ArrayList<>();
    public static ArrayList<Entity> entities = new ArrayList<>();
    public static ArrayList<Fruit> fruit = new ArrayList<>();
    private static Timer fruitTimer;
    private static final Random random = new Random();
    public static int remainingPellets = 0;
    private static int maxPellets;
    public static int score;
    public static Pacman pacman;
    private static boolean pacmanWasHurt = false;

    /**
     * Inicializáló metódus
     */
    public static void init() {
        SpriteLoader.loadSprites();
        MapLoader.loadMap();
        state = GameState.STOPPED;
        frame = new GameFrame();
        frame.showMainMenu();
        initGame();
    }

    /**
     * A játékot inicializáló metódus
     */
    private static void initGame() {
        map = MapLoader.map;
        maxPellets = remainingPellets;
        initEntities();
    }

    /**
     * Az entitásokat inicializáló metódus
     */
    private static void initEntities() {
        entities = new ArrayList<>();
        pacman = new Pacman();
        Blinky blinky = new Blinky(pacman);
        Pinky pinky = new Pinky(pacman);
        Inky inky = new Inky(pacman, blinky);
        Clyde clyde = new Clyde(pacman);

        entities.add(blinky);
        entities.add(pinky);
        entities.add(inky);
        entities.add(clyde);
        entities.add(pacman);
    }

    /**
     * Játék indító metódus
     */
    public static void start() {
        initGame();
        thread = new GameThread();
        score = 0;
        frame.showGame();
        state = GameState.RUNNING;
        thread.start();
    }


    /**
     * Frissíti a pályán lévő összes Entity állapotát
     */
    public static void update() {
        if (pacman.isHurt()) {
            pacmanWasHurt = true;
            return;
        } else if (pacmanWasHurt) {
            reset();
            pacmanWasHurt = false;
            return;
        }

        for (Entity entity : entities) {
            entity.update();
        }
        placeFruit();
        if (remainingPellets < 1) {
            quitToMenu(true);
        }
    }

    /**
     * Újrarajzolja a pályát
     */
    public static void render() {
        frame.gamePanel.repaint();
    }

    public static void reset() {
        if (pacman.getLives() < 1) {
            quitToMenu(true);
            return;
        }
        for (Entity entity : entities) {
            entity.reset();
        }
        frame.gamePanel.printReady();
    }

    private static void quitToMenu(boolean saveScore) {
        state = GameState.STOPPED;
        frame.gamePanel.printGameOver();

        frame.showMainMenu();
    }

    private static void placeFruit() {
        int pelletsEaten = maxPellets - remainingPellets;
        // Első gyümölcs
        if (fruit.size() == 2 && pelletsEaten == 70) {
            Fruit fruit1 = fruit.remove(0);
            map.get(Fruit.location.y).set(Fruit.location.x, fruit1);
            fruit1.placed();
        } else if (fruit.size() == 1 && pelletsEaten == 170 && pacman.fruitEaten) {
            Fruit fruit2 = fruit.remove(0);
            map.get(Fruit.location.y).set(Fruit.location.x, fruit2);
            pacman.fruitEaten = false;
            fruit2.placed();
        }
    }
}
