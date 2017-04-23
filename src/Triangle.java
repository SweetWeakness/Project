public class Triangle {
    int ax;
    int ay;
    int bx;
    int by;
    int cx;
    int cy;

    Triangle() {
        ax = 0;
        ay = 0;
        bx = 0;
        by = 0;
        cx = 0;
        cy = 0;
    }

    Triangle(Point a, Point b, Point c) {
        ax = a.getXP();
        ay = a.getYP();
        bx = b.getXP();
        by = b.getYP();
        cx = c.getXP();
        cy = c.getYP();
    }

    @Override
    public String toString() {
        return "a(" + ax + ";" + ay + ") " + "b(" + bx + ";" + by + ") " + "c(" + cx + ";" + cy + ")";
    }
}