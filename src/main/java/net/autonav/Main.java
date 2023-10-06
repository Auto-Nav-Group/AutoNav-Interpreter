package net.autonav;

import net.autonav.HTTP.HTTPManager;
import net.autonav.Subsystems.RBTSystem;
import net.autonav.Utils.MathUtils;

import java.io.IOException;

public class Main {
    CollectData data = new CollectData();

    public static void main(String[] args) throws IOException {
        HTTPManager.initConnections();
        RBTSystem.Logs.newLog();
        RBTSystem.Logs.log("Starting AutoNav Interpreter", RBTSystem.LogLevel.INFO);
        RBTSystem.Logs.log("Initializing HTTP connections", RBTSystem.LogLevel.FATAL);
        RBTSystem.Logs.analyzeLog(2);

        MathUtils.calculateTheta();
    }
}
