package net.autonav.HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

import static net.autonav.HTTP.HTTPManager.handleHttpExchange;
import static net.autonav.HTTP.HTTPManager.server;

public class MovementStream {
    public static void initConnection() throws IOException {
        System.out.println("Movement Stream started at port 8080");
        server.createContext("/movement", new MovementHandler());
    }

    static class MovementHandler implements HttpHandler {
        private final ObjectMapper mapper = new ObjectMapper();
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "Received Movement Request";
            handleHttpExchange(t, mapper);

            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
