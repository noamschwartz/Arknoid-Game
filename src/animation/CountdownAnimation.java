package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;


/**
 * The type Countdown animation.
 * The animation.CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) secods, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * this draws one frame to the surface.
     *
     * @param d is the surface.
     * @param dt is the speed.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.YELLOW);
        d.drawText(350, 350, Integer.toString(this.countFrom), 150);
        Sleeper sleeper = new Sleeper();
        long timing = (long) ((this.numOfSeconds / 4) * 1000);
        sleeper.sleepFor(timing);
        this.countFrom = this.countFrom - 1;
    }

    /**
     * this tells us where to stop the count from.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        if (this.countFrom < 0) {
            return true;
        }
        return false;
    }
}