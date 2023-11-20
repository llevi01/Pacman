package pacman.game.entity.ghost;

import pacman.game.entity.Entity;

public abstract class Ghost extends Entity {
    public Ghost(String id) {
        super(id);
        init();
        updateCurrentTile();
    }

    /**
     * A szellem aktuális állapota
     */
    protected GhostState state = GhostState.CHASE;

    protected GhostState nextState;

    protected int DEFAULT_SPEED = 4;
    protected int FRIGHTENED_SPEED = DEFAULT_SPEED / 2;
    protected int EATEN_SPEED = DEFAULT_SPEED * 2;

    /**
     * Frissíti a szellem állapotát
     * A játék elején a szellemek a CHASE és SCATTER
     * állapotok között váltanak
     */
    protected void updateState() {

    }

    public void toEatenState() {

    }
}
