package Outils;

import Vectoriels.Vecteur2D;

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
    public static Function<Double, Point2D> segmentFunction(Point2D p0, Vecteur2D m0, Point2D p1, Vecteur2D m1) {
        // Récupérer tout ce qui est relatif aux points directeurs (car ils ne changent pas)
        double distancePoints = p0.distance(p1);
        double p0x = p0.getX(), p0y = p0.getY(),
                p1x = p1.getX(), p1y = p1.getY(),
                m0x = m0.getX(), m0y = m0.getY(),
                m1x = m1.getX(), m1y = m1.getY();

        return new Function<Double, Point2D>() {
            @Override
            public Point2D apply(Double t) {
                // t entre 0 et 1
                double ty = H00.apply(t) * p0y + H01.apply(t) * p1y
                        + distancePoints * (H10.apply(t) * m0y + H11.apply(t) * m1y);

                double tx = H00.apply(t) * p0x + H01.apply(t) * p1x
                        + distancePoints * (H10.apply(t) * m0x + H11.apply(t) * m1x);

                return new Point2D.Double(tx, ty);
            }
        };

    }
}
