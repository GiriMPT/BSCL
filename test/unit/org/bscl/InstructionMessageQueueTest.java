package org.bscl;

import com.google.common.collect.Lists;
import org.bscl.exceptions.EmptyInstructionQueueException;
import org.bscl.exceptions.InvalidMessageException;
import org.bscl.model.InstructionMessage;
import org.bscl.model.InstructionMessageBuilder;
import org.bscl.utils.InstructionMessageComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstructionMessageQueueTest {

    private static final int DEFAULT_VALID_VALUE = 12;
    private Comparator<InstructionMessage> comparator;
    private InstructionMessageQueue instructionMessageQueue;

    @Before
    public void init() {
        comparator = new InstructionMessageComparator();
        instructionMessageQueue = new InstructionMessageQueue(comparator);
    }

    @Test
    public void canCreateInstructionMessageQueue() {
        assertThat(instructionMessageQueue, notNullValue());
    }

    @Test
    public void canAddAnyNumberOfInstructionMessages() {
        assertThat(fill(1, instructionMessageQueue).numberOfMessages(), is(1));
        assertThat(fill(100, instructionMessageQueue).numberOfMessages(), is(101));
        assertThat(fill(1000, instructionMessageQueue).numberOfMessages(), is(1101));
    }

    @Test(expected = InvalidMessageException.class)
    public void shouldThrowInvalidMessageException() {
        instructionMessageQueue.place(instructionMessage(101));
    }

    @Test
    public void shouldPrioritiseAccordingToInstructionType() {
        instructionMessageQueue.place(instructionMessage(92));
        assertInstructionMessageQueue(92);

        instructionMessageQueue.place(instructionMessage(52));
        assertInstructionMessageQueue(52, 92);

        instructionMessageQueue.place(instructionMessage(1));
        assertInstructionMessageQueue(1, 52, 92);

        instructionMessageQueue.place(instructionMessage(5));
        assertInstructionMessageQueue(1, 5, 52, 92);

        instructionMessageQueue.place(instructionMessage(99));
        instructionMessageQueue.place(instructionMessage(21));
        instructionMessageQueue.place(instructionMessage(97));
        instructionMessageQueue.place(instructionMessage(5));
        instructionMessageQueue.place(instructionMessage(42));
        instructionMessageQueue.place(instructionMessage(9));

        assertInstructionMessageQueue(1, 5, 5, 9, 52, 21, 42, 92, 99, 97);

        instructionMessageQueue.place(instructionMessage(3));
        instructionMessageQueue.place(instructionMessage(13));
        instructionMessageQueue.place(instructionMessage(93));

        assertInstructionMessageQueue(1, 5, 5, 9, 3, 52, 21, 42, 13, 92, 99, 97, 93);
    }

    @Test
    public void checkNumberOfMessages() {
        assertThat(instructionMessageQueue.numberOfMessages(), is(0));

        instructionMessageQueue.place(instructionMessage(3));
        assertThat(instructionMessageQueue.numberOfMessages(), is(1));

        instructionMessageQueue.place(instructionMessage(97));
        instructionMessageQueue.place(instructionMessage(5));
        instructionMessageQueue.place(instructionMessage(42));
        instructionMessageQueue.place(instructionMessage(9));
        assertThat(instructionMessageQueue.numberOfMessages(), is(5));
    }

    @Test
    public void checkIfInstructionQueueIsEmpty() {
        assertThat(instructionMessageQueue.isEmpty(), is(true));

        instructionMessageQueue.place(instructionMessage(97));
        instructionMessageQueue.place(instructionMessage(5));
        assertThat(instructionMessageQueue.isEmpty(), is(false));
    }

    @Test(expected = EmptyInstructionQueueException.class)
    public void shouldThrowEmptyInstructionQueueException() {
        instructionMessageQueue.peek();
    }

    @Test
    public void shouldGetMessageAtFrontOfQueue() {
        instructionMessageQueue.place(instructionMessage(97));
        instructionMessageQueue.place(instructionMessage(5));

        assertThat(instructionMessageQueue.peek(), is(instructionMessage(5)));
    }

    @Test
    public void shouldRemoveGivenInstructionMessage() {
        instructionMessageQueue.place(instructionMessage(97));
        instructionMessageQueue.remove(instructionMessage(97));
        assertThat(instructionMessageQueue.isEmpty(), is(true));


        instructionMessageQueue.place(instructionMessage(92));
        instructionMessageQueue.place(instructionMessage(5));
        instructionMessageQueue.place(instructionMessage(52));

        assertThat(instructionMessageQueue.remove(instructionMessage(52)), is(true));
        assertInstructionMessageQueue(5, 92);
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenElementToRemoveNotFound() {
        instructionMessageQueue.place(instructionMessage(92));
        instructionMessageQueue.place(instructionMessage(5));
        instructionMessageQueue.place(instructionMessage(52));

        instructionMessageQueue.remove(instructionMessage(51));
    }

    @Test(expected = NoSuchElementException.class)
    public void shouldThrowNoSuchElementExceptionWhenElementToRemoveIsNull() {
        instructionMessageQueue.remove(null);
    }

    private InstructionMessageQueue fill(int count, InstructionMessageQueue instructionMessageQueue) {
        for(int i=0; i<count ; i++) {
            instructionMessageQueue.place(instructionMessage(DEFAULT_VALID_VALUE));
        }
        return instructionMessageQueue;
    }


    private static InstructionMessage instructionMessage(int type) {
        return new InstructionMessageBuilder().withInstructionType(type)
                .withProductCode(DEFAULT_VALID_VALUE).withQuantity(DEFAULT_VALID_VALUE)
                .withQuantity(DEFAULT_VALID_VALUE)
                .withTimeStamp(DEFAULT_VALID_VALUE)
                .withUom(DEFAULT_VALID_VALUE).build();
    }


    private void assertInstructionMessageQueue(int ...values) {
        List<InstructionMessage> instructionMessageList = Lists.newArrayList() ;
        for (int value : values) {
            instructionMessageList.add(instructionMessage(value));
        }
        assertThat(instructionMessageQueue.viewInstructionQueue(), is(instructionMessageList));
    }



}
