package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidables.Collidable;
import game.MGN;
import geometry.Rectangle;
import geometry.Line;
import geometry.Point;
import game.GameLevel;


import java.awt.Color;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the paddel class.
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Rectangle rectanglePaddel;
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private double width;
    private double height;
    private int speed;

    /**
     * this is the constructor of the paddel.
     *
     * @param g         is the game
     * @param upperLeft is the upper left point.
     * @param width     is the width of the paddle.
     * @param height    is the height of the paddle.
     * @param color     is the color of the paddle.
     * @param keyboard     is the keyboard senser.
     */
    public Paddle(GameLevel g, Point upperLeft, double width, double height, Color color, KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
        this.upperRight = new Point(upperLeft.getX() + getWidth(), upperLeft.getY());
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() + getHeight());
        this.lowerRight = new Point(upperLeft.getX() + getWidth(), upperLeft.getY() + getHeight());
        this.rectanglePaddel = new Rectangle(this.upperLeft, this.width, this.height);
        this.color = color;
        this.speed = speed;
    }

    /**
     * this returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * this returns the upper-right point of the rectangle.
     *
     * @return the upper-right point of the rectangle.
     */
    public Point getUpperRight() {
        return upperRight;
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
     * @return the upper-left point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * this returns the upper line of the paddle.
     *
     * @return the upper line of the paddle.
     */
    public Line upperLine() {
        Line upperLine = new Line(getUpperLeft(), getUpperRight());
        return upperLine;
    }

    /**
     * this returns the lower line of the paddle.
     *
     * @return the lower line of the paddle.
     */
    public Line lowerLine() {
        Line lowerLine = new Line(getLowerLeft(), getLowerRight());
        return lowerLine;
    }

    /**
     * this returns the left line of the paddle.
     *
     * @return the left line of the paddle.
     */
    public Line leftLine() {
        Line leftLine = new Line(getUpperLeft(), getLowerLeft());
        return leftLine;
    }

    /**
     * this returns the right line of the paddle.
     *
     * @return the right line of the paddle.
     */
    public Line rightLine() {
        Line rightLine = new Line(getUpperRight(), getLowerRight());
        return rightLine;
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

    /**
     * this moves the paddle left.
     * @param dt is the speed
     */
    public void moveLeft(double dt) {
        this.upperLeft = new Point(this.upperLeft.getX() - MGN.SCRN_SIDE_BOUND_WIDTH - this.speed * dt,
                this.upperLeft.getY());
        if (this.upperLeft.getX() <= MGN.SCRN_SIDE_BOUND_WIDTH) {
            this.upperLeft = new Point(MGN.SCRN_SIDE_BOUND_WIDTH + 1, this.upperLeft.getY());
        }
        this.rectanglePaddel = new Rectangle(this.upperLeft, this.width, this.height);
    }

    /**
     * this moves the paddle right.
     * @param dt is the speed
     */
    public void moveRight(double dt) {
        this.upperLeft = new Point(this.upperLeft.getX() + MGN.SCRN_SIDE_BOUND_WIDTH + this.speed * dt,
                this.upperLeft.getY());
        if (this.upperLeft.getX() >= MGN.SCRN_WIDTH - this.width) {
            this.upperLeft = new Point(MGN.SCRN_WIDTH - this.width - 25, this.upperLeft.getY());
        }
        this.rectanglePaddel = new Rectangle(this.upperLeft, this.width, this.height);
    }

    /**
     * this shows how much time passed, sprites.Sprite.
     * @param dt is the speed
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight(dt);
        }
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft(dt);
        }
    }

    /**
     * this draws the paddle onto the screen.
     *
     * @param d is the surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        java.awt.Color paddleColor = this.color;
        int x = (int) this.rectanglePaddel.getUpperLeft().getX();
        int y = (int) this.rectanglePaddel.getUpperLeft().getY();
        int paddleWidth = (int) this.rectanglePaddel.getWidth();
        int paddelHeight = (int) this.rectanglePaddel.getHeight();
        d.setColor(this.color);
        d.fillRectangle(x, y, paddleWidth, paddelHeight);
        d.setColor(Color.black);
    }

    /**
     * this gets the collision rectangle.
     *
     * @return the paddel.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectanglePaddel;

    }

    /**
     * this tells the ball how to reacts when there is a hit on the paddle.
     *
     * @param collisionPoint  is the collision point with the object.
     * @param currentVelocity is the velocity in which the ball hits the object.
     * @param hitter     ball hitting.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddelSegment = this.getCollisionRectangle().upperLine().length() * 0.20;
        double segmentXValue = this.rectanglePaddel.getUpperLeft().getX();
        double sideYValue = this.rectanglePaddel.getUpperLeft().getY();
        double firstSegment = segmentXValue + paddelSegment;
        double secondSegment = firstSegment + paddelSegment;
        double thirdSegment = secondSegment + paddelSegment;
        double fourthSegment = thirdSegment + paddelSegment;
        double fifthSegment = fourthSegment + paddelSegment;
        double collisionX = collisionPoint.getX();
        double collisionY = collisionPoint.getY();
        double thisSpeed = Math.sqrt(currentVelocity.getDx()
                * currentVelocity.getDx() + currentVelocity.getDy() * currentVelocity.getDy());

        if (segmentXValue >= collisionX && collisionX <= firstSegment) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, thisSpeed);
        }
        if (firstSegment >= collisionX && collisionX <= secondSegment) {
            currentVelocity = Velocity.fromAngleAndSpeed(330, thisSpeed);
        }
        if (secondSegment >= collisionX && collisionX <= thirdSegment) {
            currentVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }
        if (thirdSegment >= collisionX && collisionX <= fourthSegment) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, thisSpeed);
        }
        if (fourthSegment >= collisionX && collisionX <= fifthSegment) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, thisSpeed);
        }
        //if the ball hits the left side of the paddel.
        if (collisionY >= sideYValue && collisionX <= firstSegment) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, thisSpeed);
        }
        //if the ball hits the right side of the paddel.
        if (collisionY >= sideYValue && collisionX >= fourthSegment) {
            currentVelocity = Velocity.fromAngleAndSpeed(60, thisSpeed);
        }
        //return the new velocity.
        return currentVelocity;
    }


    /**
     * Add this paddle to the game.
     *
     * @param g is the game to be added to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);

    }

    /**
     * this is the remove from game function. it removes the sprite from the game.
     * @param g is the gamelevel to be removed.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}