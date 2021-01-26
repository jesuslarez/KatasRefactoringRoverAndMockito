package refactoring;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Arrays.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import refactoring.SimpleViewPoint.Position;

@Getter
public class Rover {

    private ViewPoint viewPoint;

    public Rover(ViewPoint viewPoint) {
        this.viewPoint = viewPoint;
    }

    public void set(ViewPoint viewPoint) {
        if (viewPoint != null) this.viewPoint = viewPoint;
    }

    public void go(String instructions) {
        set(go(stream(instructions.split("")).map(Order::of)));
    }

    public void go(Order... orders) {
        set(go(stream(orders)));
    }

    private ViewPoint go(Stream<Order> orders) {
        return orders.filter(Objects::nonNull).reduce(viewPoint, this::execute, (v1, v2) -> null);
    }

    private ViewPoint execute(ViewPoint viewPoint, Order order) {
        return viewPoint != null ? actions.get(order).execute(viewPoint) : null;
    }

    Map<Order, Action> actions = new HashMap<>();

    {
        actions.put(Order.FORWARD, ViewPoint::forward);
        actions.put(Order.BACKWARD, ViewPoint::backward);
        actions.put(Order.LEFT, ViewPoint::turnLeft);
        actions.put(Order.RIGHT, ViewPoint::turnRight);
    }

    public enum Order {
        FORWARD, BACKWARD, LEFT, RIGHT;

        public static Order of(String instruction) {
            if (instruction.equals("F")) return FORWARD;
            if (instruction.equals("B")) return BACKWARD;
            if (instruction.equals("L")) return LEFT;
            if (instruction.equals("R")) return RIGHT;
            return null;
        }
    }

    @FunctionalInterface
    public interface Action {
        ViewPoint execute(ViewPoint viewPoint);
    }
}
