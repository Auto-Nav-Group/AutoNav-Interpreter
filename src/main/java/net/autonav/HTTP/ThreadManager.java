package net.autonav.HTTP;

import net.autonav.Data.Odometry;

public class ThreadManager {
    Thread odometryThread = new Thread();

    private static void processMovement(Odometry odometry) {
        odometryThread // run some code on that thread
    }
}
