package pacman.game.display;

import pacman.game.Game;
import pacman.game.entity.Direction;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LowerRibbon extends JPanel {
    private int displayedFruit;
    private ArrayList<BufferedImage> fruitSprites;
    private BufferedImage pacmanSprite;
    public LowerRibbon() {
        this.setMinimumSize(new Dimension(Config.SCREEN_WIDTH, Config.LOWER_RIBBON_HEIGHT));
        this.setPreferredSize(new Dimension(Config.SCREEN_WIDTH, Config.LOWER_RIBBON_HEIGHT));
        this.setMaximumSize(new Dimension(Config.SCREEN_WIDTH, Config.LOWER_RIBBON_HEIGHT));
        this.setBackground(Color.BLACK);

        fruitSprites = new ArrayList<>();
        fruitSprites.add(Game.fruit.get(0).getSprite());
        fruitSprites.add(Game.fruit.get(1).getSprite());
        pacmanSprite = SpriteLoader.pacmanSprites.get(Direction.LEFT).get(0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        updateDisplayedFruit();

        int x = 0;
        int y = 0;
        int offset = Config.ON_SCREEN_TILE_SIZE * 2;

        for (int i = 0; i < Game.pacman.getLives(); i++) {
            x += offset;
            graphics.drawImage(pacmanSprite, x, y, Config.ON_SCREEN_ENTITY_SIZE, Config.ON_SCREEN_ENTITY_SIZE, null);
        }

        x = Config.SCREEN_WIDTH - offset;

        for (int i = 0; i < displayedFruit; i++) {
            x -= offset;
            BufferedImage img = fruitSprites.get(i);
            graphics.drawImage(img, x, y, Config.ON_SCREEN_ENTITY_SIZE, Config.ON_SCREEN_ENTITY_SIZE, null);
        }
    }

    private void updateDisplayedFruit() {
        if (displayedFruit < 1 && Game.fruit.size() == 1 && Game.pacman.fruitEaten) {
            displayedFruit++;
        } else if (displayedFruit < 2 && Game.fruit.isEmpty() && Game.pacman.fruitEaten) {
            displayedFruit++;
        }
    }
}
