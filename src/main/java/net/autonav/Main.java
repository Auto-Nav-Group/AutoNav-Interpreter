package net.autonav;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

import net.autonav.Data.Odometry;
import net.autonav.HTTP.HTTPManager;
import net.autonav.Subsystems.System.RobotCommunicator;
import net.autonav.Utils.CubicSpline;

public class Main {
    private static Main instance;
    private static Logger logger = Logger.getLogger(Main.class.getName());
    private static FileHandler fh;

    static {
        try {
            fh = new FileHandler("C:\\Users\\llluy\\OneDrive\\Documents\\GitHub\\AutoNav-Interpreter\\src\\main\\java\\n" + //
                    "et\\autonav\\Logs\\main.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws IOException {
        HTTPManager.initConnections();
        logger.info("Test");

        // RobotCommunicator.sendJoystickMovement('h', 0);
        // RobotCommunicator.subscribeRobotEvents(true); //TODO: make selector for this

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.severe("Attempting to upload logs to USB... if you do not have a USB inserted, please insert one and enable the program with log backup enabled to initiate a backup."); //TODO: Add USB backup
        }));
        
        double[] x = {12.8745, 22.4117, 33.9803, 14.5686, 25.1490, 16.6823, 27.2431, 18.8196, 29.3803, 20.9568, 31.5176, 22.0980, 33.6313, 24.1921, 35.7686, 26.3490, 37.9215, 28.4745, 39.0274, 30.5803, 41.1333, 32.7098, 43.2627, 34.8392, 45.3921};
        double[] y = {21.9450, 32.4980, 43.0509, 24.6039, 35.1568, 26.7333, 37.2862, 28.8666, 39.4196, 30.9725, 41.5254, 32.0784, 43.6313, 34.1843, 45.7607, 36.3411, 47.9137, 38.4666, 49.0196, 40.5725, 51.1254, 42.6980, 53.2509, 44.8235, 55.3764};

        // Interpolate and get all points
        List<Point2D> interpolatedPoints = CubicSpline.interpolateCubicSpline(x, y);

        // Print the interpolated points
        for (Point2D point : interpolatedPoints) {
            System.out.println("x: " + point.getX() + ", y: " + point.getY());
        }


        XYSeries dataSeries = new XYSeries("Data");
        for (int i = 0; i < x.length; i++) {
            dataSeries.add(x[i], y[i]);
        }

        // Create a dataset for the interpolated points
        XYSeries interpolatedSeries = new XYSeries("Interpolated");
        for (Point2D point : interpolatedPoints) {
            interpolatedSeries.add(point.getX(), point.getY());
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(dataSeries);
        dataset.addSeries(interpolatedSeries);

        // Create the chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Cubic Spline Interpolation",
                "X",
                "Y",
                dataset
        );

        // Customize the chart
        XYPlot plot = chart.getXYPlot();
        plot.setDomainPannable(true);
        plot.setRangePannable(true);
        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        xAxis.setTickUnit(new NumberTickUnit(1.0));
        yAxis.setTickUnit(new NumberTickUnit(1.0));

        // Display the chart in a frame
        JFrame frame = new JFrame("Cubic Spline Interpolation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart), BorderLayout.CENTER);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }
        return instance;
    }
}