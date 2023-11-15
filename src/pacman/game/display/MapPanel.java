package pacman.game.display;

import pacman.game.config.Config;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    public MapPanel() {
        this.setPreferredSize(new Dimension(Config.MAP_WIDTH, Config.MAP_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;



        graphics.dispose();
    }
}
