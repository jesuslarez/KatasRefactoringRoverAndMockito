package refactoring;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static refactor.Rover.Order.*;

public class Rover_ {
    @Test
    public void could_execute_many_orders() {
        // Given
        ViewPoint mockViewPoint = create(FORWARD,FORWARD,LEFT,BACKWARD);
        Rover rover = new Rover(mockViewPoint);

        // When
        rover.go(FORWARD, FORWARD, LEFT, BACKWARD);

        // Then
        assertThat(rover.getViewPoint()).isNotEqualTo(mockViewPoint);
    }

    private ViewPoint create(Rover.Order... orders) {
        ViewPoint mockViewPoint = mock(ViewPoint.class);
        ViewPoint current = mockViewPoint;
        for (Rover.Order order : orders) {
            ViewPoint next = mock(ViewPoint.class);
            switch (order) {
                case LEFT: when(current.turnLeft()).thenReturn(next); break;
                case RIGHT: when(current.turnRight()).thenReturn(next); break;
                case FORWARD: when(current.forward()).thenReturn(next); break;
                case BACKWARD: when(current.backward()).thenReturn(next); break;
            }
            current = next;
        }
        return mockViewPoint;
    }

    @Test
    public void could_execute_legacy_instructions() {
        // Given
        ViewPoint mockViewPoint = create(BACKWARD,LEFT,FORWARD,RIGHT,FORWARD);
        Rover rover = new Rover(mockViewPoint);

        // When
        rover.go("BLFRF");

        // Then
        assertThat(rover.getViewPoint()).isNotEqualTo(mockViewPoint);
    }

    @Test
    public void could_ignore_legacy_instructions() {
        // Given
        ViewPoint mockViewPoint = create(BACKWARD,LEFT,FORWARD,RIGHT,FORWARD);
        Rover rover = new Rover(mockViewPoint);

        // When
        rover.go("BL*FRF");

        // Then
        assertThat(rover.getViewPoint()).isNotEqualTo(mockViewPoint);
    }

    @Test
    public void could_not_move_if_there_are_obstacles(){
        // Given
        ViewPoint mockViewPoint = create(BACKWARD,RIGHT,FORWARD);
        when(mockViewPoint.backward()).thenReturn(null);
        Rover rover = new Rover(mockViewPoint);

        // When
        rover.go(BACKWARD,RIGHT,FORWARD);

        // Then
        verify(mockViewPoint).backward();
        verify(mockViewPoint,never()).turnRight();
        verify(mockViewPoint,never()).forward();
        assertThat(rover.getViewPoint()).isEqualTo(mockViewPoint);
    }
}