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
    public static ImageIcon icon;
    public static BufferedImage guide;
    public static Map<String, BufferedImage> tileSprites = new HashMap<>();
    public static Map<Direction, ArrayList<BufferedImage>> pacmanSprites = new HashMap<>();
    public static ArrayList<BufferedImage> pacmanHurtAnimation = new ArrayList<>();
    public static BufferedImage pacmanMainMenu;
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
        loadMenuImages();
        loadTileSprites();
        loadEntitySprites();
    }

    private static void loadMenuImages() {
        try {
            pacmanMainMenu = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/menu/icon.png")));

            icon = new ImageIcon(ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/menu/icon.png"))));

            guide = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/menu/guide.png")));

        } catch (IOException e) {
            error();
            System.exit(1);
        }
    }

    private static void loadTileSprites() {
        loadWallSprites();
        loadEdibleSprites();
    }

    private static void loadWallSprites() {
        BufferedImage img;
        try {
            // Empty
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/empty.png")));
            tileSprites.put("empty", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/wall_placeholder.png")));
            tileSprites.put("wall_placeholder", img);

            // House
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_bottom_left_corner.png")));
            tileSprites.put("house_bottom_left_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_bottom_right_corner.png")));
            tileSprites.put("house_bottom_right_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_bottom_wall.png")));
            tileSprites.put("house_bottom_wall", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_door.png")));
            tileSprites.put("house_door", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_left_doorframe.png")));
            tileSprites.put("house_left_doorframe", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_left_wall.png")));
            tileSprites.put("house_left_wall", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_right_doorframe.png")));
            tileSprites.put("house_right_doorframe", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_right_wall.png")));
            tileSprites.put("house_right_wall", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_top_left_corner.png")));
            tileSprites.put("house_top_left_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_top_right_corner.png")));
            tileSprites.put("house_top_right_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/house_top_wall.png")));
            tileSprites.put("house_top_wall", img);

            // Maze outer corners
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_bottom_left_corner.png")));
            tileSprites.put("maze_bottom_left_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_bottom_right_corner.png")));
            tileSprites.put("maze_bottom_right_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_top_left_corner.png")));
            tileSprites.put("maze_top_left_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_top_right_corner.png")));
            tileSprites.put("maze_top_right_corner", img);

            // Maze inner corner
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_border_top_inner_corner1.png")));
            tileSprites.put("maze_border_top_inner_corner1", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_border_top_inner_corner2.png")));
            tileSprites.put("maze_border_top_inner_corner2", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_border_right_inner_corner1.png")));
            tileSprites.put("maze_border_right_inner_corner1", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_border_right_inner_corner2.png")));
            tileSprites.put("maze_border_right_inner_corner2", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_border_left_inner_corner1.png")));
            tileSprites.put("maze_border_left_inner_corner1", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/maze_border_left_inner_corner2.png")));
            tileSprites.put("maze_border_left_inner_corner2", img);

            // Obstacle walls
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_top_wall.png")));
            tileSprites.put("obstacle_top_wall", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_bottom_wall.png")));
            tileSprites.put("obstacle_bottom_wall", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_right_wall.png")));
            tileSprites.put("obstacle_right_wall", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_left_wall.png")));
            tileSprites.put("obstacle_left_wall", img);

            // Obstacle outer corners
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_bottom_left_outer_corner.png")));
            tileSprites.put("obstacle_bottom_left_outer_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_bottom_right_outer_corner.png")));
            tileSprites.put("obstacle_bottom_right_outer_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_top_left_outer_corner.png")));
            tileSprites.put("obstacle_top_left_outer_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_top_right_outer_corner.png")));
            tileSprites.put("obstacle_top_right_outer_corner", img);

            // Obstacle outer corners
            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_bottom_left_inner_corner.png")));
            tileSprites.put("obstacle_bottom_left_inner_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_bottom_right_inner_corner.png")));
            tileSprites.put("obstacle_bottom_right_inner_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_top_left_inner_corner.png")));
            tileSprites.put("obstacle_top_left_inner_corner", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/walls/obstacle_top_right_inner_corner.png")));
            tileSprites.put("obstacle_top_right_inner_corner", img);


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

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/edibles/cherry.png")));
            tileSprites.put("cherry", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/edibles/strawberry.png")));
            tileSprites.put("strawberry", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/edibles/orange.png")));
            tileSprites.put("orange", img);

            img = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/edibles/apple.png")));
            tileSprites.put("apple", img);

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
        BufferedImage neutral;
        BufferedImage img1;
        BufferedImage img2;

        try {
            // Neutral
            neutral = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/neutral.png")));
            none.add(neutral);

            // Up
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/up1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/up2.png")));

            up.add(img1);
            up.add(img2);
            up.add(img1);
            up.add(neutral);


            // Down
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/down1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/down2.png")));

            down.add(img1);
            down.add(img2);
            down.add(img1);
            down.add(neutral);

            // Right
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/right1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/right2.png")));

            right.add(img1);
            right.add(img2);
            right.add(img1);
            right.add(neutral);

            // Left
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/left1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/left2.png")));

            left.add(img1);
            left.add(img2);
            left.add(img1);
            left.add(neutral);

            // Hurt animation
            pacmanHurtAnimation.add(neutral);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/up2.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/1.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/2.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/3.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/4.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/5.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/6.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/7.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/8.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/9.png")));
            pacmanHurtAnimation.add(img1);

            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/pacman/hurt/10.png")));
            pacmanHurtAnimation.add(img1);

            for (int i = 0; i < 5; i++) {
                pacmanHurtAnimation.add(tileSprites.get("empty"));
            }

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
        BufferedImage img1;
        BufferedImage img2;

        try {
            // Neutral
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/neutral.png")));
            none.add(img1);

            // Up
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/up1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/up2.png")));

            up.add(img1);
            up.add(img2);

            // Down
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/down1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/down2.png")));

            down.add(img1);
            down.add(img2);

            // Right
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/right1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/right2.png")));

            right.add(img1);
            right.add(img2);

            // Left
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/left1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Blinky/left2.png")));

            left.add(img1);
            left.add(img2);


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
        BufferedImage img1;
        BufferedImage img2;

        try {
            // Neutral
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/neutral.png")));
            none.add(img1);

            // Up
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/up1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/up2.png")));

            up.add(img1);
            up.add(img2);

            // Down
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/down1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/down2.png")));

            down.add(img1);
            down.add(img2);

            // Right
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/right1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/right2.png")));

            right.add(img1);
            right.add(img2);

            // Left
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/left1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Pinky/left2.png")));

            left.add(img1);
            left.add(img2);

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
        BufferedImage img1;
        BufferedImage img2;

        try {
            // Neutral
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/neutral.png")));
            none.add(img1);

            // Up
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/up1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/up2.png")));

            up.add(img1);
            up.add(img2);

            // Down
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/down1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/down2.png")));

            down.add(img1);
            down.add(img2);

            // Right
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/right1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/right2.png")));

            right.add(img1);
            right.add(img2);

            // Left
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/left1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Inky/left2.png")));

            left.add(img1);
            left.add(img2);

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
        BufferedImage img1;
        BufferedImage img2;

        try {
// Neutral
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/neutral.png")));
            none.add(img1);

            // Up
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/up1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/up2.png")));

            up.add(img1);
            up.add(img2);

            // Down
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/down1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/down2.png")));

            down.add(img1);
            down.add(img2);

            // Right
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/right1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/right2.png")));

            right.add(img1);
            right.add(img2);

            // Left
            img1 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/left1.png")));

            img2 = ImageIO.read(Objects.requireNonNull(
                    SpriteLoader.class.getResourceAsStream("/sprites/ghosts/Clyde/left2.png")));

            left.add(img1);
            left.add(img2);

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
