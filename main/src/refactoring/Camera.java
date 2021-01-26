package refactoring;

import refactoring.CameraView;

public class Camera {
    CameraView frontView;
    CameraView rearView;
    int angle;

    public Camera(int angle) {
        this.angle = angle;
    }

    public Camera turnLeft(Integer degrees) {
        return new Camera(angle - degrees);
    }

    public Camera turnRight(Integer degrees) {
        return new Camera(angle + degrees);
    }
}