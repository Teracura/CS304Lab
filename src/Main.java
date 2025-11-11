import Game.PageManager;
import Scenes.*;

public class Main {

    public static void main(String[] args){
        var mainDrawPage = new MainDrawPage();
        var circlePage = new SimpleApp();
        var mainDrawing = new MainDrawing();
        var solarSystem = new SolarSystem();
        var pyramids = new Pyramids();
        var secondQuiz = new SecondQuiz();
        var gameTesting = new GameTesting();
        var thirdQuiz = new ThirdQuiz();
        PageManager.init();
        PageManager.showPage(thirdQuiz);
//        Pages.PageManager.showPage(pyramids);
//        Pages.PageManager.showPage(mainDrawing);
//        Pages.PageManager.showPage(solarSystem);
    }
}
