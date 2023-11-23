package pacman.game.tile.edible;

import pacman.game.tile.Coordinate;
import pacman.game.util.Config;

import java.awt.image.BufferedImage;

/**
 * Gyümölcsöt reprezentáló osztály
 */
public class Fruit extends Edible {
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
    }
}
