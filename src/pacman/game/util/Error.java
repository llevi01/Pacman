package pacman.game.util;

/**
 * Lehetséges hibajelzéseket, és azok üzeneteit tároló enum
 */
public enum Error {
    LOADING_SPRITES("Failed to load sprites"),
    LOADING_MAP("Failed to load map"),
    LOADING_LEADERBOARD("Failed to load leaderboard"),
    SAVING_LEADERBOARD("Failed to save leaderboard"),
    CONNECTING_TO_SERVER("Failed to connect to server"),
    LOADING_CONFIG("Failed to load configuration");
    public final String message;
    Error(String message) {
        this.message = message;
    }
}
