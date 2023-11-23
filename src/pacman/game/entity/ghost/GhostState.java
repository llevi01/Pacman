package pacman.game.entity.ghost;

import pacman.game.util.Config;

/**
 * A szellemek állapotai
 */
public enum GhostState {
    CHASE(Config.GHOST_CHASE_STATE_TIME),
    SCATTER(Config.GHOST_SCATTER_STATE_TIME),
    FRIGHTENED(Config.GHOST_FRIGHTENED_STATE_TIME),
    EATEN(Config.GHOST_STATE_INFINITE_TIME),
    INFINITE_CHASE(Config.GHOST_STATE_INFINITE_TIME);

    /**
     * Ennyi ideig tart az adott állapot
     */
    private final int time;
    GhostState(int time) {
        this.time = time;
    }
    public int getTime() {
        return time;
    }
}
