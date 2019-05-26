package animation;

import biuoop.DialogManager;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import listeners.Counter;
import scores.HighScoresTable;

import java.awt.Color;


import java.io.File;


/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    //Members.
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;
    private boolean winOrLose;

    /**
     * Constructor.
     *
     * @param k               keyboard.
     * @param winOrLose       true or false if the player won the game.
     * @param score           the score the player got.
     * @param highScoresTable the high scores table
     * @param dialog          the dialog
     * @param filename        the filename
     * @param gui             the gui
     */
    public EndScreen(KeyboardSensor k, boolean winOrLose, Counter score, HighScoresTable highScoresTable,
                     DialogManager dialog, File filename, GUI gui) {
        this.keyboard = k;
        this.stop = false;
        this.winOrLose = winOrLose;
        this.score = score;
    }

    /**
     * doOneFrame. draws the message to the screen.
     *
     * @param dt 1 / framespersecond
     * @param d  draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int width = d.getWidth();
        int height = d.getHeight();
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, width, height);
        d.setColor(Color.BLACK);
        d.drawText((int) (width * 0.3), (int) (height * 0.75), "press space to continue", 30);
        if (!winOrLose) {
            d.drawText((int) (width * 0.25), d.getHeight() / 2, "Game Over. Your score is "
                    + Integer.toString(this.score.getValue()), 32);
        } else {
            d.drawText((int) (width * 0.25), d.getHeight() / 2, "You Win! Your score is  "
                    + Integer.toString(this.score.getValue()), 32);
        }

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }


    /**
     * shouldStop. tells of to stop or not.
     *
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}