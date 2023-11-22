package pacman.game.entity.ghost;

import pacman.game.util.Config;

public enum GhostState {
    CHASE(Config.GHOST_CHASE_STATE_TIME),
    SCATTER(Config.GHOST_SCATTER_STATE_TIME),
    FRIGHTENED(Config.GHOST_FRIGHTENED_STATE_TIME),
    EATEN(Config.GHOST_EATEN_STATE_TIME);

    /**
     *
     */
    private int remainingTime;
    GhostState(int remainingTime) {
        this.remainingTime = remainingTime;
    }
    public int getRemainingTime() {
        return remainingTime;
    }
    public void decreaseRemainingTime() {
        remainingTime--;
    }
    public boolean isOver() {
        return remainingTime == 0;
    }
    public void setInfiniteRemainingTime() {
        remainingTime = -1;
    }
    public boolean isInfinite() {
        return remainingTime < 0;
    }
}
