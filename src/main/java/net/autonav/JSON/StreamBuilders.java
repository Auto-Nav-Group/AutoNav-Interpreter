package net.autonav.JSON;

import net.autonav.Queue.QueueInput;
import org.json.JSONObject;

public class StreamBuilders {

    /**
     * Packages the command stream into a QueueInput data structure for the Priority Queue
     * @param jsonObject JSON string to be parsed; called from CommandStream
     */
    public static QueueInput buildCommandStream(String jsonObject) {
        String id;
        String command;
        try {
            JSONObject json = new JSONObject(jsonObject);
            id = json.getString("id");
            command = json.getString("command");
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid JSON");
        }
        QueueInput queueInput = new QueueInput(id, 1, command);
        return queueInput;
    }

    public static void buildMovementStream() {

    }

    public static void buildMalfunctionStream() {

    }

    public static void buildOverrideStream() {

    }

    public static void buildDataReqStream() {

    }
}
