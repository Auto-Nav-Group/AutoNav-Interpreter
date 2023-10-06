package net.autonav.HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import static net.autonav.HTTP.HTTPManager.server;

import java.io.IOException;
import java.io.OutputStream;
import static net.autonav.HTTP.HTTPManager.handleHttpExchange;

public class OverrideStream {
    public static void initConnection() throws IOException {
        System.out.println("Override Stream started at port 8080");
        server.createContext("/override", new OverrideHandler());
    }

    public static void closeConnection() {
        server.removeContext("/override");
    }

    static class OverrideHandler implements HttpHandler {
        private final ObjectMapper mapper = new ObjectMapper();

        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Received Override Request";
            handleHttpExchange(t, mapper);

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
