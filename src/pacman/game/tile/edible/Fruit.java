package pacman.game.tile.edible;

import pacman.game.tile.Coordinate;

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
            case "Cherry" -> scoreModifier = CHERRY_SCORE_MODIFIER;
            case "Strawberry" -> scoreModifier = STRAWBERRY_SCORE_MODIFIER;
            case "Orange" -> scoreModifier = ORANGE_SCORE_MODIFIER;
            case "Apple" -> scoreModifier = APPLE_SCORE_MODIFIER;
            default -> scoreModifier = PELLET_SCORE_MODIFIER;
        }
    }
}
