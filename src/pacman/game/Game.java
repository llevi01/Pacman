package pacman.game;

import pacman.game.display.GameFrame;
import pacman.game.entity.Entity;
import pacman.game.entity.ghost.*;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Tile;
import pacman.game.tile.edible.Fruit;
import pacman.game.util.MapLoader;
import pacman.game.util.SpriteLoader;

import javax.swing.*;
import java.util.ArrayList;

public class Game {
    public static GameFrame frame;
    public static GameThread thread;
    public static volatile GameState state;
    public static ArrayList<ArrayList<Tile>> map = new ArrayList<>();
    public static ArrayList<Ghost> ghosts = new ArrayList<>();
    public static ArrayList<Fruit> fruit = new ArrayList<>();
    public static int remainingPellets;
    public static int maxPellets = 0;
    public static int score;
    public static Pacman pacman;
    private static boolean pacmanWasHurt;

    /**
     * Inicializáló metódus
     */
    public static void init() {
        maxPellets = 0;
        SpriteLoader.loadSprites();
        MapLoader.loadMap();
        Leaderboard.load();
        state = GameState.STOPPED;
        frame = new GameFrame();
        frame.showMainMenu();
        initGame();
    }

    /**
     * A játékot inicializáló metódus
     */
    private static void initGame() {
        score = 0;
        pacmanWasHurt = false;
        MapLoader.loadMap();
        remainingPellets = maxPellets;
        initEntities();
    }

    /**
     * Az entitásokat inicializáló metódus
     */
    private static void initEntities() {
        pacman = new Pacman();
        ghosts = new ArrayList<>();
        Blinky blinky = new Blinky(pacman);
        Pinky pinky = new Pinky(pacman);
        Inky inky = new Inky(pacman, blinky);
        Clyde clyde = new Clyde(pacman);

        ghosts.add(blinky);
        ghosts.add(pinky);
        ghosts.add(inky);
        ghosts.add(clyde);
    }

    /**
     * Játék indító metódus
     */
    public static void start() {
        initGame();
        thread = new GameThread();
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

        for (Ghost ghost : ghosts) {
            ghost.update();
        }
        pacman.update();
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

    /**
     * Kezdőállapotba állítja az Entity-ket
     * Ha a játék véget ért, kilép
     */
    public static void reset() {
        if (pacman.getLives() < 1) {
            quitToMenu(true);
            return;
        }
        for (Ghost ghost : ghosts) {
            ghost.reset();
        }
        pacman.reset();
        frame.gamePanel.printReady();
    }

    /**
     * Megállítja a játékot
     */
    public static void pause() {
        state = GameState.PAUSED;
    }

    /**
     * Újraindítja a játékot
     */
    public static void resume() {
        thread = new GameThread();
        state = GameState.RUNNING;
        thread.start();
    }

    /**
     * Kilép a menübe
     * @param saveScore ha true, a játékos pontszáma el lesz mentve
     */
    public static void quitToMenu(boolean saveScore) {
        state = GameState.STOPPED;
        frame.gamePanel.printGameOver();

        if (saveScore) {
            String name = JOptionPane.showInputDialog(frame, "Please enter your name (max 8 characters)",
                    "Pacman", JOptionPane.PLAIN_MESSAGE);
            if (name != null && !name.isEmpty() && !name.isBlank()) {
                Leaderboard.addScore(name, score);
            }
        }

        frame.showMainMenu();
    }

    /**
     * Gyümölcs elhelyező logika végrehajtása
     */
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
