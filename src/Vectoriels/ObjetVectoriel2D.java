package Vectoriels;

import java.awt.geom.Point2D;
import java.util.function.Function;

public abstract class ObjetVectoriel2D {
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
