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
    private final UpperRibbon upperRibbon;
    private final MapPanel mapPanel;
    private final LowerRibbon lowerRibbon;

    private boolean printReady = false;
    private boolean printGameOver = false;

    public GamePanel() {
        this.setMinimumSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setMaximumSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        upperRibbon = new UpperRibbon();
        mapPanel = new MapPanel();
        lowerRibbon = new LowerRibbon();

        this.add(upperRibbon);
        this.add(mapPanel);
        this.add(lowerRibbon);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        printChildren(graphics);

        if (printReady) {
            graphics.setFont(GameFrame.font.deriveFont(7F * Config.SCALE));
            FontMetrics metrics = graphics.getFontMetrics();

            int x = (Config.SCREEN_WIDTH - metrics.stringWidth("READY!")) / 2;
            int y = Config.ON_SCREEN_TILE_SIZE * 21;
            graphics.setColor(Color.YELLOW);

            graphics.drawString("READY!", x, y);

            printReady = false;
        } else if (printGameOver) {
            graphics.setFont(GameFrame.font.deriveFont(15F * Config.SCALE));
            FontMetrics metrics = graphics.getFontMetrics();

            int x = (Config.SCREEN_WIDTH - metrics.stringWidth("GAME OVER")) / 2;
            int y = Config.MAP_ROWS / 2 * Config.ON_SCREEN_TILE_SIZE;

            graphics.setColor(Color.BLACK);
            graphics.drawString("GAME OVER", x + 4, y + 4);

            graphics.setColor(Color.RED);
            graphics.drawString("GAME OVER", x, y);

            printGameOver = false;
        }

        graphics.dispose();
    }

    public void printReady() {
        printReady = true;
        Game.thread.doWait(3000);
    }

    public void printGameOver() {
        printGameOver = true;
    }
}
