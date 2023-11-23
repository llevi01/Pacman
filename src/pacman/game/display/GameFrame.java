package pacman.game.display;

import pacman.game.input.InputHandler;

import javax.swing.*;

/**
 * Ebben az ablakban jelenik meg a játék
 * Ez az osztály felelős még a hiba dialog box-ok megjelenítéséért
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Pacman");
        this.setVisible(true);
        this.addKeyListener(new InputHandler());
        //this.setLocationRelativeTo(null);
    }
}
