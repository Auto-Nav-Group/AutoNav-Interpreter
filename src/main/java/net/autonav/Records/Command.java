package net.autonav.Records;

//TODO: Fix this mess of a file
public record Command(Subsystems subsystem, IntCommands command, IntActions action, int value) {
    Command(Subsystems subsystem, IntCommands command, IntActions action) {
        if (command == IntCommands.SET) {
            throw new IllegalArgumentException("Warning! Command is SET but no value was provided!");
        } else {
            this(subsystem, command, action, -1);
        }
    }
}
