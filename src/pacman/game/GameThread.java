package pacman.game;

/**
 * A game loop-ért felelős szál
 */
public class GameThread extends Thread {
    private static double deltaTime;        // A két legutóbbi frame render között eltelt idő (ns)
    private static double lastRenderTime;   // A legutóbbi frame render időpontja (ns)
    private static double accumulator = 0;  // A legutóbbi update óta eltelt idő (ns)
    private static final int timeSlice = 1000000; // Két update között eltelő idő optimális esetben (ns)
    private static final double step = timeSlice / 1000000000.0; // Két update között eltelő idő (s)

    /**
     * A game loop indítása
     */
    @Override
    public void run() {
        lastRenderTime = System.nanoTime();

        while (Game.running) {
            deltaTime = System.nanoTime() - lastRenderTime;
            lastRenderTime += deltaTime;
            accumulator += deltaTime;

            while (accumulator > timeSlice) {
                //Game.update(step);
                accumulator -= timeSlice;
            }

            Game.render();
        }
    }
}
