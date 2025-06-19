package Outils;

import java.awt.geom.Point2D;
import java.util.function.Function;

public class HermiteSpline {
    /**
     * Fonctions de bases d'Hermite.
     */
    public static final Function<Double, Double> H00 = t -> 2 * t * t * t - 3 * t * t + 1,
            H10 = t -> t * t * t - 2 * t * t + t,
            H01 = t -> -2 * t * t * t + 3 * t * t,
            H11 = t -> t * t * t - t * t;

    /**
     * Créer la fonction qui définit la spline cubique d'Hermite entre deux points sur un intervalle arbitraire.
     *
     * @param p0 Point de départ
     * @param m0 Tangente au point de départ
     * @param p1 Point d'arrivée
     * @param m1 Tangente au point d'arrivée
     * @return Fonction qui définit la spline cubique d'Hermite
     */
    public static Function<Double, Point2D> segmentFunction(Point2D p0, double m0, Point2D p1, double m1, double intervalle) {

        return new Function<Double, Point2D>() {
            @Override
            public Point2D apply(Double x) {
                double t = (x - p0.getX()) / intervalle;
                double y = H00.apply(t) * p0.getY() + H10.apply(t) * intervalle * m0 +
                        H01.apply(t) * p1.getY() + H11.apply(t) * intervalle * m1;
                return new Point2D.Double(x, y);
            }
        };

    }
}
