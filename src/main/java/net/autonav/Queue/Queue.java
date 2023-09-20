package net.autonav.Queue;

import java.util.PriorityQueue;

public class Queue {
    public static java.util.Queue<QueueInput> queue = new PriorityQueue<>();

    public void executeQueue() {
        while(!queue.isEmpty()) {
            for(QueueInput i : queue) {
                PriorityType type = translateType(i.getPriority());
                String id = i.getId();
                String data = i.getData();

                // TODO: Parse data
                
            }
        }
    }

    private PriorityType translateType(int type) {
        switch(type) {
            case 1:
                return PriorityType.DATA;
            case 2:
                return PriorityType.COMMAND;
            case 3:
                return PriorityType.MOVEMENT;
            case 4:
                return PriorityType.OVERRIDE;
            case 5:
                return PriorityType.MALFUNCTION;
            default:
                throw new IllegalArgumentException("Type must be between 0 and 5");
        }
    }
}