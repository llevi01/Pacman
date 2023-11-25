package pacman.game.display;

import pacman.game.util.Config;

import javax.swing.*;
import java.awt.*;

public class LowerRibbon extends JPanel {
    public LowerRibbon() {
        this.setMinimumSize(new Dimension(Config.SCREEN_WIDTH, Config.LOWER_RIBBON_HEIGHT));
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.LOWER_RIBBON_HEIGHT));
        this.setMaximumSize(new Dimension(Config.SCREEN_WIDTH, Config.LOWER_RIBBON_HEIGHT));
        this.setBackground(Color.BLACK);
    }
}
