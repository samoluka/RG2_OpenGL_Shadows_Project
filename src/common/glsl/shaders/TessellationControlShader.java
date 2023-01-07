package common.glsl.shaders;

import com.jogamp.opengl.GL4;
import common.glsl.Shader;

public class TessellationControlShader extends Shader {
	public TessellationControlShader ( String shaderName, String[] shaderSource ) {
		super ( shaderName, GL4.GL_TESS_CONTROL_SHADER, shaderSource );
	}
	
	public TessellationControlShader ( String shaderName, String shaderSource ) {
		super ( shaderName, GL4.GL_TESS_CONTROL_SHADER, shaderSource );
	}
}
