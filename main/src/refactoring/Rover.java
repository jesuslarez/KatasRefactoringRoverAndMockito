package refactoring;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Rover {

    private Heading heading;
    private Position position;

    public Rover(Heading heading, Position position) {
        this.heading = heading;
        this.position = position;
    }

    public Rover(String facing, int x, int y) {
        this(Heading.of(facing), new Position(x, y));
    }

    public Rover(Heading heading, int x, int y) {
        this(heading, new Position(x, y));
    }

    public Heading heading() {
        return heading;
    }

    public Position position() {
        return this.position;
    }

    public void go(String instructions) {
        Stream<Order> orders = Arrays.stream(instructions.split(""))
                .map(Order::of)
                .filter(Objects::nonNull);
        orders.forEach(order -> actions.get(order).execute());
    }

    public void go(Order... orders) {
        for (Order order : orders) {
            actions.get(order).execute();
        }
    }

    public static class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position forward(Heading heading) {
            return new Position(this.x + dx(heading), this.y + dy(heading));
        }

        public Position back (Heading heading)
        {
            return new Position(this.x - dx(heading), this.y - dy(heading));

        }

        private int dx(Heading heading) {
            if (heading == Heading.EAST) return 1;
            if (heading == Heading.WEST) return -1;
            return 0;
        }

        private int dy(Heading heading) {
            if (heading == Heading.NORTH) return 1;
            if (heading == Heading.SOUTH) return -1;
            return 0;
        }

        @Override
        public boolean equals(Object object) {
            return isSameClass(object) && equals((Position) object);
        }

        private boolean equals(Position position) {
            return position == this || (x == position.x && y == position.y);
        }

        private boolean isSameClass(Object object) {
            return object != null && object.getClass() == Position.class;
        }

        @Override
        public String toString() {
            return "Position{" + "x= " + x + ", y= " + y + '}';
        }
    }

    Map<Order,Action> actions = new HashMap<>();
    {
        actions.put(Order.Forward, ()->position = position.forward(heading));
        actions.put(Order.Backward, ()->position = position.backward(heading));
        actions.put(Order.Left, ()->heading = heading.turnLeft());
        actions.put(Order.Right, ()->heading = heading.turnRight());
    }

    public enum Order {
        FOWARD, BACKWARD, LEFT, RIGHT;

        public static Order of(String instruction) {
            if (instruction.equals("F")) return FOWARD;
            if (instruction.equals("B")) return BACKWARD;
            if (instruction.equals("L")) return LEFT;
            if (instruction.equals("R")) return RIGHT;
            return null;
        }
    }
    @FunctionalInterface
    public interface Action {
        void execute();
    }


    public enum Heading {
        NORTH, SOUTH, EAST, WEST;

        public static Heading of(String label) {
            return of(label.charAt(0));
        }

        public static Heading of(char label) {
            if (label == 'N') return NORTH;
            if (label == 'S') return SOUTH;
            if (label == 'E') return EAST;
            return null;
        }

        public Heading turnRight() {
            return values()[add(+1)];
        }

        public Heading turnLeft() {
            return values()[add(-1)];
        }

        private int add(int offset) {
            return (this.ordinal() + offset + values().length) % values().length;
        }

    }


}

