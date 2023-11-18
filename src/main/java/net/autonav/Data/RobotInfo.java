package net.autonav.Data;

public class RobotInfo {
    private static float leftSpeeds;
    private static float rightSpeeds;
    private static float heading;

    public RobotInfo(float leftSpeeds, float rightSpeeds, float heading) {
        RobotInfo.leftSpeeds = leftSpeeds;
        RobotInfo.rightSpeeds = rightSpeeds;
        RobotInfo.heading = heading;
    }

    public static float getLeftSpeeds() {
        return leftSpeeds;
    }

    public static float getRightSpeeds() {
        return rightSpeeds;
    }

    public static float getHeading() {
        return heading;
    }
}
