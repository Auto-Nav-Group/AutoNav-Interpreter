package net.autonav;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import net.autonav.Utils.CubicSpline;

public class Main {
    private static Main instance;
    private static Logger logger = Logger.getLogger(Main.class.getName());
    private static FileHandler fh;

    static {
        try {
            fh = new FileHandler("C:\\Users\\llluy\\OneDrive\\Documents\\GitHub\\AutoNav-Interpreter\\src\\main\\java\\n" + //
                    "et\\autonav\\Logs\\main.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        logger.info("Test");

        // RobotCommunicator.sendJoystickMovement('h', 0);
        // RobotCommunicator.subscribeRobotEvents(true); //TODO: make selector for this

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.severe("Attempting to upload logs to USB... if you do not have a USB inserted, please insert one and enable the program with log backup enabled to initiate a backup."); //TODO: Add USB backup
        }));
        
        double[] x = {12.8745, 22.4117, 33.9803, 14.5686, 25.1490, 16.6823, 27.2431, 18.8196, 29.3803, 20.9568, 31.5176, 22.0980, 33.6313, 24.1921, 35.7686, 26.3490, 37.9215, 28.4745, 39.0274, 30.5803, 41.1333, 32.7098, 43.2627, 34.8392, 45.3921};
        double[] y = {21.9450, 32.4980, 43.0509, 24.6039, 35.1568, 26.7333, 37.2862, 28.8666, 39.4196, 30.9725, 41.5254, 32.0784, 43.6313, 34.1843, 45.7607, 36.3411, 47.9137, 38.4666, 49.0196, 40.5725, 51.1254, 42.6980, 53.2509, 44.8235, 55.3764};

        // Interpolate and get all points
        List<Point2D> interpolatedPoints = CubicSpline.interpolateCubicSpline(x, y);

        // Print the interpolated points
        for (Point2D point : interpolatedPoints) {
            System.out.println("x: " + point.getX() + ", y: " + point.getY());
        }
    }

    

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }
}