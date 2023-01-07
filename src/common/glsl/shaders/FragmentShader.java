package common.glsl.shaders;

import com.jogamp.opengl.GL4;
import common.glsl.Shader;

public class FragmentShader extends Shader {
	public FragmentShader ( String shaderName, String[] shaderSource ) {
		super ( shaderName, GL4.GL_FRAGMENT_SHADER, shaderSource );
	}
	
	public FragmentShader ( String shaderName, String shaderSource ) {
		super ( shaderName, GL4.GL_FRAGMENT_SHADER, shaderSource );
	}
}