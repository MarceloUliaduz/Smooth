package Outils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Vecteur2DTest {
    final static double EPSILON = 0.0001d;
    private static Vecteur2D v1;

    @BeforeEach
    void init() {
        v1 = new Vecteur2D(1, 0);
    }

    @Test
    public void tourner() {
        v1.tourner(Math.PI/4);
        Assertions.assertEquals(Math.PI/4, v1.getAngle(), EPSILON);
        v1.tourner(-Math.PI/4);
        Assertions.assertEquals(0, v1.getAngle(), EPSILON);
        v1.tourner(Math.PI);
        Assertions.assertEquals(-1, v1.getX(), EPSILON);
        Assertions.assertEquals(0, v1.getY(), EPSILON);
    }
}