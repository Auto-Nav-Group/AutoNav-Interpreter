package net.autonav.Queue;

import org.jetbrains.annotations.NotNull;

/**
 * Class for the data that is inserted into the queue
 */
public class QueueInput implements Comparable<QueueInput> {
    private String id;
    private int priority;
    private String data;

    /**
     * Constructor for QueueInput
     * @param id String id of the data
     * @param priority Integer priority of the data
     * @param data String data
     */
    public QueueInput(String id, int priority, String data) {
        this.id = id;
        this.priority = priority;
        this.data = data;
        validatePriority(priority);
        System.out.println("QueueInput created with priority " + priority);
    }

    /**
     * @param priority Integer associated with respective priority
     */
    private void validatePriority(int priority) {
        if (priority < 0 || priority > 5) {
            throw new IllegalArgumentException("Priority must be between 0 and 4");
        }
    }

    /**
     * Compares the priority of the QueueInput objects
     * @param o QueueInput object
     * @return int 1 if o is greater than this.priority, -1 if o is less than this.priority
     */
    @Override
    public int compareTo(@NotNull QueueInput o) {
        return o.priority > this.priority ? 1 : -1;
    }

    public String getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getData() {
        return data;
    }
}
