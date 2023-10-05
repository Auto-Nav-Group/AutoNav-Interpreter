package net.autonav.HTTP;

import java.io.IOException;

import static net.autonav.HTTP.HTTPManager.server;


public class MalfunctionStream {
    public static void initConnection() {
        System.out.println("Malfunction Stream started at port 8080");
        server.createContext("/malfunction", new MalfunctionHandler());
    }


    static class MalfunctionHandler implements com.sun.net.httpserver.HttpHandler {
        private final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        @Override
        public void handle(com.sun.net.httpserver.HttpExchange t) throws IOException {
            String response = "Received Malfunction Request";
            HTTPManager.handleHttpExchange(t, mapper);

            t.sendResponseHeaders(200, response.length());
            java.io.OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
