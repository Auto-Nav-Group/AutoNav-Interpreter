package net.autonav;

/**
 * Class for storing odometry data
 */
public class Odometry {
    //TODO: get position relative to 0, 0 (april tags)
    private float x;
    private float y;
    private float theta;

    public Odometry(final float x, final float y, final float theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getTheta() {
        return this.theta;
    }
}
