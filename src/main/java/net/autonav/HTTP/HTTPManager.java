package net.autonav.HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import net.autonav.Queue.InsertToQueue;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class HTTPManager {
    public static HttpServer server;

    private static void startServer() { 
        try {
            server = HttpServer.create(new java.net.InetSocketAddress(8080), 0);
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void stopServer() {
        server.stop(0);
    }

    public static void handleHttpExchange(@NotNull HttpExchange t, ObjectMapper mapper) throws IOException {
        InputStream is = t.getRequestBody();
        StringBuilder reqBody = new StringBuilder();
        int bytesRead;
        byte[] buffer = new byte[1024];
        while ((bytesRead = is.read(buffer)) != -1) {
            reqBody.append(new String(buffer, 0, bytesRead));
        }

        String data = reqBody.toString();
        System.out.println(data);
        new InsertToQueue(data);
        InsertToQueue.insert();
    }

    public static void initConnections() throws IOException {
        startServer();
        DataRequestStream.initConnection();
        OverrideStream.initConnection();
        MalfunctionStream.initConnection();
        CommandStream.initConnection();
        MovementStream.initConnection();
        System.out.println("All streams started");
    }

    public static void closeConnections() {
        DataRequestStream.closeConnection();
        OverrideStream.closeConnection();
        MalfunctionStream.closeConnection();
        CommandStream.closeConnection();
        MovementStream.closeConnection();
        stopServer();
        System.out.println("All streams closed");
    }
}
