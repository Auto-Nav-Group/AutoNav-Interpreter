package net.autonav.HTTP;

import java.io.IOException;

import static net.autonav.HTTP.HTTPManager.server;

public class CommandStream {

    public static void initConnection() {
        System.out.println("Command Stream started at port 8080");
        server.createContext("/command", new CommandHandler());
    }

    public static void closeConnection() {
        server.removeContext("/command");
    }

    static class CommandHandler implements com.sun.net.httpserver.HttpHandler {
        private final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        @Override
        public void handle(com.sun.net.httpserver.HttpExchange t) throws IOException {
            String response = "Received Command Request";
            HTTPManager.handleHttpExchange(t, mapper);

            t.sendResponseHeaders(200, response.length());
            java.io.OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
