package pacman.game.entity.ghost;

import pacman.game.entity.Direction;
import pacman.game.entity.Entity;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.util.Config;

import java.util.ArrayList;

public abstract class Ghost extends Entity {
    public Ghost(String id, Pacman pacman) {
        super(id);
        this.pacman = pacman;
        init();
        updateCurrentTile();

        for (int i = 0; i < 3; i++) {
            nextStates.add(GhostState.CHASE);
            nextStates.add(GhostState.SCATTER);
        }
        GhostState lastState = GhostState.CHASE;
        lastState.setInfiniteRemainingTime();
        nextStates.add(lastState);
    }

    /**
     * A szellem aktuális állapota
     */
    protected GhostState state = GhostState.SCATTER;
    protected ArrayList<GhostState> nextStates = new ArrayList<>();
    protected Coordinate target;

    protected final Coordinate inFrontOfGhostHouse = new Coordinate(0,0);

    // Pacman referenciája
    protected Pacman pacman;

    protected int DEFAULT_SPEED = 4 * Config.SCALE;
    protected int FRIGHTENED_SPEED = DEFAULT_SPEED / 2;
    protected int EATEN_SPEED = DEFAULT_SPEED * 2;

    @Override
    public void update() {

    }

    /**
     * Frissíti a szellem állapotát
     * A játék elején a szellemek a CHASE és SCATTER
     * állapotok között váltanak, majd CHASE állapotban maradnak
     */
    protected void updateState() {

    }

    public void toFrightenedState() {

    }

    public void toEatenState() {

    }

    protected void updateDirection() {

    }

    private boolean inNoTurnZone() {
        return false;
    }

    protected void updateTargetTile() {

    }

    protected boolean isInsideGhostHouse() {
        return false;
    }

    protected ArrayList<Direction> getValidMoves() {
        return new ArrayList<>();
    }

    protected abstract Coordinate getChaseTarget();
}
