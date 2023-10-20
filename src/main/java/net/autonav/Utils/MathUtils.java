package net.autonav.Utils;

public class MathUtils {
    /**
     * Calculates the angle between the robot and the April Tag
     * TODO: Add params, make more accurate, and return value
     */
    public static void calculateTheta() {
        double cX = 10; // C denotes camera coords
        double cY = 4;

        double tX = 12; // T denotes April Tag coords
        double tY = 1;

        double relativePosX = tX - cX;
        double relativePosY = tY - cY;

        double yaw = Math.atan2(relativePosY, relativePosX);
        double theta = Math.toDegrees(yaw);

        double adjustedTheta = theta < (double) -270 / 2 ? theta + 360 : theta;

        System.out.println(adjustedTheta);
    }
}