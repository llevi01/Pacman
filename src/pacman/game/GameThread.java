package pacman.game;

import pacman.game.util.Config;

/**
 * A game loop-ért felelős szál
 */
public class GameThread extends Thread {
    private static double deltaTime;        // A legutóbbi render-update blokk között eltelt idő (ns)
    private static double lastLoopTime;   // A legutóbbi loop időpontja (ns)
    private static final int timeSlice = 1000000000 / Config.DISPLAY_TARGET_FPS; // Két render-update blokk között eltelő idő optimális esetben (ns)

    /**
     * A game loop indítása
     */
    @Override
    public void run() {
        lastLoopTime = System.nanoTime();

        while (Game.running) {
            deltaTime += System.nanoTime() - lastLoopTime;
            lastLoopTime = System.nanoTime();

            while (deltaTime > timeSlice) {
                Game.update();
                Game.render();
                deltaTime -= timeSlice;
            }
        }
    }
}
