import Vectoriels.CourbeVectorielle2D;
import Vectoriels.SplineCubiqueHermite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;

class SplineCubiqueHermiteTest {
    final static double EPSILON = 0.0001d;

    private static ArrayList<Point2D> points_l1 = new ArrayList<>();
    private static ArrayList<Double> tangentes_l1 = new ArrayList<>();
    private static Point2D.Double p1 = new Point2D.Double(1, 1),
            p2 = new Point2D.Double(2, 2),
            p3 = new Point2D.Double(3, 3);


    @BeforeAll
    static void initAll() {
        points_l1.add(p1);
        points_l1.add(p2);
        points_l1.add(p3);

        tangentes_l1.add(1d);
        tangentes_l1.add(1d);
        tangentes_l1.add(1d);
    }

    @Test
    void ligneDroite() {
        SplineCubiqueHermite droite = new SplineCubiqueHermite(points_l1, tangentes_l1);

        ArrayList<Point2D> points = droite.getPointsInterpolation(3);
        Assertions.assertEquals(points.get(0).getX(), 1d, EPSILON);
        Assertions.assertEquals(points.get(1).getX(), 2d, EPSILON);
        Assertions.assertEquals(points.get(2).getX(), 3d, EPSILON);

        droite.ajouterPointDirecteur(new Point2D.Double(4, 4), 1d);
        points.clear();
        points = droite.getPointsInterpolation(4);
        Assertions.assertEquals(points.get(0).getX(), 1d, EPSILON);
        Assertions.assertEquals(points.get(1).getX(), 2d, EPSILON);
        Assertions.assertEquals(points.get(2).getX(), 3d, EPSILON);
        Assertions.assertEquals(points.get(3).getX(), 4d, EPSILON);
    }
}