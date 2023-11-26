package pacman.game.entity.ghost;

import pacman.game.Game;
import pacman.game.entity.Direction;
import pacman.game.entity.Entity;
import pacman.game.entity.pacman.Pacman;
import pacman.game.tile.Coordinate;
import pacman.game.tile.Tile;
import pacman.game.util.Config;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;

public abstract class Ghost extends Entity implements ActionListener {
    /**
     * Ghost default konstruktor
     * @param pacman Pacman referenciája
     */
    public Ghost(Pacman pacman) {
        this.pacman = pacman;
        init();
    }

    /**
     * A szellem aktuális állapota
     */
    private GhostState state;

    /**
     * Állapotváltást kezelő timer
     */
    private Timer stateTimer;

    /**
     * Ebben az időpontban váltott FRIGHTENED állapotba a szellem
     */
    private long enterFrightened;

    /**
     * A szellem következő állapotai
     */
    private final ArrayList<GhostState> nextStates = new ArrayList<>();

    /**
     * Cella, ami felé megy a szellem
     */
    private Coordinate target;

    /**
     * Pacman referenciája
     */
    protected Pacman pacman;

    /**
     * A FRIGHTENED állapotú szellemek sprite-jai
     */
    protected List<BufferedImage> frightenedSprites;

    /**
     * Az EATEN állapotú szellemek sprite-jai
     */
    protected Map<Direction, BufferedImage> eatenSprites;


    /**
     * Frissíti a szellem aktuális sprite-ját
     * Több féle sprite-ja van, más logika szerint
     * működik, mint az Entity metódusa
     */
    @Override
    protected void updateSprite() {
        switch (state) {
            case CHASE, SCATTER, INFINITE_CHASE -> super.updateSprite();
            case EATEN -> updateEatenSprite();
            case FRIGHTENED -> updateFrightenedSprite();
        }
    }

    private void updateEatenSprite() {
        sprite = eatenSprites.get(direction);
    }

    private void updateFrightenedSprite() {
        if (animationDrawCounter < Config.ENTITY_ANIMATION_FPS * 2) {
            animationDrawCounter++;
            return;
        }
        long fullTime = GhostState.FRIGHTENED.getDelay();
        long passed = System.currentTimeMillis() - enterFrightened;
        boolean flashing = passed >= fullTime * 3 / 4;

        spriteIndex++;
        spriteIndex %= flashing ? frightenedSprites.size() : frightenedSprites.size() / 2;
        sprite = frightenedSprites.get(spriteIndex);
        animationDrawCounter = 0;
    }

    /**
     * Inicializáló metódus
     */
    protected void init() {
        // Állapotok inicializálása
        state = GhostState.SCATTER;
        stateTimer = new Timer(state.getDelay(), this);
        stateTimer.setRepeats(false);

        for (int i = 0; i < 3; i++) {
            nextStates.add(GhostState.CHASE);
            nextStates.add(GhostState.SCATTER);
        }
        nextStates.add(GhostState.INFINITE_CHASE);

        // Többi attribútum inicializálása
        speed = Config.GHOST_DEFAULT_SPEED;
        toStartingPos();
        initSprites();
    }

    /**
     * Visszaadja a szellem target celláját CHASE állapotban
     */
    protected abstract Coordinate getChaseTarget();

    /**
     * Visszaadja a szellem target celláját SCATTER állapotban
     */
    protected abstract Coordinate getScatterTarget();

    /**
     * Kezdő állapotba helyezi a szellemeket
     */
    public void reset() {
        if (stateTimer.isRunning()) {
            stateTimer.stop();
        }
        toStartingPos();
        if (state.equals(GhostState.EATEN) || state.equals(GhostState.FRIGHTENED)) {
            state = nextStates.remove(0);
            stateTimer.setInitialDelay(state.getDelay());
        }
    }

    /**
     * Minden belső logikát kezelő függvény
     */
    @Override
    public void update() {
        if (!stateTimer.isRunning()) {
            stateTimer.start();
        }
        if (state.equals(GhostState.EATEN) && isInsideHouse()) {
            endEatenState();
        }
        updateTargetTile();
        updateDirection();

        updateSpeed();
        position = position.add(direction.getVector().multiply(speed));

        checkOutOfFrame();
        checkWallCollisions(); // ez nem kellene ide, biztonság kedvéért mégis itt van
    }

    /**
     * Frissíti a szellem állapotát
     * A játék elején a szellemek a CHASE és SCATTER
     * állapotok között váltanak, majd CHASE állapotban maradnak
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        state = nextStates.remove(0);
        if (!isInsideHouse()) {
            direction = direction.inverse();
        }
        if (state.getDelay() != Config.GHOST_STATE_INFINITE_DELAY) {
            stateTimer.setInitialDelay(state.getDelay());
            stateTimer.start();
        }
    }

    /**
     * EATEN állapotba váltja a szellemet.
     * Ez a metódus a szellemet a legközelebbi Tile közepére helyezi,
     * így biztosan el tud majd fordulni.
     */
    private void toEatenState() {
        stateTimer.stop();
        state = GhostState.EATEN;
        direction = direction.inverse();
        position = new Coordinate(
                (getMapPosition().x * Config.TILE_SPRITE_SIZE + 3) * Config.SCALE,
                (getMapPosition().y * Config.TILE_SPRITE_SIZE + 3) * Config.SCALE
        );
    }

