package net.autonav.Data;

import java.awt.geom.Point2D;
import java.io.Serializable;

import net.autonav.Enums.AutoPresets;

/**
 * Data class to store relative robot position from (0, 0)
 * Data input sent from Lidar
 */
public class Odometry implements Serializable {
    public static Odometry currentOdometry = new Odometry(8, 19, 173); 

    private float x;
    private float y;
    private float theta;

    /**
     * Constructor to store the data
     * @param x The x coord relative to (0, 0)
     * @param y The y coord relative to (0, 0)
     * @param theta The theta relative to (0, 0)
     */
    public Odometry(float x, float y, float theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public Odometry() {
        this(0, 0, 0);
    }
    
    public Odometry(AutoPresets preset) {
        switch (preset) {
            case PRESET_1:
                this.x = 0;
                this.y = 0;
                this.theta = 0;
                break;
            case PRESET_2:
                this.x = 0;
                this.y = 0;
                this.theta = 0;
                break;
            case PRESET_3:
                this.x = 0;
                this.y = 0;
                this.theta = 0;
                break;
        } //TODO: Update these to use the valid preset vaules
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getTheta() {
        return theta;
    }

    public Point2D toPoint() {
        return new Point2D.Float(x, y);
    }
}
