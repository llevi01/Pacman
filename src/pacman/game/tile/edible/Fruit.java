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
            case "Cherry" -> scoreModifier = Config.CHERRY_SCORE_MODIFIER;
            case "Strawberry" -> scoreModifier = Config.STRAWBERRY_SCORE_MODIFIER;
            case "Orange" -> scoreModifier = Config.ORANGE_SCORE_MODIFIER;
            case "Apple" -> scoreModifier = Config.APPLE_SCORE_MODIFIER;
            default -> scoreModifier = Config.PELLET_SCORE_MODIFIER;
        }
    }
}
