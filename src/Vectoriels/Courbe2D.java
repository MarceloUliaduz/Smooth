package Vectoriels;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Spécifiaction d'une courbe sur un espace 2D
 *
 * @author Camille
 */
public interface Courbe2D {
    /**
     * Obtenir un nombre de points voulu de la courbe, chaque point étant à intervalle régulier
     *
     * @param nombre_points Le nombre de points voulu de la courbe
     * @return La liste de 'nombre_points' points de la courbe
     */
    ArrayList<Point2D> getPointsInterpolation(int nombre_points);
}
