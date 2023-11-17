package net.autonav;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import net.autonav.HTTP.HTTPManager;
import net.autonav.Subsystems.System.RobotCommunicator;

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
        HTTPManager.initConnections();
        logger.info("Test");

        RobotCommunicator.sendJoystickMovement('h', 0);
        RobotCommunicator.subscribeRobotEvents(true); //TODO: make selector for this

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.severe("Attempting to upload logs to USB... if you do not have a USB inserted, please insert one and enable the program with log backup enabled to initiate a backup."); //TODO: Add USB backup
        }));
    }

    

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }
}