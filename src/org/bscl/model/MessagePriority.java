package org.bscl.model;

public enum MessagePriority {
    LOW(0), MEDIUM(1), HIGH(2);

    private final int priority;

    MessagePriority(int i) {
        this.priority = i;
    }

    public int value() {
        return priority;
    }
}
