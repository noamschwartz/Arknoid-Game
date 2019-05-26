package levels;

import biuoop.DrawSurface;
import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import sprites.Sprite;
import sprites.Velocity;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    /**
     * this returns thenumber of balls inthe game.
     *
     * @return the number of balls in the game.
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return The initial velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < (this.numberOfBalls() / 2); i++) {
            int angle = (i * 10) + 300;
            int angle2 = (i * 10) + 30;
            Velocity v = Velocity.fromAngleAndSpeed(angle, 200);
            Velocity v2 = Velocity.fromAngleAndSpeed(angle2, 200);
            velocities.add(v);
            velocities.add(v2);
        }
        return velocities;
    }

    /**
     * this ia the paddle.
     *
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return 200;

    }

    /**
     * this returns the paddle width.
     *
     * @return the paddle width.
     */
    public int paddleWidth() {
        return 600;

    }

    /**
     * the level name will be displayed at the top of the screen.
     *
     * @return the level name will be displayed at the top of the screen.
     */
    public String levelName() {
        return ("Wide Easy");

    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new Sprite() {
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 600);
                Point center = new Point(175, 175);
                d.setColor(new Color(250, 230, 150));
                for (int i = 0; i < 85; i++) {
                    d.drawLine((int) center.getX(), (int) center.getY(), (25 + (10 * i)), 300);
                }
                d.fillCircle((int) center.getX(), (int) center.getY(), 65);
                d.setColor(new Color(200, 150, 0));
                d.fillCircle((int) center.getX(), (int) center.getY(), 55);
                d.setColor(new Color(250, 200, 50));
                d.fillCircle((int) center.getX(), (int) center.getY(), 45);
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
        List<Block> blocks = new ArrayList<Block>();
        double x = 25;
        double y = 275;
        double width = 50;
        double height = 25;
        Color[] colors = new Color[15];
        colors[0] = Color.RED;
        colors[1] = Color.RED;
        colors[2] = Color.ORANGE;
        colors[3] = Color.ORANGE;
        colors[4] = Color.YELLOW;
        colors[5] = Color.YELLOW;
        colors[6] = Color.GREEN;
        colors[7] = Color.GREEN;
        colors[8] = Color.GREEN;
        colors[9] = Color.BLUE;
        colors[10] = Color.BLUE;
        colors[11] = Color.PINK;
        colors[12] = Color.PINK;
        colors[13] = Color.CYAN;
        colors[14] = Color.CYAN;
        for (int i = 0; i < 15; i++) {
            Block b = new Block(new Rectangle(new Point(x + (width * i), y), width, height), colors[i]);
            b.setNumberOnBlock(1);
            blocks.add(b);
        }
        return blocks;

    }

    /**
     * Number of levels that should be removed
     * before the level is considered to be "cleared".
     *
     * @return the number of blocks in the level.
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }
}
