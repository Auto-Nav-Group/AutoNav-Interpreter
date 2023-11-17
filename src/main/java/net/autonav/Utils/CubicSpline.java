package net.autonav.Utils;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class CubicSpline {

    public static List<Point2D> interpolateCubicSpline(double[] x, double[] y) {
        if (x == null || y == null || x.length != y.length || x.length < 2) {
            throw new IllegalArgumentException("Invalid input arrays");
        }

        int n = x.length - 1;

        double[] h = new double[n];
        double[] alpha = new double[n];
        double[] l = new double[n + 1];
        double[] mu = new double[n];
        double[] z = new double[n + 1];
        double[] c = new double[n + 1];
        double[] b = new double[n];
        double[] d = new double[n];

        for (int i = 0; i < n; i++) {
            h[i] = x[i + 1] - x[i];
        }

        alpha[0] = 3.0 / h[0] * (y[1] - y[0]);
        for (int i = 1; i < n; i++) {
            alpha[i] = (3.0 / h[i]) * (y[i + 1] - y[i]) - (3.0 / h[i - 1]) * (y[i] - y[i - 1]);
        }

        l[0] = 1.0;
        mu[0] = 0.0;
        z[0] = 0.0;

        for (int i = 1; i < n; i++) {
            l[i] = 2.0 * (x[i + 1] - x[i - 1]) - h[i - 1] * mu[i - 1];
            mu[i] = h[i] / l[i];
            z[i] = (alpha[i] - h[i - 1] * z[i - 1]) / l[i];
        }

        l[n] = 1.0;
        z[n] = 0.0;
        c[n] = 0.0;

        for (int j = n - 1; j >= 0; j--) {
            c[j] = z[j] - mu[j] * c[j + 1];
            b[j] = (y[j + 1] - y[j]) / h[j] - h[j] * (c[j + 1] + 2.0 * c[j]) / 3.0;
            d[j] = (c[j + 1] - c[j]) / (3.0 * h[j]);
        }

        List<Point2D> interpolatedPoints = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (double t = x[i]; t < x[i + 1]; t += 0.1) { // Adjust the step as necessary
                double deltaX = t - x[i];
                double interpolatedY = y[i] + b[i] * deltaX + c[i] * Math.pow(deltaX, 2) + d[i] * Math.pow(deltaX, 3);
                interpolatedPoints.add(new Point2D.Double(t, interpolatedY));
            }
        }

        interpolatedPoints.add(new Point2D.Double(x[n], y[n]));

        return interpolatedPoints;
    }

}