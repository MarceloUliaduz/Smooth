import Vectoriels.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Exemple1 extends Frame {
    public static void main(String[] args) {
        new Exemple1();
    }

    public Exemple1() {
        super("Dessiner ligne");

        setSize(1000, 1000);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public void paint(Graphics g) {
        


        CourbeVectorielle2DInterpolatrice courbe = new FiniteDifferenceSpline();
        courbe.ajouterPointDirecteur(new Point2D.Double(100, 100));
        courbe.ajouterPointDirecteur(new Point2D.Double(200, 200));
        courbe.ajouterPointDirecteur(new Point2D.Double(300, 100));
        courbe.ajouterPointDirecteur(new Point2D.Double(300, 200));
        courbe.ajouterPointDirecteur(new Point2D.Double(500, 100));
        courbe.ajouterPointDirecteur(new Point2D.Double(500, 500));
        courbe.ajouterPointDirecteur(new Point2D.Double(400, 600));
        courbe.ajouterPointDirecteur(new Point2D.Double(300, 700));

        int numpoints = 500;
        ArrayList<Point2D> points = courbe.getPointsInterpolation(numpoints);

        Color couleur = Color.red;

        Point2D vieuxPoint = points.getFirst();
        for (Point2D point : points) {

            g.setColor(couleur);
            g.drawLine((int) vieuxPoint.getX(), (int) vieuxPoint.getY(),
                    (int) point.getX(), (int) point.getY());
            vieuxPoint = point;
            couleur = new Color(couleur.getRed(), 0, (couleur.getBlue() + 1) % 256);
        }

        g.setColor(Color.red);
        for (Point2D point : courbe.getPointsDirecteurs()) {
            g.fillOval((int) point.getX() - 5, (int) point.getY() - 5, 10, 10);
        }

        for (Vecteur2D t : courbe.getDirTangentes()) {
            System.out.println("Tangente : " + t);
        }
    }
}