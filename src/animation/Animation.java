package animation;

import biuoop.DrawSurface;

/**
 * The animation.Animation interface.
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d  is the surface.
     * @param dt the dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Should stop boolean.
     *
     * @return the boolean.
     */
    boolean shouldStop();
}