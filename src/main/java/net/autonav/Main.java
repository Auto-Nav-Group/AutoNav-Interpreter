package net.autonav;

import java.io.IOException;
import net.autonav.Subsystems.RBTSystem.Logs;
import net.autonav.Subsystems.RBTSystem;
import net.autonav.HTTP.HTTPManager;

public class Main {

    public static void main(String[] args) throws IOException {
        HTTPManager.initConnections();
        Logs.newLog();
        Logs.log("Starting AutoNav Interpreter", RBTSystem.LogLevel.INFO);
        // Logs.log("", RBTSystem.LogLevel.INFO);
        //TODO: read current controller value and default controller value and set it to that + print to log
        net.autonav.Utils.MathUtils.calculateTheta();
    }
}