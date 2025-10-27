import Logic.PageManager;
import Scenes.*;

public class Main {

    public static void main(String[] args){
        var mainDrawPage = new MainDrawPage();
        var circlePage = new SimpleApp();
        var mainDrawing = new MainDrawing();
        var solarSystem = new SolarSystem();
        var pyramids = new Pyramids();
        PageManager.init();
        PageManager.showPage(pyramids);
        PageManager.showPage(mainDrawing);
    }
}
