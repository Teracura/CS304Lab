package Graphics;

import com.jogamp.opengl.GL2;

public class Human {
    Coordinate center;
    double height;
    double width;
    Color bodyColor;
    Color skinColor;

    public Human(Coordinate center, double height, double width) {
        this(center, height, width, Color.BLUE, new Color(1, 0.8, 0.6)); // default: blue body, skin tone head
    }

    public Human(Coordinate center, double height, double width, Color bodyColor, Color skinColor) {
        this.center = center;
        this.height = height;
        this.width = width;
        this.bodyColor = bodyColor;
        this.skinColor = skinColor; //zingy factor
    }

    public void draw(GL2 gl) {
        double headRadius = height * 0.2;
        double bodyHeight = height * 0.45;
        double limbLength = height * 0.35;
        double limbThickness = width * 0.15;

        Rectangle body = new Rectangle(center, width * 0.3, bodyHeight);
        body.draw(gl, true, bodyColor);

        Coordinate headCenter = new Coordinate(center.x(), center.y() + bodyHeight / 2 + headRadius * 1.2);
        Circle head = new Circle(headCenter, headRadius);
        skinColor.useColorGl(gl);
        head.draw(gl, true);

        double armY = center.y() + bodyHeight / 4;
        double armOffsetX = width / 2;
        Rectangle rightArm = new Rectangle(new Coordinate(center.x() + armOffsetX, armY), limbLength, limbThickness);
        Rectangle leftArm = new Rectangle(new Coordinate(center.x() - armOffsetX, armY), limbLength, limbThickness);
        rightArm.rotation = -30;
        leftArm.rotation = 30;
        rightArm.draw(gl, true, bodyColor);
        leftArm.draw(gl, true, bodyColor);

        double legStartY = center.y() - bodyHeight / 2;
        double legOffsetX = width * 0.1;
        Rectangle rightLeg = new Rectangle(new Coordinate(center.x() + legOffsetX, legStartY - limbLength / 2), limbThickness, limbLength);
        Rectangle leftLeg = new Rectangle(new Coordinate(center.x() - legOffsetX, legStartY - limbLength / 2), limbThickness, limbLength);
        rightLeg.rotation = 15;
        leftLeg.rotation = -15;
        rightLeg.draw(gl, true, bodyColor);
        leftLeg.draw(gl, true, bodyColor);
    }
}
