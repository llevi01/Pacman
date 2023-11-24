package pacman.game.display;

import pacman.game.Game;
import pacman.game.entity.Entity;
import pacman.game.util.Config;
import pacman.game.tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Ezen a panelen jelenik meg a pálya
 */
public class GamePanel extends JPanel {
    public GamePanel() {
        this.setPreferredSize(new Dimension(Config.MAP_WIDTH, Config.MAP_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    @Override
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

        graphics.dispose();
    }
}
