package collidables;

import geometry.Rectangle;
import geometry.Point;
import sprites.Ball;
import sprites.Velocity;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the interface of collidable.
 */
public interface Collidable {
    /**
     * this returns the "collision shape" of the object.
     *
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * this notifies the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint  is the collision point with the object.
     * @param currentVelocity is the velocity in which the ball hits the object.
     * @param hitter          is the the ball hits the object.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}