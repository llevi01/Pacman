package pacman.game.util;

import pacman.game.entity.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpriteLoader {
    public static Map<String, BufferedImage> mapSprites = new HashMap<>();
    public static Map<String, Map<Direction, ArrayList<BufferedImage>>> movingSprites = new HashMap<>();

    public static void loadSprites() {
        // Load Pacman sprites
        Map<Direction, ArrayList<BufferedImage>> pacmanSprites = new HashMap<>();
        ArrayList<BufferedImage> right = new ArrayList<>();
        BufferedImage img;
        try {
            img = ImageIO.read(new File("/res/pacman/right1.png"));
            right.add(img);
        } catch (IOException e) {
            System.exit(1);
        }

        pacmanSprites.put(Direction.RIGHT, right);
        movingSprites.put("Pacman", pacmanSprites);

    }
}
