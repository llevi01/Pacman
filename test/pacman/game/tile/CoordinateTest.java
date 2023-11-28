package pacman.game.tile;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {
    Coordinate coord1;
    Coordinate coord2;

    @BeforeEach
    void setUp() {
        coord1 = new Coordinate(1, 2);
        coord2 = new Coordinate(3, 4);
    }

    @Test
    void add() {
        Coordinate old1 = coord1;
        Coordinate expected = new Coordinate(4, 6);
        Coordinate res = coord1.add(coord2);
        assertEquals(expected, res);
        assertEquals(coord1, old1);
    }

    @Test
    void subtract() {
        Coordinate old1 = coord1;
        Coordinate expected = new Coordinate(-2, -2);
        Coordinate res = coord1.subtract(coord2);
        assertEquals(expected, res);
        assertEquals(coord1, old1);
    }

    @Test
    void multiply() {
        int n = 3;
        Coordinate old1 = coord1;
        Coordinate expected = new Coordinate(3, 6);
        Coordinate res = coord1.multiply(n);
        assertEquals(expected, res);
        assertEquals(coord1, old1);
    }

    @Test
    void testEquals() {
        Coordinate coord3 = new Coordinate(1, 2);
        assertNotEquals(coord1, coord2);
        assertEquals(coord1, coord3);
    }

    @Test
    void getDistance() {
        int a = coord1.x - coord2.x;
        int b = coord1.y - coord2.y;
        double expected = Math.sqrt(a * a + b * b);
        double res = coord1.getDistance(coord2);
        assertEquals(expected, res);
    }
}