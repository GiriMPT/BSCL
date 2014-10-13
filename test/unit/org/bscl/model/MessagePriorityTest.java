package org.bscl.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MessagePriorityTest {

    @Test
    public void shouldHaveZeroValueForLowPriority() {
        assertThat(MessagePriority.LOW.value(), is(0));
    }

    @Test
    public void shouldHaveOneForMediumPriority() {
        assertThat(MessagePriority.MEDIUM.value(), is(1));
    }

    @Test
    public void shouldHaveTwoForHighPriority() {
        assertThat(MessagePriority.HIGH.value(), is(2));
    }
}
