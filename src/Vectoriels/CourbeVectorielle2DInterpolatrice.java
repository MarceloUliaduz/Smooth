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
     * Liste des intervalles de chaque segments
     */
    protected ArrayList<Double> intervallesSegments;
    /**
     * Liste des tangentes aux points qui définisssent la courbe.
     */
    protected ArrayList<Double> tangentes;

    public CourbeVectorielle2DInterpolatrice() {
        // Initialiser les listes
        this.pointsDirecteurs = new ArrayList<>();
        this.tangentes = new ArrayList<>();
        this.intervallesSegments = new ArrayList<>();
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

                double abscisseDepart = pointsDirecteurs.get(segmentActuel).getX();

                // Intervalle du segment actuel
                double intervalleSegment = intervallesSegments.get(segmentActuel);

                return fonctionsParSegments.get(segmentActuel)
                        .apply((segmentsParcourus - segmentActuel) * intervalleSegment + abscisseDepart);
            }
        };
    }

    public abstract void ajouterPointDirecteur(Point2D nouveauPoint);

    protected void ajouterSegment(Point2D p0, double m0, Point2D p1, double m1) {
        intervallesSegments.add(p1.getX() - p0.getX());
        fonctionsParSegments.add(segmentFunction(p0, m0, p1, m1, intervallesSegments.getLast()));
    }

    public void setTangente(int numeroDuPoint, double tangente) {
        tangentes.set(numeroDuPoint, tangente);
    }

    public ArrayList<Point2D> getPointsDirecteurs() {
        return new ArrayList<>(pointsDirecteurs);
    }

    public ArrayList<Double> getTangentes() {
        return new ArrayList<>(tangentes);
    }

    public ArrayList<Function<Double, Point2D>> getFonctionsParSegments() {
        return new ArrayList<>(fonctionsParSegments);
    }
}
