package Vectoriels;

import Outils.Vecteur2D;

import java.awt.geom.Point2D;

public class FiniteDifferenceSpline extends CourbeVectorielle2DInterpolatrice {
    public FiniteDifferenceSpline() {
        super();
    }

    private static Vecteur2D determinerVecteurTangent(Point2D p1, Point2D p2, Point2D p3) {
        double vx1 = p2.getX() - p1.getX(),
                vx2 = p3.getX() - p2.getX(),
                vy1 = p2.getY() - p1.getY(),
                vy2 = p3.getY() - p2.getY();

        Vecteur2D vecteurTangent = new Vecteur2D(vx1 + vx2, vy1 + vy2);
        vecteurTangent.normaliser(Vecteur2D::norme1);
        return vecteurTangent;
    }

    /**
     * Ajouter un point directeur en fin de courbe pour l'allonger.
     *
     * <p>La fonction crée automatiquement le segment associé</p>
     *
     * @param nouveauPoint point à ajouter
     */
    public void ajouterPointDirecteur(Point2D nouveauPoint) {
        int nombrePoints = pointsDirecteurs.size();

        // S'il n'y a qu'un point, on lui donne une tangente nulle
        double pente = 0;
        Vecteur2D vecteurTangent = new Vecteur2D(0, 0);

        if (nombrePoints > 0) {

            vecteurTangent = new Vecteur2D(pointsDirecteurs.getLast(), nouveauPoint);
            vecteurTangent.normaliser(Vecteur2D::norme1);
            if (nombrePoints == 1) {
                // S'il y en a deux leur tangente est la pente entre les deux points
                dirTangentes.set(0, vecteurTangent);

            } else {
                dirTangentes.set(nombrePoints - 1, determinerVecteurTangent(
                        pointsDirecteurs.get(nombrePoints - 2),
                        pointsDirecteurs.getLast(),
                        nouveauPoint
                ));
            }
        }

        dirTangentes.add(vecteurTangent);
        pointsDirecteurs.add(nouveauPoint);

        mettreAJour2DerniersSegments();
    }
}
