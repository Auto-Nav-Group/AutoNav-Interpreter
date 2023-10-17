package net.autonav;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.autonav.Subsystems.RBTSystem.Logs;
import net.autonav.Subsystems.RBTSystem;
import net.autonav.HTTP.HTTPManager;
import net.autonav.Utils.ListUtils;
import net.autonav.Utils.MapUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        HTTPManager.initConnections();
        Logs.newLog();
        Logs.log("Starting AutoNav Interpreter", RBTSystem.LogLevel.INFO);
        RBTSystem.Controller.load(); //TODO for maps: make sure that when you write a map that you overwrite the existing one
        net.autonav.Utils.MathUtils.calculateTheta();
    }
}