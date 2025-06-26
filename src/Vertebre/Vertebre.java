package Vertebre;

import Outils.Vecteur2D;
import Outils.Vecteur2DNorme;

import java.awt.geom.Point2D;

/**
 * Classe d'une vertebre
 */
public class Vertebre {
    private Point2D position;
    private double rayon;
    private Vecteur2DNorme direction;

    public Vertebre(double x, double y, double rayon) {
        this.position = new Point2D.Double(x, y);
        this.rayon = rayon;
        this.direction = new Vecteur2DNorme(1, 0);
    }

    public Vertebre(Point2D position, double rayon) {
        this(position.getX(), position.getY(), rayon);
    }

    public Vertebre(double rayon) {
        this(0, 0, rayon);
    }

    public Vertebre(double x, double y, double rayon, Vecteur2DNorme direction) {
        this(x, y, rayon);
        this.direction = direction;
    }

    public Vertebre(Point2D position, double rayon, Vecteur2DNorme direction) {
        this(position.getX(), position.getY(), rayon, direction);
    }

    public Vertebre(Vertebre vertebre) {
        this(vertebre.getPosition(), vertebre.getRayon(), vertebre.getDirection());
    }

    public Point2D getPosition() {
        return new Point2D.Double(position.getX(), position.getY());
    }

    public void setPosition(Point2D position) {
        this.position.setLocation(position);
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public Vecteur2DNorme getDirection() {
        return new Vecteur2DNorme(direction);
    }

    public void setDirection(Vecteur2D direction) {
        this.direction = new Vecteur2DNorme(direction);
    }

    public void setPosition(double x, double y) {
        position.setLocation(x, y);
    }

    public void translater(double dx, double dy) {
        position.setLocation(position.getX() + dx, position.getY() + dy);
    }

    public void tourner(double radangle) {
        direction.tourner(radangle);
    }

    public Vecteur2D getOrthogonal() {
        return Vecteur2D.rotation(direction, Math.PI / 2);
    }
}
