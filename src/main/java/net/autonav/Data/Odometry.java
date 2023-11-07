package net.autonav.Data;

public class Odometry {
    private float x;
    private float y;
    private float theta;

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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setTheta(float theta) {
        this.theta = theta;
    }
}
