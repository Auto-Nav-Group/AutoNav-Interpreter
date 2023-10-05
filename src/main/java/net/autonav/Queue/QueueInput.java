package net.autonav.Queue;

import org.jetbrains.annotations.NotNull;

public class QueueInput implements Comparable<QueueInput> {
    private String id;
    private int priority;
    private String data;

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
