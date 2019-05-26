package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;
import game.GameEnvironment;
import geometry.Line;
import geometry.Point;
import game.GameLevel;
import listeners.CollisionInfo;

import java.awt.Color;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the class of the ball. its creates a ball.
 */
public class Ball implements Sprite {
    private int r;
    private Point center;
    private java.awt.Color color;
    private Velocity v;
    private int width;
    private int height;
    private int start;
    private GameEnvironment ballGameEnvironment;
    private int upperFrame;
    private int lowerFrame;
    private int rightFrame;
    private int leftFrame;


    /**
     * this is the constructor of the ball class.
     *
     * @param center is the center of the ball containing an x axis and a y axis.
     * @param r      is the radius of the ball.
     * @param color  is the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.ballGameEnvironment = new GameEnvironment();
    }

    /**
     * notify the sprite that time has passed.
     * @param dt is the speed.
     */
    public void timePassed(double dt) {

        this.moveOneStep(dt);
    }

    /**
     * this updates the game frame.
     *
     * @param upperFrameB is the upper frame bar.
     * @param lowerFrameB is the lower frame bar.
     * @param rightFrameB is the right frame bar.
     * @param leftFrameB  is the left frame bar.
     */
    public void updateFrame(int upperFrameB, int lowerFrameB, int rightFrameB, int leftFrameB) {
        this.upperFrame = upperFrameB;
        this.lowerFrame = lowerFrameB;
        this.rightFrame = rightFrameB;
        this.leftFrame = leftFrameB;
    }

    /**
     * get the top left point of the screen.
     *
     * @return the top left point of the screen.
     */
    public Point getTopLeftPoint() {
        Point topLeft = new Point((double) upperFrame, (double) leftFrame);
        return topLeft;
    }

    /**
     * get the bottom left point of the screen.
     *
     * @return the bottom left point of the screen.
     */
    public Point getBottomLeftPoint() {
        Point bottomLeft = new Point((double) upperFrame, (double) lowerFrame);
        return bottomLeft;

    }

    /**
     * get the top right point of the screen.
     *
     * @return the top right point of the screen.
     */
    public Point getTopRightPoint() {
        Point topRight = new Point((double) rightFrame, (double) upperFrame);
        return topRight;
    }

    /**
     * ge the bottom right point of the screen.
     *
     * @return the bottom right point of the screen.
     */
    public Point getBottomRightPoint() {
        Point bottomRight = new Point((double) lowerFrame, (double) rightFrame);
        return bottomRight;
    }

    /**
     * this sets the ball game envirenment.
     *
     * @param newGameEnvironment is the ball game envirenment.
     */
    public void setBallGameEnvironment(GameEnvironment newGameEnvironment) {
        this.ballGameEnvironment = newGameEnvironment;
    }

    /**
     * this gives the x coordinate.
     *
     * @return the x point value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * get the width of the screen.
     *
     * @return the width of the screen.
     */
    public int getWidth() {
        return this.width;

    }

    /**
     * get th height of the screen.
     *
     * @return the height of the screen.
     */
    public int getHeight() {
        return this.height;

    }

    /**
     * this gives the y coordinate on the y axis.
     *
     * @return the y value of the center.
     */
    public int getY() {
        return (int) this.center.getY();

    }

    /**
     * this gives us the radius of the ball.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * this gives us the color of the ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.getColor();
    }

    /**
     * this draws the ball on the given DrawSurface.
     *
     * @param surface is the surface created to draw the ball on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * this sets the velocity of the ball.
     *
     * @param getV is the velocity of the ball.
     */
    public void setVelocity(Velocity getV) {
        this.v = getV;
    }

    /**
     * this sets the velocity of the ball using dy and dx.
     *
     * @param dxToSet is the change on the x axis.
     * @param dyToSet is the change on the y axis.
     */
    public void setVelocity(double dxToSet, double dyToSet) {
        Velocity newV = new Velocity(dxToSet, dyToSet);
        this.v = newV;
    }

    /**
     * this gives us the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * this calculates the trajectory of the ball.
     *@param velocity is the velocity
     * @return the trajectory line of the ball.
     */
    public Line calculateTrajectory(Velocity velocity) {
        //calculate the trajectory of the ball.
        Line trajectory = new Line(this.center, velocity.applyToPoint(this.center));
        return trajectory;

    }

    /**
     * this moves the ball and makes sure to 'bounce' it when it hits the 'walls'.
     * @param dt is the speed
     */
    public void moveOneStep(double dt) {
        //flag
        boolean check = true;
        Velocity newV = new Velocity((this.v.getDx() * dt), (this.v.getDy() * dt));
        //the trajectory line which shows the path of the ball.
        Line trajectory = calculateTrajectory(newV);
        //gets the collisions.
        CollisionInfo collisionInfo = this.ballGameEnvironment.getClosestCollision(trajectory);
        //if there are no collisions.
        if (collisionInfo == null) {
            this.center = newV.applyToPoint(this.center);
        } else {
            //the collision object.
            Rectangle rectangle = collisionInfo.collisionObject().getCollisionRectangle();
            //checks if the ball collided with the upper line of the block.
            if (collisionInfo.collisionPoint().getY() == rectangle.getUpperLeft().getY()) {
                check = false;
                Point newY = new Point(this.center.getX(), rectangle.getUpperLeft().getY() - getSize());
                this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
                this.center = newY;
            }
            //checks is the ball collided with the lower line of the block.
            if (collisionInfo.collisionPoint().getY() == rectangle.getUpperLeft().getY() + rectangle.getHeight()) {
                check = false;
                Point newY = new Point(this.center.getX(), rectangle.getUpperLeft().getY()
                        + getSize() + rectangle.getHeight());
                this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
                this.center = newY;
            }
            //checks is the ball collided with the right line of the block.
            if (collisionInfo.collisionPoint().getX() == (rectangle.getUpperLeft().getX() + rectangle.getWidth())) {
                check = false;
                Point newX = new Point(rectangle.getUpperLeft().getX()
                        + getSize() + rectangle.getWidth(), this.center.getY());
                this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
                this.center = newX;
            }
            //check if the ball collided with the left line of the block.
            if (collisionInfo.collisionPoint().getX() == rectangle.getUpperLeft().getX()) {
                check = false;
                Point newX = new Point(rectangle.getUpperLeft().getX() - getSize(), this.center.getY());
                this.v = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), this.v);
                this.center = newX;
            }
            //set the new velocity.
            if (check) {
                this.center = this.getVelocity().applyToPoint(this.center);

            }
        }
    }

    /**
     * this adds the ball as a sprite to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Remove from game.
     *
     * @param g the g
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}

