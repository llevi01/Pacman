package pacman.game.entity.ghost;

public enum GhostState {
    CHASE(20),
    SCATTER(7),
    FRIGHTENED(20),
    EATEN(-1);

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
        return remainingTime == -1;
    }
}
