import Controller.ControllerMainwindow;
import Controller.ControllerSubwindow;
import Controller.PokerCalculator;
import View.Mainwindow.MainFrame;
import View.Subwindow.SubFrame;

public class Main {
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        SubFrame subFrame = new SubFrame();
        
        PokerCalculator calc = new PokerCalculator();
        ControllerMainwindow controllerMain = new ControllerMainwindow(frame, subFrame, calc.getPlayground());
        ControllerSubwindow controllerSub = new ControllerSubwindow(subFrame, frame, controllerMain.getPlayground(), controllerMain, calc);
        
    }
}
