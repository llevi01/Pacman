package pacman.game;

import pacman.game.display.GameFrame;
import pacman.game.display.MapPanel;
import pacman.game.tile.Tile;

import java.util.ArrayList;

public class Game {
    private static GameFrame frame;
    private static MapPanel mapPanel;
    private static GameThread gameThread;
    public static boolean running;
    public static ArrayList<ArrayList<Tile>> map;

    static {
        gameThread = new GameThread();
        // Init window
        frame = new GameFrame();
        mapPanel = new MapPanel();
        frame.add(mapPanel);
        frame.pack();
    }

    public static void startGame() {
        running = true;
        gameThread.start();
    }
    public static void update(double step) {
        // TODO method body
    }

    public static void setMap(ArrayList<ArrayList<Tile>> map) {
        Game.map = map;
    }
}
