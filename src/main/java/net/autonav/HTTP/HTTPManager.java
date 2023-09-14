package net.autonav.HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class HTTPManager {
    public static HttpServer server;

    static {
        try {
            server = HttpServer.create(new java.net.InetSocketAddress(8080), 0);
            server.setExecutor(null);
            server.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void handleHttpExchange(HttpExchange t, ObjectMapper mapper) throws IOException {
        t.getResponseHeaders().set("Content-Type", "application/json");
        InputStream is = t.getRequestBody();
        StringBuilder reqBody = new StringBuilder();
        int bytesRead;
        byte[] buffer = new byte[1024];
        while ((bytesRead = is.read(buffer)) != -1) {
            reqBody.append(new String(buffer, 0, bytesRead));
        }

        Map jsonMap = mapper.readValue(reqBody.toString(), Map.class);
        System.out.println(jsonMap);
    }

    public static void initConnections() throws IOException {
        DataRequestStream.initConnection();
        OverrideStream.initConnection();
        MalfunctionStream.initConnection();
        CommandStream.initConnection();
        MovementStream.initConnection();
    }
}
