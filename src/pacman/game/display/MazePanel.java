package pacman.game.display;

import javax.swing.*;
import java.awt.*;

public class MazePanel extends JPanel {
    public static final int TILE_SIZE = 8;
    public static final int SCALE = 3;
    public static final int ON_SCREEN_TILE_SIZE = TILE_SIZE * SCALE;
    public static final int COLUMNS = 28;
    public static final int ROWS = 31;
    public static final int MAZE_WIDTH = COLUMNS * ON_SCREEN_TILE_SIZE;
    public static final int MAZE_HEIGHT = ROWS * ON_SCREEN_TILE_SIZE;

    public MazePanel() {
        this.setPreferredSize(new Dimension(MAZE_WIDTH, MAZE_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }
}
