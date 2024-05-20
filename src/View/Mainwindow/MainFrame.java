package View.Mainwindow;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import CustomCompontents.CustomTitleBar;



public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private CalcPanel calcPanel;
    private GamePanel gamePanel;
    private CustomTitleBar titleBar;
   
    
    


    public MainFrame(){
        setSize(1000, 810);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Poker Planner");
        setLocation(0, 220);
        setUndecorated(true);
        setAlwaysOnTop(true);
        
        mainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Image img = new ImageIcon("src/View/Images/pokerGreen.jpg").getImage();
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        
        
        titleBar = new CustomTitleBar();

        JPanel calcHolderPanel = new JPanel();
        calcHolderPanel.setOpaque(false);
        calcPanel = new CalcPanel();
        calcHolderPanel.add(calcPanel);
        
        
        gamePanel = new GamePanel();

        mainPanel.add(titleBar, BorderLayout.NORTH);
        mainPanel.add(calcHolderPanel, BorderLayout.CENTER);
        mainPanel.add(gamePanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        setVisible(true);
    }


    public CalcPanel getCalcPanel() {
        return calcPanel;
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
    public CustomTitleBar getTitleBar() {
        return titleBar;
    }


    public void setTitleBar(CustomTitleBar titleBar) {
        this.titleBar = titleBar;
    }
}
