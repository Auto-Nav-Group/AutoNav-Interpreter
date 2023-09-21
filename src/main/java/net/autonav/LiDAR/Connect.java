package net.autonav.LiDAR;

public class Connect {
    String lidarIP = "10.67.2.184";
    int lidarPort = 25565;

    try (
        Socket lidarSocket = new Socket(lidarIP, lidarPort);
        PrintWriter out = new PrintWriter(lidarSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(lidarSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    )
    
}
