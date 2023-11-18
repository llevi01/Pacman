package pacman.game.util;

import pacman.game.Game;
import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.tile.wall.Wall;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;

public class MapLoader {
    public static void loadMap() {
        ArrayList<ArrayList<Tile>> map = new ArrayList<>();

        try (BufferedReader input = new BufferedReader(
                new InputStreamReader(new FileInputStream("map/map.txt")))) {

            String line = input.readLine();
            ArrayList<Tile> row = new ArrayList<>();
            int i = 0;

            while (!line.isEmpty()) {
                for (int j = 0; j < line.length(); j++) {
                    char c = line.charAt(j);
                    String key = getSpriteKey(c);
                    BufferedImage sprite = SpriteLoader.tileSprites.get(key);

                    // Ha betűvel van reprezentálva, akkor fal
                    if (!Character.isDigit(c)) {
                        row.add(new Wall(new Coordinate(i, j), sprite));
                        continue;
                    }
                    /*
                    switch (c) {
                        case '1' -> row.add(  );
                        case '2' -> row.add(  );
                        default -> row.add(  );
                    }
                    TODO load edibles to map
                     */
                }
                map.add(row);
                line = input.readLine();
                i++;
            }

        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Game.setMap(map);
    }

    private static String getSpriteKey(char c) {
        String res = null;
        switch (c) {
            case '-' -> res = "empty_placeholder";
            case '#' -> res = "wall_placeholder";
        }
        // TODO tile types
        return res;
    }
}
