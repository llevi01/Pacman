package pacman.game.entity;

import pacman.game.Game;
import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

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
        defaultSprites = SpriteLoader.entitySprites.get(id);
        spriteList = defaultSprites.get(Direction.RIGHT);
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
    protected Map<Direction, ArrayList<BufferedImage>> defaultSprites;

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
     * Ennyi framenként vált spriteot az Entity
     */
    private final int ANIMATION_FPS = 5;

    private int animationFrameCounter = 0;

    /**
     * Az Entity-hez tartozó logika végrehajtása
     * (pl. állapot frissítése, collision detection, animáció, stb.)
     */
    public abstract void update();

    protected abstract void init();

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
            spriteList = defaultSprites.get(direction);
        }

        if (animationFrameCounter < ANIMATION_FPS) {
            animationFrameCounter++;
            return;
        }

        spriteIndex++;
        spriteIndex %= spriteList.size();
        animationFrameCounter = 0;
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

    protected Rectangle getBounds() {
        int offset = 3 * Config.SCALE;
        Coordinate boundsPos = new Coordinate(
                this.position.x - offset,
                this.position.y - offset
        );
        return new Rectangle(boundsPos.x, boundsPos.y, Config.ON_SCREEN_TILE_SIZE, Config.ON_SCREEN_TILE_SIZE);
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

    /**
     * Fallal való ütközést vizsgálja
     * Ha ütközést talál, visszaállítja az Entityt az előző helyére
     */
    protected void checkWallCollisions() {
        Coordinate mapPos = getMapPosition();
        Coordinate nextTileMapPos = mapPos.add(direction.getVector());
        Tile nextTile;
        try {
            nextTile = Game.map.get(nextTileMapPos.y).get(nextTileMapPos.x);
        } catch (IndexOutOfBoundsException e) {
            return;
        }
        if (nextTile.isWalkable()) {
            return;
        }

        Rectangle bounds = getBounds();

        Coordinate nextTileDrawPos = nextTileMapPos.multiply(Config.ON_SCREEN_TILE_SIZE);
        Rectangle tileBounds = new Rectangle(nextTileDrawPos.x, nextTileDrawPos.y, Config.ON_SCREEN_TILE_SIZE, Config.ON_SCREEN_TILE_SIZE);

        if (bounds.intersects(tileBounds)) {
            position = position.subtract(direction.getVector().multiply(speed));
        }
    }

    /**
     * Azt vizsgálja, mikor megy ki az Entity a képernyőről
     * Ha kimegy, a pálya másik oldalára rakja azt
     */
    protected void checkOutOfFrame() {
        if (position.x < -Config.ON_SCREEN_ENTITY_SIZE) {
            position.x += Config.MAP_WIDTH + 2 * Config.ON_SCREEN_ENTITY_SIZE;
        } else if (position.x > Config.MAP_WIDTH + Config.ON_SCREEN_ENTITY_SIZE) {
            position.x -= Config.MAP_WIDTH + 2 * Config.ON_SCREEN_ENTITY_SIZE;
        }
    }
}
