package pacman.game.display;

import pacman.game.Game;
import pacman.game.entity.ghost.Ghost;
import pacman.game.tile.Tile;
import pacman.game.util.Config;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ezen a panelen razolódik ki a pálya
 */
public class MapPanel extends JPanel {
    /**
     * MapPanel default konstruktor
     */
    public MapPanel() {
        this.setMinimumSize(new Dimension(Config.SCREEN_WIDTH, Config.MAP_HEIGHT));
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.MAP_HEIGHT));
        this.setMaximumSize(new Dimension(Config.SCREEN_WIDTH, Config.MAP_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(false);
    }

    /**
     * A pályát renderelő metódus
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        for (ArrayList<Tile> rows: Game.map) {
            for (Tile tile : rows) {
                tile.render(graphics);
            }
        }

        for (Ghost ghost : Game.ghosts) {
            ghost.render(graphics);
        }
        Game.pacman.render(graphics);
    }
}
