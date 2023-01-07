import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import common.camera.UpdatableCamera;
import common.glView.MouseKeyGLView;
import common.glsl.simpleShaderProgram.SimpleShaderProgram;
import common.graphicsObject.AbstractGraphicsObject;
import common.graphicsObject.Cube;
import common.scene.Scene;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.util.ArrayList;

public class MainView extends MouseKeyGLView {
    private static final int textureUnit = 1;
    private final SimpleShaderProgram shaderProgram;
    private final UpdatableCamera camera;
    private final int minFilter;
    private final int magFilter;
    private int previousMouseX;
    private int previousMouseY;

    private MainView(SimpleShaderProgram shaderProgram, UpdatableCamera camera, int minFilter, int magFilter) {
        super(MainView.createScene(shaderProgram, camera), new Vector4f(1, 1, 1, 0));
        this.shaderProgram = shaderProgram;
        this.camera = camera;
        this.minFilter = minFilter;
        this.magFilter = magFilter;
    }

    public MainView(UpdatableCamera camera, int minFilter, int magFilter) {
        this(
                new SimpleShaderProgram(),
                camera,
                minFilter,
                magFilter
        );
    }

    private static float getRandom() {
        final int min = -5;
        final int max = 5;
        return (float) (min + Math.random() * (max - min));
    }

    private static Scene createScene(SimpleShaderProgram shaderProgram, UpdatableCamera camera) {

        Matrix4f floorTransform = new Matrix4f().translate(0, 0, 0);

        final int numberOfCubes = 10;
        ArrayList<AbstractGraphicsObject> graphicsObjects = new ArrayList<>(numberOfCubes + 1);

        for (int i = 0; i < numberOfCubes; i++) {
            graphicsObjects.add(i, new Cube(shaderProgram, new Matrix4f().translate(getRandom(), 1, getRandom()), 2));
        }

        Floor floor = new Floor(shaderProgram, floorTransform, 15, 15);
        graphicsObjects.add(numberOfCubes, floor);

        return new Scene(camera, graphicsObjects);
    }

    @Override
    public void render(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4bc();

        gl.glEnable(GL4.GL_DEPTH_TEST);

        super.render(drawable);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_UP: {
                this.camera.move(2);
                break;
            }
            case KeyEvent.VK_DOWN: {
                this.camera.move(-2);
                break;
            }
            case KeyEvent.VK_LEFT: {
                this.camera.yRotate(2);
                break;
            }
            case KeyEvent.VK_RIGHT: {
                this.camera.yRotate(-2);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        this.previousMouseX = event.getX();
        this.previousMouseY = event.getY();
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        int dx = event.getX() - this.previousMouseX;
        int dy = event.getY() - this.previousMouseY;

        this.previousMouseX = event.getX();
        this.previousMouseY = event.getY();

        this.camera.yRotate(dx);
        this.camera.xRotate(dy);
    }

    @Override
    public void mouseWheelMoved(MouseEvent event) {
        float dz = event.getRotation()[1];

        this.camera.move(dz);
    }

}
