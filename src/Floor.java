import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.GL4;
import common.camera.Camera;
import common.glsl.simpleShaderProgram.SimpleShaderProgram;
import common.graphicsObject.GraphicsObject;
import org.joml.Matrix4f;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Floor extends GraphicsObject {
    private final SimpleShaderProgram shaderProgram;
    private final float width;
    private final float depth;
    private int vertexArrayObjectId;
    private int vertexBufferObjectId;
    private int textureCoordinateBufferObjectId;

    public Floor(SimpleShaderProgram shaderProgram, Matrix4f transform, float width, float depth) {
        super(shaderProgram, transform);

        this.shaderProgram = shaderProgram;

        this.width = width;
        this.depth = depth;
    }

    @Override
    protected void initializeInternal(GL4 gl) {
        IntBuffer intBuffer = IntBuffer.allocate(1);
        gl.glGenVertexArrays(1, intBuffer);
        this.vertexArrayObjectId = intBuffer.get(0);

        gl.glBindVertexArray(this.vertexArrayObjectId);

        float[] vertices = {
                -0.5f * this.width, 0, 0.5f * this.depth,
                -0.5f * this.width, 0, -0.5f * this.depth,
                0.5f * this.width, 0, -0.5f * this.depth,
                0.5f * this.width, 0, 0.5f * this.depth,
        };

        float[] textureCoordinates = {
                0 * this.width, 1 * this.depth,
                0 * this.width, 0 * this.depth,
                1 * this.width, 0 * this.depth,
                1 * this.width, 1 * this.depth
        };

        FloatBuffer verticesBuffer = Buffers.newDirectFloatBuffer(vertices, 0);
        FloatBuffer textureCoordinatesBuffer = Buffers.newDirectFloatBuffer(textureCoordinates, 0);

        intBuffer.rewind();
        gl.glGenBuffers(1, intBuffer);
        this.vertexBufferObjectId = intBuffer.get(0);
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, this.vertexBufferObjectId);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, vertices.length * Float.BYTES, verticesBuffer, GL4.GL_STATIC_DRAW);
        gl.glEnableVertexAttribArray(0);
        gl.glVertexAttribPointer(0, 3, GL4.GL_FLOAT, false, 0, 0);

        intBuffer.rewind();
        gl.glGenBuffers(1, intBuffer);
        this.textureCoordinateBufferObjectId = intBuffer.get(0);
        gl.glBindBuffer(GL4.GL_ARRAY_BUFFER, this.textureCoordinateBufferObjectId);
        gl.glBufferData(GL4.GL_ARRAY_BUFFER, textureCoordinates.length * Float.BYTES, textureCoordinatesBuffer, GL4.GL_STATIC_DRAW);
        gl.glEnableVertexAttribArray(1);
        gl.glVertexAttribPointer(1, 2, GL4.GL_FLOAT, false, 0, 0);
    }

    @Override
    protected void renderInternal(GL4 gl, Matrix4f parentTransform, Camera camera) {
        gl.glBindVertexArray(this.vertexArrayObjectId);

        Matrix4f transform = new Matrix4f().mul(camera.getViewProjection()).mul(parentTransform);

        this.shaderProgram.setTransformUniform(gl, transform);

        gl.glDrawArrays(GL4.GL_QUADS, 0, 24);

        gl.glBindVertexArray(0);
    }

    @Override
    protected void destroyInternal(GL4 gl) {
        IntBuffer buffer = Buffers.newDirectIntBuffer(2);
        buffer.put(this.vertexBufferObjectId);
        buffer.put(this.textureCoordinateBufferObjectId);
        buffer.rewind();
        gl.glDeleteBuffers(2, buffer);

        buffer.rewind();
        buffer.put(this.vertexArrayObjectId);
        buffer.rewind();
        gl.glDeleteVertexArrays(1, buffer);
    }
}
