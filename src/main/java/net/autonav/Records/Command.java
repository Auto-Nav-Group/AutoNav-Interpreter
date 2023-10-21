// package net.autonav.Records;
//
// public record Command(Subsystems subsystem, IntCommands command, IntActions action, int value) {
//     Command(Subsystems subsystem, IntCommands command, IntActions action) {
//         if (command == IntCommands.SET) {
//             throw new IllegalArgumentException("Warning! Command is SET but no value was provided!");
//         } else {
//             this(subsystem, command, action, -1);
//         }
//     }
// }
