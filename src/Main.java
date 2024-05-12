import Controller.ControllerMainwindow;
import Controller.ControllerSubwindow;
import Controller.PokerCalculator;
import Model.Playground;
import View.Mainwindow.MainFrame;
import View.Subwindow.SubFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        SubFrame subFrame = new SubFrame();
        
        Playground playground = new Playground();
        ControllerMainwindow controllerMain = new ControllerMainwindow(frame, subFrame, playground);
        ControllerSubwindow controllerSub = new ControllerSubwindow(subFrame, frame, controllerMain.getPlayground(), controllerMain);
        PokerCalculator calc = new PokerCalculator(playground, frame, controllerMain);
    }
}
