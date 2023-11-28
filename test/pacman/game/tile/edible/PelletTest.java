package pacman.game.tile.edible;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pacman.game.tile.Coordinate;
import pacman.game.util.Config;

import static org.junit.jupiter.api.Assertions.*;
class PelletTest {

    Pellet pellet;
    Coordinate pos;

    @BeforeEach
    void setUp() {
        pos = new Coordinate(1, 1);
        pellet = new Pellet(pos, null);
    }

    @Test
    void getScoreModifier() {
        int expected = Config.PELLET_SCORE_MODIFIER;
        int res = pellet.getScoreModifier();
        assertEquals(expected, res);
    }

    @Test
    void toEatenState() {
        pellet.toEatenState();
        assertTrue(pellet.isEaten());
    }

    @Test
    void isWalkable() {
        assertTrue(pellet.isWalkable());
    }
}