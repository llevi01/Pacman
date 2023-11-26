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
 * A gggyümölcsök mind ugyan ott jelennek meg a pályán
 * bizonyos időközönként
 */
public class Fruit extends Edible implements ActionListener {
    /**
     * A gyümölcsök helye a pályán
     */
    public static Coordinate location;

    /**
     * Random generátor
     * A gyümölcsök eltűnési idejéhez kell
     */
    private static final Random random = new Random();

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

    /**
     * Visszaadja azt a képernyő koordinátát, ahonnan a gyümölcs rajzolását kezdeni kell
     */
    protected Coordinate getDrawPosition() {
        int offset = (4) * Config.SCALE;
        return new Coordinate(
                super.getDrawPosition().x - offset,
                super.getDrawPosition().y - offset
        );
    }

    /**
     * A képernyőre rajzolja a gyümölcsöt
     * @param graphics Erre történik a festés
     */
    public void render(Graphics2D graphics) {
        if (state == EdibleState.EATEN) {
            return;
        }

        Coordinate drawPosition = this.getDrawPosition();

        graphics.drawImage(sprite, drawPosition.x, drawPosition.y,
                Config.ON_SCREEN_ENTITY_SIZE, Config.ON_SCREEN_ENTITY_SIZE, null);
    }

    /**
     * A gyümölcs eltűntetéséhez használt metódus
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        toEatenState();
    }

    /**
     * A gyümölcs elhelyezésénél meghívott metódus
     * Elindítja a gyümölcs időzítőjét, ami majd eltűnteti azt
     */
    public void placed() {
        Timer timer = new Timer(9000 + random.nextInt(1000), this);
        timer.setRepeats(false);
        timer.start();
    }
}
