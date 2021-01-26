package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.SimpleViewPoint.Position;

import static org.junit.Assert.assertEquals;
import static refactoring.SimpleViewPoint..Heading.*;

public class Position_ {

    @Test
    public void should_calculate_forward_position() {
        assertEquals(new Position(-1, 0), new Position(0, 0).forward(NORTH).forward(WEST).forward(SOUTH));
        assertEquals(new Position(0, -1), new Position(0, 0).forward(EAST).forward(WEST).forward(SOUTH));
    }

    @Test
    public void should_calculate_back_position() {
        assertEquals(new Position(2, 0), new Position(3, 0).backward(NORTH).back(WEST).back(SOUTH));
        assertEquals(new Position(0, -2), new Position(0, -1).backward(NORTH).back(EAST).back(SOUTH));
    }
}
