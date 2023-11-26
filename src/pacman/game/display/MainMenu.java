package pacman.game.display;

import pacman.game.Game;
import pacman.game.Leaderboard;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * A főmenüt megjelenítő osztály
 */
public class MainMenu extends JPanel {
    /**
     * Menüpontok listája
     */
    private final ArrayList<String> options;
    private final String START = "START GAME";
    private final String LEADERBOARD = "LEADERBOARD";
    private final String GUIDE = "HOW TO PLAY";
    private final String QUIT = "QUIT";

    /**
     * Igaz, ha a főmenüben vagyunk
     */
    private boolean inTitleScreen;

    /**
     * A kiválasztott menüpont sorszáma
     */
    private int selected = 0;

    /**
     * A kiválasztott menüpontot kiemelő szín
     */
    private final Color selectedColor;

    /**
     * Főmenü konstruktor
     */
    public MainMenu() {
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT));

        options = new ArrayList<>();
        options.add(START);
        options.add(LEADERBOARD);
        options.add(GUIDE);
        options.add(QUIT);

        inTitleScreen = true;
        selectedColor = new Color(112, 154, 209);
    }

    /**
     * A főmenüt renderelő metódus
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        if (inTitleScreen) {
            paintTitleSceen(graphics);
        } else {
            switch (options.get(selected)) {
                case LEADERBOARD -> paintLeaderboard(graphics);
                case GUIDE -> paintGuide(graphics);
            }
        }

        graphics.dispose();
    }

    /**
     * Kirajzolja a főmenüt
     */
    private void paintTitleSceen(Graphics2D graphics) {
        // Paint title
        graphics.setFont(GameFrame.font.deriveFont(28F * Config.SCALE));
        FontMetrics metrics = graphics.getFontMetrics();

        String TITLE = "PACMAN";
        int x = (Config.SCREEN_WIDTH - metrics.stringWidth(TITLE)) / 2;
        int y = Config.ON_SCREEN_TILE_SIZE * 8;

        graphics.setColor(Color.DARK_GRAY);
        graphics.drawString(TITLE, x + Config.TILE_SPRITE_SIZE, y + Config.TILE_SPRITE_SIZE);
        graphics.setColor(Color.YELLOW);
        graphics.drawString(TITLE, x, y);

        // Paint image
        int imageScale = 3;
        x = (Config.SCREEN_WIDTH - Config.ON_SCREEN_ENTITY_SIZE * imageScale) / 2;
        y = Config.ON_SCREEN_TILE_SIZE * 10;
        graphics.drawImage(SpriteLoader.pacmanMainMenu, x, y,
                Config.ON_SCREEN_ENTITY_SIZE * imageScale, Config.ON_SCREEN_ENTITY_SIZE * imageScale, null);

        // Paint options
        graphics.setFont(GameFrame.font.deriveFont(11F * Config.SCALE));
        metrics = graphics.getFontMetrics();
        y = Config.ON_SCREEN_TILE_SIZE * 16;

        for (int i = 0; i < options.size(); i++) {
            String string = options.get(i);
            x = (Config.SCREEN_WIDTH - metrics.stringWidth(string)) / 2;
            y += Config.ON_SCREEN_TILE_SIZE * 4;
            if (i == selected) {
                graphics.setColor(selectedColor);
            }
            graphics.drawString(string, x, y);
            graphics.setColor(Color.YELLOW);
        }
    }

    /**
     * Kirajzolja a leaderboard-ot
     */
    private void paintLeaderboard(Graphics2D graphics) {
        ArrayList<Map.Entry<String, Integer>> players = Leaderboard.getTopFive();

        int line1 = Config.ON_SCREEN_TILE_SIZE * 9;
        int line2 = Config.ON_SCREEN_TILE_SIZE * 19;
        int x;
        int y = Config.ON_SCREEN_TILE_SIZE * 6;

        graphics.setColor(Color.YELLOW);
        graphics.setFont(GameFrame.font.deriveFont(8F * Config.SCALE));
        FontMetrics metrics = graphics.getFontMetrics();

        // Print title
        x = line1 - metrics.stringWidth("PLAYER") / 2;
        graphics.drawString("PLAYER", x, y);
        x = line2 - metrics.stringWidth("SCORE") / 2;
        graphics.drawString("SCORE", x, y);

        // Print scores
        graphics.setFont(GameFrame.font.deriveFont(10F * Config.SCALE));
        metrics = graphics.getFontMetrics();
        y += Config.ON_SCREEN_TILE_SIZE * 4;

        if (players.isEmpty()) {
            x = line1 - metrics.stringWidth("-") / 2;
            graphics.drawString("-", x, y);
            x = line2 - metrics.stringWidth("-") / 2;
            graphics.drawString("-", x, y);
            return;
        }

        for (Map.Entry<String, Integer> entry : players) {
            x = line1 - metrics.stringWidth(entry.getKey()) / 2;
            graphics.drawString(entry.getKey(), x, y);
            x = line2 - metrics.stringWidth(String.valueOf(entry.getValue())) / 2;
            graphics.drawString(String.valueOf(entry.getValue()), x, y);
            y += Config.ON_SCREEN_TILE_SIZE * 2;
        }
    }

    /**
     * Kirajzolja az útmutatót
     */
    private void paintGuide(Graphics2D graphics) {
        graphics.setFont(GameFrame.font.deriveFont(10F * Config.SCALE));
        FontMetrics metrics = graphics.getFontMetrics();

        int x = (Config.SCREEN_WIDTH - metrics.stringWidth("guide will show here")) / 2;
        int y = Config.ON_SCREEN_TILE_SIZE * 8;

        graphics.setColor(Color.YELLOW);
        graphics.drawString("guide will show here", x, y);
    }

    /**
     * Egy menüponttal lejjebb lép
     */
    public void down() {
        if (!inTitleScreen) {
            return;
        }
        if (selected < options.size() - 1) {
            selected++;
            repaint();
        }
    }

    /**
     * Egy menüponttal feljebb lép
     */
    public void up() {
        if (!inTitleScreen) {
            return;
        }
        if (selected > 0) {
            selected--;
            repaint();
        }
    }

    /**
     * Kiválasztja a menüpontot
     */
    public void select() {
        if (!inTitleScreen) {
            return;
        }
        switch (options.get(selected)) {
             case START -> Game.start();
            case LEADERBOARD, GUIDE -> {
                 inTitleScreen = false;
                 repaint();
             }
             case QUIT -> System.exit(0);
        }
    }

    /**
     * Visszamegy a főmenübe
     */
    public void back() {
        if (!inTitleScreen) {
            inTitleScreen = true;
            repaint();
        }
    }
}
