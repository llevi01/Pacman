package pacman.game.display;

import pacman.game.input.InputHandler;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Pacman");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new InputHandler());
    }
}
