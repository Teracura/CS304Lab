package EventListeners.GLEventListeners;

import Graphics.*;
import Graphics.BasicShapes.Circle;
import Graphics.BasicShapes.Rectangle;
import Graphics.ComplexShapes.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;

public class TownDrawingRenderer implements GLEventListener {
    GL2 gl;
    Coordinate trueCenter = new Coordinate(800, 500);

    int staticList;

    @Override
    public void init(GLAutoDrawable glAutoDrawable) {
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(0, 0, 1, 1);

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0, 500, 0, 300, -1, 1);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();

        Rectangle sky = new Rectangle(0, 0, 1000, 600);

        Color color = Color.GREEN;

        Rectangle rectangle = new Rectangle(trueCenter.x(), 0, 1600, 200);
        Circle road = new Circle(new Coordinate(100, -1935), 2000);
        House house = new House(new Coordinate(80, 130), 75, 50, 40,
                new Color("#b86507"), Color.DARK_BROWN);
        House house1 = new House(new Coordinate(200, 134), 60, 44, 40,
                new Color("#a86c42"), Color.DARK_BROWN);
        House house2 = new House(new Coordinate(350, 134), 60, 44, 40,
                new Color("#dbaf7d"), Color.DARK_BROWN);

        Tree tree = new Tree(new Coordinate(250,90), 90, 10, new Color("#2b821f"), Color.DARK_BROWN);
        Tree tree1 = new Tree(new Coordinate(400,95), 50, 9, new Color("#2b821f"), Color.DARK_BROWN, true, false);
        Tree tree2 = new Tree(new Coordinate(450,95), 50, 7, new Color("#2b821f"), Color.DARK_BROWN);

        Cloud cloud = new Cloud(new Coordinate(76, 270), 25);
        Cloud cloud1 = new Cloud(new Coordinate(300, 250), 15);

        Color bushColor = new Color(52 / 255.0, 191 / 255.0, 90 / 255.0);
        Circle bush = new Circle(new Coordinate(140, 95), 11);
        Circle bush1 = new Circle(new Coordinate(250, 90), 8);
        Circle bush2 = new Circle(new Coordinate(259, 93), 11);
        Circle bush3 = new Circle(new Coordinate(375, 95), 8);

        Sun sun = new Sun(new Coordinate(460, 260), 30);
        Fence fence = new Fence(new Coordinate(20,80), 120, 20, 2,8);

        Human human = new Human(new Coordinate(230, 95), 25,10,
                Color.BLUE, new Color("#F5DEB3"));
        Human human1 = new Human(new Coordinate(300,95), 20,10,
                Color.RED, new Color("#F5DEB3"));


        staticList = gl.glGenLists(2);
        gl.glNewList(staticList, GL2.GL_COMPILE);
        sky.draw(gl, true, Color.SKY);
        rectangle.draw(gl, true, color);

        Color.LIGHT_GRAY.useColorGl(gl);
        gl.glLineWidth(150.0f);
        road.draw(gl);
        gl.glLineWidth(1.0f);
        house.draw(gl);
        house1.draw(gl);
        house2.draw(gl);

        tree.draw(gl);
        tree1.draw(gl);
        tree2.draw(gl);

        cloud.draw(gl);
        cloud1.draw(gl);
        bushColor.useColorGl(gl);
        bush.draw(gl, true);
        bush1.draw(gl, true);
        bush2.draw(gl,true);
        bush3.draw(gl,true);

        human.draw(gl);
        human1.draw(gl);

        sun.draw(gl);

        fence.draw(gl);
        gl.glEndList();
    }

    @Override
    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glCallList(staticList);

    }

    @Override
    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

    }
}
