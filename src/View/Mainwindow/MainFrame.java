package View.Mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private CalcPanel calcPanel;
    private GamePanel gamePanel;
   
    
    public MainFrame(){
        setSize(1000, 830);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Poker Planner");
        setLocation(0, 220);
        setLayout(new BorderLayout());
        
        
        
        
        mainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Image img = new ImageIcon("src/View/Images/pokerGreen.jpg").getImage();
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
            }
        };
        
        
        
        calcPanel = new CalcPanel();
        
        gamePanel = new GamePanel();

       
        mainPanel.add(calcPanel, BorderLayout.SOUTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }


    public CalcPanel getCalcPanel() {
        return calcPanel;
    }
    public GamePanel getGamePanel() {
        return gamePanel;
    }
}
