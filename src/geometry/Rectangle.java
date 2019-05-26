package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the rectangle class.
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private double width;
    private double height;

    /**
     * Creates a new rectangle with location and width/height.
     *
     * @param upperLeft is the upper left point of the rectangle.
     * @param width     is the width of the rectangle.
     * @param height    is the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + getWidth(), upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + getHeight());
        this.lowerRight = new Point(upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight());
    }

    /**
     * this returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * this returns the upper-right point of the rectangle.
     *
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {

        return this.upperRight;
    }

    /**
     * this returns the lower-left point of the rectangle.
     *
     * @return the lower-left point of the rectangle.
     */
    public Point getLowerLeft() {

        return this.lowerLeft;
    }

    /**
     * this returns the lower-right point of the rectangle.
     *
     * @return the lower-right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * this is the upper line of the rectangle.
     *
     * @return the upper line of the rectangle.
     */
    public Line upperLine() {
        Line upperLine = new Line(getUpperLeft(), getUpperRight());
        return upperLine;
    }

    /**
     * this is the lower line of the rectangle.
     *
     * @return the lower line of the rectangle
     */

    public Line lowerLine() {
        Line lowerLine = new Line(getLowerLeft(), getLowerRight());
        return lowerLine;
    }

    /**
     * this is the left line of the rectangle.
     *
     * @return the left line of the rectangle.
     */
    public Line leftLine() {
        Line leftLine = new Line(getUpperLeft(), getLowerLeft());
        return leftLine;
    }

    /**
     * this is the right line of the rectangle.
     *
     * @return the right line of the rectangle.
     */
    public Line rightLine() {
        Line rightLine = new Line(getUpperRight(), getLowerRight());
        return rightLine;
    }


    /**
     * this returns a (possibly empty) List of intersection points
     * with the specified line.
     *
     * @param line is the line that is checked intersection with.
     * @return a list of intersection points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //create an array list of points.
        List<Point> intesectionPointList = new ArrayList<Point>();
        //check intersection with upper line.
        if (upperLine().isIntersecting(line)) {
            intesectionPointList.add(upperLine().intersectionWith(line));
        }
        //check intersection with lower line.
        if (lowerLine().isIntersecting(line)) {
            intesectionPointList.add(lowerLine().intersectionWith(line));
        }
        //check intersection with left line.
        if (leftLine().isIntersecting(line)) {
            intesectionPointList.add(leftLine().intersectionWith(line));
        }
        //check intersection with right line.
        if (rightLine().isIntersecting(line)) {
            intesectionPointList.add(rightLine().intersectionWith(line));
        }
        //return the list of points.
        return intesectionPointList;
    }

    /**
     * this returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * this returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }
}
