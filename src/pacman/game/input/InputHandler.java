package pacman.game.input;

import pacman.game.Game;
import pacman.game.GameState;
import pacman.game.display.GameFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public static boolean upPressed;
    public static boolean downPressed;
    public static boolean rightPressed;
    public static boolean leftPressed;
    public static boolean escPressed;
    public static boolean enterPressed;
    private GameFrame frame;

    public InputHandler(GameFrame frame) {
        this.frame = frame;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

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
        }
    }

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
