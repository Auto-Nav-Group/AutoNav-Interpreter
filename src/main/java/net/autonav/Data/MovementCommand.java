package net.autonav.Data;

public class MovementCommand {
    public static MovementCommand current = new MovementCommand(0, 0);
    private float angularVelocity;
    private float linearVelocity;

    public MovementCommand(float angularVelocity, float linearVelocity) {
        this.angularVelocity = angularVelocity;
        this.linearVelocity = linearVelocity;
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public float getLinearVelocity() {
        return linearVelocity;
    }
}
