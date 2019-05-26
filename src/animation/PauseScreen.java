package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * The Pause screen class.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Instantiates a new Pause screen.
     *
     * @param k the keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * this is the do one frame function. it draws the pause screen.
     *
     * @param d is the surface.
     * @param dt is the surface of the pause screen message.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int width = d.getWidth();
        int height = d.getHeight();
        d.drawText((int) (width * 0.3), height / 2, "Paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * this function determines if to stop or not.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}