    /**
     * FRIGHTENED állapotba váltja a szellemet.
     * Pacman hívja meg, amikor elfogyaszt egy PowerPellet-et.
     */
    public void toFrightenedState() {
        if (state.equals(GhostState.EATEN)) {
            return;
        }
        enterFrightened = System.currentTimeMillis();
        stateTimer.stop();

        if (!state.equals(GhostState.FRIGHTENED)) {
            nextStates.add(0, state);
        }
        state = GhostState.FRIGHTENED;
        stateTimer.setInitialDelay(state.getDelay());
        stateTimer.start();
        direction = direction.inverse();
    }

    private void endEatenState() {
        state = nextStates.remove(0);
        if (state.getDelay() != Config.GHOST_STATE_INFINITE_DELAY) {
            stateTimer.setInitialDelay(state.getDelay());
            stateTimer.start();
        }
    }

    /**
     * Interakció Pacman és a szellem között
     * Pacman hívja meg, amikor egy Tile-re
     * kerülnek a szellemmel
     */
    public void interact() {
        switch (state) {
            case CHASE, SCATTER, INFINITE_CHASE -> pacman.hurt();
            case FRIGHTENED -> {
                pacman.ghostEaten();
                toEatenState();
            }
        }
    }

    /**
     * Frissíti a szellem sebességét az állapotától függően
     */
    private void updateSpeed() {
        switch (state) {
            case CHASE, SCATTER, INFINITE_CHASE -> speed = Config.GHOST_DEFAULT_SPEED;
            case FRIGHTENED -> speed = Config.GHOST_FRIGHTENED_SPEED;
            case EATEN -> speed = Config.GHOST_EATEN_SPEED;
        }

        // Ha lassú zónában van, olyan lassan megy, mint FRIGHTENED állapotban
        if (isInSlowZone() && !state.equals(GhostState.EATEN)) {
            speed = Config.GHOST_FRIGHTENED_SPEED;
        }
    }

    /**
     * Frissíti a szellem irányát
     */
    protected void updateDirection() {
        Coordinate mapPos = getMapPosition();
        Coordinate midTile = new Coordinate(
                (mapPos.x * Config.TILE_SPRITE_SIZE + 3) * Config.SCALE,
                (mapPos.y * Config.TILE_SPRITE_SIZE + 3) * Config.SCALE
        );
        // Nem változtat irányt ha nem egy Tile közepén van
        if (!position.equals(midTile)) {
            return;
        }
        // Ha olyan helyen van ahol nem fordulhat, visszatérünk
        // Ez alól kivétel, ha felfelé megy, mivel ilyenkor jön ki a házból
        // Illetve, amikor EATEN állapotban van, mivel ilyenkor megy be a házba
        if (isInNoTurnZone() && !state.equals(GhostState.EATEN) &&
                !direction.equals(Direction.UP) && !direction.equals(Direction.DOWN)) {
            return;
        }

        List<Direction> validMoves = getValidMoves();

        // Ha nincs érvényes lépés, nem változtatunk irányt (ez az alagútban fordulhat elő)
        if (validMoves.isEmpty()) {
            return;
        }
        // Ha csak egy irányba tudunk menni, azt választjuk
        if (validMoves.size() == 1) {
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
                case 0 -> bestMoves.add(dir);
                case -1 -> {
                    bestMoves.clear();
                    bestMoves.add(dir);
                    lowestDist = dist;
                }
            }
        }
        // Az irányokat a prioritásuk szerint rendezzük
        bestMoves.sort(Comparator.comparing(Direction::getPriority));
        return bestMoves.get(0);
    }

    private int compareDistances(double dist1, double dist2) {
        double delta = 0.0001d;
        if ((Math.abs(dist1 - dist2)) < delta) {
            return 0;
        }
        return dist1 < dist2 ? -1 : 1;
    }

    protected void updateTargetTile() {
        if (isInsideHouse()) {
            target = Config.IN_FRONT_OF_GHOST_HOUSE;
            return;
        }
        switch (state) {
            case CHASE, INFINITE_CHASE -> target = getChaseTarget();
            case SCATTER -> target = getScatterTarget();
            case FRIGHTENED -> target = null;
            case EATEN -> target = isInFrontOfHouse() ? Config.INSIDE_GHOST_HOUSE : Config.IN_FRONT_OF_GHOST_HOUSE;
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
        return (mapPos.y >= 12 && mapPos.y <= 15) && (mapPos.x >= 12 && mapPos.x <= 16);
    }

    protected boolean isInFrontOfHouse() {
        return getMapPosition().equals(Config.IN_FRONT_OF_GHOST_HOUSE);
    }
}
