package pacman.game.util;

public enum Error {
    LOADING_SPRITES("Error while loading sprites"),
    LOADING_MAP("Error while loading map");
    public final String message;
    Error(String message) {
        this.message = message;
    }
}
