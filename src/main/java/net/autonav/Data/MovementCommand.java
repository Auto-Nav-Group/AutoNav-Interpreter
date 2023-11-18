package net.autonav.Data;

public class MovementCommand {
    private Odometry odometry;
    private float speed;

    public MovementCommand(Odometry odometry, float speed) {
        this.odometry = odometry;
        this.speed = speed;
    }

    public Odometry getOdometry() {
        return odometry;
    }

    public float getSpeed() {
        return speed;
    }
}
