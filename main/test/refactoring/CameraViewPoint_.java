package refactoring;


public class CameraViewPoint_ {

    private ViewPoint initialViewPoint;
    private Camera camera;

    @Before
    public void setUp() {
        camera = mock(Camera.class);
        ImageProcessor imageProcessor = mock(ImageProcessor.class);
        initialViewPoint = new CameraViewPoint(camera, imageProcessor);
    }

    @Test
    public void when_turning_left_should_return_a_new_view_point() {
        assertThat(initialViewPoint.turnLeft()).isNotNull().isNotEqualTo(initialViewPoint);
        verify(camera).turnLeft(90);
    }

    @Test
    public void should_be_able_to_turn_right() {
        Camera camera = mock(Camera.class);
        when(camera.turnRight(90)).thenReturn(camera);
        ImageProcessor imageProcessor = mock(ImageProcessor.class);
        CameraViewPoint cameraViewPoint = new CameraViewPoint(camera, imageProcessor);
        cameraViewPoint.turnRight();
        verify(camera).turnRight(90);
    }
}
}
