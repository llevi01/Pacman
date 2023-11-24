package pacman.game.display;

import pacman.game.input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Ebben az ablakban jelenik meg a játék
 * Ez az osztály felelős még a hiba dialog box-ok megjelenítéséért is
 */
public class GameFrame extends JFrame {
    /**
     * Főmenü referenciája
     */
    public MainMenu mainMenu;
    /**
     * GamePanel referenciája
     */
    public GamePanel gamePanel;
    /**
     * Az ablak közepén lévő panel
     * Ebben helyezkedik el a mainMenu és a gamePanel
     */
    private JPanel centerPanel;
    /**
     * A játékban használt betűtípus
     */
    static Font font;

    /**
     * GameFrame konstruktor
     */
    public GameFrame() {
        this.setTitle("Pacman");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.addKeyListener(new InputHandler());
        loadFont();

        centerPanel = new JPanel(new CardLayout());
        mainMenu = new MainMenu();
        gamePanel = new GamePanel();

        centerPanel.add(mainMenu, "menu");
        centerPanel.add(gamePanel, "game");
        this.add(centerPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * A játékban használt betűtípus betöltése
     */
    private void loadFont() {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    Objects.requireNonNull(getClass().getResourceAsStream("/font/dogicapixelbold.ttf")));
        } catch (IOException | FontFormatException e) {
            JOptionPane.showMessageDialog(this, "Error while loading font",
                    "Pacman", JOptionPane.ERROR_MESSAGE);
        }
    }
}
