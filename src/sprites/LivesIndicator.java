package sprites;

import biuoop.DrawSurface;
import listeners.Counter;


import java.awt.Color;

/**
 * The Lives indicator class.
 */
public class LivesIndicator implements Sprite {
    private Counter gameLives;
    //private collidable.collidables.Block scoreBlock;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param lives the lives
     */
    public LivesIndicator(Counter lives) {
        this.gameLives = lives;
        //  this.scoreBlock = scoreBlock;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public Counter getCounter() {
        return this.gameLives;

    }

    /**
     * draw the sprite to the screen.
     *
     * @param d is the screen to draw on.
     */
    public void drawOn(DrawSurface d) {
        //this.scoreBlock.drawOn(d);
        d.setColor(Color.black);
        d.drawText(375, 28, "Lives:" + Integer.toString(this.gameLives.getValue()), 20);


    }

    /**
     * this notifies the sprite that time has passed.
     * @param dt is the speed
     */
    public void timePassed(double dt) {

    }
}
