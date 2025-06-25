import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Timer;

import Vectoriels.*;

public class Tracer extends Frame {
    private final int width = 1000, height = 1000;
    int numPoints = 500;
    CourbeVectorielle2DInterpolatrice courbe = new FiniteDifferenceSpline();

    public static void main(String[] args) {
        new Tracer();
    }

    public Tracer() {
        super("Dessiner courbe");

        setSize(width, height);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point2D dernierPoint = new Point2D.Double(e.getX(), e.getY());
                long debut = System.nanoTime();
                courbe.ajouterPointDirecteur(dernierPoint);
                long t1 = System.nanoTime();

                System.out.println( "ajout point " + (double) (t1 - debut) / 1000000);
                debut = System.nanoTime();
                repaint();
                long t2 = System.nanoTime();
                System.out.println("dessin " + (double) (t2 - debut) / 1000000);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_R || e.getKeyCode() == KeyEvent.VK_F5) {
                    courbe = new FiniteDifferenceSpline();
                    repaint();
                }
            }
        });
    }

    public void paint(Graphics g) {
        ArrayList<Point2D> points = new ArrayList<>();

        // Remplir l'arrière plan
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        // Récupérer les points
        if (!courbe.getPointsDirecteurs().isEmpty()) {
            points = courbe.getPointsInterpolation(500);

            // Dessiner la courbe
            Color couleur = Color.red;

            Point2D vieuxPoint = points.getFirst();
            for (Point2D point : points) {

                g.setColor(couleur);
                g.drawLine((int) vieuxPoint.getX(), (int) vieuxPoint.getY(),
                        (int) point.getX(), (int) point.getY());
                vieuxPoint = point;
                couleur = new Color(couleur.getRed(), 0, (couleur.getBlue() + 1) % 256);
            }

            // Dessiner les points directeurs
            g.setColor(Color.red);
            for (Point2D pointDirecteur : courbe.getPointsDirecteurs()) {
                g.fillOval((int) pointDirecteur.getX() - 5, (int) pointDirecteur.getY() - 5, 10, 10);
            }
        }
    }
}