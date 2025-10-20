package Graphics.ComplexShapes;

import Graphics.BasicShapes.Circle;
import Graphics.BasicShapes.Rectangle;
import Graphics.BasicShapes.Triangle;
import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;

public class Tree {
    Coordinate baseCenter;
    double height;
    double thickness;
    boolean useTrianglesForLeaves;
    boolean useTriangleTrunk;
    Color leafColor;
    Color trunkColor;

    public Tree(Coordinate baseCenter, double height, double thickness,
                Color leafColor, Color trunkColor,
                boolean useTrianglesForLeaves, boolean useTriangleTrunk) {
        this.baseCenter = baseCenter;
        this.height = height;
        this.thickness = thickness;
        this.useTrianglesForLeaves = useTrianglesForLeaves;
        this.useTriangleTrunk = useTriangleTrunk;
        this.leafColor = leafColor;
        this.trunkColor = trunkColor;
    }


    public Tree(Coordinate baseCenter, double height, double thickness,
                Color leafColor, Color trunkColor) {
        this.baseCenter = baseCenter;
        this.height = height;
        this.thickness = thickness;
        this.useTrianglesForLeaves = false;
        this.useTriangleTrunk = false;
        this.leafColor = leafColor;
        this.trunkColor = trunkColor;
    }

    public void draw(GL2 gl) {
        double trunkHeight = height * 0.4;
        double leafHeight = height * 0.6;
        if (useTrianglesForLeaves)  leafHeight = height * 0.9;

        if (useTriangleTrunk) {
            Triangle trunk = new Triangle(
                    new Coordinate(baseCenter.x(), baseCenter.y() + trunkHeight / 2),
                    thickness, trunkHeight * 1.2
            );
            trunk.draw(gl, true, trunkColor);
        } else {
            Rectangle trunk = new Rectangle(
                    new Coordinate(baseCenter.x(), baseCenter.y() + trunkHeight / 2),
                    thickness, trunkHeight
            );
            trunk.draw(gl, true, trunkColor);
        }

        double leafBaseY = baseCenter.y() + trunkHeight;

        if (!useTrianglesForLeaves) {
            double radius = leafHeight / 3;
            Coordinate c1 = new Coordinate(baseCenter.x(), leafBaseY + radius * 1.5);
            Coordinate c2 = new Coordinate(baseCenter.x() - radius * 0.7, leafBaseY);
            Coordinate c3 = new Coordinate(baseCenter.x() + radius * 0.7, leafBaseY);

            leafColor.useColorGl(gl);
            new Circle(c1, radius).draw(gl, true);
            new Circle(c2, radius).draw(gl, true);
            new Circle(c3, radius).draw(gl, true);
        } else {
            double triBase = thickness * 3;
            double triHeight = leafHeight / 2;

            Triangle lower = new Triangle(
                    new Coordinate(baseCenter.x(), leafBaseY + triHeight / 2),
                    triBase * 0.8, triHeight, 0
            );
            Triangle upper = new Triangle(
                    new Coordinate(baseCenter.x(), leafBaseY + triHeight / 1.3),
                    triBase, triHeight, 0
            );

            lower.draw(gl, true, leafColor);
            upper.draw(gl, true, leafColor);
        }
    }
}
