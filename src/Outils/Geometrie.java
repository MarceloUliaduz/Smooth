package Outils;

import java.awt.geom.Point2D;

public class Geometrie {
    public static double pente(Point2D p1, Point2D p2) {
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }
}
