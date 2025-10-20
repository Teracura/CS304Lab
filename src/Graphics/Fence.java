package Graphics;

import com.jogamp.opengl.GL2;

public class Fence {
    int numberOfRows;
    int numberOfColumns;
    double width;
    double height;
    Coordinate start;
    Color color;

    public Fence(Coordinate start, double width, double height, int numberOfRows, int numberOfColumns) {
        this(start, width, height, numberOfRows, numberOfColumns, new Color(0.36, 0.25, 0.20));
    }

    public Fence(Coordinate start, double width, double height, int numberOfRows, int numberOfColumns, Color color) {
        this.start = start;
        this.width = width;
        this.height = height;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.color = color;
    }

    public void draw(GL2 gl) {
        double columnSpacing = width / (numberOfColumns - 1);
        double rowSpacing = height / (numberOfRows + 1);

        double postWidth = Math.min(10, columnSpacing * 0.2);
        double plankHeight = Math.min(10, rowSpacing * 0.15);

        color.useColorGl(gl);

        for (int c = 0; c < numberOfColumns; c++) {
            double x = start.x() + c * columnSpacing;
            Rectangle post = new Rectangle(new Coordinate(x, start.y() + height / 2), postWidth, height);
            post.draw(gl, true, color);
        }

        for (int r = 0; r < numberOfRows + 1; r++) {
            if (r == 0) continue;
            double y = start.y() + r * rowSpacing;
            Rectangle plank = new Rectangle(new Coordinate(start.x() + width / 2, y), width, plankHeight);
            plank.draw(gl, true, color);
        }
    }
}
