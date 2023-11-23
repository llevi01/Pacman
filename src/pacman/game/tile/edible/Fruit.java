package pacman.game.tile.edible;

import pacman.game.tile.Coordinate;
import pacman.game.util.Config;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Gyümölcsöt reprezentáló osztály
 */
public class Fruit extends Edible {
    public static Coordinate location;
    public static int timer;
    /**
     * Fruit default konstruktor
     * @param type A gyümölcs neve
     */
    public Fruit(Coordinate mapPosition, BufferedImage sprite, String type) {
        super(mapPosition, sprite);
        switch (type) {
            case "cherry" -> scoreModifier = Config.CHERRY_SCORE_MODIFIER;
            case "strawberry" -> scoreModifier = Config.STRAWBERRY_SCORE_MODIFIER;
            case "orange" -> scoreModifier = Config.ORANGE_SCORE_MODIFIER;
            case "apple" -> scoreModifier = Config.APPLE_SCORE_MODIFIER;
            default -> scoreModifier = Config.PELLET_SCORE_MODIFIER;
        }
        location = mapPosition;
        timer = 0;
    }

    protected Coordinate getDrawPosition() {
        int offset = (4) * Config.SCALE; // TODO ezt szebben kiszámolni
        return new Coordinate(
                super.getDrawPosition().x - offset,
                super.getDrawPosition().y - offset
        );
    }

    public void render(Graphics2D graphics) {
        if (state == EdibleState.EATEN) {
            return;
        }

        Coordinate drawPosition = this.getDrawPosition();

        graphics.drawImage(sprite, drawPosition.x, drawPosition.y,
                Config.ON_SCREEN_ENTITY_SIZE, Config.ON_SCREEN_ENTITY_SIZE, null);
    }
}
