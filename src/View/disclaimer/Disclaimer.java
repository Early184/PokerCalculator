package View.disclaimer;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Disclaimer extends JFrame {

    public Disclaimer() {
        
        setSize(new Dimension(512, 796));
        setLocationRelativeTo(null);
        setResizable(false);
        
        setUndecorated(true);
        setBackground(new Color(0f,0f,0f,0f));
        setLayout(new BorderLayout());
        setVisible(false);
        
        
        JPanel disclaimerPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("src/View/Images/Disclaimer2.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        disclaimerPanel.setOpaque(false);
        add(disclaimerPanel, BorderLayout.CENTER);

        
    }
    
    public void showDisclaimer() {
        setVisible(true); // Disclaimer-Fenster anzeigen
    }

    public void closeDisclaimer() {
        dispose(); // Disclaimer-Fenster schließen
    }
}
