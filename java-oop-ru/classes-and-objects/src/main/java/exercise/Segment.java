package exercise;

// BEGIN
public class Segment {
    private Point beginPoint;
    private Point endPoint;

    public Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Point getMidPoint() {
        int beginPointX = beginPoint.getX();
        int beginPointY = beginPoint.getY();
        int endPointX = endPoint.getX();
        int endPointY = endPoint.getY();

        int midPointX = (beginPointX + endPointX) / 2;
        int midPointY = (beginPointY + endPointY) / 2;

        return new Point(midPointX, midPointY);
    }
}
// END
