package pacman.game.util;

import pacman.game.Game;
import pacman.game.entity.Direction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpriteLoader {
    public static Map<String, BufferedImage> tileSprites = new HashMap<>();
    public static Map<Direction, ArrayList<BufferedImage>> pacmanSprites = new HashMap<>();
    public static Map<Direction, ArrayList<BufferedImage>> blinkySprites = new HashMap<>();
    public static Map<Direction, ArrayList<BufferedImage>> inkySprites = new HashMap<>();
    public static Map<Direction, ArrayList<BufferedImage>> pinkySprites = new HashMap<>();
    public static Map<Direction, ArrayList<BufferedImage>> clydeSprites = new HashMap<>();
    public static ArrayList<BufferedImage> ghostFrightenedSprites = new ArrayList<>();
    public static Map<Direction, BufferedImage> ghostEatenSprites = new HashMap<>();

    private static void error() {
        JOptionPane.showMessageDialog(Game.frame, Error.LOADING_SPRITES.message,
                "Pacman", JOptionPane.ERROR_MESSAGE);
    }

    public static void loadSprites() {
        loadTileSprites();
        loadEntitySprites();
    }

    private static void loadTileSprites() {
        loadWallSprites();
        loadEdibleSprites();
    }

    private static void loadWallSprites() {
        BufferedImage img;
        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/empty.png")));
            tileSprites.put("empty", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/wall_placeholder.png")));
            tileSprites.put("wall_placeholder", img);

        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
    }

    private static void loadEdibleSprites() {
        BufferedImage img;
        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/edibles/pellet.png")));
            tileSprites.put("pellet", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/edibles/powerpellet.png")));
            tileSprites.put("powerpellet", img);

        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
    }

    private static void loadEntitySprites() {
        loadPacmanSprites();
        loadGhostSprites();
    }

    private static void loadPacmanSprites() {
        ArrayList<BufferedImage> none = new ArrayList<>();
        ArrayList<BufferedImage> up = new ArrayList<>();
        ArrayList<BufferedImage> down = new ArrayList<>();
        ArrayList<BufferedImage> left = new ArrayList<>();
        ArrayList<BufferedImage> right = new ArrayList<>();
        BufferedImage img;

        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/neutral.png")));
            none.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/up1.png")));
            up.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/down1.png")));
            down.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/right1.png")));
            right.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/left1.png")));
            left.add(img);


        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
        pacmanSprites.put(Direction.NONE, none);
        pacmanSprites.put(Direction.UP, up);
        pacmanSprites.put(Direction.DOWN, down);
        pacmanSprites.put(Direction.RIGHT, right);
        pacmanSprites.put(Direction.LEFT, left);
    }

    private static void loadGhostSprites() {
        loadBlinkySprites();
        loadPinkySprites();
        loadInkySprites();
        loadClydeSprites();
        loadFrightenedSprites();
        loadEatenSprites();
    }

    private static void loadBlinkySprites() {
        ArrayList<BufferedImage> none = new ArrayList<>();
        ArrayList<BufferedImage> up = new ArrayList<>();
        ArrayList<BufferedImage> down = new ArrayList<>();
        ArrayList<BufferedImage> left = new ArrayList<>();
        ArrayList<BufferedImage> right = new ArrayList<>();
        BufferedImage img;

        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/neutral.png")));
            none.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/up1.png")));
            up.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/down1.png")));
            down.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/right1.png")));
            right.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/left1.png")));
            left.add(img);


        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
        blinkySprites.put(Direction.NONE, none);
        blinkySprites.put(Direction.UP, up);
        blinkySprites.put(Direction.DOWN, down);
        blinkySprites.put(Direction.RIGHT, right);
        blinkySprites.put(Direction.LEFT, left);
    }

    private static void loadPinkySprites() {
        ArrayList<BufferedImage> none = new ArrayList<>();
        ArrayList<BufferedImage> up = new ArrayList<>();
        ArrayList<BufferedImage> down = new ArrayList<>();
        ArrayList<BufferedImage> left = new ArrayList<>();
        ArrayList<BufferedImage> right = new ArrayList<>();
        BufferedImage img;

        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/neutral.png")));
            none.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/up1.png")));
            up.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/down1.png")));
            down.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/right1.png")));
            right.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/left1.png")));
            left.add(img);


        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
        pinkySprites.put(Direction.NONE, none);
        pinkySprites.put(Direction.UP, up);
        pinkySprites.put(Direction.DOWN, down);
        pinkySprites.put(Direction.RIGHT, right);
        pinkySprites.put(Direction.LEFT, left);
    }

    private static void loadInkySprites() {
        ArrayList<BufferedImage> none = new ArrayList<>();
        ArrayList<BufferedImage> up = new ArrayList<>();
        ArrayList<BufferedImage> down = new ArrayList<>();
        ArrayList<BufferedImage> left = new ArrayList<>();
        ArrayList<BufferedImage> right = new ArrayList<>();
        BufferedImage img;

        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/neutral.png")));
            none.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/up1.png")));
            up.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/down1.png")));
            down.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/right1.png")));
            right.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/left1.png")));
            left.add(img);


        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
        inkySprites.put(Direction.NONE, none);
        inkySprites.put(Direction.UP, up);
        inkySprites.put(Direction.DOWN, down);
        inkySprites.put(Direction.RIGHT, right);
        inkySprites.put(Direction.LEFT, left);
    }

    private static void loadClydeSprites() {
        ArrayList<BufferedImage> none = new ArrayList<>();
        ArrayList<BufferedImage> up = new ArrayList<>();
        ArrayList<BufferedImage> down = new ArrayList<>();
        ArrayList<BufferedImage> left = new ArrayList<>();
        ArrayList<BufferedImage> right = new ArrayList<>();
        BufferedImage img;

        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/neutral.png")));
            none.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/up1.png")));
            up.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/down1.png")));
            down.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/right1.png")));
            right.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/left1.png")));
            left.add(img);


        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
        clydeSprites.put(Direction.NONE, none);
        clydeSprites.put(Direction.UP, up);
        clydeSprites.put(Direction.DOWN, down);
        clydeSprites.put(Direction.RIGHT, right);
        clydeSprites.put(Direction.LEFT, left);
    }

    private static void loadFrightenedSprites() {
        BufferedImage img;

        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/frightened/blue1.png")));
            ghostFrightenedSprites.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/frightened/blue2.png")));
            ghostFrightenedSprites.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/frightened/white1.png")));
            ghostFrightenedSprites.add(img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/frightened/white2.png")));
            ghostFrightenedSprites.add(img);


        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
    }
    private static void loadEatenSprites() {
        BufferedImage img;

        try {
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/eaten/up.png")));
            ghostEatenSprites.put(Direction.UP, img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/eaten/down.png")));
            ghostEatenSprites.put(Direction.DOWN, img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/eaten/right.png")));
            ghostEatenSprites.put(Direction.RIGHT, img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/eaten/left.png")));
            ghostEatenSprites.put(Direction.LEFT, img);


        } catch (IOException | NullPointerException e) {
            error();
            System.exit(1);
        }
    }
}
