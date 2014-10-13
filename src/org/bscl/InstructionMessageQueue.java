package org.bscl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.bscl.exceptions.EmptyInstructionQueueException;
import org.bscl.exceptions.InvalidMessageException;
import org.bscl.model.InstructionMessage;

import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import static java.lang.String.format;

public class InstructionMessageQueue {

    private final Comparator<InstructionMessage> instructionMessageComparator;
    private List<InstructionMessage> instructionMessages = Lists.newCopyOnWriteArrayList();

    public InstructionMessageQueue(Comparator<InstructionMessage> instructionMessageComparator) {
        this.instructionMessageComparator = instructionMessageComparator;
    }

    public void place(InstructionMessage instructionMessage) {
        checkIfMessageValid(instructionMessage);
        instructionMessages.add(getNextIndex(instructionMessage), instructionMessage);
    }

    public int numberOfMessages() {
        return instructionMessages.size();
    }

    public boolean isEmpty() {
        return numberOfMessages() == 0;
    }

    public InstructionMessage peek() {
        if(isEmpty()) {
            throw new EmptyInstructionQueueException();
        } else {
            return instructionMessages.get(0);
        }
    }

    public boolean remove(InstructionMessage instructionMessage) {
        if(!instructionMessages.remove(instructionMessage)) {
            throw new NoSuchElementException();
        }
        return true;
    }

    public List<InstructionMessage> viewInstructionQueue() {
        return ImmutableList.copyOf(instructionMessages);
    }

    private int getNextIndex(InstructionMessage instructionMessage) {
        int index = instructionMessages.size();
        ListIterator<InstructionMessage> listIterator = instructionMessages.listIterator();
        while(listIterator.hasNext()) {
            InstructionMessage currentInstructionMessage = listIterator.next();
            if(instructionMessageComparator.compare(instructionMessage, currentInstructionMessage) == 1) {
                index = listIterator.previousIndex();
                break;
            }
        }
        return index;
    }

    private void checkIfMessageValid(InstructionMessage instructionMessage)  {
        if(!instructionMessage.isValidMessage()) {
            throw new InvalidMessageException(format("Invalid message: %s", instructionMessage));
        }
    }

}
