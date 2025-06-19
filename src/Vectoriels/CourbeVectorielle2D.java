package Vectoriels;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Spécifiaction d'une courbe vectorielle sur un espace 2D
 *
 * @author Camille
 */
public abstract class CourbeVectorielle2D extends ObjetVectoriel2D implements Courbe2D {

    public ArrayList<Point2D> getPointsInterpolation(int nombrePoints) {
        assert function != null;

        ArrayList<Point2D> points = new ArrayList<>(nombrePoints);
        int diviseur = nombrePoints - 1;

        for (double i = 0; i < nombrePoints; i++) {
            points.add(function.apply(i / diviseur));
        }

        return points;
    }
}
