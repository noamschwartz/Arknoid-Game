package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class for decorator of animation that has key that stops them.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;
    private boolean stop;

    /**
     * Constructor of the classs.
     *
     * @param sensor    keyboard sensor.
     * @param key       pressed key.
     * @param animation animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboardSensor = sensor;
        this.isAlreadyPressed = true;
        this.stop = false;
    }

    /**
     * this is the do one frame function. it checks of the ks was pressed and changes the boolian accordingly.
     *
     * @param dt 1/ framesPerSecond.
     * @param d  draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        }
        if (!this.keyboardSensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * The tells if to stop ir not.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            return true;
        }
        return false;
    }

}