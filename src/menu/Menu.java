package menu;

import animation.Animation;

/**
 * Menu interface.
 *
 * @param <T> tasks.
 */
public interface Menu<T> extends Animation {
    /**
     * add the selection.
     *
     * @param key       key.
     * @param message   to show.
     * @param returnVal the value.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get the status of the menu.
     *
     * @return the status.
     */
    T getStatus();

    /**
     * add a sub menu to the main menu.
     *
     * @param key     key.
     * @param message to show.
     * @param subMenu the sub menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * Clears the task's list.
     */
    void cleanTasks();
}