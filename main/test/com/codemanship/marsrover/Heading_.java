package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.Rover.Heading;

import static org.junit.Assert.assertEquals;
import static refactoring.Rover.Heading.*;

public class Heading_ {

	@Test
	public void should_be_created_from_string() {
		assertEquals(NORTH, Heading.of("N"));
		assertEquals(SOUTH, Heading.of("S"));
		assertEquals(EAST, Heading.of("E"));
		assertEquals(WEST, Heading.of("W"));
	}

	@Test
	public void should_be_created_from_char() {
		assertEquals(NORTH, Heading.of('N'));
		assertEquals(SOUTH, Heading.of('S'));
		assertEquals(EAST, Heading.of('E'));
		assertEquals(WEST, Heading.of('W'));
	}

	@Test
	public void should_be_able_turn_right() {
		assertEquals(EAST, NORTH.turnRight());
		assertEquals(SOUTH, EAST.turnRight());
		assertEquals(WEST, SOUTH.turnRight());
		assertEquals(NORTH, WEST.turnRight());
	}

	@Test
	public void should_be_able_turn_left() {
		assertEquals(WEST, NORTH.turnLeft());
		assertEquals(NORTH, EAST.turnLeft());
		assertEquals(EAST, SOUTH.turnLeft());
		assertEquals(SOUTH, WEST.turnLeft());
	}
}
