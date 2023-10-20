package net.autonav.Data;

import org.jetbrains.annotations.ApiStatus.ScheduledForRemoval;

import net.autonav.Queue.QueueInput;


/**
 * Responsible for parsing data from the queue into the format that the executor can use
 */
@ScheduledForRemoval
@Deprecated
public class ParseData {
    private final QueueInput input;

    /**
     * Constructor for ParseData
     * Takes in QueueInput object
     * @param input
     */
    public ParseData(QueueInput input) {
        this.input = input;
    }

    /**
     * Parses data from QueueInput into String[]
     * @return String[] of parsed data
     * 
     * Format of String[]:
     * <ul>
     * <li>[0] = Subsystem target
     * <li>[1] = Data type
     * <li>[2] = Data
     * </ul>
     */
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