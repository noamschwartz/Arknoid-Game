package geometry;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this class is the point class.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor of the class.
     *
     * @param x is the x value.
     * @param y is he y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This takes two points and returns the distance between them.
     *
     * @param other is the other point received to be compared to.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        // calculation of the distance
        return Math.sqrt((dx * dx) + (dy * dy));
    }

    /**
     * This compares two points. if the points are equal, true is returned. otherwise false is returned.
     *
     * @param other point to be compared to.
     * @return true or false.
     */
    public boolean equals(Point other) {
        return ((this.x == other.x) && (this.y == other.y));
    }

    /**
     * this returns the x value of the point.
     *
     * @return x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * this returns the y value of the point.
     *
     * @return the y value of the point.
     */
    public double getY() {
        return this.y;
    }
}
