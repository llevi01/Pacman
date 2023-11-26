package pacman.game.entity.ghost;

import pacman.game.util.Config;

/**
 * A szellemek állapotai
 */
public enum GhostState {
    CHASE(Config.GHOST_CHASE_STATE_DELAY),
    SCATTER(Config.GHOST_SCATTER_STATE_DELAY),
    FRIGHTENED(Config.GHOST_FRIGHTENED_STATE_DELAY),
    EATEN(Config.GHOST_STATE_INFINITE_DELAY),
    INFINITE_CHASE(Config.GHOST_STATE_INFINITE_DELAY);

    /**
     * Ennyi ideig tart az adott állapot
     */
    private final int delay;
    GhostState(int delay) {
        this.delay = delay;
    }
    public int getDelay() {
        return delay;
    }
}
