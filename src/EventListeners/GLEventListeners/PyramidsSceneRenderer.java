package EventListeners.GLEventListeners;

import EventListeners.GLEventListeners.Enums.Effect;
import Graphics.BasicShapes.Triangle;
import Graphics.Color;
import Graphics.ComplexShapes.Cloud;
import Graphics.ComplexShapes.Tree;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class PyramidsSceneRenderer implements GLEventListener {
    GL2 gl;
    int staticList;
    Effect effect = Effect.DEFAULT;
    float rotationAmount;
    float scaleFactor = 1;
    float rotationAngle = 0;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 0, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(-400, 400, -300, 300, -1, 1);

        staticList = gl.glGenLists(1);

        Triangle triangle = new Triangle(new Coordinate(0, 0), 250, 200);
        var triangle1 = triangle.copy();
        triangle1.move(200, 0);
        triangle1.scale(0.9);
        var triangle2 = triangle.copy();
        triangle2.move(-200, 0);
        triangle2.scale(1.1);

        Tree tree = new Tree.Builder().setCenter(0, 0).setHeight(100).setThickness(10).setLeafColor(new Color("#2b821f")).build();
        Tree tree1 = tree.copy();
        tree.move(330, -30);
        tree.scale(2);
        tree1.move(-320, -230);
        tree1.scale(3);

        Cloud cloud = new Cloud(new Coordinate(250, 220), 70);

        gl.glNewList(staticList, GL2.GL_COMPILE);
        triangle.draw(gl, true, Color.YELLOW);
        triangle1.draw(gl, true, Color.YELLOW);
        triangle2.draw(gl, true, Color.YELLOW);
        tree.draw(gl);
        tree1.draw(gl);
        cloud.draw(gl);
        gl.glEndList();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();

        gl.glCallList(staticList);

        gl.glPopMatrix();
    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int w, int h) {
        gl.glViewport(0, 0, w, h);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        float aspect = (float) w / (float) h;
        float viewHeight = 600;
        float viewWidth = viewHeight * aspect;

        gl.glOrtho(-viewWidth / 2, viewWidth / 2, -viewHeight / 2, viewHeight / 2, -1, 1);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public void setRotationAmount(float rotationAmount) {
        this.rotationAmount = rotationAmount;
    }
}
