package net.autonav.Subsystems.System;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import net.autonav.Data.Odometry;
import net.autonav.HTTP.ThreadManager;

public class Communication {
    public static void send(Target target, String message) throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName("localhost"); //TODO: Change to correct address
            int port = 8080; //TODO: Change to correct port

            byte[] buf = message.getBytes();
            socket.send(new DatagramPacket(buf, buf.length, address, port));
            socket.close();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public static void init() {
        try (ServerSocket socket = new ServerSocket(8080)) {
            while (true) {
                try (Socket client = socket.accept()) {
                    handleClient(client);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleClient(Socket client) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String inputLine = in.readLine();
            String[] input = inputLine.split(":");
            String type = input[0];
            String body = input[1];

            switch (type) { //TODO: Add more types as needed
                case ("ODOMETRY"): 
                    Gson gson = new Gson();
                    Odometry odometry = gson.fromJson(body, Odometry.class);
                    ThreadManager.processMovement(odometry);
                    break;
            } 

            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static enum Target {
        RIO,
        INTERFACE,
        SERVER, 
        ALL;
    }


    // Communicating with the robot

    static {
        System.loadLibrary("RobotComms");
    }

    public static native void sendJoystickMovement(char joystick, double direction); //TODO: Add more parameters as needed
    public static native void sendJoystickButton(char button); //TODO: Add more parameters as needed
}
