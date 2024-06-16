package pacman.game;

/**
 * Játékost reprezentáló osztály
 */
public class Player {
    public String name;
    public int score;

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public Player() {}
}
