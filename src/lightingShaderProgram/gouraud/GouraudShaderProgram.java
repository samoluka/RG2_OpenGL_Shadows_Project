package lightingShaderProgram.gouraud;

import common.Utilities;
import common.glsl.shaders.FragmentShader;
import common.glsl.shaders.VertexShader;
import lightingShaderProgram.BasicLightingShaderProgram;

public class GouraudShaderProgram extends BasicLightingShaderProgram {

    public GouraudShaderProgram(String name) {
        super(
                name,
                new VertexShader(
                        name + ".vertexShader",
                        Utilities.readFile(GouraudShaderProgram.class, "vertexShader.glsl")
                ),
                new FragmentShader(
                        name + ".fragmentShader",
                        Utilities.readFile(GouraudShaderProgram.class, "fragmentShader.glsl")
                )
        );
    }

    public GouraudShaderProgram() {
        this("GouraudShaderProgram");
    }
}
