package pacman.game.input;

import pacman.game.Game;
import pacman.game.display.GameFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Bemenetet kezelő osztály
 */
public class InputHandler implements KeyListener {
    /**
     * Igaz, ha valamelyik "fel" gomb éppen le van nyomva
     */
    public static boolean upPressed;

    /**
     * Igaz, ha valamelyik "le" gomb éppen le van nyomva
     */
    public static boolean downPressed;

    /**
     * Igaz, ha valamelyik "jobbra" gomb éppen le van nyomva
     */
    public static boolean rightPressed;

    /**
     * Igaz, ha valamelyik "balra" gomb éppen le van nyomva
     */
    public static boolean leftPressed;

    /**
     * Igaz, ha az "Esc" billentyű le van nyomva
     */
    public static boolean escPressed;

    /**
     * Igaz, ha az "Enter" le van nyomva
     */
    public static boolean enterPressed;

    /**
     * A játékot megjelenítő ablak
     */
    private final GameFrame frame;

    /**
     * InputHandler default konstruktor
     * @param frame A játékot megjelenítő ablak
     */
    public InputHandler(GameFrame frame) {
        this.frame = frame;
    }

    /**
     * Üres metódus
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {}

    /**
     * Billentyű lenyomásánál meghívott metódus
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> upPressed = true;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> downPressed = true;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> rightPressed = true;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> leftPressed = true;
            case KeyEvent.VK_ESCAPE -> escPressed = true;
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> enterPressed = true;
        }

        switch (Game.state) {
            case STOPPED -> {
                if (upPressed) frame.mainMenu.up();
                if (downPressed) frame.mainMenu.down();
                if (escPressed) frame.mainMenu.back();
                if (enterPressed) frame.mainMenu.select();
            }
            case RUNNING -> {
                if (escPressed) Game.pause();
            }
            case PAUSED -> {
                if (escPressed) Game.resume();
                if (enterPressed) Game.quitToMenu(false);
            }
        }
    }

    /**
     * Billentyű felengedésénél meghívott metódus
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case KeyEvent.VK_UP, KeyEvent.VK_W -> upPressed = false;
            case KeyEvent.VK_DOWN, KeyEvent.VK_S -> downPressed = false;
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> rightPressed = false;
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> leftPressed = false;
            case KeyEvent.VK_ESCAPE -> escPressed = false;
            case KeyEvent.VK_ENTER, KeyEvent.VK_SPACE -> enterPressed = false;
        }
    }
}
