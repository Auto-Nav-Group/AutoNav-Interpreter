package net.autonav.Data;

import net.autonav.Enums.Commands;
import net.autonav.Enums.Subsystems;
import net.autonav.Queue.Queue;

public class Executor {
    public void execute() {
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
                case CONV_CONTROL -> {}
                case STREAMS_STOP -> {}
                case STREAMS_START -> {}
                case LOGS_ANALYZE -> {}
                case LOGS_UPLOAD -> {}
            }
            }
        }
    }
}
