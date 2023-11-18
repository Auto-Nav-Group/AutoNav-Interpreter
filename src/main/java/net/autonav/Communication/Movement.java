package net.autonav.Communication;

import net.autonav.Data.MovementCommand;
import net.autonav.Data.RobotInfo;
import net.autonav.Subsystems.System.RobotCommunicator;

public class Movement {
    private static final float WHEELBASE_RADIUS = 0; //TODO: Change this value
    private static final float WHEEL_DIAMAETER = 0; //TODO: Change this value

    public static void move(MovementCommand command) {
        float currentLeftSpeed = RobotInfo.getLeftSpeeds();
        float currentRightSpeed = RobotInfo.getRightSpeeds();

        float[] leftSpeeds = smoothVelocity(currentLeftSpeed, command.getLinearVelocity(), 100);
        float[] rightSpeeds = smoothVelocity(currentRightSpeed, command.getLinearVelocity(), 100);

        float[] leftEncoderUnits = new float[100];
        float[] rightEncoderUnits = new float[100];

        float turningSpeed = (command.getAngularVelocity() * WHEELBASE_RADIUS);
        for (int i = 0; i < 100; i++) {
            if (command.getAngularVelocity() > 0) {
                leftEncoderUnits[i] = leftSpeeds[i] + turningSpeed;
                rightEncoderUnits[i] = rightSpeeds[i] - turningSpeed;
            } else if (command.getAngularVelocity() < 0) {
                leftEncoderUnits[i] = leftSpeeds[i] - turningSpeed;
                rightEncoderUnits[i] = rightSpeeds[i] + turningSpeed;
            } else {
                leftEncoderUnits[i] = leftSpeeds[i];
                rightEncoderUnits[i] = rightSpeeds[i];
            }
        }
        
        leftEncoderUnits = toEncoderUnits(leftEncoderUnits);
        rightEncoderUnits = toEncoderUnits(rightEncoderUnits);
        // RobotCommunicator.sendMovementCommand(leftEncoderUnits, rightEncoderUnits);
    }

    private static float[] smoothVelocity(float start, float end, int steps) {
        float[] speeds = new float[steps];
        for (int i = 0; i < steps; i++) {
            double t = (double) i / (steps - 1);
            speeds[i] = (float) quadraticBezier(t, start, end, end);
        }
        return speeds;
    }

    private static double quadraticBezier(double t, double p0, double p1, double p2) {
        double u = 1 - t;
        return u * u * p0 + 2 * u * t * p1 + t * t * p2;
    }

    // encoder units are ticks per 100ms
    // 2048 ticks per revolution
    private static float[] toEncoderUnits(float[] speeds) {
        float distancePerTick = (float) (WHEEL_DIAMAETER * Math.PI) / 2048;
        for (int i = 0; i < speeds.length; i++) {
            speeds[i] = (speeds[i] / 10) / distancePerTick;
        }
        return speeds;
    }
}