package net.autonav;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        net.autonav.HTTP.HTTPManager.initConnections();
        // net.autonav.Subsystems.RBTSystem.Logs.newLog();
        // net.autonav.Subsystems.RBTSystem.Logs.log("Starting AutoNav Interpreter", RBTSystem.LogLevel.INFO);
        // net.autonav.Subsystems.RBTSystem.Logs.log("Initializing HTTP connections", RBTSystem.LogLevel.FATAL);
        // net.autonav.Subsystems.RBTSystem.Logs.analyzeLog(2);

        net.autonav.Utils.MathUtils.calculateTheta();
    }
}