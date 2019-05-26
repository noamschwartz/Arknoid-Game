package listeners;

import collidables.Collidable;
import geometry.Point;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the collisioninfo class.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * this is the constructor of the collisioninfo.
     *
     * @param collisionPoint is the collision point.
     * @param colidable      is the collidable which the contace was made.
     */
    public CollisionInfo(Point collisionPoint, Collidable colidable) {
        this.collisionPoint = collisionPoint;
        this.collidable = colidable;
    }

    /**
     * this returns the point at which the collision occurs.
     *
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * this is the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collidable;
    }
}