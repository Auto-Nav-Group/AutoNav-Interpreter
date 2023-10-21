package net.autonav;

import java.io.IOException;
import java.math.BigInteger;

import net.autonav.Subsystems.RBTSystem.Logs;
import net.autonav.Subsystems.RBTSystem;
import net.autonav.HTTP.HTTPManager;

public class Main {

    public static void main(String[] args) throws IOException {
         HTTPManager.initConnections();
        // RBTSystem.Controller.load(); //TODO for maps: make sure that when you write a map that you overwrite the existing one (test this functionality later)

        Logs.newLog();

        Logs.log("Starting AutoNav Interpreter", RBTSystem.LogLevel.INFO);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Logs.log("Shutting down AutoNav Interpreter", RBTSystem.LogLevel.INFO);
            if (Logs.uploadLogsOnExit) {
                try {
                    Logs.uploadLog(true);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (Logs.deleteAllLogsOnExit) {
                try {
                    Logs.deleteLogFile(true);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }));

    }

    public static Main getInstance() {
        return new Main();
    }
}