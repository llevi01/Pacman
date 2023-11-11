package pacman.game;

import pacman.game.display.GameFrame;
import pacman.game.display.MazePanel;

public class Game {
    private static GameFrame frame;
    private static MazePanel mazePanel;
    private static GameThread gameThread;
    public static boolean running;

    public static void startGame() {
        // Init window
        frame = new GameFrame();
        mazePanel = new MazePanel();
        frame.add(mazePanel);
        frame.pack();

        running = true;
    }
}
