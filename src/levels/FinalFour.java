package levels;

import biuoop.DrawSurface;
import collidables.Block;
import geometry.Rectangle;
import game.MGN;
import geometry.Point;

import sprites.Sprite;
import sprites.Velocity;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Final four.
 */
public class FinalFour implements LevelInformation {
    /**
     * this is the number of balls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 3;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return the initial velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> ballVelocities = new ArrayList<Velocity>();
        Velocity velocity1 = Velocity.fromAngleAndSpeed(250, 200);
        ballVelocities.add(velocity1);
        Velocity velocity2 = Velocity.fromAngleAndSpeed(350, 200);
        ballVelocities.add(velocity2);
        Velocity velocity3 = Velocity.fromAngleAndSpeed(500, 200);
        ballVelocities.add(velocity3);
        return ballVelocities;


    }

    /**
     * this is the paddles speed.
     *
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return 200;

    }

    /**
     * this is the paddles width.
     *
     * @return the paddles width.
     */
    public int paddleWidth() {
        return 150;

    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the level name.
     */
    public String levelName() {
        return ("Final Four");

    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the background.
     */
    public Sprite getBackground() {
        return new Sprite() {
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(45, 170, 230));
                d.fillRectangle(0, 0, 800, 600);
                int xValue = 400;
                int yValue = 150;
                //draw the clouds.
                for (int j = 0; j < 2; j++) {
                    d.setColor(new Color(200, 200, 200));
                    for (int i = 580 - (j * xValue); i < 660 - (j * xValue); i = i + 10) {
                        d.drawLine(i, 500 - (j * yValue), i - 10, 600);
                    }
                    d.fillCircle(590 - (j * xValue), 480 - (j * yValue), 20);
                    d.fillCircle(600 - (j * xValue), 510 - (j * yValue), 25);
                    d.setColor(new Color(160, 160, 160));
                    d.fillCircle(620 - (j * xValue), 490 - (j * yValue), 25);
                    d.setColor(new Color(120, 120, 120));
                    d.fillCircle(650 - (j * xValue), 500 - (j * yValue), 25);
                    d.fillCircle(630 - (j * xValue), 510 - (j * yValue), 20);
                }
            }

            public void timePassed(double dt) {
            }
        };

    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return the blocks of the level.
     */
    public List<Block> blocks() {
        List<Block> totalBlocks = new ArrayList<Block>();
        Block[] topBlocks = new Block[15];
        for (int i = 0; i < 15; i++) {
            topBlocks[i] = new Block(new Rectangle(new Point((25 + (MGN.BLOCK_WIDTH * i)), 100),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.GRAY);
            topBlocks[i].setNumberOnBlock(1);
            totalBlocks.add(topBlocks[i]);
        }
        Block[] secondLineBlocks = new Block[15];
        for (int k = 0; k < 15; k++) {
            secondLineBlocks[k] = new Block(new Rectangle(new Point(25 + (MGN.BLOCK_WIDTH * k),
                    125), MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.RED);
            secondLineBlocks[k].setNumberOnBlock(1);
            totalBlocks.add(secondLineBlocks[k]);
        }
        Block[] thirdBlocks = new Block[15];
        for (int j = 0; j < 15; j++) {
            thirdBlocks[j] = new Block(new Rectangle(new Point(25 + (MGN.BLOCK_WIDTH * j), 150),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.YELLOW);
            thirdBlocks[j].setNumberOnBlock(1);
            totalBlocks.add(thirdBlocks[j]);
        }
        Block[] fourthBlocks = new Block[15];
        for (int m = 0; m < 15; m++) {
            fourthBlocks[m] = new Block(new Rectangle(new Point(25 + (MGN.BLOCK_WIDTH * m), 175),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.GREEN);
            fourthBlocks[m].setNumberOnBlock(1);
            totalBlocks.add(fourthBlocks[m]);
        }
        Block[] fifthBlocks = new Block[15];
        for (int n = 0; n < 15; n++) {
            fifthBlocks[n] = new Block(new Rectangle(new Point(25 + (MGN.BLOCK_WIDTH * n), 200),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.WHITE);
            fifthBlocks[n].setNumberOnBlock(1);
            totalBlocks.add(fifthBlocks[n]);
        }
        Block[] sixthBlocks = new Block[15];
        for (int u = 0; u < 15; u++) {
            sixthBlocks[u] = new Block(new Rectangle(new Point(25 + (MGN.BLOCK_WIDTH * u), 225),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.PINK);
            sixthBlocks[u].setNumberOnBlock(1);
            totalBlocks.add(sixthBlocks[u]);
        }
        Block[] seventhBlocks = new Block[15];
        for (int w = 0; w < 15; w++) {
            seventhBlocks[w] = new Block(new Rectangle(new Point(25 + (MGN.BLOCK_WIDTH * w), 250),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.CYAN);
            seventhBlocks[w].setNumberOnBlock(1);
            totalBlocks.add(seventhBlocks[w]);
        }
        return totalBlocks;

    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks to remove in the level.
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();

    }

}
