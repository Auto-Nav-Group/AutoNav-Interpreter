package net.autonav.Data;

import net.autonav.Queue.QueueInput;

public class ParseData {
    /**
     * Parse data from QueueInput
     * 
     * @return String[] of parsed data
     * Format of String[]:
     * [0] = Subsystem target
     * [1] = Data type
     * [2] = Data
     * 
     * Example command format:
     * "drivetrain:velocity:0.5"
     * "drivetrain:angularVelocity:0.5"
     */

    private QueueInput input;

    public ParseData(QueueInput input) {
        this.input = input;
    }

    public String[] parseData() {
        String[] data = null;
        return data;
    }

}