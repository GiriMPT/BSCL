package org.bscl.utils;

import org.bscl.model.InstructionMessage;

import java.util.Comparator;

public class InstructionMessageComparator implements Comparator<InstructionMessage> {

    @Override
    public int compare(InstructionMessage o1, InstructionMessage o2) {
            if(o1.getMessagePriority().value() == o2.getMessagePriority().value()) return 0;
            else if(o1.getMessagePriority().value() > o2.getMessagePriority().value()) return 1;
            else return -1;
    }
}
