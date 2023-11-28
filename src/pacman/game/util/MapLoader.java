package pacman.game.util;

import pacman.game.Game;
import pacman.game.tile.Coordinate;
import pacman.game.tile.EmptyTile;
import pacman.game.tile.Tile;
import pacman.game.tile.edible.Fruit;
import pacman.game.tile.edible.Pellet;
import pacman.game.tile.edible.PowerPellet;
import pacman.game.tile.wall.Wall;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

/**
 * A pálya betöltéséért felelős osztály
 */
public class MapLoader {
    private static void error() {
        JOptionPane.showMessageDialog(Game.frame, Error.LOADING_MAP.message,
                "Pacman", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Ez a metódus tölti be a pályát
     */
    public static void loadMap() {
        ArrayList<ArrayList<Tile>> map = new ArrayList<>();
        Game.maxPellets = 0;

        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                        MapLoader.class.getResourceAsStream("/map/map.txt"))))) {

            String line = input.readLine();
            ArrayList<Tile> row;

            for (int i = 0; i < Config.MAP_ROWS; i++) {
                row = new ArrayList<>();

                for (int j = 0; j < Config.SCREEN_COLUMNS; j++) {
                    char c = line.charAt(j);

                    // Ha betűvel van reprezentálva, akkor fal
                    if (!Character.isDigit(c)) {
                        String key = getWallSpriteKey(c);
                        BufferedImage sprite = SpriteLoader.tileSprites.get(key);

                        if (key.equals("empty") || key.equals("house_door")) {
                            row.add(row.size(), new EmptyTile(new Coordinate(j, i), sprite));
                        } else {
                            row.add(row.size(), new Wall(new Coordinate(j, i), sprite));
                        }
                        continue;
                    }

                    switch (c) {
                        case '1' ->  {
                            row.add(new Pellet(new Coordinate(j, i), SpriteLoader.tileSprites.get("pellet")));
                            Game.maxPellets++;
                        }
                        case '2' -> row.add(new PowerPellet(new Coordinate(j, i), SpriteLoader.tileSprites.get("powerpellet")));
                        case '3' -> {
                            initFruit(new Coordinate(j, i));
                            row.add(new EmptyTile(new Coordinate(j, i), SpriteLoader.tileSprites.get("empty")));
                        }
                    }
                }
                map.add(map.size(), row);
                line = input.readLine();
            }

        } catch (IOException | NullPointerException | IndexOutOfBoundsException e) {
            error();
            System.exit(1);
        }
        Game.map = map;
    }

    /**
     * Egy fal sprite-jának kulcsát adja vissza
     * @param c A fal reprezentációja a map.txt-ben
     * @return A fal sprite-jának kulcsa a SpriteLoader.tileSprites map-ben
     */
    private static String getWallSpriteKey(char c) {
        String res = null;
        switch (c) {
            case '.' -> res = "empty";
            case '#' -> res = "wall_placeholder";
            case 'A' -> res = "house_door";
            case 'B' -> res = "house_top_wall";
            case 'C' -> res = "house_bottom_wall";
            case 'D' -> res = "house_left_wall";
            case 'E' -> res = "house_right_wall";
            case 'F' -> res = "house_top_left_corner";
            case 'G' -> res = "house_top_right_corner";
            case 'H' -> res = "house_bottom_left_corner";
            case 'I' -> res = "house_bottom_right_corner";
            case 'J' -> res = "house_left_doorframe";
            case 'K' -> res = "house_right_doorframe";
            case '\'' -> res = "maze_top_left_corner";
            case '\"' -> res = "maze_top_right_corner";
            case 'ˇ' -> res = "maze_bottom_left_corner";
            case '^' -> res = "maze_bottom_right_corner";
            case '_' -> res = "obstacle_top_wall";
            case '-' -> res = "obstacle_bottom_wall";
            case '/' -> res = "obstacle_left_wall";
            case '\\' -> res = "obstacle_right_wall";
            case '[' -> res = "obstacle_bottom_left_outer_corner";
            case ']' -> res = "obstacle_bottom_right_outer_corner";
            case '(' -> res = "obstacle_top_left_outer_corner";
            case ')' -> res = "obstacle_top_right_outer_corner";
            case '<' -> res = "obstacle_bottom_left_inner_corner";
            case '>' -> res = "obstacle_bottom_right_inner_corner";
            case '{' -> res = "obstacle_top_left_inner_corner";
            case '}' -> res = "obstacle_top_right_inner_corner";
            case '$' -> res = "maze_border_top_inner_corner1";
            case '€' -> res = "maze_border_top_inner_corner2";
            case '*' -> res = "maze_border_right_inner_corner1";
            case '+' -> res = "maze_border_right_inner_corner2";
            case '@' -> res = "maze_border_left_inner_corner1";
            case '&' -> res = "maze_border_left_inner_corner2";
        }
        return res;
    }

    /**
     * Inicializálja a gyümölcsöket
     * @param coordinate Ezen a helyen fognak megjelenni a gyümölcsök
     */
    private static void initFruit(Coordinate coordinate) {
        Collections.shuffle(Config.FRUIT_TYPES);
        Game.fruit = new ArrayList<>();
        try {
            for (int i = 0; i < 2; i++) {
                String key = Config.FRUIT_TYPES.get(i);
                BufferedImage sprite = SpriteLoader.tileSprites.get(key);
                Game.fruit.add(new Fruit(coordinate, sprite, key));
            }

        } catch (IndexOutOfBoundsException | NullPointerException e) {
            error();
            System.exit(1);
        }
    }
}
