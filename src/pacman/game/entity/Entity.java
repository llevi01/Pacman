package pacman.game.entity;

import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.util.Config;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

/**
 * Mozgó entitást reprezentáló osztály
 */
public abstract class Entity {

    /**
     * Az entitást azonosító karakter
     * Jelentésük:
     * P - Pacman
     * b - Blinky
     * i - Inky
     * p - Pinky
     * c - Clide
     */
    public char id;

    /**
     * Az Entity-t reprezentáló sprite középpontjának helye a képernyőn
     * Valójában nem teljesen a középpontját tároljuk, a tárolt koordinátát
     * úgy kapjuk meg, hogy a sprite bal felső sarkának koordinátáiból kivonunk
     * a 6 * SCALE egész számot.
     */
    private Coordinate position;

    /**
     * Az Entity aktuális haladási iránya
     */
    private Direction direction;

    /**
     * Az a Tile, amin éppen van az Entity
     */
    private Tile currentTile;

    /**
     * Az Entity sprite-jai irányonként rendezve
     * A megfelelő irányt megadva a map-ből megkapjuk azon sprite-ok
     * sorozatát, melyeken index szerint végighaladva egy animációt kapunk
     */
    private Map<Direction, ArrayList<BufferedImage>> sprites;

    /**
     * A jelenleg használt sprite indexe
     */
    private int spriteIndex;

    /**
     * Két sprite update között eltelő idő (ns)
     */
    private int spriteUpdateInterval;

    /**
     * A legutóbbi sprite frissítés időpontja (ns)
     */
    private int lastSpriteUpdate;

    /**
     * Az Entity-hez tartozó logika végrehajtása
     * (pl. állapot frissítése, collision detection, animáció, stb.)
     * @param step Két update között eltelő idő (s)
     */
    public abstract void update(double step);

    /**
     * Az Entity megjelenítése a képernyőn
     * @param graphics Erre történik a festés
     */
    public void render(Graphics2D graphics) {
        ArrayList<BufferedImage> spriteList = sprites.get(direction);

        // Frissítjük az indexet, ha már itt az ideje
        if (System.nanoTime() - lastSpriteUpdate > spriteUpdateInterval) {
            spriteIndex = (spriteIndex++) % spriteList.size();
        }
        BufferedImage sprite = spriteList.get(spriteIndex);
        Coordinate drawPosition = posToDrawPos();
        
        graphics.drawImage(sprite, drawPosition.x, drawPosition.y,
                Config.ON_SCREEN_ENTITY_SIZE, Config.ON_SCREEN_ENTITY_SIZE,
                null);
    }

    /**
     * Átkonvertálja a képernyő koordinátákat pálya koordinátákra
     * @return A tile helye a pályán
     */
    public Coordinate getMapPosition() {
        return new Coordinate(
                position.x / Config.ON_SCREEN_TILE_SIZE,
                position.y / Config.ON_SCREEN_TILE_SIZE
        );
    }

    /**
     * Visszaadja azt a képernyő koordinátát, ahonnan a sprite rajzolását kezdeni kell
     * A Tile és az Entity sprite-ok méreteinek különbsége miatt külön ki kell számolni,
     * hova kell rajzolni az Entity-ket
     */
    private Coordinate posToDrawPos() {
        return new Coordinate(
                this.position.x - 6 * Config.SCALE,
                this.position.y - 6 * Config.SCALE
        );
    }
}
