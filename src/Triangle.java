public class Triangle {
    double ax;
    double ay;
    double bx;
    double by;
    double cx;
    double cy;

    Triangle() {
        ax = 0;
        ay = 0;
        bx = 0;
        by = 0;
        cx = 0;
        cy = 0;
    }

    Triangle(Point a, Point b, Point c) {
        ax = a.getX();
        ay = a.getY();
        bx = b.getX();
        by = b.getY();
        cx = c.getX();
        cx = c.getY();
    }

    @Override
    public String toString() {
        return "a(" + ax + ";" + ay + ") " + "b(" + bx + ";" + by + ") " + "c(" + cx + ";" + cy + ")";
    }
}