package lightingShaderProgram.phong;

import common.Utilities;
import common.glsl.shaders.FragmentShader;
import common.glsl.shaders.VertexShader;
import lightingShaderProgram.BasicLightingShaderProgram;

public class PhongShaderProgram extends BasicLightingShaderProgram {

    public PhongShaderProgram(String name) {
        super(
                name,
                new VertexShader(
                        name + ".vertexShader",
                        Utilities.readFile(PhongShaderProgram.class, "vertexShader.glsl")
                ),
                new FragmentShader(
                        name + ".fragmentShader",
                        Utilities.readFile(PhongShaderProgram.class, "fragmentShader.glsl")
                )
        );
    }

    public PhongShaderProgram() {
        this("PhongShaderProgram");
    }
}
