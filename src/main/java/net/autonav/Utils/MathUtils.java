package net.autonav.Utils;

import net.autonav.Data.Odometry;

import java.awt.Point;
import java.awt.geom.Point2D;

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

    public static void moveProcedure(Odometry odometry) {
        Odometry currentOdometry = Odometry.currentOdometry;

        Point2D.Double p0 = new Point2D.Double(currentOdometry.getX(), currentOdometry.getY());
        Point2D.Double p1 = new Point2D.Double(odometry.getX(), odometry.getY());
        Point2D.Double result = cubicSpline(2, p0, p1, odometry.getTheta(), currentOdometry.getTheta());
        System.out.println(result.x + " " + result.y);
    }

    private static Point2D.Double cubicSpline(double t, Point2D.Double p0, Point2D.Double p1, double theta0, double theta1) {
        double one_minus_t = t - 1;

        double x = one_minus_t * one_minus_t * one_minus_t * p0.x * 
                3 * one_minus_t * one_minus_t * t * (p0.x + theta0) +
                3 * one_minus_t * t * t * (p1.x - theta1) +
                t * t * t * p1.x;   

        double y = one_minus_t * one_minus_t * one_minus_t * p0.y *
                3 * one_minus_t * one_minus_t * t * (p0.y + theta0) +
                3 * one_minus_t * t * t * (p1.y - theta1) +
                t * t * t * p1.y;
        
        return new Point2D.Double(x, y);
    }
}