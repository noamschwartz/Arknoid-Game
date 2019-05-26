package levels;

import biuoop.DrawSurface;
import collidables.Block;
import game.MGN;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import sprites.Velocity;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green three.
 */
public class GreenThree implements LevelInformation {
    /**
     * this is the number of balls.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 2;
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
        return ballVelocities;

    }

    /**
     * this is the paddles speed.
     *
     * @return the paddles speed.
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
        return ("Green 3");

    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return the levels background.
     */
    public Sprite getBackground() {
        return new Sprite() {
            public void drawOn(DrawSurface d) {
                int x = 150;
                int y = 225;
                d.setColor(new Color(10, 135, 35));
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(new Color(100, 100, 100));
                d.fillRectangle(x, y, 10, 500);
                d.setColor(Color.darkGray);
                d.fillRectangle(x - 10, y + 170, 30, 500);
                d.setColor(Color.BLACK);
                d.fillRectangle(x - 40, y + 220, 90, 500);
                d.setColor(new Color(160, 130, 25));
                d.fillCircle(x + 5, y - 5, 15);
                d.setColor(new Color(160, 50, 50));
                d.fillCircle(x + 5, y - 5, 10);
                d.setColor(new Color(250, 250, 250));
                d.fillCircle(x + 5, y - 5, 3);
                x = x - 30;
                y = y + 225;
                int newXValue = x;
                int width = 10;
                int height = 25;
                d.setColor(Color.WHITE);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 4; j++) {
                        d.fillRectangle(newXValue, y, width, height);
                        newXValue = newXValue + width + 10;
                    }
                    newXValue = x;
                    y = y + height + 5;
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
     * @return the blocks in the level.
     */
    public List<Block> blocks() {
        List<Block> totalBlocks = new ArrayList<Block>();
        Block[] topBlocks = new Block[10];
        for (int i = 0; i < 10; i++) {
            topBlocks[i] = new Block(new Rectangle(new Point((275 + (MGN.BLOCK_WIDTH * i)), 100),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.GRAY);
            topBlocks[i].setNumberOnBlock(2);
            totalBlocks.add(topBlocks[i]);
        }
        Block[] secondLineBlocks = new Block[9];
        for (int k = 0; k < 9; k++) {
            secondLineBlocks[k] = new Block(new Rectangle(new Point(325 + (MGN.BLOCK_WIDTH * k),
                    125), MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.RED);
            secondLineBlocks[k].setNumberOnBlock(1);
            totalBlocks.add(secondLineBlocks[k]);
        }
        Block[] thirdBlocks = new Block[8];
        for (int j = 0; j < 8; j++) {
            thirdBlocks[j] = new Block(new Rectangle(new Point(375 + (MGN.BLOCK_WIDTH * j), 150),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.YELLOW);
            thirdBlocks[j].setNumberOnBlock(1);
            totalBlocks.add(thirdBlocks[j]);
        }
        Block[] fourthBlocks = new Block[7];
        for (int m = 0; m < 7; m++) {
            fourthBlocks[m] = new Block(new Rectangle(new Point(425 + (MGN.BLOCK_WIDTH * m), 175),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.BLUE);
            fourthBlocks[m].setNumberOnBlock(1);
            totalBlocks.add(fourthBlocks[m]);
        }
        Block[] fifthBlocks = new Block[6];
        for (int n = 0; n < 6; n++) {
            fifthBlocks[n] = new Block(new Rectangle(new Point(475 + (MGN.BLOCK_WIDTH * n), 200),
                    MGN.BLOCK_WIDTH, MGN.BLOCK_HEIGHT), Color.WHITE);
            fifthBlocks[n].setNumberOnBlock(1);
            totalBlocks.add(fifthBlocks[n]);
        }
        return totalBlocks;


    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number if blocks to be removed.
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();

    }


}
