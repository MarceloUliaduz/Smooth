package Vectoriels;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.function.Function;

public class SplineCubiqueHermite extends CourbeVectorielle2D {
    /**
     * Fonctions de bases d'Hermite.
     */
    public static final Function<Double, Double> H00 = t -> 2 * t * t * t - 3 * t * t + 1,
            H10 = t -> t * t * t - 2 * t * t + t,
            H01 = t -> -2 * t * t * t + 3 * t * t,
            H11 = t -> t * t * t - t * t;
    /**
     * Liste des points qui définissent la courbe.
     */
    private ArrayList<Point2D> pointsDirecteurs;
    /**
     * Liste des tangentes aux points qui définisssent la courbe.
     */
    private ArrayList<Double> tangentes;
    /**
     * Listes des fonctions qui définissent chaque segment.
     */
    private ArrayList<Function<Double, Point2D>> fonctionsParSegments;
    /**
     * Liste des intervalles de chaque segments
     */
    private ArrayList<Double> intervallesSegments;

    public SplineCubiqueHermite(ArrayList<Point2D> points, ArrayList<Double> tangentes) {
        assert points != null && tangentes != null;

        // Initialiser les listes
        this.pointsDirecteurs = new ArrayList<>();
        this.tangentes = new ArrayList<>();
        this.intervallesSegments = new ArrayList<>();
        this.fonctionsParSegments = new ArrayList<>();

        for (int i = 0; i < points.size(); i++) {
            ajouterPointDirecteur(points.get(i), tangentes.get(i));
        }

        // Définir la fonction qui effectue l'interpolation
        function = new Function<Double, Point2D>() {
            @Override
            // Ici segmentsParcourus est le nombre de segments parcourus
            public Point2D apply(Double segmentsParcourus) {
                // Numéro du segment actuel
                int segmentActuel = segmentsParcourus.intValue();
                int test = fonctionsParSegments.size();
                System.out.println("LE TEST " + test);
                // Si c'est le dernier point, le dernier segment n'existe pas
                if (segmentActuel == segmentsParcourus) {
                    return pointsDirecteurs.get(segmentActuel);
                }

                double abscisseDepart = pointsDirecteurs.get(segmentActuel).getX();

                // Intervalle du segment actuel
                double intervalleSegment = intervallesSegments.get(segmentActuel);

                return fonctionsParSegments.get(segmentActuel)
                        .apply((segmentsParcourus - segmentActuel) * intervalleSegment + abscisseDepart);
            }
        };
    }

    /**
     * Créer la fonction qui définit la spline cubique d'Hermite entre deux points sur un intervalle arbitraire.
     *
     * @param p0 Point de départ
     * @param m0 Tangente au point de départ
     * @param p1 Point d'arrivée
     * @param m1 Tangente au point d'arrivée
     * @return Fonction qui définit la spline cubique d'Hermite
     */
    private Function<Double, Point2D> SegmentFunction(Point2D p0, double m0, Point2D p1, double m1, double intervalle) {

        return new Function<Double, Point2D>() {
            @Override
            public Point2D apply(Double x) {
                double t = (x - p0.getX()) / intervalle;
                double y = H00.apply(t) * p0.getY() + H10.apply(t) * intervalle * m0 +
                        H01.apply(t) * p1.getY() + H11.apply(t) * intervalle * m1;
                return new Point2D.Double(x, y);
            }
        };

    }

    private void ajouterSegment(Point2D p0, double m0, Point2D p1, double m1) {
        intervallesSegments.add(p1.getX() - p0.getX());
        fonctionsParSegments.add(SegmentFunction(p0, m0, p1, m1, intervallesSegments.getLast()));
        nombreSegments++;
    }

    /**
     * Ajouter un point directeur en fin de courbe pour l'allonger.
     *
     * <p>La fonction crée automatiquement le segment associé</p>
     *
     * @param nouveauPoint    point à ajouter
     * @param tangenteAuPoint tangente au point
     */
    public void ajouterPointDirecteur(Point2D nouveauPoint, double tangenteAuPoint) {
        if (!pointsDirecteurs.isEmpty()) {
            ajouterSegment(pointsDirecteurs.getLast(), tangentes.getLast(), nouveauPoint, tangenteAuPoint);
        }

        pointsDirecteurs.add(nouveauPoint);
        tangentes.add(tangenteAuPoint);
    }
}
