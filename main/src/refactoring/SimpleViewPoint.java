package refactoring;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class SimpleViewPoint implements ViewPoint {
    private Heading heading;
    private Position position;
    private static final Map<Position, Obstacle> obstacles = new HashMap<>();

    public SimpleViewPoint(String facing, int x, int y) {
        this(Heading.of(facing), new Position(x, y));
    }

    public SimpleViewPoint(Heading heading, int x, int y) {
        this(heading, new Position(x, y));
    }

    public SimpleViewPoint(Heading heading, Position position) {
        this.heading = heading;
        this.position = position;
    }

    public void addObstacle(Obstacle obstacle) {
        obstacles.put(obstacle.getPosition(), obstacle);
    }

    @Override
    public ViewPoint forward() {
        Position nextPosition = position.forward(this.heading);
        if (nextPosition != null) {
            this.position = nextPosition;
            return this;
        }
        return null;
    }

    @Override
    public ViewPoint backward() {
        Position nextPosition = position.backward(this.heading);
        if (nextPosition != null) {
            this.position = nextPosition;
            return this;
        }
        return null;
    }

    @Override
    public ViewPoint turnLeft() {
        heading = heading.turnLeft();
        return this;
    }

    @Override
    public ViewPoint turnRight() {
        heading = heading.turnRight();
        return this;
    }

    public static class Position {
        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Position forward(Heading heading) {
            Position nextPosition = forwardPosition(heading);
            return thereIsObstacle(nextPosition) ? null : nextPosition;
        }

        public Position backward(Heading heading) {
            Position nextPosition = backwardPosition(heading);
            return thereIsObstacle(nextPosition) ? null : nextPosition;
        }

        private boolean thereIsObstacle(Position position) {
            return obstacles.containsKey(position);
        }

        private Position forwardPosition(Heading heading) {
            return new Position(this.x + dx(heading), this.y + dy(heading));
        }

        private Position backwardPosition(Heading heading) {
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
        public String toString() {
            return "Position{" + "x=" + x + ", y=" + y + '}';
        }

        @Override
        public boolean equals(Object object) {
            return isSameClass(object) && equals((Position) object);
        }

        private boolean isSameClass(Object object) {
            return object != null && object.getClass() == Position.class;
        }

        private boolean equals(Position position) {
            return position == this || (x == position.x && y == position.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public enum Heading {
        NORTH, EAST, SOUTH, WEST;

        public static Heading of(String label) {
            return of(label.charAt(0));
        }

        public static Heading of(char label) {
            if (label == 'N') return NORTH;
            if (label == 'S') return SOUTH;
            if (label == 'W') return WEST;
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
