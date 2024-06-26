
import javax.swing.Timer;
import Controller.ControllerMainwindow;
import Controller.ControllerSubwindow;
import Controller.PokerCalculator;
import Model.AllDraws;
import Model.Playground;
import View.Mainwindow.MainFrame;
import View.Subwindow.SubFrame;
import View.disclaimer.Disclaimer;

public class Main {
    public static void main(String[] args) {
        Disclaimer disclaimer = new Disclaimer();
        disclaimer.showDisclaimer(); // show disclaimer

        // 5 seconds timer for disclaimer
        Timer timer = new Timer(5000, e -> {
            disclaimer.closeDisclaimer();
            MainFrame frame = new MainFrame();
            SubFrame subFrame = new SubFrame();
            
            Playground playground = new Playground();
            ControllerMainwindow controllerMain = new ControllerMainwindow(frame, subFrame, playground);
            ControllerSubwindow controllerSub = new ControllerSubwindow(subFrame, frame, controllerMain.getPlayground(), controllerMain);
            PokerCalculator calc = new PokerCalculator(playground, frame, controllerMain); 
            AllDraws allDraws = new AllDraws();
        });
        timer.setRepeats(false); // dont repeat timer
        timer.start(); // timer start
        
        
    }
}
