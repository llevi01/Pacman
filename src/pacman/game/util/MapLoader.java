package pacman.game.util;

import pacman.game.tile.Tile;

import java.io.*;
import java.util.ArrayList;

public class MapLoader {
    public static void loadMap() {
        ArrayList<ArrayList<Tile>> map = new ArrayList<>();

        try (BufferedInputStream input =
                     new BufferedInputStream(new FileInputStream("/res/map/map.txt"))) {



        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
