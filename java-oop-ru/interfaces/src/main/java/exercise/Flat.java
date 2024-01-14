package exercise;

// BEGIN
public class Flat implements Home{
    double area;
    double balconyArea;
    int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    public double getArea() {
        return area + balconyArea;
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
        return "Квартира площадью " + getArea() + " метров на " + floor + " этаже";
    }
}
// END
