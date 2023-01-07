package common.glView;

import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import common.scene.Scene;
import org.joml.Vector4f;

public abstract class GLView implements GLEventListener {
	private Scene    scene;
	private Vector4f clearColor;
	
	public GLView ( Scene scene, Vector4f clearColor ) {
		this.scene = scene;
		
		this.clearColor = clearColor;
	}
	
	public GLView ( Scene scene ) {
		this ( scene, new Vector4f ( 0, 0, 0, 0 ) );
	}
	
	@Override
	public void init ( GLAutoDrawable drawable ) {
		this.scene.initialize ( drawable );
	}
	
	protected void render ( GLAutoDrawable drawable ) {
		GL4 gl = drawable.getGL ( ).getGL4bc ( );
		
		gl.glClearColor ( this.clearColor.x, this.clearColor.y, this.clearColor.z, this.clearColor.w );
		
		gl.glClear ( GL4.GL_COLOR_BUFFER_BIT | GL4.GL_STENCIL_BUFFER_BIT | GL4.GL_DEPTH_BUFFER_BIT );
		
		this.scene.display ( drawable );
	}
	
	private void update ( ) {
		this.scene.update ( );
	}
	
	@Override
	public void dispose ( GLAutoDrawable drawable ) {
		this.scene.destroy ( drawable.getGL ( ).getGL4bc ( ) );
	}
	
	@Override
	public void display ( GLAutoDrawable drawable ) {
		this.render ( drawable );
		this.update ( );
	}
	
	@Override
	public void reshape ( GLAutoDrawable drawable, int x, int y, int width, int height ) { }
}
