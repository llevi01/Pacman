package pacman.game.display;

import pacman.game.Game;
import pacman.game.util.Config;

import javax.swing.*;
import java.awt.*;

/**
 * Ezen a panelen jelenik meg a pálya és az alsó és felső szalagok
 */
public class GamePanel extends JPanel {

    /**
     * Igaz, ha ki kell írni a "Ready" szöveget
     */
    private boolean printReady = false;

    /**
     * Igaz, ha ki kell írni a "Game Over" szöveget
     */
    private boolean printGameOver = false;

    /**
     * Igaz, ha ki kell írni a "Paused" szöveget
     */
    private boolean printPause = false;

    /**
     * GamePanel default konstruktor
     */
    public GamePanel() {
        this.setMinimumSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));
        this.setMaximumSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new UpperRibbon());
        this.add(new MapPanel());
        this.add(new LowerRibbon());
    }

    /**
     * A GamePanel-t renderelő metódus
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        printChildren(graphics);

        if (printReady) {
            paintReady(graphics);
        } else if (printPause) {
            paintPause(graphics);
        } else if (printGameOver) {
            paintGameOver(graphics);
        }

        graphics.dispose();
    }

    /**
     * Kiírja a "Ready" szöveget
     */
    private void paintReady(Graphics2D graphics) {
        graphics.setFont(GameFrame.font.deriveFont(7F * Config.SCALE));
        FontMetrics metrics = graphics.getFontMetrics();

        int x = (Config.SCREEN_WIDTH - metrics.stringWidth("READY!")) / 2;
        int y = Config.ON_SCREEN_TILE_SIZE * 21;
        graphics.setColor(Color.YELLOW);

        graphics.drawString("READY!", x, y);

        printReady = false;
    }

    /**
     * Kiírja a "Game over" szöveget
     * @param graphics
     */
    private void paintGameOver(Graphics2D graphics) {
        graphics.setFont(GameFrame.font.deriveFont(15F * Config.SCALE));
        FontMetrics metrics = graphics.getFontMetrics();
        String GAME_OVER = "GAME_OVER";

        int x = (Config.SCREEN_WIDTH - metrics.stringWidth(GAME_OVER)) / 2;
        int y = 5 * Config.ON_SCREEN_TILE_SIZE;

        graphics.setColor(Color.BLACK);
        graphics.drawString(GAME_OVER, x + 4, y + 4);

        graphics.setColor(Color.RED);
        graphics.drawString(GAME_OVER, x, y);

        printGameOver = false;
    }

    /**
     * Kiírja a "Paused" szöveget
     */
    private void paintPause(Graphics2D graphics) {
        // Draw bg
        graphics.setColor(Color.YELLOW);
        graphics.fillRoundRect(8 * Config.ON_SCREEN_TILE_SIZE, 13 * Config.ON_SCREEN_TILE_SIZE,
                12 * Config.ON_SCREEN_TILE_SIZE, 9 * Config.ON_SCREEN_TILE_SIZE, 10, 10);

        graphics.setColor(Color.BLACK);
        graphics.fillRoundRect(9 * Config.ON_SCREEN_TILE_SIZE, 14 * Config.ON_SCREEN_TILE_SIZE,
                10 * Config.ON_SCREEN_TILE_SIZE, 7 * Config.ON_SCREEN_TILE_SIZE, 10, 10);

        graphics.setFont(GameFrame.font.deriveFont(8F * Config.SCALE));
        FontMetrics metrics = graphics.getFontMetrics();

        // Draw title
        graphics.setColor(Color.YELLOW);

        int x = (Config.SCREEN_WIDTH - metrics.stringWidth("PAUSED")) / 2;
        int y = 16 * Config.ON_SCREEN_TILE_SIZE;

        graphics.drawString("PAUSED", x, y);

        // Draw instructions
        graphics.setFont(GameFrame.font.deriveFont(6F * Config.SCALE));
        metrics = graphics.getFontMetrics();

        x = (Config.SCREEN_WIDTH - metrics.stringWidth("Esc - RESUME")) / 2;
        y += 2 * Config.ON_SCREEN_TILE_SIZE;

        graphics.drawString("Esc - RESUME", x, y);

        x = (Config.SCREEN_WIDTH - metrics.stringWidth("Enter - QUIT")) / 2;
        y += 2 * Config.ON_SCREEN_TILE_SIZE;

        graphics.drawString("Enter - QUIT", x, y);

        printPause = false;
    }

    /**
     * Kiírja a "Ready" szöveget a következő rendernél
     */
    public void printReady() {
        printReady = true;
        Game.thread.doWait(3000);
    }

    /**
     * Kiírja a "Game Over" szöveget a következő rendernél
     */
    public void printGameOver() {
        printGameOver = true;
        repaint();
    }

    /**
     * Kiírja a "Paused" szöveget a következő rendernél
     */
    public void printPause() {
        printPause = true;
        repaint();
    }
}
