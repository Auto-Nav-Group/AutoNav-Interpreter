package net.autonav;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.autonav.Subsystems.RBTSystem.Logs;
import net.autonav.Subsystems.RBTSystem;
import net.autonav.HTTP.HTTPManager;

public class Main {

    public static void main(String[] args) throws IOException {
        HTTPManager.initConnections();
        Logs.newLog();
        Logs.log("Starting AutoNav Interpreter", RBTSystem.LogLevel.INFO);
        //TODO: read current controller value and default controller value and set it to that + print to log
        net.autonav.Utils.MathUtils.calculateTheta();
        Map<String, String> map = new HashMap<>();
        System.out.println(map);
        net.autonav.Utils.MapUtil.load(map);
        System.out.println(map);
    }
}