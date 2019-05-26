package menu;

import animation.AnimationRunner;

import biuoop.KeyboardSensor;

/**
 * the sub menu class.
 *
 * @param <T> type of task.
 */
public class SubMenu<T> extends MenuAnimation implements Task<Void> {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;

    /**
     * Constructor.
     *
     * @param keyboardSensor  keyboard sensor.
     * @param animationRunner animation runner.
     */
    public SubMenu(KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        super(keyboardSensor);
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
    }

    /**
     * run.
     *
     * @return null.
     */
    public Void run() {
        while (true) {
            this.animationRunner.run(this);
            Task<Void> t = this.getStatus();
            if (t.run() == null) {
                break;
            }
            if (t != null) {
                t.run();
            }
        }
        this.cleanTasks();
        return null;
    }


}