package listeners;

import collidables.Block;

import sprites.Ball;

/**
 * The Score tracking listener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * this is the hit even function. it increases the score according to the hit points.
     * @param beingHit is the block being hit.
     * @param hitter   is the ball being hit.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
        if (beingHit.getNumOfHits() == 1) {
            this.currentScore.increase(10);
        }

    }
}