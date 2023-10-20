package net.autonav.Enums;


/**
 * Enum for commands that can be sent to the robot from the AutoNav server
 */
public enum Commands {
    CHANGE_HEADING,
    CHANGE_VELOCITY,
    GET_OBJECT_DISTANCE,
    GET_OBJECT_HEADING,
    GET_OBJECT_VELOCITY,
    GET_ROBOT_HEADING,
    GET_ROBOT_VELOCITY,
    GET_LIDAR_RAW,
    CONV_CONTROL,
    STREAMS_STOP,
    STREAMS_START,
    LOGS_UPLOAD,
    LOGS_ANALYZE
}
