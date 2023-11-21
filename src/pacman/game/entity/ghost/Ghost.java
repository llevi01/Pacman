package pacman.game.entity.ghost;

import pacman.game.Game;
import pacman.game.entity.Direction;
import pacman.game.entity.Entity;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.util.Config;

import java.awt.image.BufferedImage;
import java.util.*;

public abstract class Ghost extends Entity {
    /**
     * Ghost default konstruktor
     * @param id A szellem neve
     * @param pacman Pacman referenciája
     */
    public Ghost(String id, Pacman pacman) {
        super(id);
        this.pacman = pacman;
        init();
        updateCurrentTile();
    }

    /**
     * A szellem aktuális állapota
     */
    private GhostState state;
    /**
     * A szellem következő állapotai
     */
    private ArrayList<GhostState> nextStates = new ArrayList<>();
    /**
     * Cella, ami felé megy a szellem
     */
    private Coordinate target;
    /**
     * A szellem target cellája SCATTER állapotban
     */
    protected Coordinate SCATTER_TARGET;
    /**
     * A ház előtti cella helye
     * A játék elején és EATEN állapotban kell
     */
    private final Coordinate IN_FRONT_OF_HOUSE = new Coordinate(14,11);
    /**
     * A ház egyik belső cellájának helye
     * EATEN állapotban kell
     */
    private final Coordinate INSIDE_HOUSE = new Coordinate(14, 14);

    /**
     * Pacman referenciája
     */
    protected Pacman pacman;

    /**
     * A szellem normál sebessége
     */
    private int DEFAULT_SPEED = 4 * Config.SCALE;
    /**
     * A szellem sebessége FRIGHTENED állapotban
     * Ez a sebessége a lassú zónákban is
     */
    private int FRIGHTENED_SPEED = DEFAULT_SPEED / 2;
    /**
     * A szellem sebessége EATEN állapotban
     */
    private int EATEN_SPEED = DEFAULT_SPEED * 2;

    /**
     * Az EATEN állapotú szellemek sprite-jai TODO load
     */
    private Map<Direction, BufferedImage> eatenSprites;

    /**
     * A FRIGHTENED állapotú szellemek sprite-jai TODO load
     */
    private List<BufferedImage> frightenedSprites;


    /**
     * Frissíti a szellem aktuális sprite-ját
     * Több féle sprite-ja van, más logika szerint
     * működik, mint az Entity metódusa
     */
    @Override
    protected void updateSprite() {
        switch (state) {
            case CHASE, SCATTER -> super.updateSprite();
            case EATEN -> updateEatenSprite();
            case FRIGHTENED -> updateFrightenedSprite();
        }
    }

    private void updateEatenSprite() {
        sprite = eatenSprites.get(direction);
    }

    private void updateFrightenedSprite() {
        int fullTime = GhostState.FRIGHTENED.getRemainingTime();
        int remainingTime = state.getRemainingTime();
        if (remainingTime > fullTime / 4) {
            sprite = frightenedSprites.get(0);
            return;
        }

        if (animationFrameCounter < ANIMATION_FPS * 2) {
            animationFrameCounter++;
            return;
        }
        spriteIndex++;
        spriteIndex %= frightenedSprites.size();
        sprite = frightenedSprites.get(spriteIndex);
        animationFrameCounter = 0;
    }

    /**
     * Inicializáló metódus
     */
    protected void init() {
        // Állapotok inicializálása
        state = GhostState.SCATTER;

        for (int i = 0; i < 3; i++) {
            nextStates.add(GhostState.CHASE);
            nextStates.add(GhostState.SCATTER);
        }
        GhostState lastState = GhostState.CHASE;
        lastState.setInfiniteRemainingTime();
        nextStates.add(lastState);
    }

    /**
     * CHASE állapotban visszaadja a szellem target celláját
     * Szellemenként eltér az implementációja
     */
    protected abstract Coordinate getChaseTarget();

    /**
     * Minden belső logikát kezelő függvény
     */
    @Override
    public void update() {
        updateState();
        updateTargetTile();
        updateDirection();

        updateSpeed();
        position = position.add(direction.getVector()).multiply(speed);

        checkWallCollisions(); // ez nem kellene ide, biztonság kedvéért mégis itt van
        checkOutOfFrame();

        updateCurrentTile();
    }

    /**
     * Frissíti a szellem állapotát
     * A játék elején a szellemek a CHASE és SCATTER
     * állapotok között váltanak, majd CHASE állapotban maradnak
     */
    private void updateState() {
        if (state.isOver() ||
                state.equals(GhostState.EATEN) && isInsideHouse()) {
            state = nextStates.get(0);
            direction = direction.inverse();
            return;
        }
        state.decreaseRemainingTime();
    }

    private void toEatenState() {
        nextStates.add(0, state);
        state = GhostState.EATEN;
        direction = direction.inverse();
    }

    /**
     * A szellemet FRIGHTENED állapotba váltja
     * Pacman hívja meg, amikor elfogyaszt egy
     * PowerPellet-et
     */
    public void toFrightenedState() {
        nextStates.add(0, state);
        state = GhostState.FRIGHTENED;
        direction = direction.inverse();
    }

