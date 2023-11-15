package pacman.game;

public class GameThread extends Thread {
    private static double deltaTime;        // A két legutóbbi frame render között eltelt idő (ns)
    private static double lastUpdateTime;   // A legutóbbi frame render időpontja (ns)
    private static double accumulator = 0;  // A legutóbbi update óta eltelt idő (ns)
    private static final double timeSlice = 1000000; // Két update között eltelő idő optimális esetben (ns)

    @Override
    public void run() {
        lastUpdateTime = System.nanoTime();

        while (Game.running) {
            deltaTime = System.nanoTime() - lastUpdateTime;
            lastUpdateTime += deltaTime;
            accumulator += deltaTime;

            while (accumulator > timeSlice) {
                Game.update(timeSlice / 1000000000);
                accumulator -= timeSlice;
            }
            // TODO draw
        }
    }
}
