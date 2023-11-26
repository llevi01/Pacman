package pacman.game.display;

import pacman.game.Game;
import pacman.game.Leaderboard;
import pacman.game.util.Config;

import javax.swing.*;
import java.awt.*;

/**
 * A felső szalagot megjelenítő osztály
 */
public class UpperRibbon extends JPanel {
    /**
     * Maximális pontszám
     */
    int highScore;

    /**
     * UpperRibbon default konstruktor
     */
    public UpperRibbon() {
        this.setMinimumSize(new Dimension(Config.SCREEN_WIDTH, Config.UPPER_RIBBON_HEIGHT));
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.UPPER_RIBBON_HEIGHT));
        this.setMaximumSize(new Dimension(Config.SCREEN_WIDTH, Config.UPPER_RIBBON_HEIGHT));
        this.setBackground(Color.BLACK);

        this.highScore = 0;
    }

    /**
     * A felső szalagot kirajzoló metódus
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(Color.WHITE);
        graphics.setFont(GameFrame.font.deriveFont(7F * Config.SCALE));
        FontMetrics metrics = graphics.getFontMetrics();

        int x = Config.ON_SCREEN_TILE_SIZE * 10 - metrics.stringWidth("SCORE");
        int y = (int) metrics.getStringBounds("SCORE", graphics).getHeight() * 2;

        graphics.drawString("SCORE", x, y);

        x += (metrics.stringWidth("SCORE") - metrics.stringWidth(String.valueOf(Game.score)));
        y *= 2;

        graphics.drawString(String.valueOf(Game.score), x, y);

        x = Config.ON_SCREEN_TILE_SIZE * 23 - metrics.stringWidth("HIGH SCORE");
        y = (int) metrics.getStringBounds("HIGH SCORE", graphics).getHeight() * 2;

        highScore = Leaderboard.getHighScore();
        if (Game.score > highScore) {
            highScore = Game.score;
        }

        graphics.drawString("HIGH SCORE", x, y);

        x += (metrics.stringWidth("HIGH SCORE") - metrics.stringWidth(String.valueOf(highScore)));
        y *= 2;

        graphics.drawString(String.valueOf(highScore), x, y);
    }
}
