package listeners;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * The Name indicator class.
 */
public class NameIndicator implements Sprite {

    private String gameName;

    /**
     * Instantiates a new Name indicator.
     *
     * @param gameName the game name
     */
    public NameIndicator(String gameName) {
        this.gameName = gameName;

    }

    /**
     * Gets name of the level.
     *
     * @return the name of the level
     */
    public String getName() {
        return this.gameName;

    }

    /**
     * draw the sprite to the screen.
     *
     * @param d is the screen to draw on.
     */
    public void drawOn(DrawSurface d) {
        //this.scoreBlock.drawOn(d);
        d.setColor(Color.black);
        d.drawText(475, 28, "Level Name:" + this.gameName, 20);


    }

    /**
     * this notifies the sprite that time has passed.
     * @param dt is the speed
     */
    public void timePassed(double dt) {

    }
}

