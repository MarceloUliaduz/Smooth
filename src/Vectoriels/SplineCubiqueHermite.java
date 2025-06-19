package Vectoriels;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SplineCubiqueHermite extends FiniteDifferenceSpline {

    public SplineCubiqueHermite() {
        super();
    }

    public SplineCubiqueHermite(ArrayList<Point2D> points, ArrayList<Double> tangentes) {
        this();
        assert points != null && tangentes != null;

        for (int i = 0; i < points.size(); i++) {
            ajouterPointDirecteur(points.get(i), tangentes.get(i));
        }
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
