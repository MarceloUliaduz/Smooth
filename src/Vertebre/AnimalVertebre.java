package Vertebre;

import Outils.Paire;
import Outils.Vecteur2D;
import Outils.Vecteur2DNorme;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.function.Consumer;

/**
 * Classe d'un animal vertébré.
 * <p>
 * Un animal vertebré est une liste de vertebres et deux leurs distances aux précédentes
 */
public abstract class AnimalVertebre implements Iterable<Paire<Vertebre, Double>> {
    private CelluleVertebre tete;
    private CelluleVertebre queue;
    private Vecteur2DNorme vecteurDirection;
    private double vitesse;
    private double vitesseMax = 100d;

    static class CelluleVertebre {
        /** Vertebre suivante. */
        CelluleVertebre suivante;
        /**
         * Distance à la vertèbre précédente
         */
        double distancePrecedente;

        /**
         * Vertebre associée à la cellule
         */
        Vertebre vertebre;

        CelluleVertebre(){};

        CelluleVertebre(Vertebre vertebre, double distanceSuivante) {
            this.vertebre = vertebre;
            this.distancePrecedente = distanceSuivante;
        }
    }

    /**
     * Iterateur de la liste de vertèbres
     */
    class ListeVertebresIterator implements Iterator<Paire<Vertebre, Double>> {
        /**
         * Cellule courante
         */
        CelluleVertebre courant = tete;

        public boolean hasNext() {
            return courant != null;
        }

        public Paire<Vertebre, Double> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Paire<Vertebre, Double> actuel = new Paire<>(new Vertebre(courant.vertebre),
                    courant.distancePrecedente);

            courant = courant.suivante;

            return actuel;
        }
    }

    public AnimalVertebre(double x, double y, double rayon, Vecteur2D direction) {
        vecteurDirection = new Vecteur2DNorme(direction);

        tete = new CelluleVertebre(new Vertebre(x, y, rayon, vecteurDirection), 0);
        queue = tete;
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

    public void ajouterVertebre(Vertebre vertebre, double distancePrecedente) {
        queue.suivante = new CelluleVertebre(vertebre, distancePrecedente);
        queue = queue.suivante;
    }

    public void retirerVertebre(int numeroDeVertebre) {
        // Vérifier que l'index est positif
        if (numeroDeVertebre < 0) throw new IndexOutOfBoundsException("le numéro doit être positif");

        if (!estSansvertebres()) {
            // Supprimer la tête
            if (numeroDeVertebre == 0) {
                retirerTete();
            } else {
                CelluleVertebre vertebreCourante = tete;

                // Rechercher la vertebre précendent celle à retirer
                for (int i = 0; vertebreCourante.suivante != null && i < numeroDeVertebre - 1; i++) {
                    vertebreCourante = vertebreCourante.suivante;
                }

                // Vérifier que l'index demandé ne soit pas trop grand
                if (vertebreCourante.suivante == null) throw new IndexOutOfBoundsException("L'index est trop grand");

                // Cas où on supprime l'élément de queue
                if (vertebreCourante.suivante == queue) {
                    vertebreCourante.suivante = null;
                    queue = vertebreCourante;

                // Cas général
                } else {
                    vertebreCourante.suivante = vertebreCourante.suivante.suivante;
                }

            }
            // Appeler le garbage collector
            System.gc();
        }
    }

    public int taille() {
        CelluleVertebre vertebreCourante = tete.suivante;
        int taille = 1;

        while (vertebreCourante != null) {
            vertebreCourante = vertebreCourante.suivante;
            taille++;
        }

        return taille;
    }

    public void retirerTete() {
        tete = tete.suivante;
        tete.distancePrecedente = 0;
    }

    public boolean estSansvertebres() {
        return queue == tete;
    }

    public Point2D getPositionTete() {
        return tete.vertebre.getPosition();
    }

    public double getVitesse() {
        return vitesse;
    }

    public void setVitesse(double vitesse) {
        this.vitesse = vitesse;
    }

    abstract void avancer();

    public Iterator<Paire<Vertebre, Double>> iterator() {
        return new ListeVertebresIterator();
    }

}
