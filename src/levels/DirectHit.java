package levels;

import biuoop.DrawSurface;
import collidables.Block;
import geometry.Line;
import geometry.Rectangle;
import geometry.Point;

import sprites.Sprite;
import sprites.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Direct hit.
 */
public class DirectHit implements LevelInformation {
    /**
     * this is the number of balls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return the velocity list.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<Velocity>();
        Velocity velocity = Velocity.fromAngleAndSpeed(5, 200);
        ballVelocities.add(velocity);
        return ballVelocities;
    }

    /**
     * this is the paddle speed.
     *
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return 200;

    }

    /**
     * this is the paddle width.
     *
     * @return the paddle width.
     */
    public int paddleWidth() {
        return 150;

    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return ("Direct Hit");
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background of the level.
     */
    public Sprite getBackground() {
        return new Sprite() {
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.BLUE);
                Line lineOne = new Line(400, 70, 400, 310);
                Line lineTwo = new Line(260, 185, 530, 185);
                Point center = lineTwo.intersectionWith(lineOne);
                d.drawLine((int) lineOne.start().getX(), (int) lineOne.start().getY(),
                        (int) lineOne.end().getX(), (int) lineOne.end().getY());
                d.drawLine((int) lineTwo.start().getX(), (int) lineTwo.start().getY(),
                        (int) lineTwo.end().getX(), (int) lineTwo.end().getY());
                d.drawCircle((int) center.getX(), (int) center.getY(), 75);
                d.drawCircle((int) center.getX(), (int) center.getY(), 100);
                d.drawCircle((int) center.getX(), (int) center.getY(), 125);
            }

            public void timePassed(double dt) {
            }
        };


    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the levels blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point(380, 170), 50, 50), Color.RED);
        block.setNumberOnBlock(1);
        blocks.add(block);
        return blocks;

    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of block in the level.
     */
    public int numberOfBlocksToRemove() {
        return 1;

    }
}
