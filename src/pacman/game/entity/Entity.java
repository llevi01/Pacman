package pacman.game.entity;

import pacman.game.Game;
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
    public Entity(String id) {
        this.id = id;
        lastSpriteUpdate = System.nanoTime();
    }
    /**
     * Az entitást azonosító sztring
     */
    public String id;

    /**
     * Az Entity-t reprezentáló sprite középpontjának helye a képernyőn
     * Valójában nem teljesen a középpontját tároljuk, a tárolt koordinátát
     * úgy kapjuk meg, hogy a sprite bal felső sarkának koordinátáiból kivonunk
     * a (ENTITY_SIZE - TILE_SIZE) * SCALE egész számot.
     */
    protected Coordinate position;

    /**
     * Az Entity aktuális haladási iránya
     */
    protected Direction direction;

    /**
     * Az a Tile, amin éppen van az Entity
     */
    protected Tile currentTile;

    /**
     * Az entitás aktuális sebessége (pixel / s)
     */
    protected int speed;

    /**
     * Az Entity sprite-jai irányonként rendezve
     * A megfelelő irányt megadva a map-ből megkapjuk azon sprite-ok
     * sorozatát, melyeken index szerint végighaladva egy animációt kapunk
     */
    protected Map<Direction, ArrayList<BufferedImage>> spriteMap;

    /**
     * A jelenlegi iránynak megfelelő sprite-ok listája
     * Ezen spite-ok egymásutánja adja ki a karakter animációját
     */
    protected ArrayList<BufferedImage> spriteList;

    /**
     * A jelenleg használt sprite indexe az iránynak megfelelő sprite-ok listájában
     */
    protected int spriteIndex = 0;

    /**
     * Két sprite update között eltelő idő (n)
     */
    protected double spriteUpdateInterval = 500.0;

    /**
     * A legutóbbi sprite frissítés időpontja (n)
     */
    protected double lastSpriteUpdate;

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
        updateSprite();

        BufferedImage sprite = spriteList.get(spriteIndex);
        Coordinate drawPosition = getDrawPosition();
        
        graphics.drawImage(sprite, drawPosition.x, drawPosition.y,
                Config.ON_SCREEN_ENTITY_SIZE, Config.ON_SCREEN_ENTITY_SIZE,
                null);
    }

    /**
     * Frissíti a sprite-ot, ha már itt az ideje
     */
    protected void updateSprite() {
        if (direction != Direction.NONE) {
            spriteList = spriteMap.get(direction);
        }

        if (System.nanoTime() - lastSpriteUpdate > spriteUpdateInterval) {
            spriteIndex++;
            spriteIndex %= spriteList.size();
        }
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
    protected Coordinate getDrawPosition() {
        int offset = (Config.ENTITY_SIZE - Config.TILE_SIZE) * Config.SCALE;
        return new Coordinate(
                this.position.x - offset,
                this.position.y - offset
        );
    }

    protected void updateCurrentTile() {
        Coordinate tileCoords = getMapPosition();

        try {
            Tile nextTile = Game.map.get(tileCoords.y).get(tileCoords.x);
            if (nextTile.equals(currentTile)) {
                return;
            }

            if (currentTile == null) {
                currentTile = nextTile;
                return;
            }

            currentTile.entities.remove(this);
            currentTile = nextTile;
            currentTile.entities.add(this);


        } catch (IndexOutOfBoundsException e) {
            currentTile = null;
        }
    }
}
