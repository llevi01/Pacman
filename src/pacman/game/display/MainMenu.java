package pacman.game.display;

import pacman.game.Game;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * A főmenüt megjelenítő osztály
 */
public class MainMenu extends JPanel {
    /**
     *
     */
    private final String TITLE = "PACMAN";
    /**
     * Menüpontok
     */
    private final ArrayList<String> options;
    private final String START = "START GAME";
    private final String GUIDE = "HOW TO PLAY";
    private final String QUIT = "QUIT";
    private boolean showingGuide = false;
    /**
     * A kiválasztott menüpont sorszáma
     */
    private int selected = 0;

    /**
     * Főmenü konstruktor
     */
    public MainMenu() {
        options = new ArrayList<>();
        options.add(START);
        options.add(GUIDE);
        options.add(QUIT);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);

        graphics.setFont(GameFrame.font.deriveFont(26F * Config.SCALE));
        FontMetrics metrics = graphics.getFontMetrics(GameFrame.font.deriveFont(26F * Config.SCALE));

        int x = (Config.SCREEN_WIDTH - metrics.stringWidth(TITLE)) / 2;
        int y = Config.ON_SCREEN_TILE_SIZE * 8;

        // Paint title
        graphics.setColor(Color.DARK_GRAY);
        graphics.drawString(TITLE, x + Config.TILE_SPRITE_SIZE, y + Config.TILE_SPRITE_SIZE);
        graphics.setColor(Color.YELLOW);
        graphics.drawString(TITLE, x, y);

        // Paint image
        int imageScale = 2;
        x = (Config.SCREEN_WIDTH - Config.ON_SCREEN_ENTITY_SIZE * imageScale) / 2;
        y = Config.ON_SCREEN_TILE_SIZE * 12;
        graphics.drawImage(SpriteLoader.pacmanMainMenu, x, y,
                Config.ON_SCREEN_ENTITY_SIZE * imageScale, Config.ON_SCREEN_ENTITY_SIZE * imageScale, null);

        graphics.setFont(GameFrame.font.deriveFont(10F * Config.SCALE));
        metrics = graphics.getFontMetrics(GameFrame.font.deriveFont(10F * Config.SCALE));

        for (int i = 0; i < options.size(); i++) {
            String string = options.get(i);
            x = (Config.SCREEN_WIDTH - metrics.stringWidth(string)) / 2;
            y = Config.ON_SCREEN_TILE_SIZE * 20 + Config.ON_SCREEN_TILE_SIZE * 3 * i;
            if (i == selected) {
                graphics.setColor(Color.BLUE);
            }
            graphics.drawString(string, x, y);
            graphics.setColor(Color.YELLOW);
        }

        graphics.dispose();
    }

    /**
     * Egy menüponttal lejjebb megy
     */
    public void down() {
        if (showingGuide) {
            return;
        }
        if (selected < options.size() - 1) {
            selected++;
            repaint();
        }
    }

    /**
     * Egy menüponttal feljebb megy
     */
    public void up() {
        if (showingGuide) {
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
        if (showingGuide) {
            return;
        }
        switch (options.get(selected)) {
             case START -> Game.start();
             case GUIDE -> {
                 showingGuide = true;
                 repaint();
             }
             case QUIT -> System.exit(0);
        }
    }

    /**
     * Visszamegy a főmenübe a HOW TO PLAY menüpontból
     */
    public void back() {
        if (showingGuide) {
            showingGuide = false;
            repaint();
        }
    }
}
