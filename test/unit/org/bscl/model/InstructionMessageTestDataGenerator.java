package org.bscl.model;

import static junitparams.JUnitParamsRunner.$;

public class InstructionMessageTestDataGenerator {

    private static final int DEFAULT_VALID_VALUE = 12;

    public static Object[] provideInstructionType() {
        return $(
                $(instructionMessageBuilder().withInstructionType(-1).build(), false),
                $(instructionMessageBuilder().withInstructionType(0).build(), false),
                $(instructionMessageBuilder().withInstructionType(100).build(), false),
                $(instructionMessageBuilder().withInstructionType(1).build(), true),
                $(instructionMessageBuilder().withInstructionType(50).build(), true),
                $(instructionMessageBuilder().withInstructionType(99).build(), true));
    }

    public static Object[] provideProductCode() {
        return $(
                $(instructionMessageBuilder().withProductCode(-1).build(), false),
                $(instructionMessageBuilder().withProductCode(0).build(), false),
                $(instructionMessageBuilder().withProductCode(100).build(), true));
    }


    public static Object[] provideQuantity() {
        return $(
                $(instructionMessageBuilder().withQuantity(-1).build(), false),
                $(instructionMessageBuilder().withQuantity(0).build(), false),
                $(instructionMessageBuilder().withQuantity(5000).build(), true));
    }

    public static Object[] provideUom() {
        return $(
                $(instructionMessageBuilder().withUom(-1).build(), false),
                $(instructionMessageBuilder().withUom(256).build(), false),
                $(instructionMessageBuilder().withUom(0).build(), true),
                $(instructionMessageBuilder().withUom(1).build(), true),
                $(instructionMessageBuilder().withUom(255).build(), true));
    }

    public static Object[] provideTimestamp() {
        return $(
                $(instructionMessageBuilder().withTimeStamp(-1).build(), false),
                $(instructionMessageBuilder().withTimeStamp(0).build(), false),
                $(instructionMessageBuilder().withTimeStamp(99).build(), true));
    }


    private static InstructionMessageBuilder instructionMessageBuilder() {
        return new InstructionMessageBuilder()
                .withInstructionType(DEFAULT_VALID_VALUE)
                .withProductCode(DEFAULT_VALID_VALUE).withQuantity(DEFAULT_VALID_VALUE)
                .withQuantity(DEFAULT_VALID_VALUE)
                .withTimeStamp(DEFAULT_VALID_VALUE)
                .withUom(DEFAULT_VALID_VALUE);
    }
}
