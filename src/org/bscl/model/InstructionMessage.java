package org.bscl.model;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class InstructionMessage {

    private int instructionType;
    private int productCode;
    private int quantity;
    private int uom;
    private int timestamp;

    public InstructionMessage() {
    }

    public InstructionMessage(int instructionType, int productCode, int quantity, int uom, int timestamp) {
        this.instructionType = instructionType;
        this.productCode = productCode;
        this.quantity = quantity;
        this.uom = uom;
        this.timestamp = timestamp;
    }

    public int getInstructionType() {
        return instructionType;
    }

    public int getProductCode() {
        return productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUom() {
        return uom;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setInstructionType(int instructionType) {
        this.instructionType = instructionType;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUom(int uom) {
        this.uom = uom;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isValidMessage() {
        return checkInstructionTypeRange(instructionType, 0, 100)
                && checkUomRange(uom, 0, 256)
                && checkGreaterThanZero(productCode, quantity, timestamp);
    }

    public MessagePriority getMessagePriority() {
        MessagePriority messagePriority;
        if(checkPriorityRange(instructionType, 0 , 11)) {
            messagePriority = MessagePriority.HIGH;
        } else if(checkPriorityRange(instructionType, 10 , 91)) {
            messagePriority = MessagePriority.MEDIUM;
        } else {
            messagePriority = MessagePriority.LOW;
        }
        return messagePriority;
    }

    @Override
    public boolean equals(final Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    private boolean checkPriorityRange(int value, int min, int max) {
        return min < value && checkUpperRange(value, max);
    }

    private boolean checkInstructionTypeRange(int value, int min, int max) {
        return value > min && checkUpperRange(value, max);
    }

    private boolean checkUomRange(int value, int min, int max) {
        return value >= min && checkUpperRange(value, max);
    }

    private boolean checkUpperRange(int value, int max) {
        return value < max;
    }

    private boolean checkGreaterThanZero(int... values) {
        for (int value : values) {
            if (value <= 0) {
                return false;
            }
        }
        return true;
    }
}