    /**
     * Interakció Pacman és a szellem között
     * Pacman hívja meg, amikor egy Tile-re
     * kerülnek a szellemmel
     */
    public void interact() {
        switch (state) {
            case CHASE, SCATTER -> pacman.hurt();
            case EATEN -> toEatenState();
        }
    }

    /**
     * Frissíti a szellem sebességét az állapotától függően
     */
    private void updateSpeed() {
        switch (state) {
            case CHASE, SCATTER -> speed = DEFAULT_SPEED;
            case FRIGHTENED -> speed = FRIGHTENED_SPEED;
            case EATEN -> speed = EATEN_SPEED;
        }

        // Ha lassú zónában van, olyan lassan megy, mint FRIGHTENED állapotban
        if (isInSlowZone() && !state.equals(GhostState.EATEN)) {
            speed = FRIGHTENED_SPEED;
        }
    }

    /**
     * Frissíti a szellem irányát
     */
    protected void updateDirection() {
        Coordinate mapPos = getMapPosition();
        Coordinate midTile = new Coordinate(
                (mapPos.x * Config.TILE_SIZE + 3) * Config.SCALE,
                (mapPos.y * Config.TILE_SIZE + 3) * Config.SCALE
        );
        // Nem változtat ha nem egy Tile közepén van
        if (!position.equals(midTile)) {
            return;
        }
        // Ha olyan helyen van ahol nem fordulhat, visszatérünk
        // Ez alól kivétel, ha felfelé megy, mivel ilyenkor jön ki a házból
        if (isInNoTurnZone() && !direction.equals(Direction.UP)) {
            return;
        }

        List<Direction> validMoves = getValidMoves();

        // Ha csak egy irányba tudunk menni, azt választjuk
        if (validMoves.size() < 2) {
            direction = validMoves.get(0);
        }

        direction = bestMove(validMoves);
    }

    /**
     * Visszaadja az érvényes lépéseket a jelenlegi Tile-ből kiindulva
     * @return Érvényes lépések listája
     */
    private List<Direction> getValidMoves() {
        List<Direction> res = new ArrayList<>();
        Coordinate mapPos = getMapPosition();
        Coordinate tileCoords;
        Tile tile;
        for (Direction dir : Direction.values()) {
            // Ha ezt az irányt választva megfordulna, vagy nem mozdulna, továbbmegyünk
            if (direction.inverse().equals(dir) || dir.equals(Direction.NONE)) {
                continue;
            }

            // Megkeressük az irányhoz tartozó Tile-t
            tileCoords = mapPos.add(dir.getVector());
            try {
                tile = Game.map.get(tileCoords.y).get(tileCoords.x);
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            // Ha a Tile-re rá lehet lépni, hozzáadjuk a listához
            if (tile.isWalkable()) {
                res.add(dir);
            }
        }
        return res;
    }

    /**
     * Visszaadja a legjobb lépést
     * A lépéseket a target-től való távolságuk,
     * azon belül a prioritásuk szerint választja ki
     */
    private Direction bestMove(List<Direction> moves) {
        if (state.equals(GhostState.FRIGHTENED)) {
            Random random = new Random();
            int index = random.nextInt(moves.size());
            return moves.get(index);
        }
        Coordinate ghostPos = getMapPosition();
        List<Direction> bestMoves = new ArrayList<>();
        double lowestDist = 99999.0;

        // Berakjuk a listába a legkisebb távolságú irányokat
        for (Direction dir : moves) {
            if (dir.equals(Direction.NONE)) {
                continue;
            }

            Coordinate tilePos = ghostPos.add(dir.getVector());
            double dist = tilePos.getDistance(target);

            switch (compareDistances(dist, lowestDist)) {
                case 0 -> {
                    bestMoves.add(dir);
                }
                case -1 -> {
                    bestMoves.clear();
                    bestMoves.add(dir);
                }
            }
        }
        // Az irányokat a prioritásuk szerint rendezzük
        bestMoves.sort(Comparator.comparing(Direction::getPriority));
        return bestMoves.get(0);
    }

    private int compareDistances(double dist1, double dist2) {
        double delta = 0.0001d;
        if ((dist1 - dist2) < delta) {
            return 0;
        }
        return dist1 < dist2 ? -1 : 1;
    }

    protected void updateTargetTile() {
        if (isInsideHouse()) {
            target = IN_FRONT_OF_HOUSE;
        }
        switch (state) {
            case CHASE -> target = getChaseTarget();
            case SCATTER -> target = SCATTER_TARGET;
            case FRIGHTENED -> target = null;
            case EATEN -> target = isInFrontOfHouse() ? INSIDE_HOUSE : IN_FRONT_OF_HOUSE;
        }
    }

    private boolean isInNoTurnZone() {
        Coordinate mapPos = getMapPosition();
        return (mapPos.y == 11 || mapPos.y == 23) && (mapPos.x >= 11 && mapPos.x <= 16);
    }
    private boolean isInSlowZone() {
        Coordinate mapPos = getMapPosition();
        return mapPos.y == 14 && (mapPos.x <= 4 || mapPos.x >= 22) ;
    }

    protected boolean isInsideHouse() {
        Coordinate mapPos = getMapPosition();
        return (mapPos.y >= 13 && mapPos.y <= 15) && (mapPos.x >= 12 && mapPos.x <= 16);
    }

    protected boolean isInFrontOfHouse() {
        return position.equals(IN_FRONT_OF_HOUSE);
    }
}
