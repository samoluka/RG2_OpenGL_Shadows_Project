package lightingShaderProgram;

import com.jogamp.opengl.GL4;
import common.glsl.Shader;
import common.light.LightingShaderProgram;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public abstract class BasicLightingShaderProgram extends LightingShaderProgram {
    public BasicLightingShaderProgram(String name, Shader... shaders) {
        super(name, shaders);
    }

    public void setLightPosition(GL4 gl, Vector3f lightPosition) {
        int location = super.getUniformLocation("lightPosition");

        gl.glUniform3f(location, lightPosition.x, lightPosition.y, lightPosition.z);
    }

    private void setMatrix4f(GL4 gl, String name, Matrix4f matrix4f) {
        int location = super.getUniformLocation(name);

        float[] transform = new float[16];

        matrix4f.get(transform);

        gl.glUniformMatrix4fv(location, 1, false, transform, 0);
    }

    public void setProjection(GL4 gl, Matrix4f projection) {
        this.setMatrix4f(gl, "projection", projection);
    }

    public void setView(GL4 gl, Matrix4f view) {
        this.setMatrix4f(gl, "view", view);
    }

    public void setModel(GL4 gl, Matrix4f model) {
        this.setMatrix4f(gl, "model", model);
    }

    public void setNormalTransform(GL4 gl, Matrix4f transform) {
        int location = super.getUniformLocation("transform");

        float[] transformArray = new float[16];
        transform.get(transformArray);
        gl.glUniformMatrix4fv(location, 1, false, transformArray, 0);
    }
}
