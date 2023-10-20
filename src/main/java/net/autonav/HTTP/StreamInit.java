package net.autonav.HTTP;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import static net.autonav.HTTP.HTTPManager.server;

import java.io.IOException;
import java.io.OutputStream;
import static net.autonav.HTTP.HTTPManager.handleHttpExchange;

public class StreamInit {
    /**
     * Initializes the HTTP server and streams
     * Streams:
     * <ul>
     * <li>Malfunction
     * <li>Override
     * <li>Movement
     * <li>Data
     * <li>Command
     * <ul>
     * @throws IOException
     */
    public static void initConnection() throws IOException {
        System.out.println("Streams started at port 8080");
        server.createContext("/malfunction", new Handler());
        server.createContext("/override", new Handler());
        server.createContext("/movement", new Handler());
        server.createContext("/data", new Handler());
        server.createContext("/command", new Handler());
    }

    /**
     * Closes the HTTP server and streams
     */
    public static void closeConnection() {
        server.removeContext("/override");
        server.removeContext("/movement");
        server.removeContext("/data");
        server.removeContext("/command");
        server.removeContext("/malfunction");
    }

    /**
     * Handles the incomming HTTP data
     */
    static class Handler implements HttpHandler {
        private final ObjectMapper mapper = new ObjectMapper();
        
        /**
         * Handles the HTTP exchange
         * @param t HttpExchange object
         * @throws IOException
         */
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