package levels;

import collidables.Block;
import sprites.Sprite;
import sprites.Velocity;

import java.util.List;

/**
 * The interface Level information.
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return the initial velocity.
     */
    List<Velocity> initialBallVelocities();

    /**
     * sprites.Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * sprites.Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return Returns a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * Blocks list.
     *
     * @return the list
     */
// The Blocks that make up this level, each block contains
    // its size, color and location.
    List<Block> blocks();

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks.
     */
    int numberOfBlocksToRemove();
}