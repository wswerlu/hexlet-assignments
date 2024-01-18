package exercise;

// BEGIN
import static java.lang.Math.PI;

public class Circle {
    private final Point point;
    private final int radius;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Отрицательный радиус");
        }

        return PI * radius * radius;
    }
}
// END
