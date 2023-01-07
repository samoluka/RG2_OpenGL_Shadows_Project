package common.glsl.shaders;

import com.jogamp.opengl.GL4;
import common.glsl.Shader;

public class VertexShader extends Shader {
	public VertexShader ( String shaderName, String[] shaderSource ) {
		super ( shaderName, GL4.GL_VERTEX_SHADER, shaderSource );
	}
	
	public VertexShader ( String shaderName, String shaderSource ) {
		super ( shaderName, GL4.GL_VERTEX_SHADER, shaderSource );
	}
}
