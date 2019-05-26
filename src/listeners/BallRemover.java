package listeners;

import collidables.Block;
import game.GameLevel;
import sprites.Ball;

/**
 * The sprites.Ball remover class.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new sprites.Ball remover.
     *
     * @param gameLevel      the game level.
     * @param remainingBalls the remaining balls.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * this is the being hit method. It removes the ball from the game.
     *
     * @param beingHit is the block being hit.
     * @param hitter   is the ball hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);

    }

}