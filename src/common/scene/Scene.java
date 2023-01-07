package common.scene;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import common.camera.Camera;
import common.graphicsObject.AbstractGraphicsObject;
import org.joml.Matrix4f;

import java.util.ArrayList;

public class Scene {
    protected Camera camera;
    protected AbstractGraphicsObject[] abstractGraphicsObjects;

    public Scene(Camera camera, ArrayList<AbstractGraphicsObject> abstractGraphicsObjects) {
        this.camera = camera;

        this.abstractGraphicsObjects = new AbstractGraphicsObject[abstractGraphicsObjects.size()];
        for (int i = 0; i < this.abstractGraphicsObjects.length; ++i) {
            this.abstractGraphicsObjects[i] = abstractGraphicsObjects.get(i);
        }
    }

    public void initialize(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4bc();
        for (AbstractGraphicsObject graphicsObject : this.abstractGraphicsObjects) {
            graphicsObject.initialize(gl);
        }
    }

    public void update() {
        this.camera.update();
        for (AbstractGraphicsObject graphicsObject : this.abstractGraphicsObjects) {
            graphicsObject.update();
        }
    }

    public void display(GLAutoDrawable drawable) {
        GL4 gl = drawable.getGL().getGL4bc();

        Matrix4f identity = new Matrix4f();
        for (AbstractGraphicsObject graphicsObject : this.abstractGraphicsObjects) {
            graphicsObject.render(gl, identity, this.camera);
        }
    }

    public void destroy(GL4 gl4) {
        for (AbstractGraphicsObject graphicsObject : this.abstractGraphicsObjects) {
            graphicsObject.destroy(gl4);
        }
    }
}
