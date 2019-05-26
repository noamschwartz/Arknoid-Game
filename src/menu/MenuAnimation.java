package menu;


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


/**
 * menu animation class.
 */
public class MenuAnimation implements Menu<Task<Void>> {
    //Members.
    private List<TaskInfo> tasksList;
    private Task<Void> status;
    private KeyboardSensor ks;
    private boolean stop;


    /**
     * Constructor.
     *
     * @param ks keyboard sensor.
     */
    public MenuAnimation(KeyboardSensor ks) {
        this.tasksList = new LinkedList<TaskInfo>();
        this.ks = ks;
        this.stop = false;


    }

    /**
     * add the tasks to the menu.
     *
     * @param key       key.
     * @param message   to show.
     * @param returnVal the value.
     */
    public void addSelection(String key, String message, Task<Void> returnVal) {
        TaskInfo task = new TaskInfo(key, message, returnVal);
        this.tasksList.add(task);
    }

    /**
     * get the status of the menu.
     *
     * @return the status.
     */
    public Task<Void> getStatus() {
        return this.status;
    }

    /**
     * doOneFrame.
     *
     * @param dt 1/framesPerSecond.
     * @param d  draw surface.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        int width = d.getWidth();
        int height = d.getHeight();
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, width, height);
        d.setColor(Color.ORANGE);
        d.drawText((int) (width * 0.28), (int) (height * 0.22), "Menu:", 50);
        int i = 0;
        for (TaskInfo mt : this.tasksList) {
            String message = mt.getMessage();
            String key = mt.getKey();
            d.drawText((int) (width * 0.35), (int) (height * 0.4) + (i * 31), "(" + key + ") " + message, 40);
            i = i + 3;
        }
        for (TaskInfo mt : this.tasksList) {
            if (ks.isPressed(mt.getKey())) {
                this.status = mt.getReturnVal();
                this.stop = true;
            }
        }
    }

    /**
     * shouldStop.
     * this returns true or false if to stop.
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

    /**
     * add the sub menu to the main menu.
     *
     * @param key     key.
     * @param message to show.
     * @param subMenu the sub menu.
     */
    public void addSubMenu(String key, String message, Menu<Task<Void>> subMenu) {
        this.addSelection(key, message, subMenu.getStatus());
    }

    /**
     * Clears the tasks list.
     */
    public void cleanTasks() {
        this.tasksList.clear();
    }
}
