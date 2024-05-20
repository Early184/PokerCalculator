package View.Subwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CustomCompontents.CustomTitleBar;



public class SubFrame extends JDialog {
    
    private JPanel mainPanel, headerPanel, cardContainerPanel;
    private JLabel header;
    private CardContainer heart;
    private CardContainer clubs;
    private CardContainer diamonds;
    private CardContainer spades;
    private JButton refreshButton;
    private CustomTitleBar titleBar;
    
   
   
    
    
    public SubFrame(){
        setSize(800, 930);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocation(1100,0);
        setBackground(Color.BLACK);
        setUndecorated(true);
        setAlwaysOnTop(true);
        createUI();
        add(mainPanel);


        setVisible(true);

    }
    private void createUI() {
        mainPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Image img = new ImageIcon("src/View/Images/pokerGreen.jpg").getImage();
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        

        
        headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setPreferredSize(new Dimension(0,45));

        header = new JLabel("Wähle eine Karte");
        header.setLabelFor(cardContainerPanel);
        header.setBorder(new EmptyBorder(10, 0,0,0));
        header.setFont(new Font("Arial", 1, 20));
        header.setForeground(Color.WHITE);
        
        header.setAlignmentX(0);
        header.setOpaque(false);
        headerPanel.add(header);

        JPanel titleBarAndTitleHolder = new JPanel();
        titleBarAndTitleHolder.setOpaque(false);
        titleBar = new CustomTitleBar();
        titleBar.getCloseButton().setVisible(false);
        titleBar.getOverlayButton().setVisible(false);
        titleBar.getMinimizeButton().setVisible(false);
        titleBarAndTitleHolder.setLayout(new BorderLayout());
        titleBarAndTitleHolder.add(headerPanel, BorderLayout.SOUTH);
        titleBarAndTitleHolder.add(titleBar, BorderLayout.NORTH);

        


        cardContainerPanel = new JPanel();
        cardContainerPanel.setLayout(new FlowLayout(1, 10, 0));
        cardContainerPanel.setOpaque(false);
        
        JPanel buttonHolder = new JPanel();
        buttonHolder.setOpaque(false);
        buttonHolder.setPreferredSize(new Dimension(0, 100));
        
        ImageIcon icon = new ImageIcon("src/View/Images/resetNormal.png");
        int heightAndWidth = 70;
        Image scaledImage = icon.getImage().getScaledInstance(heightAndWidth, heightAndWidth, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
        refreshButton = new JButton(scaledIcon);
        
        
        refreshButton.setBorderPainted(false);
        refreshButton.setContentAreaFilled(false);
        refreshButton.setFocusPainted(false);
        

        buttonHolder.add(refreshButton);


        mainPanel.add(titleBarAndTitleHolder, BorderLayout.NORTH);
        mainPanel.add(cardContainerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonHolder, BorderLayout.SOUTH);
    }

    
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

    public JPanel getHeaderPanel() {
        return headerPanel;
    }

    public void setHeaderPanel(JPanel headerPanel) {
        this.headerPanel = headerPanel;
    }

    public JLabel getHeader() {
        return header;
    }

    public void setHeader(JLabel header) {
        this.header = header;
    }

    public JPanel getCardContainerPanel() {
        return cardContainerPanel;
    }
    
    public CardContainer getHeart() {
        return heart;
    }
    public CardContainer getClubs() {
        return clubs;
    }
    public CardContainer getDiamonds() {
        return diamonds;
    }
    public CardContainer getSpades() {
        return spades;
    }
    public JButton getRefreshButton() {
        return refreshButton;
    }
    public void setRefreshButton(JButton refreshButton) {
        this.refreshButton = refreshButton;
    }
    public CustomTitleBar getTitleBar() {
        return titleBar;
    }
    public void setTitleBar(CustomTitleBar titleBar) {
        this.titleBar = titleBar;
    }
}
