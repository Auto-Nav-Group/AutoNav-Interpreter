package net.autonav.Queue;

public enum PriorityType {
    MOVEMENT, // Priority 3
    MALFUNCTION, // Priority 5 
    COMMAND, // Priority 2
    DATA, // Priority 1
    OVERRIDE // Priority 4
}
