package exercise;

// BEGIN
public class Cottage implements Home{
    double area;
    int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public double getArea() {
        return area;
    }

    public int compareTo(Home another) {
        double currentArea = getArea();
        double anotherArea = another.getArea();

        if (currentArea > anotherArea) {
            return 1;
        } else if (currentArea < anotherArea) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return floorCount + " этажный коттедж площадью " + getArea() + " метров";
    }
}
// END
