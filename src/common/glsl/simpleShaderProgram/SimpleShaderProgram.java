package common.glsl.simpleShaderProgram;

import com.jogamp.opengl.GL4;
import common.Utilities;
import common.glsl.Shader;
import common.glsl.ShaderProgram;
import common.glsl.shaders.FragmentShader;
import common.glsl.shaders.VertexShader;
import org.joml.Matrix4f;

public class SimpleShaderProgram extends ShaderProgram {
	public SimpleShaderProgram ( String name, FragmentShader fragmentShader ) {
		super (
				name,
				new VertexShader (
						name + ".vertexShader",
						Utilities.readFile ( SimpleShaderProgram.class, "vertexShader.glsl" )
				),
				fragmentShader
		);
	}
	
	public SimpleShaderProgram ( String name ) {
		super (
			name,
			new VertexShader (
				name + ".vertexShader",
				Utilities.readFile ( SimpleShaderProgram.class, "vertexShader.glsl" )
			),
			new FragmentShader (
				name + ".fragmentShader",
				Utilities.readFile ( SimpleShaderProgram.class, "fragmentShader.glsl" )
			)
		);
	}
	
	public SimpleShaderProgram ( String name, Shader... shaders ) {
		super ( name, shaders );
	}
	
	public SimpleShaderProgram ( ) {
		this ( "SimpleShaderProgram" );
	}
	
	public void setTransformUniform ( GL4 gl, Matrix4f transform ) {
		int location = super.getUniformLocation ( "transform" );
		
		float transformArray[] = new float[16];
		transform.get ( transformArray );
		gl.glUniformMatrix4fv ( location, 1, false, transformArray, 0 );
	}
}
