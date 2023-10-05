package net.autonav.Queue;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

public class InsertToQueue {
    private static String data;
    public InsertToQueue(String data) {
        InsertToQueue.data = data;
    }

    /**
     * Packages the incoming data into the QueueInput object
     * */
    public static void insert() {
        System.out.println("Inserting data into queue");
        int priority = 0;
        JSONObject json = new JSONObject(data);
        System.out.println(json);
        String id = json.getString("id");
        System.out.println(id);
        priority = parsePriority(json);
        System.out.println(priority);
        String data = json.getString("target") +
                ":" +
                json.getString("command") +
                ":" +
                json.getString("value");
        QueueInput input = new QueueInput(id, priority, data);
        Queue.insert(input);
    }

    private static int parsePriority(@NotNull JSONObject json) {
        if (json.getString("type").equals("malfunction")) {
            return 5;
        } else if (json.getString("type").equals("override")) {
            return 4;
        } else if (json.getString("type").equals("movement")) {
            return 3;
        } else if (json.getString("type").equals("command")) {
            return 2;
        } else {
            return 1;
        }
    }
}
