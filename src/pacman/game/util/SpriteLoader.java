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
    public static Map<String, BufferedImage> tileSprites = new HashMap<>();
    public static Map<String, Map<Direction, ArrayList<BufferedImage>>> entitySprites = new HashMap<>();

    public static void loadSprites() {
        loadTileSprites();
        loadEntitySprites();
    }

    private static void loadTileSprites() {
        BufferedImage img;
        try {
            // Load wall sprites
            img = ImageIO.read(new File("/res/sprites/empty_placeholder.png"));
            tileSprites.put("empty_placeholder", img);

            img = ImageIO.read(new File("/res/sprites/wall/wall_placeholder.png"));
            tileSprites.put("wall_placeholder", img);


        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void loadEntitySprites() {
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
        entitySprites.put("Pacman", pacmanSprites);
    }
}
