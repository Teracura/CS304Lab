package Graphics;

import com.jogamp.opengl.GL2;

public class House {
    private Coordinate center;
    private double width;
    private double wallHeight;
    private double roofHeight;
    private double doorWidth;
    private Color roofColor;
    private Color wallColor;
    private Color doorColor;
    private Color windowColor;

    public House(Coordinate center, double width, double wallHeight, double roofHeight,
                 Color wallColor, Color roofColor) {
        this.center = center;
        this.width = width;
        this.wallHeight = wallHeight;
        this.roofHeight = roofHeight;
        this.roofColor = roofColor;
        this.wallColor = wallColor;
        this.doorWidth = width * 0.3;
        this.doorColor = Color.DARK_BROWN;
        this.windowColor = Color.SKY;
    }

    public void draw(GL2 gl) {
        draw(gl, true);
    }

    public void draw(GL2 gl, boolean fill) {
        double x = center.x();
        double y = center.y();

        Coordinate wallCenter = new Coordinate(x, y - roofHeight / 2);
        Rectangle wall = new Rectangle(wallCenter, width, wallHeight);
        wall.draw(gl, fill, wallColor);

        Coordinate roofCenter = new Coordinate(x, y + wallHeight / 2);
        Triangle roof = new Triangle(roofCenter, width, roofHeight);
        roof.draw(gl, fill, roofColor);

        double doorHeight = wallHeight * 0.7;
        Coordinate doorCenter = new Coordinate(x - width / 5, wallCenter.y() - wallHeight / 2 + doorHeight / 2);
        Rectangle door = new Rectangle(doorCenter, doorWidth, doorHeight);
        Circle doorHandle = new Circle(doorCenter.x() + doorWidth / 5, doorCenter.y(), doorWidth / 10);
        door.draw(gl, fill, doorColor);
        Color.YELLOW.useColorGl(gl);
        doorHandle.draw(gl, fill);

        double windowSize = wallHeight * 0.35;
        double windowOffsetX = width / 4;
        double windowCenterY = wallCenter.y() + wallHeight * 0.15;
        Coordinate windowCenter = new Coordinate(x + windowOffsetX, windowCenterY);
        Rectangle window = new Rectangle(windowCenter, windowSize, windowSize);
        window.draw(gl, fill, windowColor);

        Color.BLACK.useColorGl(gl);
        gl.glLineWidth(2);
        gl.glBegin(GL2.GL_LINES);
        gl.glVertex2d(windowCenter.x() - windowSize / 2, windowCenter.y());
        gl.glVertex2d(windowCenter.x() + windowSize / 2, windowCenter.y());
        gl.glVertex2d(windowCenter.x(), windowCenter.y() - windowSize / 2);
        gl.glVertex2d(windowCenter.x(), windowCenter.y() + windowSize / 2);
        gl.glEnd();
    }
}
