package pacman.game.display;

import pacman.game.input.InputHandler;
import pacman.game.util.Config;

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
        this.addKeyListener(new InputHandler(this));
        loadFont();

        this.getContentPane().setLayout(new CardLayout());
        mainMenu = new MainMenu();
        gamePanel = new GamePanel();

        this.getContentPane().add(mainMenu, "menu");
        this.getContentPane().add(gamePanel, "game");

        setVisible(true);
        pack();
        setLocationRelativeTo(null);
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

    public void showMainMenu() {
        CardLayout layout = (CardLayout) this.getContentPane().getLayout();
        layout.show(this.getContentPane(), "menu");
        mainMenu.repaint();
        pack();
    }

    public void showGame() {
        CardLayout layout = (CardLayout) this.getContentPane().getLayout();
        layout.show(this.getContentPane(), "game");
        gamePanel.printReady();
        pack();
    }
}
