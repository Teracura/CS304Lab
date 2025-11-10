package Physics;

import Graphics.Color;
import Graphics.Coordinate;

import java.util.List;

public class RandomUtils {
    public static Coordinate randomCoordinate(double xStart, double xEnd, double yStart, double yEnd){
        return new Coordinate(xStart + Math.random() * (xEnd - xStart), yStart + Math.random() * (yEnd - yStart));
    }

    public static Coordinate randomDirection(){
        return new Coordinate(Math.random() * 2 - 1, Math.random() * 2 - 1);
    }

    public static double randomDouble(double min, double max){
        return min + Math.random() * (max - min);
    }

    public static Color randomColor(){
        return new Color(Math.random() * 255, Math.random() * 255, Math.random() * 255);
    }

    public static double randomAngleRad() {
        return Math.toRadians(Math.random() * 360);
    }

    public static <T> T randomChoice(List<T> list) {
        return list.get((int) (Math.random() * list.size()));
    }
}
