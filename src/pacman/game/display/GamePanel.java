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
    private boolean printReady = false;
    private boolean printGameOver = false;

    public GamePanel() {
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
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

        if (printReady) {
            graphics.setFont(GameFrame.font.deriveFont(7F * Config.SCALE));
            FontMetrics metrics = graphics.getFontMetrics(GameFrame.font.deriveFont(7F * Config.SCALE));

            int x = (Config.SCREEN_WIDTH - metrics.stringWidth("READY!")) / 2;
            int y = Config.ON_SCREEN_TILE_SIZE * 18;
            graphics.setColor(Color.YELLOW);

            graphics.drawString("READY!", x, y);


            printReady = false;
        } else if (printGameOver) {
            graphics.setFont(GameFrame.font.deriveFont(15F * Config.SCALE));
            FontMetrics metrics = graphics.getFontMetrics(GameFrame.font.deriveFont(15F * Config.SCALE));

            int x = (Config.SCREEN_WIDTH - metrics.stringWidth("GAME OVER")) / 2;
            int y = Config.MAP_ROWS / 2 * Config.ON_SCREEN_TILE_SIZE;

            graphics.setColor(Color.BLACK);
            graphics.drawString("GAME OVER", x + 4, y + 4);

            graphics.setColor(Color.RED);
            graphics.drawString("GAME OVER", x, y);

            printGameOver = false;
        }

        //graphics.dispose();
    }

    public void printReady() {
        printReady = true;
        Game.thread.doWait(3000);
    }

    public void printGameOver() {
        printGameOver = true;
    }
}
