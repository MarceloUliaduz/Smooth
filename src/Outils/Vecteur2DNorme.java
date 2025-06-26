package Outils;

import java.awt.geom.Point2D;
import java.util.function.BiFunction;

public class Vecteur2DNorme extends Vecteur2D {
    private BiFunction<Double, Double, Double> fNorme;
    private static final BiFunction<Double, Double, Double> FONCTION_NORME_DEFAUT = Vecteur2D::normeEuclidienne;

    public Vecteur2DNorme(double x, double y, BiFunction<Double, Double, Double> fonctionNorme) {
        super(x, y);
        fNorme = fonctionNorme;
        this.normaliser();
    }
    
    public Vecteur2DNorme(double x, double y) {
        this(x, y, FONCTION_NORME_DEFAUT);
    }

    public Vecteur2DNorme(Point2D p1, Point2D p2) {
        this(p1, p2, FONCTION_NORME_DEFAUT);
    }

    public Vecteur2DNorme(Point2D p1, Point2D p2, BiFunction<Double, Double, Double> fonctionNorme) {
        super(p1, p2);
        fNorme = fonctionNorme;
        this.normaliser();
    }

    public Vecteur2DNorme(Vecteur2D vecteur, BiFunction<Double, Double, Double> fonctionNorme) {
        super(vecteur);
        fNorme = fonctionNorme;
        this.normaliser();
    }
    
    public Vecteur2DNorme(Vecteur2D vecteur) {
        this(vecteur, FONCTION_NORME_DEFAUT);
    }

    public Vecteur2DNorme(double tangente) {
        this(tangente, FONCTION_NORME_DEFAUT);
    }

    public Vecteur2DNorme(double tangente, BiFunction<Double, Double, Double> fonctionNorme) {
        super(tangente, fonctionNorme);
        fNorme = fonctionNorme;
    }

    public void normaliser() {
        super.normaliser(fNorme);
    }
    
    @Override
    public void multiplier(double a) {
        super.multiplier(Math.signum(a));
    }
    
    @Override
    public void setX(double x) {
        super.setX(x);
        normaliser();
    }

    @Override
    public void setY(double y) {
        super.setY(y);
        normaliser();
    }

    public BiFunction<Double, Double, Double> getFonctionNorme() {
        return fNorme;
    }

    public void setfNorme(BiFunction<Double, Double, Double> fNorme) {
        this.fNorme = fNorme;
        normaliser();
    }
}
