package scores;

import animation.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;
    private boolean toStop;
    private KeyboardSensor keyboardSensor;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores         the scores
     * @param keyboardSensor the keyboard sensor
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor keyboardSensor) {
        this.highScoresTable = scores;
        this.keyboardSensor = keyboardSensor;
        this.toStop = false;
    }

    /**
     * this is the do oneframe method.is draws the scores table.
     *
     * @param d  is the surface.
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        //background.
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        int width = d.getWidth();
        int height = d.getHeight();
        //headline.
        d.setColor(Color.BLACK);
        d.drawText((int) (width * 0.3), (int) (height * 0.15), "HighScores:", 64);
        d.setColor(Color.ORANGE);
        d.drawText(width / 2 - 200, 120, "Name", 30);
        d.drawText(width / 2, 120, "Score", 30);
        d.setColor(Color.orange);
        d.fillRectangle(width / 2 - 200 + 3, 142, 280, 10);
        d.setColor(Color.BLUE);
        d.fillRectangle(width / 2 - 200, 140, 280, 10);
        int i = 1;
        d.setColor(Color.RED);
        for (ScoreInfo score : this.highScoresTable.getHighScores()) {
            d.drawText(width / 2 - 200, 160 + i * 30,
                    score.getName(), 25);
            d.drawText(width / 2, 160 + i * 30, String.valueOf(score.getScore()), 25);
            i++;
        }
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean.
     */
    public boolean shouldStop() {
        if (this.toStop) {
            this.toStop = false;
            return true;
        }
        return false;
    }

}
