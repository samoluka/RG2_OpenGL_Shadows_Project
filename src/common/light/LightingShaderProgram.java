package common.light;

import com.jogamp.opengl.GL4;
import common.glsl.Shader;
import common.glsl.ShaderProgram;
import org.joml.Vector3f;

public abstract class LightingShaderProgram extends ShaderProgram {
	public LightingShaderProgram ( String name, Shader... shaders ) {
		super ( name, shaders );
	}
	
	public void setLightPosition ( GL4 gl, Vector3f lightPosition ) {
		int location = super.getUniformLocation ( "lightPosition" );
		
		gl.glUniform3f ( location, lightPosition.x, lightPosition.y, lightPosition.z );
	}
}
