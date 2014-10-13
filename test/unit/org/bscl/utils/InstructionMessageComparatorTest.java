package org.bscl.utils;

import org.bscl.model.InstructionMessage;
import org.bscl.model.InstructionMessageBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InstructionMessageComparatorTest {

    private static final int DEFAULT_VALID_VALUE = 12;
    private static final int LOW_PRIORITY_INSTRUCTION_TYPE = 92;
    private static final int HIGH_PRIORITY_INSTRUCTION_TYPE = 2;
    private static final int MEDIUM_PRIORITY_INSTRUCTION_TYPE = 52;

    private InstructionMessageComparator comparator;
    private InstructionMessage highPriorityMessage;
    private InstructionMessage mediumPriorityMessage;
    private InstructionMessage lowPriorityMessage;

    @Before
    public void setup() {
        comparator = new InstructionMessageComparator();
        lowPriorityMessage = instructionMessage(LOW_PRIORITY_INSTRUCTION_TYPE);
        mediumPriorityMessage = instructionMessage(MEDIUM_PRIORITY_INSTRUCTION_TYPE);
        highPriorityMessage = instructionMessage(HIGH_PRIORITY_INSTRUCTION_TYPE);
    }

    @Test
    public void shouldReturnZeroWhenMessagePriorityIsSame() {
        assertThat(compare(highPriorityMessage, highPriorityMessage), is(0));
        assertThat(compare(mediumPriorityMessage, mediumPriorityMessage), is(0));
        assertThat(compare(lowPriorityMessage, lowPriorityMessage), is(0));
    }

    @Test
    public void shouldReturnPositiveIntegerForGreaterThan() {
        assertThat(compare(highPriorityMessage, mediumPriorityMessage), is(1));
        assertThat(compare(mediumPriorityMessage, lowPriorityMessage), is(1));
    }

    @Test
    public void shouldReturnNegativeIntegerForLessThan() {
        assertThat(compare(mediumPriorityMessage, highPriorityMessage), is(-1));
        assertThat(compare(lowPriorityMessage, highPriorityMessage), is(-1));
        assertThat(compare(lowPriorityMessage, mediumPriorityMessage), is(-1));
    }

    private int compare(InstructionMessage first, InstructionMessage second) {
        return comparator.compare(first, second);
    }

    private InstructionMessage instructionMessage(int type) {
        return new InstructionMessageBuilder()
                .withInstructionType(type)
                .withProductCode(DEFAULT_VALID_VALUE).withQuantity(DEFAULT_VALID_VALUE)
                .withQuantity(DEFAULT_VALID_VALUE)
                .withTimeStamp(DEFAULT_VALID_VALUE)
                .withUom(DEFAULT_VALID_VALUE).build();
    }

}
