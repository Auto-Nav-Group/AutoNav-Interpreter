package net.autonav.Subsystems.System;

public class RobotCommunicator {
    static {
        System.loadLibrary("native");
    }

    enum Controllers {
        AUTO, 
        TELE
    }

    public static void setController(Controllers controller) {
        switch (controller) {
            case AUTO:
                setRobotController("AUTO");
                break;
            case TELE:
                setRobotController("TELE");
                break;
        }
    }
    
    public static native void sendJoystickMovement(char joystick, float value);
    public static native void subscribeRobotEvents(boolean value);
    public static native void setRobotController(String controller);
}
