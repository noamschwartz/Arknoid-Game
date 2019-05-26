package game;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import listeners.Counter;
import listeners.ScoreIndicator;
import scores.HighScoresAnimation;
import scores.HighScoresTable;
import scores.ScoreInfo;
import sprites.LivesIndicator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter score;
    private Counter life;
    private LivesIndicator livesIndicator;
    private ScoreIndicator scoreIndicator;
    private boolean ifWin;
    private File filename;
    private HighScoresTable highScoresTable;
    private DialogManager dialog;
    private GUI gui;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the ar
     * @param ks the ks
     * @param highScoresTable the highscoretable
     * @param filename the filename
     * @param dialog the dialog
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable highScoresTable, File filename,
                    DialogManager dialog, GUI gui) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.highScoresTable = highScoresTable;
        this.filename = filename;
        this.dialog = dialog;
        this.gui = gui;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter scoreCounter = new Counter(0);
        this.score = scoreCounter;
        Counter liveCounter = new Counter(7);
        this.life = liveCounter;
        ScoreIndicator scoreIndi = new ScoreIndicator(scoreCounter);
        LivesIndicator liveIndicator = new LivesIndicator(liveCounter);
        this.livesIndicator = liveIndicator;
        this.scoreIndicator = scoreIndi;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score, life,
                    this.livesIndicator, this.scoreIndicator);
            level.initialize();
            while ((level.getNumOfBlocks() > 0) && (liveCounter.getValue() > 0)) {
                level.playOneTurn();
                if (level.getNumOfBlocks() > 0) {
                    this.life.decrease(1);
                }
                if (level.getNumOfBlocks() == 0) {
                    this.score.increase(100);
                }
            }
            if (liveCounter.getValue() == 0) {
                this.ifWin = false;
                break;
            }
            this.ifWin = true;
        }
        //end screen.
        this.animationRunner.run(new EndScreen(this.keyboardSensor, this.ifWin, scoreCounter, this.highScoresTable,
                this.dialog, this.filename, this.gui));
        if (this.highScoresTable.getRank(score.getValue()) < this.highScoresTable.size()) {
            highScoresTable.add(new ScoreInfo(this.dialog.showQuestionDialog("Name", "What is your name?", ""),
                    score.getValue()));
            try {
                highScoresTable.save(this.filename);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(this.highScoresTable, keyboardSensor)));
    }
}