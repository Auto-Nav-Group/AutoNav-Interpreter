package net.autonav;

import net.autonav.HTTP.HTTPManager;
import net.autonav.Queue.QueueInput;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    CollectData data = new CollectData();
    String id = data.genID();

    public static void main(String[] args) throws IOException {
        HTTPManager.initConnections();

        QueueInput queueInput = new QueueInput("test", 1, "test");
        QueueInput queueInput2 = new QueueInput("test2", 2, "test2");
        QueueInput queueInput3 = new QueueInput("test3", 3, "test3");
        QueueInput queueInput4 = new QueueInput("test4", 4, "test4");

        Queue<QueueInput> movementQueue = new PriorityQueue<>();
        movementQueue.add(queueInput);
        movementQueue.add(queueInput3);
        movementQueue.add(queueInput2);
        movementQueue.add(queueInput4);

        while(!movementQueue.isEmpty()) {
            System.out.println(movementQueue.poll().getData());
        }
    }
}
