package refactoring;

public class CameraViewPoint {

import lombok.Getter;
import lombok.Setter;

    @Getter
    @Setter
    public class CameraViewPoint implements ViewPoint {
        private Camera camera;
        private ImageProcessor imageProcessor;

        public CameraViewPoint(Camera camera, ImageProcessor imageProcessor) {
            this.camera = camera;
            this.imageProcessor = imageProcessor;
        }

        @Override
        public ViewPoint forward() {
            return null;
        }

        @Override
        public ViewPoint backward() {
            return null;
        }

        @Override
        public ViewPoint turnLeft() {
            camera.turnLeft(90);
            return new CameraViewPoint(camera, imageProcessor);
        }

        @Override
        public ViewPoint turnRight() {
            return null;
        }

    }
}
