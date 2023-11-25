package pacman.game.display;

import pacman.game.Game;
import pacman.game.entity.Entity;
import pacman.game.tile.Tile;
import pacman.game.util.Config;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel {
    boolean printReady = false;
    boolean printGameOver = true;
    public MapPanel() {
        this.setMinimumSize(new Dimension(Config.SCREEN_WIDTH, Config.MAP_HEIGHT));
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.MAP_HEIGHT));
        this.setMaximumSize(new Dimension(Config.SCREEN_WIDTH, Config.MAP_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        for (ArrayList<Tile> rows: Game.map) {
            for (Tile tile : rows) {
                tile.render(graphics);
            }
        }

        for (Entity entity : Game.entities) {
            entity.render(graphics);
        }
    }
}
