package listeners;

import biuoop.DrawSurface;
import collidables.Block;
import geometry.Point;
import geometry.Rectangle;
import java.awt.Color;

import sprites.Sprite;




/**
 * The Score indicator class.
 */
public class ScoreIndicator implements Sprite {
    private Counter gameScore;
    private Block scoreBlock;

    /**
     * Instantiates a new Score indicator.
     *
     * @param gameScore the game score
     */
    public ScoreIndicator(Counter gameScore) {
        this.gameScore = gameScore;
        this.scoreBlock = new Block(new Rectangle(new Point(0, 0), 800, 50), Color.LIGHT_GRAY);
        ;
    }

    /**
     * draw the score to the screen.
     *
     * @param d is the screen to draw on.
     */
    public void drawOn(DrawSurface d) {
        this.scoreBlock.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(150, 28, "Score:" + Integer.toString(this.gameScore.getValue()), 20);
    }

    /**
     * this notifies the sprite that time has passed.
     * @param dt is the speed
     */
    public void timePassed(double dt) {

    }

    /**
     * Gets counter fo the score.
     *
     * @return the counter of the score.
     */
    public Counter getCounter() {
        return this.gameScore;

    }

    /**
     * Gets value of the score..
     *
     * @return the value of the score.
     */
    public int getValue() {
        return this.gameScore.getValue();
    }
}
