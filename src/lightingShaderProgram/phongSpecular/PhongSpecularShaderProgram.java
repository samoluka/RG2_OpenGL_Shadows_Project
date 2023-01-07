package lightingShaderProgram.phongSpecular;

import com.jogamp.opengl.GL4;
import common.Utilities;
import common.glsl.shaders.FragmentShader;
import common.glsl.shaders.VertexShader;
import lightingShaderProgram.BasicLightingShaderProgram;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class PhongSpecularShaderProgram extends BasicLightingShaderProgram {

    private final Vector3f specularColor;
    private final float specularPower;
    private final Vector2f coefficients;

    public PhongSpecularShaderProgram(String name, Vector3f specularColor, float specularPower, Vector2f coefficients) {
        super(
                name,
                new VertexShader(
                        name + ".vertexShader",
                        Utilities.readFile(PhongSpecularShaderProgram.class, "vertexShader.glsl")
                ),
                new FragmentShader(
                        name + ".fragmentShader",
                        Utilities.readFile(PhongSpecularShaderProgram.class, "fragmentShader.glsl")
                )
        );

        this.specularColor = specularColor;
        this.specularPower = specularPower;
        this.coefficients = coefficients;
    }

    public PhongSpecularShaderProgram() {
        this(
                "PhongShaderProgram",
                new Vector3f(1, 0, 0),
                50,
                new Vector2f(1, 1)
        );
    }

    @Override
    public void setLightPosition(GL4 gl, Vector3f lightPosition) {
        super.setLightPosition(gl, lightPosition);

        int specularColorLocation = super.getUniformLocation("specularColor");
        int specularPowerLocation = super.getUniformLocation("specularPower");
        int coefficientsLocation = super.getUniformLocation("coefficients");

        gl.glUniform3f(specularColorLocation, this.specularColor.x, this.specularColor.y, this.specularColor.z);
        gl.glUniform1f(specularPowerLocation, this.specularPower);
        gl.glUniform2f(coefficientsLocation, this.coefficients.x, this.coefficients.y);
    }
}
