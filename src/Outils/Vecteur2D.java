package Outils;

import java.awt.geom.Point2D;
import java.util.function.BiFunction;

public class Vecteur2D {
    private double x, y;

    /**
     * Créer un vecteur à partir de ses composantes en x et en y.
     *
     * @param x composante en x
     * @param y composante en y
     */
    public Vecteur2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Créer un vecteur à partir de deux points.
     *
     * <p>Le vecteur est de norme p1.distance(p2) et est dirigé de p1 vers p2</p>
     *
     * @param p1 point de départ
     * @param p2 point d'arrivée
     */
    public Vecteur2D(Point2D p1, Point2D p2) {
        this(p2.getX() - p1.getX(), p2.getY() - p1.getY());
    }

    /**
     * Créer un vecteur à partir d'un autre vecteur.
     * <p>C'est essentiellement une copie</p>
     *
     * @param vecteur vecteur à copier
     */
    public Vecteur2D(Vecteur2D vecteur) {
        this(vecteur.getX(), vecteur.getY());
    }

    /**
     * Créer un vecteur normé de norme euclidienne à partir d'une tangente.
     *
     * @param tangente tangente
     */
    public Vecteur2D(double tangente) {
        this(tangente, Vecteur2D::normeEuclidienne);
    }

    /**
     * Créer un vecteur normé de norme indiquée à partir d'une tangente.
     *
     * @param tangente      tangente
     * @param fonctionNorme norme
     */
    public Vecteur2D(double tangente, BiFunction<Double, Double, Double> fonctionNorme) {
        double x, y;

        if (tangente == Double.POSITIVE_INFINITY) {
            x = 0;
            y = 1;
        } else if (tangente == Double.NEGATIVE_INFINITY) {
            x = 0;
            y = -1;
        } else {
            double norme = fonctionNorme.apply(1d, tangente);
            x = 1 / norme;
            y = tangente / norme;
        }

        this(x, y);
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

    /**
     * Calculer la norme1 selon les compostantes du vecteur
     *
     * @param dx composante en x
     * @param dy composante en y
     * @return la norme1
     */
    public static double norme1(double dx, double dy) {
        return Math.abs(dx) + Math.abs(dy);
    }

    /**
     * Calcul le vecteur somme de deux vecteurs
     *
     * @param v1 vecteur
     * @param v2 vecteur
     * @return un vecteur étant la somme de deux autres vecteurs
     */
    public static Vecteur2D somme(Vecteur2D v1, Vecteur2D v2) {
        return new Vecteur2D(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }


    public static Vecteur2D muliplication(Vecteur2D v, double a) {
        return new Vecteur2D(v.getX() * a, v.getY() * a);
    }

    public static Vecteur2D rotation(Vecteur2D v, double raddeg) {
        double vx = v.getX(), vy = v.getY();

        double x = vx * Math.cos(raddeg) - vy * Math.sin(raddeg);
        double y = vy * Math.cos(raddeg) + vx * Math.sin(raddeg);

        return new Vecteur2D(x, y);
    }

    public void multiplier(double a) {
        x *= a;
        y *= a;
    }

    /**
     * Obtenir la norme indiquée du vecteur.
     *
     * @param norme norme voulue.
     * @return la norme voulue du vecteur.
     */
    public double getNorme(BiFunction<Double, Double, Double> norme) {
        return norme.apply(getX(), getY());
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getAngle() {
        return Math.atan2(y, x);
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

    public void tourner(double raddeg) {
        double xContainer = x;
        x = x * Math.cos(raddeg) - y * Math.sin(raddeg);
        y = y * Math.cos(raddeg) + xContainer * Math.sin(raddeg);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vecteur2D vecteur2D)) return false;
        return Double.compare(x, vecteur2D.x) == 0 && Double.compare(y, vecteur2D.y) == 0;
    }
}
