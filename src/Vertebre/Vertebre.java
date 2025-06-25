package Vertebre;

import Outils.Vecteur2D;

import java.awt.geom.Point2D;

/**
 * Classe d'une vertebre
 */
public class Vertebre {
    private Point2D position;
    private double largeur;
    private Vecteur2D direction;

    public Vertebre(double x, double y, double largeur) {
        this.position = new Point2D.Double(x, y);
        this.largeur = largeur;
        this.direction = new Vecteur2D(1, 0);
    }

    public Vertebre(Point2D position, double largeur) {
        this(position.getX(), position.getY(), largeur);
    }

    public Vertebre(double x, double y, double largeur, Vecteur2D direction) {
        this(x, y, largeur);
        this.direction = direction;
    }

    public Vertebre(Point2D position, double largeur, Vecteur2D direction) {
        this(position.getX(), position.getY(), largeur, direction);
    }

    public Point2D getPosition() {
        return new Point2D.Double(position.getX(), position.getY());
    }

    public void setPosition(Point2D position) {
        this.position.setLocation(position);
    }

    public double getLargeur() {
        return largeur;
    }

    public void setLargeur(double largeur) {
        this.largeur = largeur;
    }

    public Vecteur2D getDirection() {
        return new Vecteur2D(direction);
    }

    public void setDirection(Vecteur2D direction) {
        this.direction = new Vecteur2D(direction);
    }

    public void setPosition(double x, double y) {
        position.setLocation(x, y);
    }

    public void translater(double dx, double dy) {
        position.setLocation(position.getX() + dx, position.getY() + dy);
    }

    public void tourner(double raddeg) {
        direction.tourner(raddeg);
    }

    public Vecteur2D getOrthogonal() {
        return Vecteur2D.rotation(direction, Math.PI / 2);
    }
}
