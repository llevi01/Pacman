package pacman.game;

import pacman.game.util.Config;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A game loop-ért felelős szál
 */
public class GameThread extends Thread {
    private static double deltaTime;        // A legutóbbi render-update blokk óta eltelt idő (ns)
    private static final double timeSlice = 500000000 / (double) Config.DISPLAY_TARGET_FPS; // Két render-update blokk között eltelő idő optimális esetben (ns) TODO nem jó

    private Timer timer = new Timer();
    private TimerTask task;

    private volatile boolean waiting = false;

    /**
     * A game loop indítása
     */
    @Override
    public void run() {
        // A legutóbbi loop időpontja (ns)
        double lastLoopTime = System.nanoTime();

        while (Game.state.equals(GameState.RUNNING)) {
            deltaTime += System.nanoTime() - lastLoopTime;
            lastLoopTime = System.nanoTime();

            while (deltaTime >= timeSlice) {
                if (waiting) {
                    Game.pacman.updateDirection();
                } else {
                Game.update();
                Game.render();
                }
                deltaTime -= timeSlice;
            }
        }
    }
    public void doWait(int millis) {
        waiting = true;
        task = new TimerTask() {
            @Override
            public void run() {
                waiting = false;
            }
        };
        timer.schedule(task, millis);
    }

}
