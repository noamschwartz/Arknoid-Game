package geometry;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the class of the line.
 */
public class Line {

    private Point p1;
    private Point p2;

    /**
     * this is the constructor of the line class.
     *
     * @param start is the strating point of the line.
     * @param end   is the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.p1 = start;
        this.p2 = end;

    }

    /**
     * this takes two points a creates the both. assigned to p1 and p2.
     *
     * @param x1 x value of p1
     * @param y1 y value of p1
     * @param x2 x value of p2
     * @param y2 y value of p2
     */
    public Line(double x1, double y1, double x2, double y2) {

        p1 = new Point(x1, y1);
        p2 = new Point(x2, y2);

    }

    /**
     * this returns the lengh of the line using p1 and p2.
     *
     * @return the length of the line.
     */
    public double length() {
        //formula for calculating the length
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX())
                + (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
    }

    /**
     * this returns the middle point of the line between p1 and p2.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double mx = 0.5 * (p1.getX() + p2.getX());
        double my = 0.5 * (p1.getY() + p2.getY());
        return new Point(mx, my);
    }

    /**
     * this returns the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return p1;
    }

    /**
     * this returns the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return p2;
    }

    /**
     * this checks if two lines are intersecting using many different math formulas.
     *
     * @param other is the other line being compared to.
     * @return true is they intersect,false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // if the lines are of the length of 0
        if (this.start() == this.end() || other.start() == other.end()) {
            return false;
        }
        //calculates the x and y values of a b and c.
        double ax = this.p2.getX() - this.p1.getX();
        double ay = this.p2.getY() - this.p1.getY();
        double bx = other.p1.getX() - other.p2.getX();
        double by = other.p1.getY() - other.p2.getY();
        double cx = this.p1.getX() - other.p1.getX();
        double cy = this.p1.getY() - other.p1.getY();
        //calculates the numerator and common denominator.
        double alphaNumerator = by * cx - bx * cy;
        double commonDenominator = ay * bx - ax * by;
        //check if the common denominator is positive.
        if (commonDenominator > 0) {
            //if the alpha numerator is negative and is greater then the common denominator the lines to not intersect.
            if (alphaNumerator < 0 || alphaNumerator > commonDenominator) {
                return false;
            }
            //check if the common denominator is negative.
        } else if (commonDenominator < 0) {
            //if the alpha numerator is negative and is less then the common denominator the line do not intersect.
            if (alphaNumerator > 0 || alphaNumerator < commonDenominator) {
                return false;
            }
        }
        double betaNumerator = ax * cy - ay * cx;
        // check if the common denominator is positive.
        if (commonDenominator > 0) {
            //if the beta numerator is negative and greater then the common denominator the lines do not intersect.
            if (betaNumerator < 0 || betaNumerator > commonDenominator) {
                return false;
            }
            //check if the common denominator is negative.
        } else if (commonDenominator < 0) {
            //if the beta numerator is positive and less then the common denominator the line do not intersect.
            if (betaNumerator > 0 || betaNumerator < commonDenominator) {
                return false;
            }
        }
        // The lines are parallel and do not intersect.
        if (commonDenominator == 0) {
            // Check if they're collinear.
            double y3LessY1 = other.p1.getY() - this.p1.getY();
            double collTestForP3 = this.p1.getX() * (this.p2.getY() - other.p1.getY()) + this.p2.getX() * (y3LessY1)
                    + other.p1.getX() * (this.p1.getY() - this.p2.getY());
            // If p3 is collinear with p1 and p2 then p4 will also be collinear, since p1-p2 is parallel with p3-p4
            //The lines are collinear.
            if (collTestForP3 == 0) {
                // checks if the lines overlap.
                if (p1.getX() >= other.p1.getX() && p1.getX() <= other.p2.getX()
                        || p1.getX() <= other.p1.getX() && p1.getX() >= other.p2.getX()
                        || p2.getX() >= other.p1.getX() && p2.getX() <= other.p2.getX()
                        || p2.getX() <= other.p1.getX() && p2.getX() >= other.p2.getX()
                        || other.p1.getX() >= p1.getX() && other.p1.getX() <= p2.getX()
                        || other.p1.getX() <= p1.getX() && other.p1.getX() >= p2.getX()) {
                    if (p1.getY() >= other.p1.getY() && p1.getY() <= other.p2.getY()
                            || p1.getY() <= other.p1.getY() && p1.getY() >= other.p2.getY()
                            || p2.getY() >= other.p1.getY() && p2.getY() <= other.p2.getY()
                            || p2.getY() <= other.p1.getY() && p2.getY() >= other.p2.getY()
                            || other.p1.getY() >= p1.getY() && other.p1.getY() <= p2.getY()
                            || other.p1.getY() <= p1.getY() && other.p1.getY() >= p2.getY()) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    /**
     * this checks the intersection point between the lines. returns null otherwise.
     *
     * @param other the other line to be compared to.
     * @return the intersection point.
     */
    public Point intersectionWith(Line other) {
        //calculate the denomenator.
        double denomenator = (other.p2.getY() - other.p1.getY())
                * (this.p2.getX() - this.p1.getX()) - (other.p2.getX() - other.p1.getX())
                * (this.p2.getY() - this.p1.getY());
        // check if the lines are parallel.
        if (denomenator == 0) {
            return null;
        }
        //calculate a and b before checking their "behaviour".
        double aFormula = ((other.p2.getX() - other.p1.getX())
                * (this.p1.getY() - other.p1.getY()) - (other.p2.getY() - other.p1.getY())
                * (this.p1.getX() - other.p1.getX())) / denomenator;
        double bFormula = ((this.p2.getX() - this.p1.getX())
                * (this.p1.getY() - other.p1.getY()) - (this.p2.getY() - this.p1.getY())
                * (this.p1.getX() - other.p1.getX())) / denomenator;
        //if both a and b and between 0 and 1 the points intersect.
        if (aFormula >= 0 && aFormula <= 1 && bFormula >= 0 && bFormula <= 1) {
            // returns the intersection point.
            return new Point((int) (this.p1.getX() + aFormula
                    * (this.p2.getX() - this.p1.getX())), (int) (this.p1.getY() + aFormula
                    * (this.p2.getY() - this.p1.getY())));
        }
        return null;
    }

    /**
     * this checks of the lines are equal and returns true. it returns false otherwise.
     *
     * @param other is the other line to compare with.
     * @return true equal or false otherwise.
     */
    public boolean equals(Line other) {
        if (this.length() == other.length()) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect is the rectangle which we check the intersection point.
     * @return the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        Line intersectionLine = new Line(this.p1, this.p2);
        if (rect.intersectionPoints(intersectionLine).isEmpty()) {
            return null;
        }
        if (rect.intersectionPoints(intersectionLine).size() == 1) {
            return rect.intersectionPoints(intersectionLine).get(0);
        }
        if (rect.intersectionPoints(intersectionLine).get(0).distance(intersectionLine.start())
                < rect.intersectionPoints(intersectionLine).get(1).distance(intersectionLine.start())) {
            return rect.intersectionPoints(intersectionLine).get(0);
        }
        return rect.intersectionPoints(intersectionLine).get(1);
    }

    /**
     * this checks if the point is on the line.
     *
     * @param point is the point to check in the line.
     * @return true if is on line, false otherwise.
     */
    public boolean isPointOnLine(Point point) {
        return (this.p1.distance(this.p2) == this.p1.distance(point) + this.p2.distance(point));

    }
}


