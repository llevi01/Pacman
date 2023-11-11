package pacman.game.display;

import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Pacman");
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
