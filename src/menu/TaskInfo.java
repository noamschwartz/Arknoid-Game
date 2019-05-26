package menu;

/**
 * the task information class.
 */
public class TaskInfo {
    //Members.
    private String key;
    private String message;
    private Task<Void> returnVal;

    /**
     * Consturctor.
     *
     * @param key       key.
     * @param message   message.
     * @param returnVal the value.
     */
    public TaskInfo(String key, String message, Task<Void> returnVal) {
        this.key = key;
        this.message = message;
        this.returnVal = returnVal;
    }

    /**
     * getKey.
     *
     * @return the task key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * getMessage.
     *
     * @return the task message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * getReturnVal.
     *
     * @return the task type.
     */
    public Task<Void> getReturnVal() {
        return this.returnVal;
    }
}