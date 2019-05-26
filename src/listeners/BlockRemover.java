package listeners;

import collidables.Block;
import game.GameLevel;
import sprites.Ball;

/**
 * The type collidable.collidables.Block remover.
 * a listeners.BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */

public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new collidable.collidables.Block remover.
     *
     * @param gameLevel     the game level
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the gameLevel. Remember to remove this listener from the block
     * that is being removed from the gameLevel.
     *
     * @param beingHit is the block being hit.
     * @param hitter   is the ball hitting the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHits() == 1) {
            this.remainingBlocks.decrease(1);
            beingHit.removeFromGame(this.gameLevel);
        }

    }
}