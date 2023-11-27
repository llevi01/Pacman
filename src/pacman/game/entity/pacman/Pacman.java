package pacman.game.entity.pacman;

import pacman.game.Game;
import pacman.game.entity.Direction;
import pacman.game.entity.Entity;
import pacman.game.entity.ghost.Ghost;
import pacman.game.input.InputHandler;
import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.tile.edible.*;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Pacmant reprezentáló osztály
 */
public class Pacman extends Entity {
    /**
     * Pacman default konstruktor
     */
    public Pacman() {
        init();
    }

    /**
     * Ez az animáció játszódik le, amikor Pacman-t megsebzik
     */
    private ArrayList<BufferedImage> hurtAnimation;

    /**
     * Pacman maradék életeinek száma
     */
    private int lives;

    /**
     * Igaz, ha megsebezték Pacman-t
     */
    public boolean hurt;

    /**
     * Megevett szellemek száma egy PowerPellet elfogyasztása után
     * Azért kell, mert minél több szellemet eszik meg Pacman,
     * annál több pontot kap.
     */
    private int ghostsEaten;

    /**
     * Azon alkalmak száma, amikor a játékos egy PowerPellet elfogyasztása
     * után mind a 4 szellemet elkapta. Ha ez mind a 4 PowerPellet után
     * megtörténik, a játékos 12000 extra pontot kap.
     */
    private int perfectRun;

    /**
     * Igaz, ha Pacman megette az aktuálisan a pályán lévő gyümölcsöt
     */
    public boolean fruitEaten;

    /**
     * A következő lehetőségnél Pacman ebbe az írányba fordul
     * Akkor van értelme, amikor a játékos kiválaszt egy olyan irányt,
     * amerre Pacman éppen nem tud menni (ez kereszteződések előtt fordul elő)
     */
    private Direction nextDirection;

    /**
     * Visszaadja Pacman életeinek számát
     */
    public int getLives() {
        return lives;
    }

    /**
     * Kezdőállapotba helyezi Pacman-t
     */
    public void reset() {
        toStartingPos();
    }

    /**
     * Inicializáló metódus
     */
    protected void init() {
        lives = Config.PACMAN_LIVES;
        speed = Config.PACMAN_SPEED;
        ghostsEaten = 0;
        perfectRun = 0;
        fruitEaten = false;
        toStartingPos();

        initSprites();
    }

    /**
     * Sprite-okat inicializáló metódus
     */
    protected void initSprites() {
        spriteIndex = 0;
        defaultSprites = SpriteLoader.pacmanSprites;
        hurtAnimation = SpriteLoader.pacmanHurtAnimation;
        spriteList = defaultSprites.get(Direction.NONE);
        sprite = spriteList.get(spriteIndex);
    }

    /**
     * Sprite frissítése
     */
    @Override
    protected void updateSprite() {
        if (lives < 1) {
            return;
        }
        if (!hurt) {
            super.updateSprite();
            return;
        }
        // Play hurt animation
        sprite = hurtAnimation.get(spriteIndex);
        if (animationDrawCounter < Config.ENTITY_ANIMATION_FPS * 2) {
            animationDrawCounter++;
            return;
        }
        spriteIndex++;
        if (spriteIndex >= hurtAnimation.size()) {
            hurt = false;
            spriteIndex = 0;
            lives--;
        }
        animationDrawCounter = 0;
    }

    /**
     * A kezdő pozíciójába helyezi Pacman-t
     */
    protected void toStartingPos() {
        position = Config.PACMAN_STARTING_POS;
        direction = Direction.NONE;
        nextDirection = Direction.NONE;
    }

    /**
     * Belső logika végrehajtása
     */
    @Override
    public void update() {
        updateDirection();
        position = position.add(direction.getVector().multiply(speed));

        checkOutOfFrame();
        checkWallCollisions();
        checkGhostCollisions();

        interactWithTile();
    }

