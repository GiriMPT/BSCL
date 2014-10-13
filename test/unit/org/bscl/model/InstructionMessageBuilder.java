package org.bscl.model;

public class InstructionMessageBuilder {
    private InstructionMessage instructionMessage;

    public InstructionMessageBuilder() {
        instructionMessage = new InstructionMessage();
    }

    public InstructionMessageBuilder withInstructionType(int instructionType) {
        instructionMessage.setInstructionType(instructionType);
        return this;
    }

    public InstructionMessageBuilder withProductCode(int productCode) {
        instructionMessage.setProductCode(productCode);
        return this;
    }

    public InstructionMessageBuilder withQuantity(int quantity) {
        instructionMessage.setQuantity(quantity);
        return this;
    }

    public InstructionMessageBuilder withUom(int uom) {
        instructionMessage.setUom(uom);
        return this;
    }

    public InstructionMessageBuilder withTimeStamp(int timeStamp) {
        instructionMessage.setTimestamp(timeStamp);
        return this;
    }

    public InstructionMessage build() {
        return instructionMessage;
    }
}
