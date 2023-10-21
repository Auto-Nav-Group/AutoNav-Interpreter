package net.autonav;

import java.io.IOException;
import java.math.BigInteger;

import net.autonav.Subsystems.RBTSystem.Logs;
import net.autonav.Subsystems.RBTSystem;
import net.autonav.HTTP.HTTPManager;

public class Main {

    public static void main(String[] args) throws IOException {
        // HTTPManager.initConnections();
         Logs.newLog();
         Logs.log("Starting AutoNav Interpreter", RBTSystem.LogLevel.INFO);
        // RBTSystem.Controller.load(); //TODO for maps: make sure that when you write a map that you overwrite the existing one (test this functionality later)
        // net.autonav.Utils.MathUtils.calculateTheta();

        RBTSystem.Logs.uploadLog();
    }

    public static Main getInstance() {
        return new Main();
    }
}