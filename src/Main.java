import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.FPSAnimator;
import common.camera.UpdatableCamera;
import common.glView.MouseKeyGLView;

public class Main {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int FPS = 60;

    private static final String TITLE = "Z8 - Texture view";

    private static final int[] MIN_FILTERS = {
            GL4.GL_NEAREST,
            GL4.GL_LINEAR,
            GL4.GL_NEAREST_MIPMAP_NEAREST,
            GL4.GL_NEAREST_MIPMAP_LINEAR,
            GL4.GL_LINEAR_MIPMAP_NEAREST,
            GL4.GL_LINEAR_MIPMAP_NEAREST,
    };

    private static final int[] MAG_FILTERS = {
            GL4.GL_NEAREST,
            GL4.GL_LINEAR,
            GL4.GL_LINEAR,
            GL4.GL_LINEAR,
            GL4.GL_LINEAR,
            GL4.GL_LINEAR,
    };

    private static final String[] FILTER_NAMES = {
            "min = NEAREST mag = NEAREST",
            "min = LINEAR mag = LINEAR",
            "min = NEAREST-NEAREST mag = LINEAR",
            "min = NEAREST-LINEAR mag = LINEAR",
            "min = LINEAR-NEAREST mag = LINEAR",
            "min = LINEAR-LINEAR mag = LINEAR"
    };

    private static void createWindow(GLCapabilities glCapabilities, String title, MouseKeyGLView view, int width, int height, int x, int y) {
        GLWindow window = GLWindow.create(glCapabilities);

        final FPSAnimator fpsAnimator = new FPSAnimator(window, Main.FPS, true);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowDestroyNotify(WindowEvent arg0) {
                new Thread() {
                    @Override
                    public void run() {
                        fpsAnimator.stop();
                        System.exit(0);
                    }
                }.start();
            }
        });

        window.addGLEventListener(view);
        window.addKeyListener(view);
        window.addMouseListener(view);
        window.setSize(width, height);
        window.setPosition(x, y);

        window.setTitle(title);
        window.setVisible(true);
        fpsAnimator.start();
    }

    public static void main(String[] args) {
        GLProfile glProfile = GLProfile.getDefault();

        System.out.println(glProfile.getGLImplBaseClassName());
        System.out.println(glProfile.getImplName());
        System.out.println(glProfile.getName());
        System.out.println(glProfile.hasGLSL());

        GLCapabilities glCapabilities = new GLCapabilities(glProfile);

        glCapabilities.setAlphaBits(8);
        glCapabilities.setDepthBits(24);
        System.out.println(glCapabilities);

        UpdatableCamera camera = new UpdatableCamera(-20, 30, 45, 1.0f, 0.1f, 1000.0f);
        Main.createWindow(
                glCapabilities,
                Main.TITLE,
                new MainView(camera, GL4.GL_NEAREST, GL4.GL_NEAREST),
                Main.WINDOW_WIDTH,
                Main.WINDOW_HEIGHT,
                30,
                100
        );
    }
}