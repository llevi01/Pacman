package pacman.game.util;

import pacman.game.entity.Direction;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/empty.png")));
            tileSprites.put("empty_placeholder", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/wall_placeholder.png")));
            tileSprites.put("wall_placeholder", img);

            // Load edible sprites
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/edibles/pellet.png")));
            tileSprites.put("pellet", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/edibles/powerpellet.png")));
            tileSprites.put("powerpellet", img);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void loadEntitySprites() {
        ArrayList<BufferedImage> right = new ArrayList<>();
        ArrayList<BufferedImage> left = new ArrayList<>();
        ArrayList<BufferedImage> up = new ArrayList<>();
        ArrayList<BufferedImage> down = new ArrayList<>();
        BufferedImage img;

        // Load Pacman sprites
        Map<Direction, ArrayList<BufferedImage>> pacmanSprites = new HashMap<>();

        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/right1.png")));
            right.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/left1.png")));
            left.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/up1.png")));
            up.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/down1.png")));
            down.add(img);
        } catch (IOException e) {
            System.exit(1);
        }
        pacmanSprites.put(Direction.RIGHT, right);
        pacmanSprites.put(Direction.LEFT, left);
        pacmanSprites.put(Direction.UP, up);
        pacmanSprites.put(Direction.DOWN, down);

        entitySprites.put("Pacman", pacmanSprites);
    }
}
