package pacman.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
    public static boolean upPressed;
    public static boolean downPressed;
    public static boolean rightPressed;
    public static boolean leftPressed;
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
        }
    }
}
