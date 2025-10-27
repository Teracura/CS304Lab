package Graphics.ComplexShapes;

import Graphics.BasicShapes.Circle;
import Graphics.BasicShapes.Rectangle;
import Graphics.BasicShapes.Triangle;
import Graphics.Color;
import Graphics.Coordinate;
import Graphics.Shape;
import com.jogamp.opengl.GL2;

public class Tree implements Shape {
    Coordinate baseCenter;
    double height;
    double thickness;
    boolean useTrianglesForLeaves;
    boolean useTriangleTrunk;
    Color leafColor;
    Color trunkColor;

    private Tree(Coordinate baseCenter, double height, double thickness,
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

    @Override
    public void move(double x, double y) {
        baseCenter = new Coordinate(baseCenter.x() + x, baseCenter.y() + y);
    }

    @Override
    public void draw(GL2 gl) {
        double trunkHeight = height * 0.4;
        double leafHeight = useTrianglesForLeaves ? height * 0.9 : height * 0.6;
        double leafBaseY = baseCenter.y() + trunkHeight;

        drawTrunk(gl, trunkHeight);
        drawLeaves(gl, leafBaseY, leafHeight);
    }

    @Override
    public void rotate(double angle) {

    }

    @Override
    public void scale(double scaleFactor) {
        height *= scaleFactor;
        thickness *= scaleFactor;
    }

    @Override
    public Tree copy() {
        return new Tree(baseCenter, height, thickness, leafColor, trunkColor, useTrianglesForLeaves, useTriangleTrunk);
    }


    private void drawTrunk(GL2 gl, double trunkHeight) {
        if (useTriangleTrunk) {
            new Triangle(
                    new Coordinate(baseCenter.x(), baseCenter.y() + trunkHeight / 2),
                    thickness, trunkHeight * 1.2
            ).draw(gl, true, trunkColor);
        } else {
            new Rectangle(
                    new Coordinate(baseCenter.x(), baseCenter.y() + trunkHeight / 2),
                    thickness, trunkHeight
            ).draw(gl, true, trunkColor);
        }
    }

    private void drawLeaves(GL2 gl, double leafBaseY, double leafHeight) {
        if (!useTrianglesForLeaves) {
            double radius = leafHeight / 3;
            new Circle.Builder()
                    .setCenter(baseCenter.x(), leafBaseY + radius * 1.5)
                    .setRadius(radius)
                    .setColor(leafColor)
                    .build()
                    .draw(gl, true);

            new Circle.Builder()
                    .setCenter(baseCenter.x() - radius * 0.7, leafBaseY)
                    .setRadius(radius)
                    .setColor(leafColor)
                    .build()
                    .draw(gl, true);

            new Circle.Builder()
                    .setCenter(baseCenter.x() + radius * 0.7, leafBaseY)
                    .setRadius(radius)
                    .setColor(leafColor)
                    .build()
                    .draw(gl, true);

        } else {
            double triBase = thickness * 3;
            double triHeight = leafHeight / 2;

            new Triangle(new Coordinate(baseCenter.x(), leafBaseY + triHeight / 2),
                    triBase * 0.8, triHeight, 0).draw(gl, true, leafColor);
            new Triangle(new Coordinate(baseCenter.x(), leafBaseY + triHeight / 1.3),
                    triBase, triHeight, 0).draw(gl, true, leafColor);
        }
    }

    public static class Builder {
        private Coordinate baseCenter;
        private double height;
        private double thickness;
        private boolean useTrianglesForLeaves;
        private boolean useTriangleTrunk;
        private Color leafColor;
        private Color trunkColor;

        public Builder() {
        }

        public Builder setBaseCenter(Coordinate baseCenter) {
            this.baseCenter = baseCenter;
            return this;
        }

        public Builder setHeight(double height) {
            this.height = height;
            return this;
        }

        public Builder setThickness(double thickness) {
            this.thickness = thickness;
            return this;
        }

        public Builder setCenter(Coordinate coordinate) {
            this.baseCenter = coordinate;
            return this;
        }

        public Builder setCenter(double x, double y) {
            this.baseCenter = new Coordinate(x, y);
            return this;
        }

        public Builder setUseTrianglesForLeaves(boolean useTrianglesForLeaves) {
            this.useTrianglesForLeaves = useTrianglesForLeaves;
            return this;
        }

        public Builder setUseTriangleTrunk(boolean useTriangleTrunk) {
            this.useTriangleTrunk = useTriangleTrunk;
            return this;
        }

        public Builder setLeafColor(Color leafColor) {
            this.leafColor = leafColor;
            return this;
        }

        public Builder setTrunkColor(Color trunkColor) {
            this.trunkColor = trunkColor;
            return this;
        }

        public Tree build() {
            if (baseCenter == null)
                throw new IllegalArgumentException("Base center cannot be null");
            if (height <= 0 || thickness <= 0) {
                throw new IllegalArgumentException("Height and thickness must be positive");
            }
            if (leafColor == null)
                leafColor = Color.GREEN;
            if (trunkColor == null)
                trunkColor = Color.DARK_BROWN;

            return new Tree(baseCenter, height, thickness, leafColor, trunkColor,
                    useTrianglesForLeaves, useTriangleTrunk);
        }
    }

}
