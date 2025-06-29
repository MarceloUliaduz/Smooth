package Vertebre;

import Outils.Paire;
import Outils.Vecteur2D;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.function.Consumer;

public class Serpent extends AnimalVertebre {

    public Serpent(double x, double y, double rayon, Vecteur2D direction) {
        super(x, y, rayon, direction);
    }

    public Serpent(double x, double y, double rayon) {
        super(x, y, rayon);
    }

    public Serpent(Point2D position, double rayon) {
        super(position, rayon);
    }

    public Serpent(Point2D position, double rayon, Vecteur2D direction) {
        super(position, rayon, direction);
    }

    @Override
    public void avancer() {

    }
}