    /**
     * Frissíti Pacman irányát
     */
    public void updateDirection() {
        Direction chosenDirection = getChosenDirection();
        Coordinate mapPos = getMapPosition();
        Coordinate chosenTilePos = mapPos.add(chosenDirection.getVector());
        boolean turningAround = direction.getVector().add(chosenDirection.getVector()).equals(Coordinate.NULLVECTOR);
        Tile chosenTile;

        if (chosenDirection.equals(Direction.NONE)) {
            return;
        }

        try {
            chosenTile = Game.map.get(chosenTilePos.y).get(chosenTilePos.x);
        } catch (IndexOutOfBoundsException e) {
            return;
        }

        // Pacman csak akkor fordulhat, ha egy Tile közepén van éppen
        Coordinate midTile = new Coordinate(
                (mapPos.x * Config.TILE_SPRITE_SIZE + 3) * Config.SCALE,
                (mapPos.y * Config.TILE_SPRITE_SIZE + 3) * Config.SCALE
        );

        // El akar fordulni a játékos, de még nem ért a Tile közepére
        if (!turningAround && !position.equals(midTile) && !direction.equals(Direction.NONE)) {
            nextDirection = chosenDirection;
            return;
        }

        // A kiválasztott Tile fal, így nem változtatunk irányt
        if (!chosenTile.isWalkable()) {
            return;
        }

        direction = chosenDirection;
        nextDirection = Direction.NONE;
    }

    /**
     * Visszaadja a felhasználó által éppen kiválasztott irányt
     */
    private Direction getChosenDirection() {
        if (InputHandler.upPressed) {
            return Direction.UP;
        }
        if (InputHandler.leftPressed) {
            return Direction.LEFT;
        }
        if (InputHandler.downPressed) {
            return Direction.DOWN;
        }
        if (InputHandler.rightPressed) {
            return Direction.RIGHT;
        }
        return nextDirection;
    }

    /**
     * Elfogyasztjuk az Edible-t, amin éppen vagyunk
     */
    private void interactWithTile() {
        Coordinate mapPos = getMapPosition();
        Tile currentTile;
        try {
            currentTile = Game.map.get(mapPos.y).get(mapPos.x);
        } catch (IndexOutOfBoundsException e) {
            // A pályán kívüli tile-n áll pacman
            return;
        }

        if (currentTile instanceof Edible edible) {
            if (!edible.isEaten()) {
                // Találtunk egy nem elfogyasztott ehetőt
                edible.toEatenState();
                Game.score += edible.getScoreModifier();

                if (edible instanceof Pellet) {
                    Game.remainingPellets--;
                } else if (edible instanceof PowerPellet) {
                    powerPelletEaten();
                } else if (edible instanceof Fruit) {
                    fruitEaten = true;
                }
            }
        }
    }

    /**
     * Szellemekkel való ütközést kezelő metódus
     */
    private void checkGhostCollisions() {
        Coordinate mapPos = getMapPosition();
        for (Ghost ghost : Game.ghosts) {
            Coordinate ghostPos = ghost.getMapPosition();
            if (mapPos.equals(ghostPos)) {
                ghost.interact();
            }
        }
    }

    /**
     * Pacman elveszít egy életet
     * Az a szellem hívja meg, aki megsebezte
     */
    public void hurt() {
        spriteIndex = 0;
        animationDrawCounter = 0;
        hurt = true;
    }

    /**
     * @return True, ha Pacmant éppen megsebezték
     */
    public boolean isHurt() {
        return hurt;
    }

    /**
     * PowerPellet elfogyasztásakor meghívott függvény
     */
    private void powerPelletEaten() {
        for (Ghost ghost : Game.ghosts) {
            ghost.toFrightenedState();
        }
        ghostsEaten = 0;
    }

    /**
     * Módosítja a pontszámot a megfelelő számmal
     * Az a szellem hívja meg, akit Pacman megevett
     */
    public void ghostEaten() {
        ghostsEaten++;
        Game.score += (1 << ghostsEaten) * 100;
        if (ghostsEaten > 3) {
            perfectRun ++;
        }
        if (perfectRun > 3) {
            Game.score += 12000;
        }
    }
}
