package listeners;

import collidables.Block;

import sprites.Ball;

/**
 * The Printing hit listener class.
 */
public class PrintingHitListener implements HitListener {
    /**
     * this is the hit event function. it prints out when a hit was made.
     * @param beingHit is the block being hit.
     * @param hitter   is the ball being hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A collidable.collidables.Block with " + beingHit.getNumOfHits() + " points was hit.");
    }
}