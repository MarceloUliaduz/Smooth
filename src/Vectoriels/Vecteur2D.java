package Vectoriels;

import java.awt.geom.Point2D;
import java.util.function.BiFunction;

public class Vecteur2D {
    private double x, y;

    public Vecteur2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vecteur2D(Point2D p1, Point2D p2) {
        this(p2.getX() - p1.getX(), p2.getY() - p1.getY());
    }

    public Vecteur2D(Vecteur2D vecteur) {
        this(vecteur.getX(), vecteur.getY());
    }

    public Vecteur2D(double tangente) {
        double x, y;

        if (tangente == Double.POSITIVE_INFINITY) {
            x = 0;
            y = 1;
        } else if (tangente == Double.NEGATIVE_INFINITY) {
            x = 0;
            y = -1;
        } else {
            x = 1 / norme1(1, tangente);
            y = 1 - x;
        }

        this(x, y);
    }

    public Vecteur2D(double tangente, boolean renverserX) {
        this(tangente);

        if (renverserX) {
            x = -x;
        }
    }

    /**
     * Calculer la norme euclidienne selon les compostantes du vecteur
     *
     * @param dx composante en x
     * @param dy composante en y
     * @return la norme euclidienne
     */
    public static double normeEuclidienne(double dx, double dy) {
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double norme1(double dx, double dy) {
        return Math.abs(dx) + Math.abs(dy);
    }

    public static Vecteur2D somme(Vecteur2D v1, Vecteur2D v2) {
        return new Vecteur2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    public double getNorme(BiFunction<Double, Double, Double> norme) {
        return norme.apply(getX(), getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vecteur2D getVecteurNormalise(BiFunction<Double, Double, Double> fonctionNorme) {
        double norme = this.getNorme(fonctionNorme);

        return new Vecteur2D(x / norme, y / norme);
    }

    public void normaliser(BiFunction<Double, Double, Double> fonctionNorme) {
        if (x != 0 || y != 0) {
            double norme = this.getNorme(fonctionNorme);
            x = x / norme;
            y = y / norme;
        }
    }

    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}
