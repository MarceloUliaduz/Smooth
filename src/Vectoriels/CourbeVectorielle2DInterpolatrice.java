package Vectoriels;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.function.Function;

import static Outils.HermiteSpline.segmentFunction;

/**
 * Courbes vectorielles en 2D qui passent par les points directeurs indiqués.
 *
 * <p>Courbes définies par segments</p>
 */
public abstract class CourbeVectorielle2DInterpolatrice extends CourbeVectorielle2D {
    /**
     * Liste des points qui définissent la courbe.
     */
    protected ArrayList<Point2D> pointsDirecteurs;
    /**
     * Listes des fonctions qui définissent chaque segment.
     */
    protected ArrayList<Function<Double, Point2D>> fonctionsParSegments;

    /**
     * Liste des tangentes aux points qui définisssent la courbe.
     */
    protected ArrayList<Vecteur2D> dirTangentes;

    public CourbeVectorielle2DInterpolatrice() {
        // Initialiser les listes
        this.pointsDirecteurs = new ArrayList<>();
        this.dirTangentes = new ArrayList<>();
        this.fonctionsParSegments = new ArrayList<>();

        // Définir la fonction qui effectue l'interpolation
        function = new Function<Double, Point2D>() {
            @Override
            // Ici t est la proportion de la spline entière parcourue
            public Point2D apply(Double t) {
                // Nombre de segments parcourus
                int nombreSegments = fonctionsParSegments.size();
                double segmentsParcourus = t * nombreSegments;

                // Numéro du segment actuel
                int segmentActuel = (int) segmentsParcourus;

                // Si c'est le dernier point, le dernier segment n'existe pas
                if (segmentActuel == nombreSegments) {
                    return pointsDirecteurs.getLast();
                }

                // On retrouve x a partir de t, puis on l'applique à la fonction
                return fonctionsParSegments.get(segmentActuel)
                        .apply(segmentsParcourus - segmentActuel);
            }
        };
    }

    public abstract void ajouterPointDirecteur(Point2D nouveauPoint);

    /**
     * Mets à jour les deux derniers segments
     */
    protected void mettreAJour2DerniersSegments() {
        int nombreSegments = Math.max(0, pointsDirecteurs.size() - 1);

        if (nombreSegments > 0) {
            // Ajouter le dernier segment (intervalle + fonction)
            Point2D p1 = pointsDirecteurs.get(nombreSegments - 1),
                    p2 = pointsDirecteurs.getLast();

            fonctionsParSegments.add(segmentFunction(p1, dirTangentes.get(nombreSegments - 1),
                    p2, dirTangentes.getLast()));

            // S'il y a plus d'un segment alors une tangente a due être modifiée
            if (nombreSegments > 1) {
                // Recalculer la fonction du segment d'avant
                fonctionsParSegments.set(nombreSegments - 2,
                        segmentFunction(pointsDirecteurs.get(nombreSegments - 2),
                                dirTangentes.get(nombreSegments - 2),
                                p1,
                                dirTangentes.get(nombreSegments - 1)));
            }
        }

    }

    public ArrayList<Point2D> getPointsDirecteurs() {
        return new ArrayList<>(pointsDirecteurs);
    }

    public ArrayList<Vecteur2D> getDirTangentes() {
        return new ArrayList<>(dirTangentes);
    }

    public ArrayList<Function<Double, Point2D>> getFonctionsParSegments() {
        return new ArrayList<>(fonctionsParSegments);
    }
}
