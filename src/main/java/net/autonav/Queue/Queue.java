package net.autonav.Queue;

import java.util.PriorityQueue;

public class Queue {
    public static java.util.Queue<QueueInput> queue = new PriorityQueue<>();


    public static void insert(QueueInput input) {
        queue.add(input);
        System.out.println("Inserted data into queue");
        System.out.println("Queue size: " + queue.size());

        for (QueueInput i : queue) {
            assert queue.peek() != null;
            System.out.println("Priority: " + i.getPriority() + " Data " + i.getData());
            System.out.println("---");
        }
    }
}