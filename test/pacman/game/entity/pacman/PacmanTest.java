package pacman.game.entity.pacman;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.game.util.Config;
import pacman.game.util.SpriteLoader;

import static org.junit.jupiter.api.Assertions.*;

class PacmanTest {
    Pacman pacman;

    @BeforeAll
    static void loadSprites() {
        SpriteLoader.loadSprites();
    }

    @BeforeEach
    void setUp() {
        pacman = new Pacman();
    }

    @Test
    void getLives() {
        int expected = Config.PACMAN_LIVES;
        int actual = pacman.getLives();
        assertEquals(expected, actual);
    }

    @Test
    void hurt() {
        pacman.hurt();
        assertTrue(pacman.isHurt());
    }
}