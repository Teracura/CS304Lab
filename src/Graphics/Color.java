package Graphics;

import com.jogamp.opengl.GL2;

public record Color(double r, double g, double b, double a) {

    public static final Color WHITE = new Color(1, 1, 1, 1);
    public static final Color BLACK = new Color(0, 0, 0, 1);
    public static final Color RED = new Color(1, 0, 0, 1);
    public static final Color GREEN = new Color(0, 1, 0, 1);
    public static final Color BLUE = new Color(0, 0, 1, 1);
    public static final Color YELLOW = new Color(1, 1, 0, 1);
    public static final Color MAGENTA = new Color(1, 0, 1, 1);
    public static final Color CYAN = new Color(0, 1, 1, 1);
    public static final Color GRAY = new Color(0.5, 0.5, 0.5, 1);
    public static final Color LIGHT_GRAY = new Color(0.7, 0.7, 0.7, 1);
    public static final Color DARK_GRAY = new Color(0.2, 0.2, 0.2, 1);
    public static final Color DARK_BROWN = new Color(139.0 / 255, 69.0 / 255, 19.0 / 255, 1);
    public static final Color SKY = new Color(95/255.0, 187/255.0, 227/255.0);

    public Color {
        r = clamp(r);
        g = clamp(g);
        b = clamp(b);
        a = clamp(a);
    }

    public Color(double r, double g, double b) {
        this(r, g, b, 1);
    }

    public Color(String hex) {
        if (!hex.matches("^#([A-Fa-f0-9]{6})$"))
            throw new IllegalArgumentException("Invalid hex color: " + hex);

        this(
                Integer.valueOf(hex.substring(1, 3), 16) / 255.0,
                Integer.valueOf(hex.substring(3, 5), 16) / 255.0,
                Integer.valueOf(hex.substring(5, 7), 16) / 255.0,
                1
        );
    }

    public Color(double gray) {
        this(gray, gray, gray);
    }

    private static double clamp(double v) {
        return Math.max(0.0, Math.min(1.0, v));
    }

    public Color copy() {
        return new Color(r, g, b, a);
    }

    public double[] toArray() {
        return new double[]{r, g, b, a};
    }

    public void useColorGl(GL2 gl) {
        gl.glColor4d(r, g, b, a);
    }

    public String toHexString() {
        return String.format("#%02X%02X%02X", (int) (r * 255), (int) (g * 255), (int) (b * 255));
    }

    public String toString() {
        return String.format("Color(%.2f, %.2f, %.2f, %.2f)", r, g, b, a);
    }
}
