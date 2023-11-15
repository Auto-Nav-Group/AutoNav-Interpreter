package net.autonav.Data;

/**
 * Data class to store relative robot position from (0, 0)
 * Data input sent from Lidar
 */
public class Odometry {
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

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getTheta() {
        return theta;
    }
}
