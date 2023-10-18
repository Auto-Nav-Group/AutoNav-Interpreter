package net.autonav.Data;

import java.io.IOException;

import net.autonav.Enums.Commands;
import net.autonav.Enums.Subsystems;
import net.autonav.HTTP.HTTPManager;
import net.autonav.Queue.Queue;
import net.autonav.Subsystems.RBTSystem;

public class Executor {
    public void execute() throws IOException {
        String[] data = new ParseData(Queue.queue.poll()).parseData();
        String targetStr = data[0];
        String commandStr = data[1];
        String value = data[2];
        Commands command = Commands.valueOf(commandStr.toUpperCase());
        Subsystems target = Subsystems.valueOf(targetStr.toUpperCase());

        switch (target) {
            case DRIVETRAIN -> {
                switch (command) {
                    case CHANGE_HEADING -> {}
                    case CHANGE_VELOCITY -> {}
                }
            }
            case LIDAR -> {
                switch (command) {
                    case GET_OBJECT_DISTANCE -> {}
                    case GET_OBJECT_HEADING -> {}
                    case GET_OBJECT_VELOCITY -> {}
                    case GET_ROBOT_HEADING -> {}
                    case GET_ROBOT_VELOCITY -> {}
                    case GET_LIDAR_RAW -> {}
                }
            }
            case SYSTEM -> {
                switch (command) {
                    case CONV_CONTROL -> {
                        RBTSystem.Controller.set(RBTSystem.Controllers.valueOf(value.toUpperCase()));
                    }
                    case STREAMS_STOP -> {
                        HTTPManager.closeConnections();
                    }
                    case STREAMS_START -> {
                        HTTPManager.initConnections();
                    }
                    case LOGS_ANALYZE -> {
                        RBTSystem.Logs.analyzeLog(RBTSystem.Logs.currentLog());
                    }
                    case LOGS_UPLOAD -> {
                        if (value == "-1") {
                            RBTSystem.Logs.uploadLog();
                        } else {
                            RBTSystem.Logs.uploadLog(Integer.parseInt(value));
                        }
                    }
                }
            }
        }
    }
}
