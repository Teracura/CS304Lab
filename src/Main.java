import Logic.PageManager;
import Scenes.MainDrawPage;
import Scenes.SimpleApp;

public class Main {

    public static void main(String[] args) {
        var mainDrawPage = new MainDrawPage();
        var circlePage = new SimpleApp();
        PageManager.init();
        PageManager.showPage(mainDrawPage);
        PageManager.showPage(circlePage);
    }
}
