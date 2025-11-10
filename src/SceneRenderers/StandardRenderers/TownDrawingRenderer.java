package SceneRenderers.StandardRenderers;

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

        Rectangle sky = new Rectangle.Builder().setCenter(0, 0).setWidth(1000)
                .setHeight(600).setFill(true).setColor(Color.SKY).build();

        Rectangle ground = new Rectangle.Builder().setCenter(trueCenter.x(), 0)
                .setWidth(1600)
                .setColor(new Color("#8eed91"))
                .setFill(true)
                .setHeight(200).build();
        Circle road = new Circle.Builder().setCenter(100, -1935).setRadius(2000).setColor(new Color("#b7ca8e")).build();

        House house = new House(new Coordinate(80, 130), 65, 50, 40,
                new Color("#b86507"), Color.DARK_BROWN);
        House house1 = new House(new Coordinate(200, 134), 60, 44, 40,
                new Color("#a86c42"), Color.DARK_BROWN);
        House house2 = new House(new Coordinate(350, 134), 60, 44, 40,
                new Color("#dbaf7d"), Color.DARK_BROWN);

        Tree tree = new Tree.Builder()
                .setCenter(250, 90)
                .setHeight(90)
                .setThickness(10)
                .setLeafColor(new Color("#2b821f"))
                .build();

        Tree tree1 = new Tree.Builder()
                .setCenter(400, 95)
                .setHeight(50)
                .setThickness(9)
                .setLeafColor(new Color("#2b821f"))
                .setUseTrianglesForLeaves(true)
                .build();

        Tree tree2 = new Tree.Builder()
                .setCenter(450, 95)
                .setHeight(50)
                .setThickness(7)
                .setLeafColor(new Color("#2b821f"))
                .build();

        Cloud cloud = new Cloud(new Coordinate(76, 270), 25);
        Cloud cloud1 = new Cloud(new Coordinate(300, 250), 15);

        Color bushColor = new Color(52 / 255.0, 191 / 255.0, 90 / 255.0);

        Circle bush = new Circle.Builder()
                .setCenter(140, 95)
                .setRadius(11)
                .setColor(bushColor)
                .setFill(true)
                .build();

        Circle bush1 = new Circle.Builder()
                .setCenter(250, 90)
                .setRadius(8)
                .setColor(bushColor)
                .setFill(true)
                .build();

        Circle bush2 = new Circle.Builder()
                .setCenter(259, 93)
                .setRadius(11)
                .setColor(bushColor)
                .setFill(true)
                .build();

        Circle bush3 = new Circle.Builder()
                .setCenter(375, 95)
                .setRadius(8)
                .setColor(bushColor)
                .setFill(true)
                .build();

        Sun sun = new Sun.Builder()
                .setCenter(460, 260)
                .setRadius(30)
                .setColor(Color.YELLOW)
                .setRayCount(12)
                .build();

        Fence fence = new Fence(new Coordinate(20, 80), 120, 20, 2, 8);

        Human human = new Human.Builder()
                .setCenter(230, 95)
                .setHeight(25)
                .setWidth(10)
                .setSkinColor(new Color("#F5DEB3"))
                .build();

        Human human1 = new Human.Builder()
                .setCenter(300, 95)
                .setHeight(20)
                .setWidth(10)
                .setBodyColor(Color.RED)
                .setSkinColor(new Color("#F5DEB3"))
                .build();


        staticList = gl.glGenLists(2);
        gl.glNewList(staticList, GL2.GL_COMPILE);
        sky.draw(gl);
        ground.draw(gl);

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
        bush.draw(gl);
        bush1.draw(gl);
        bush2.draw(gl);
        bush3.draw(gl);

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
