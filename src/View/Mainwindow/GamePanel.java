package View.Mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import Model.CardPanel;

public class GamePanel extends JPanel {
    private JPanel communityCardPanel, handCardPanel;
    

    
    
    
    
    
    
    public GamePanel(){
        createPanel();
    }

    public void createPanel(){
        setPreferredSize(new Dimension(900, 440));
        setLayout(new FlowLayout(1,0,10));
        setOpaque(false);
        communityCardPanel = new JPanel();
        communityCardPanel.setLayout(new GridLayout(1, 5, 20, 0));
        communityCardPanel.setPreferredSize(new Dimension(880, 230));
        communityCardPanel.setBorder(new EmptyBorder(0, 0, 20, 0));
        communityCardPanel.setOpaque(false);
        


        handCardPanel = new JPanel();
        handCardPanel.setLayout(new GridLayout(1, 2, 20, 0));
        handCardPanel.setPreferredSize(new Dimension(300, 190));
        handCardPanel.setOpaque(false);



       
    }

    
    public JPanel getCommunityCardPanel() {
        return communityCardPanel;
    }

    public JPanel getHandCardPanel() {
        return handCardPanel;
    }

    
    
}
