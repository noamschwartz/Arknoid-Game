package sprites;

import geometry.Point;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the velocity class. velocity is the movement of the ball
 */
// sprites.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    private double dx;
    private double dy;

    /**
     * this is the constructor of the velocity class.
     *
     * @param dx is the change on the x axis.
     * @param dy is the change on the y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

    }

    /**
     * this sets the velocity of the ball.
     *
     * @param dxToSet is the change on the x axis.
     * @param dyToSet is the change on the y axis.
     */
    public void setVelocity(double dxToSet, double dyToSet) {
        Velocity v = new Velocity(dx, dy);
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p is the old point needed to be changed.
     * @return the new point (old point + dx and dy).
     */
    public Point applyToPoint(Point p) {
        Point newPoint = new Point(p.getX() + dx, p.getY() + dy);
        return newPoint;
    }

    /**
     * this changes the angle and speed and returns is as a velocity.
     *
     * @param angle is the movement angle of the ball.
     * @param speed is the speed of the ball.
     * @return the new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleToRad = Math.toRadians(angle);
        double dx = speed * Math.sin(angleToRad);
        double dy = speed * -1 * Math.cos(angleToRad);
        return new Velocity(dx, dy);
    }

    /**
     * this returns dx.
     *
     * @return dx. which is the cnage on the x axis.
     */
    public double getDx() {
        return dx;
    }

    /**
     * this returns dy.
     *
     * @return dy. which is the change on the y axis.
     */
    public double getDy() {
        return dy;
    }
}
