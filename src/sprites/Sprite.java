package sprites;

import biuoop.DrawSurface;

/**
 * Name: Noam Schwartz.
 * ID: 200160042.
 * this is the sprite interface.
 */

public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d is the screen to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * this notifies the sprite that time has passed.
     * @param dt is the speed
     */
    void timePassed(double dt);
}