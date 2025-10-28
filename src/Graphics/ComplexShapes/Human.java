package Graphics.ComplexShapes;

import Graphics.BasicShapes.Circle;
import Graphics.BasicShapes.Rectangle;
import Graphics.Color;
import Graphics.Coordinate;
import com.jogamp.opengl.GL2;

public class Human {
    private final Coordinate center;
    private final double height;
    private final double width;
    private final Color bodyColor;
    private final Color skinColor;

    private Human(Coordinate center, double height, double width, Color bodyColor, Color skinColor) {
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

        drawBody(gl, bodyHeight);
        drawHead(gl, headRadius, bodyHeight, limbThickness);
        drawArms(gl, bodyHeight, limbLength, limbThickness);
        drawLegs(gl, bodyHeight, limbLength, limbThickness);
    }

    private void drawBody(GL2 gl, double bodyHeight) {
        Rectangle body = new Rectangle.Builder().setCenter(center).setWidth(width * 0.3).setHeight(bodyHeight)
                        .setFill(true).setColor(bodyColor).build();
        body.draw(gl);
    }

    private void drawHead(GL2 gl, double headRadius, double bodyHeight, double limbThickness) {
        Coordinate headCenter = new Coordinate(center.x() + limbThickness * 0.5,
                center.y() + bodyHeight / 2 + headRadius * 1.1);
        Circle head = new Circle.Builder().setCenter(headCenter).setRadius(headRadius).setColor(skinColor)
                .setFill(true)
                .build();
        head.draw(gl);
    }

    private void drawArms(GL2 gl, double bodyHeight, double limbLength, double limbThickness) {
        double armY = center.y() + bodyHeight / 4;
        double armOffsetX = width / 2;

        Rectangle rightArm = new Rectangle.Builder().setCenter(center.x() + armOffsetX, armY)
                .setWidth(limbLength).setHeight(limbThickness).setFill(true).setColor(bodyColor).build();
        Rectangle leftArm = rightArm.copy();

        leftArm.setCenter(center.x() - armOffsetX, armY);
        rightArm.setRotation(-30);
        leftArm.setRotation(30);

        rightArm.draw(gl);
        leftArm.draw(gl);
    }

    private void drawLegs(GL2 gl, double bodyHeight, double limbLength, double limbThickness) {
        double legStartY = center.y() - bodyHeight / 2;
        double legOffsetX = width * 0.1;

        Rectangle rightLeg = new Rectangle.Builder().setCenter(center.x() + legOffsetX, legStartY - limbLength / 2)
                .setWidth(limbThickness).setHeight(limbLength).setColor(bodyColor).setFill(true).build();
        Rectangle leftLeg = rightLeg.copy();
        leftLeg.setCenter(center.x() - legOffsetX, legStartY - limbLength / 2);

        rightLeg.setRotation(15);
        leftLeg.setRotation(-15);

        rightLeg.draw(gl);
        leftLeg.draw(gl);
    }

    public static class Builder {
        private Coordinate center;
        private double height;
        private double width;
        private Color bodyColor = Color.BLUE;
        private Color skinColor = new Color(1, 0.8, 0.6);

        public Builder setCenter(Coordinate center) {
            this.center = center;
            return this;
        }

        public Builder setCenter(double x, double y) {
            this.center = new Coordinate(x, y);
            return this;
        }

        public Builder setHeight(double height) {
            this.height = height;
            return this;
        }

        public Builder setWidth(double width) {
            this.width = width;
            return this;
        }

        public Builder setBodyColor(Color bodyColor) {
            this.bodyColor = bodyColor;
            return this;
        }

        public Builder setSkinColor(Color skinColor) {
            this.skinColor = skinColor;
            return this;
        }

        public Human build() {
            if (center == null) throw new IllegalArgumentException("Center cannot be null");
            if (height <= 0 || width <= 0) throw new IllegalArgumentException("Height and width must be positive");

            return new Human(center, height, width, bodyColor, skinColor);
        }
    }
}
