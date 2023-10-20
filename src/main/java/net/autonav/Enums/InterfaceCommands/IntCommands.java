package net.autonav.Enums.InterfaceCommands;


/**
 * Enum for commands that can be sent to the robot from the user interface
 */
public enum IntCommands {
    CONTROl,
    STREAMS,
    LOGS,
    LIDAR_RAW,
    /**
     * Only GET
     */
    ROBOT_VELOCITY, 
    /**
     * Only GET
     */
    ROBOT_HEADING
}
