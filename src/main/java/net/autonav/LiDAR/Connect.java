package net.autonav.LiDAR;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connect {
    Socket lidarSocket;
    String lidarIP = "10.67.2.184";
    final int lidarPort = 10940;
    boolean connected = false;
    
    private void connectToLidar() {
        try {
            ServerSocket serverSocket = new ServerSocket(lidarPort);
            lidarSocket = serverSocket.accept();
            
            InputStream input = lidarSocket.getInputStream();
            OutputStream output = lidarSocket.getOutputStream();
            connected = true;
        } catch(Exception e) {
            System.out.println("Error connecting to LiDAR: " + e.getMessage());
        }
    }
}
