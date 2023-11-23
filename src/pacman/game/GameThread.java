package pacman.game;

import pacman.game.util.Config;

/**
 * A game loop-ért felelős szál
 */
public class GameThread extends Thread {
    private static double deltaTime;        // A legutóbbi render-update blokk óta eltelt idő (ns)
    private static final double timeSlice = 500000000 / (double) Config.DISPLAY_TARGET_FPS; // Két render-update blokk között eltelő idő optimális esetben (ns) TODO nem jó
    private static double timer = 0;
    private static int drawCounter = 0;

    /**
     * A game loop indítása
     */
    @Override
    public void run() {
        // A legutóbbi loop időpontja (ns)
        double lastLoopTime = System.nanoTime();

        while (Game.running) {
            deltaTime += System.nanoTime() - lastLoopTime;
            timer += System.nanoTime() - lastLoopTime;
            lastLoopTime = System.nanoTime();

            while (deltaTime >= timeSlice) {
                Game.update();
                Game.render();
                deltaTime -= timeSlice;
                drawCounter++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCounter);
                drawCounter = 0;
                timer = 0;
            }
        }
    }
}
