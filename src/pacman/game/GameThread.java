package pacman.game;

import pacman.game.util.Config;

/**
 * A game loop-ért felelős szál
 */
public class GameThread extends Thread {
    private static double deltaTime;        // A legutóbbi render-update blokk óta eltelt idő (ns)
    private static final double timeSlice = 500000000 / (double) Config.DISPLAY_TARGET_FPS; // Két render-update blokk között eltelő idő optimális esetben (ns) TODO nem jó
    private boolean doWait = false;
    private long waitTime = 0;

    /**
     * A game loop indítása
     */
    @Override
    public void run() {
        // A legutóbbi loop időpontja (ns)
        double lastLoopTime = System.nanoTime();

        while (Game.state.equals(GameState.RUNNING)) {

            if (doWait) {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException ignored) {}
                doWait = false;
                lastLoopTime = System.nanoTime();
            }

            deltaTime += System.nanoTime() - lastLoopTime;
            lastLoopTime = System.nanoTime();

            while (deltaTime >= timeSlice) {
                Game.update();
                Game.render();
                deltaTime -= timeSlice;
            }
        }
    }
    public void doWait(long millis) {
        doWait = true;
        waitTime = millis;
    }
}
