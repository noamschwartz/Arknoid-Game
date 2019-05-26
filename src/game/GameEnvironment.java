package game;

import collidables.Collidable;
import geometry.Line;
import geometry.Point;
import listeners.CollisionInfo;


import java.util.ArrayList;
import java.util.List;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the game envirenment class.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * add the given collidable to the environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param collidables is the list of collidables.
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = collidables;
    }

    /**
     * this adds the collidable to the list.
     *
     * @param c is the collidable to be added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * this removes the collidable to the list.
     *
     * @param c is the collidable to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory is the expected path of the ball.
     * @return the collision info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        //get the collision point.
        Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidables.get(0).getCollisionRectangle());
        // get the collidable which the collision is at.
        Collidable collisionObject = collidables.get(0);
        for (int i = 0; i < collidables.size(); i++) {
            Point intersectionPoint = trajectory.closestIntersectionToStartOfLine(collidables.get(i)
                    .getCollisionRectangle());
            //check if the first point is null.
            if (collisionPoint == null) {
                collisionPoint = intersectionPoint;
                collisionObject = collidables.get(i);
                //check which point is closer to the start of the line.
            } else if (intersectionPoint != null) {
                if (trajectory.start().distance(collisionPoint) > trajectory.start().distance(intersectionPoint)) {
                    collisionPoint = intersectionPoint;
                    collisionObject = collidables.get(i);
                }
            }
        }
        //if there is no collision point at al.
        if (collisionPoint == null) {
            return null;
        }
        //return the collision info.
        return new CollisionInfo(collisionPoint, collisionObject);
    }
}