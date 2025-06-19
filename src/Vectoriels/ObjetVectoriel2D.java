package Vectoriels;

import java.awt.geom.Point2D;
import java.util.function.Function;

public abstract class ObjetVectoriel2D {
    /**
     * Fonction qui définit les points de la courbe.
     * <p>
     * Elle prend en paramètre un double compris entre 0 et 1 inclus représentant approximativement
     * la proportions de la courbe sur laquelle se trouve le point.
     */
    protected Function<Double, Point2D> function;

    /**
     * Obtenir la fonction qui définit la courbe
     *
     * @return Fonction qui définit les points de la courbe
     */
    public Function<Double, Point2D> getFunction() {
        return function;
    }
}
