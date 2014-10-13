package org.bscl.model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


@RunWith(JUnitParamsRunner.class)
public class InstructionMessageTest {

    @Test
    public void constructorShouldSetAllParameters() {
        InstructionMessage instructionMessage = new InstructionMessage(1, 2, 3, 4, 5);
        assertThat(instructionMessage.getInstructionType(), is(1));
        assertThat(instructionMessage.getProductCode(), is(2));
        assertThat(instructionMessage.getQuantity(), is(3));
        assertThat(instructionMessage.getUom(), is(4));
        assertThat(instructionMessage.getTimestamp(), is(5));
    }

    @Test
    public void hasGotCorrectMessagePriority() {
        assertThat(instructionMessageWithInstructionType(1).getMessagePriority(), is(MessagePriority.HIGH));
        assertThat(instructionMessageWithInstructionType(5).getMessagePriority(), is(MessagePriority.HIGH));
        assertThat(instructionMessageWithInstructionType(10).getMessagePriority(), is(MessagePriority.HIGH));

        assertThat(instructionMessageWithInstructionType(11).getMessagePriority(), is(MessagePriority.MEDIUM));
        assertThat(instructionMessageWithInstructionType(50).getMessagePriority(), is(MessagePriority.MEDIUM));
        assertThat(instructionMessageWithInstructionType(90).getMessagePriority(), is(MessagePriority.MEDIUM));

        assertThat(instructionMessageWithInstructionType(91).getMessagePriority(), is(MessagePriority.LOW));
        assertThat(instructionMessageWithInstructionType(95).getMessagePriority(), is(MessagePriority.LOW));
        assertThat(instructionMessageWithInstructionType(99).getMessagePriority(), is(MessagePriority.LOW));
    }

    @Test
    @Parameters(source = InstructionMessageTestDataGenerator.class)
    public void validatesMessage(InstructionMessage message, boolean valid) {
        assertThat(message.isValidMessage(), is(valid));
    }

    private InstructionMessage instructionMessageWithInstructionType(int instructionType) {
        return new InstructionMessage(instructionType, 2, 3, 4, 5);
    }


}
