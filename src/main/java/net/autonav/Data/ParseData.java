package net.autonav.Data;

import net.autonav.Queue.QueueInput;

public class ParseData {
    /**
     * Parse data from QueueInput into String[]
     * Format of String[]:
     * [0] = Subsystem target
     * [1] = Data type
     * [2] = Data
     * Example command format:
     * "drivetrain:velocity:0.5"
     * "drivetrain:angularVelocity:0.5"
     */

    private final QueueInput input;

    public ParseData(QueueInput input) {
        this.input = input;
    }

    public String[] parseData() {
        String[] data = null;
        String[] split = input.getData().split(":");
        if (split.length == 3) {
            data = split;
        } else {
            throw new IllegalArgumentException("Data must be in format: \"target:command:value\"");
        }
        return data;
    }
}