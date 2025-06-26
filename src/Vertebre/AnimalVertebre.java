package Vertebre;

import Outils.Vecteur2D;
import Outils.Vecteur2DNorme;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public abstract class AnimalVertebre {
    private List<Vertebre> vertebres;
    private Vecteur2DNorme vecteurDirection;
    private double vitesse;

    public AnimalVertebre(double x, double y, double rayon, Vecteur2D direction) {
        vertebres = new ArrayList<>();
        vecteurDirection = new Vecteur2DNorme(direction);

        vertebres.add(new Vertebre(x, y, rayon, vecteurDirection));
    }

    public AnimalVertebre(double x, double y, double rayon) {
        this(x, y, rayon, new Vecteur2DNorme(0));
    }

    public AnimalVertebre(Point2D position, double rayon) {
        this(position, rayon, new Vecteur2DNorme(0));
    }

    public AnimalVertebre(Point2D position, double rayon, Vecteur2D direction) {
        this(position.getX(), position.getY(), rayon, direction);
    }

    void ajouterVertebre(Vertebre vertebre) {
        vertebres.add(vertebre);
    }

    Point2D getPositionTete() {
        return vertebres.getFirst().getPosition();
    }
}
