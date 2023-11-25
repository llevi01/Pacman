package pacman.game.tile.edible;

import pacman.game.tile.Coordinate;
import pacman.game.util.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Gyümölcsöt reprezentáló osztály
 */
public class Fruit extends Edible implements ActionListener {
    public static Coordinate location;
    private static Random random = new Random();
    private Timer timer;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        toEatenState();
    }

    public void placed() {
        timer = new Timer(9000 + random.nextInt(1000), this);
        timer.setRepeats(false);
        timer.start();
    }
}
