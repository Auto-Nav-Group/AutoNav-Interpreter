package net.autonav.Communication;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import net.autonav.Data.MovementCommand;
import net.autonav.Data.Odometry;
import net.autonav.Utils.CubicSpline;

/**
 * The Movement class handles the movement of a robot by processing movement commands and generating speed curves for the robot's wheels.
 */
public class Movement {
    
    /**
     * The diameter of the robot's wheels in meters.
     * TODO: Update this value with the actual diameter of the robot's wheels.
     */
    public static final int WHEEL_DIAMETER_IN_METERS = 0;
    
    /**
     * The width of the robot in meters.
     * TODO: Update this value with the actual width of the robot.
     */
    public static final int ROBOT_WIDTH_IN_METERS = 0;

    /**
     * The thread responsible for continuously processing movement commands.
     */
    Thread odometryThread = new Thread(() -> {
        while (true) {
            MovementCommand movementData;
            try {
                if (Recieve.movementQueue.isEmpty()) {
                    continue;
                }
                movementData = Recieve.movementQueue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            CompletableFuture<MovementCommand> odometry = CompletableFuture.completedFuture(movementData);
            odometry.thenAcceptAsync((data) -> {
                processMovement(data);
            });
        }
    });
    
    {
        odometryThread.start();
    }

    /**
     * Processes a movement command by interpolating points and moving the robot accordingly.
     * 
     * @param data The movement command to be processed.
     */
    public static void processMovement(MovementCommand data) {
        Odometry currentOdometry = Odometry.currentOdometry;
        List<Point2D> interpolatedPoints = CubicSpline.interpolateCubicSpline(new double[] { currentOdometry.getX(), data.getOdometry().getX() },
                new double[] { currentOdometry.getY(), data.getOdometry().getY() });
        for (Point2D point : interpolatedPoints) {
            moveRobot(point, data.getSpeed());
        }
    }

    /**
     * Moves the robot to a specified point at a given speed.
     * 
     * @param point The destination point for the robot.
     * @param speed The speed at which the robot should move.
     */
    private static void moveRobot(Point2D point, float speed) {
        Point2D currentPoint = Odometry.currentOdometry.toPoint();
        double theta = Math.atan2(point.getY() - currentPoint.getY(), point.getX() - currentPoint.getX());
        double currentHeading = Odometry.currentOdometry.getTheta();
        double angle = theta - currentHeading;

        double currentEncoderUnitsPerDecisecond = 0; // TODO: Update this value by sending it from robot
        double currentVelocity = encoderUnitsPerDecisecondToMetersPerSecond(currentEncoderUnitsPerDecisecond, WHEEL_DIAMETER_IN_METERS);
        double velocityToSustainMovement = currentVelocity * Math.cos(angle);
        double currentSpeed = 0; //TODO: Update this value by sending it from robot
        float[] speeds = generateSpeedCurve((float) currentSpeed, (float) velocityToSustainMovement);
        for (int i = 0; i < speeds.length; i++) {
            speeds[i] = (float) metersPerSecondToEncoderUnitsPerDecisecond(speeds[i], WHEEL_DIAMETER_IN_METERS);
        }

        float[] leftSpeeds = new float[speeds.length];
        float[] rightSpeeds = new float[speeds.length];
        double turningRadius = ROBOT_WIDTH_IN_METERS / (2 * Math.tan(Math.toRadians(angle / 2)));
        for (int i = 0; i < speeds.length; i++) {
            float speedVar = speeds[i];
            float insideWheelSpeed = (float) (speedVar * (turningRadius / (turningRadius + (ROBOT_WIDTH_IN_METERS / 2))));
            float outsideWheelSpeed = (float) (speedVar * ((turningRadius + ROBOT_WIDTH_IN_METERS) / turningRadius));
            if (angle < 0) {
                leftSpeeds[i] = insideWheelSpeed;
                rightSpeeds[i] = outsideWheelSpeed;
            } else {
                leftSpeeds[i] = outsideWheelSpeed;
                rightSpeeds[i] = insideWheelSpeed;
            }
        }
    }

    /**
     * Converts speed from encoder units per decisecond to meters per second.
     * 
     * @param speedInEncoderUnitsPerDecisecond The speed in encoder units per decisecond.
     * @param wheelDiameterInMeters The diameter of the robot's wheels in meters.
     * @return The speed in meters per second.
     */
    private static double encoderUnitsPerDecisecondToMetersPerSecond(double speedInEncoderUnitsPerDecisecond, double wheelDiameterInMeters) {
        double wheelCircumferenceInMeters = Math.PI * wheelDiameterInMeters;
        double encoderUnitsPerMeter = 2048 / wheelCircumferenceInMeters;
        double speedInEncoderUnitsPerSecond = speedInEncoderUnitsPerDecisecond * 10;
        double speedInMetersPerSecond = speedInEncoderUnitsPerSecond / encoderUnitsPerMeter;
        return speedInMetersPerSecond;
    }

    /**
     * Converts speed from meters per second to encoder units per decisecond.
     * 
     * @param speedInMetersPerSecond The speed in meters per second.
     * @param wheelDiameterInMeters The diameter of the robot's wheels in meters.
     * @return The speed in encoder units per decisecond.
     */
    private static double metersPerSecondToEncoderUnitsPerDecisecond(double speedInMetersPerSecond, double wheelDiameterInMeters) {
        double wheelCircumferenceInMeters = Math.PI * wheelDiameterInMeters;
        double encoderUnitsPerMeter = 2048 / wheelCircumferenceInMeters;
        double speedInEncoderUnitsPerSecond = speedInMetersPerSecond * encoderUnitsPerMeter;
        double speedInEncoderUnitsPerDecisecond = speedInEncoderUnitsPerSecond / 10;
        return speedInEncoderUnitsPerDecisecond;
    }

    /**
     * Generates a speed curve between the current speed and the target speed.
     * 
     * @param currentSpeed The current speed of the robot.
     * @param targetSpeed The target speed of the robot.
     * @return An array of speeds representing the speed curve.
     */
    private static float[] generateSpeedCurve(float currentSpeed, float targetSpeed) {
        int steps = 100; // Change as needed
        float[] speeds = new float[steps];
        for (int i = 0; i < steps; i++) {
            double t = (double) i / (steps - 1);
            speeds[i] = (float) quadraticBezier(t, currentSpeed, currentSpeed, targetSpeed);
        }
        return speeds;
    }

    /**
     * Calculates a point on a quadratic Bezier curve.
     * 
     * @param t The parameter value between 0 and 1.
     * @param p0 The starting point of the curve.
     * @param p1 The control point of the curve.
     * @param p2 The ending point of the curve.
     * @return The calculated point on the curve.
     */
    private static double quadraticBezier(double t, double p0, double p1, double p2) {
        return Math.pow(1 - t, 2) * p0 + 2 * (1 - t) * t * p1 + Math.pow(t, 2) * p2;
    }
}
