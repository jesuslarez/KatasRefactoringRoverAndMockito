package com.codemanship.marsrover;

import org.junit.Test;
import refactoring.Obstacle;
import refactoring.Rover;
import refactoring.Rover.Position;

import static org.assertj.core.api.Assertions.assertThat;
import static refactoring.Rover.Heading.*;
import static refactoring.Rover.Order.*;

public class Rover__ {

    @Test
    public void could_turn_left() {
        Rover rover = new Rover(new SimpleViewPoint(NORTH, new Position(3, 3)));
        rover.go(LEFT);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(WEST);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(3, 3));
    }

    @Test
    public void could_turn_right() {
        Rover rover = new Rover(new SimpleViewPoint(EAST, new Position(5, 1)));
        rover.go(RIGHT);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(SOUTH);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(5, 1));
    }

    @Test
    public void could_go_forward() {
        Rover rover = new Rover(new SimpleViewPoint(SOUTH, new Position(-1, 1)));
        rover.go(FORWARD);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(SOUTH);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(-1, 0));
    }

    @Test
    public void could_go_backward() {
        Rover rover = new Rover(new SimpleViewPoint(WEST, new Position(-4, 4)));
        rover.go(BACKWARD);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(WEST);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(-3, 4));
    }

    @Test
    public void could_execute_many_orders() {
        Rover rover = new Rover(new SimpleViewPoint(WEST, new Position(3, 1)));
        rover.go(BACKWARD, LEFT, FORWARD, RIGHT, FORWARD);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(WEST);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(3, 0));
    }

    @Test
    public void could_execute_legacy_instructions() {
        Rover rover = new Rover(new SimpleViewPoint(WEST, new Position(3, 1)));
        rover.go("BLFRF");
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(WEST);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(3, 0));
    }

    @Test
    public void could_ignore_legacy_instructions() {
        Rover rover = new Rover(new SimpleViewPoint(WEST, new Position(3, 1)));
        rover.go("BLF*RF");
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(WEST);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(3, 0));
    }

    @Test
    public void could_not_move_forward_if_there_is_an_obstacle() {
        SimpleViewPoint simpleViewPoint = new SimpleViewPoint(NORTH, new Position(1, 1));
        Rover rover = new Rover(simpleViewPoint);
        Obstacle obstacle = new Obstacle(new Position(1, 2));
        simpleViewPoint.addObstacle(obstacle);
        rover.go(FORWARD);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(NORTH);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(1, 1));
    }

    @Test
    public void could_not_move_backward_if_there_is_an_obstacle() {
        SimpleViewPoint simpleViewPoint = new SimpleViewPoint(SOUTH, new Position(-1, 3));
        Rover rover = new Rover(simpleViewPoint);
        Obstacle obstacle = new Obstacle(new Position(0, 3));
        simpleViewPoint.addObstacle(obstacle);
        rover.go(RIGHT, BACKWARD);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getHeading()).isEqualTo(WEST);
        assertThat(((SimpleViewPoint) rover.getViewPoint()).getPosition()).isEqualTo(new Position(-1, 3));
    }
}
