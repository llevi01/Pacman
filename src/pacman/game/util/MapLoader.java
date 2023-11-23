package pacman.game.util;

import pacman.game.Game;
import pacman.game.tile.Coordinate;
import pacman.game.tile.EmptyTile;
import pacman.game.tile.Tile;
import pacman.game.tile.edible.Pellet;
import pacman.game.tile.edible.PowerPellet;
import pacman.game.tile.wall.Wall;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * A pálya betöltéséért felelős osztály
 */
public class MapLoader {
    /**
     * Ez a metódus tölti be a pályát
     */
    public static void loadMap() {
        ArrayList<ArrayList<Tile>> map = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(
                        MapLoader.class.getResourceAsStream("/map/map.txt"))))) {

            String line = input.readLine();
            ArrayList<Tile> row;
            int i = 0;

            while (line != null) {
                row = new ArrayList<>();
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);

                    // Ha betűvel van reprezentálva, akkor fal
                    if (!Character.isDigit(c)) {
                        String key = getWallSpriteKey(c);
                        BufferedImage sprite = SpriteLoader.tileSprites.get(key);

                        if (c == '-') {
                            row.add(row.size(), new EmptyTile(new Coordinate(j, i), sprite));
                        } else {
                            row.add(row.size(), new Wall(new Coordinate(j, i), sprite));
                        }
                        continue;
                    }

                    switch (c) {
                        case '1' -> row.add(new Pellet(new Coordinate(j, i), SpriteLoader.tileSprites.get("pellet")));
                        case '2' -> row.add(new PowerPellet(new Coordinate(j, i), SpriteLoader.tileSprites.get("powerpellet")));
                        //default -> row.add(  ); TODO add fruit
                    }
                }
                map.add(map.size(), row);
                line = input.readLine();
                i++;
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Game.setMap(map);
    }

    /**
     * Egy fal sprite-jának kulcsát adja vissza
     * @param c A fal reprezentációja a map.txt-ben
     * @return A fal sprite-jának kulcsa a SpriteLoader.tileSprites map-ben
     */
    private static String getWallSpriteKey(char c) {
        String res = null;
        switch (c) {
            case '-' -> res = "empty";
            case '#' -> res = "wall_placeholder";
        }
        // TODO tile types
        return res;
    }
}
