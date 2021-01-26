package refactoring;

public interface ViewPoint {
    ViewPoint forward();

    ViewPoint backward();

    ViewPoint turnLeft();

    ViewPoint turnRight();
}