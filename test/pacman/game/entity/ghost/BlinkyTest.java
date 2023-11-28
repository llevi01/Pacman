package pacman.game.entity.ghost;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.game.entity.pacman.Pacman;
import pacman.game.util.SpriteLoader;

import static org.junit.jupiter.api.Assertions.*;

class BlinkyTest {
    Pacman pacman;
    Blinky blinky;

    @BeforeAll
    static void loadSprites() {
        SpriteLoader.loadSprites();
    }

    @BeforeEach
    void setUp() {
        pacman = new Pacman();
        blinky = new Blinky(pacman);
    }

    @Test
    void toFrightenedState() {
        blinky.toFrightenedState();
        GhostState expected = GhostState.FRIGHTENED;
        GhostState actual = blinky.getState();
        assertEquals(expected, actual);
    }

    @Test
    void interactWhenNotFrightened() {
        blinky.interact();
        assertTrue(pacman.isHurt());
    }

    @Test
    void interactWhenFrightened() {
        blinky.toFrightenedState();
        blinky.interact();
        GhostState expected = GhostState.EATEN;
        GhostState actual = blinky.getState();
        assertEquals(expected, actual);
    }
}