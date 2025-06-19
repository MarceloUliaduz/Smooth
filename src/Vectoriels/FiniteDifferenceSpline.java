package Vectoriels;

import java.awt.geom.Point2D;

import static Outils.Geometrie.pente;
import static Outils.HermiteSpline.segmentFunction;

public class FiniteDifferenceSpline extends CourbeVectorielle2DInterpolatrice {
    public FiniteDifferenceSpline() {
        super();
    }

    /**
     * Determiner la tangente d'un point au milieu de deux autres.
     * @param p1 point 1
     * @param p2 point 2 (celui dont on détermine la tangente)
     * @param p3 point 3
     * @return la tangente
     */
    private static double determinerTangente(Point2D p1, Point2D p2, Point2D p3) {
        return 0.5d * ((p3.getY() - p2.getY()) / (p3.getX() - p2.getX())
                + (p2.getX() - p1.getX()) / (p2.getY() - p1.getX()));
    }

    /**
     * Ajouter un point directeur en fin de courbe pour l'allonger.
     *
     * <p>La fonction crée automatiquement le segment associé</p>
     *
     * @param nouveauPoint    point à ajouter
     */
    public void ajouterPointDirecteur(Point2D nouveauPoint) {
        int nombrePoints = pointsDirecteurs.size();

        // S'il n'y a qu'un point, on lui donne une tangente nulle
        double pente = 0;

        if (nombrePoints > 0) {

            pente = pente(pointsDirecteurs.getLast(), nouveauPoint);
            if (nombrePoints == 1) {
                // S'il y en a deux leur tangente est la pente entre les deux points
                tangentes.set(0, pente);

            } else {
                tangentes.set(nombrePoints - 1, determinerTangente(
                        pointsDirecteurs.get(nombrePoints - 2),
                        pointsDirecteurs.getLast(),
                        nouveauPoint
                ));

            }

            ajouterSegment(pointsDirecteurs.getLast(), tangentes.getLast(),
                    nouveauPoint, pente);
        }

        tangentes.add(pente);
        pointsDirecteurs.add(nouveauPoint);
    }
}
