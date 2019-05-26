package listeners;

import collidables.Block;
import sprites.Ball;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprites.Ball that's doing the hitting.
     *
     * @param beingHit is the block being hit.
     * @param hitter   is the ball being hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
