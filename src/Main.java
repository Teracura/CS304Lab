import Logic.PageManager;
import Scenes.MainDrawPage;
import Scenes.MainDrawing;
import Scenes.SimpleApp;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var mainDrawPage = new MainDrawPage();
        var circlePage = new SimpleApp();
        var mainDrawing = new MainDrawing();
        PageManager.init();
        PageManager.showPage(mainDrawing);
    }
}
