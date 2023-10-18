package net.autonav.Records;

//TODO: Fix this mess of a file
public record Command(Subsystems subsystem, IntCommands command, IntActions action, int value) {
    Command(Subsystems subsystem, IntCommands command, IntActions action) {
        this(subsystem, command, action, -1);
    }
}
