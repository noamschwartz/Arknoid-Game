package listeners;

/**
 * The listeners.Counter class.
 */
public class Counter {
    private int counter;

    /**
     * Instantiates a new listeners.Counter.
     *
     * @param num the num
     */
    public Counter(int num) {
        this.counter = num;
    }

    /**
     * Increase. add number to current count.
     *
     * @param number is the number.
     */
    public void increase(int number) {
        this.counter = this.counter + number;

    }

    /**
     * Decrease. subtract number from current count.
     *
     * @param number is the number.
     */
    public void decrease(int number) {
        this.counter = this.counter - number;

    }

    /**
     * Get value of count.
     *
     * @return the counter.
     */
    public int getValue() {

        return this.counter;

    }
}