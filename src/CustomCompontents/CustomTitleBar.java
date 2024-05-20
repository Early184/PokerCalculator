package CustomCompontents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class CustomTitleBar extends JPanel {
    private JButton closeButton, overlayButton, minimizeButton;
    
    
    public CustomTitleBar() {
        setPreferredSize(new Dimension(0, 26));
        setBackground(CustomColors.getColor("Green"));
        setLayout(new FlowLayout(4, 15, 3));
        

        ImageIcon closeImage = new ImageIcon("src/View/Images/TitleBarImages/close.png");
        Image scaledCloseImage = closeImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledCloseIcon = new ImageIcon(scaledCloseImage);
        closeButton = new JButton();
        closeButton.setIcon(scaledCloseIcon);
        closeButton.setPreferredSize(new Dimension(20, 20));
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setBackground(new Color(0f,0f,0f,0f));
        closeButton.setBorder(null);
        

        ImageIcon minImage = new ImageIcon("src/View/Images/TitleBarImages/minimize.png");
        Image scaledMinimizeImage = minImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledMinimizeIcon = new ImageIcon(scaledMinimizeImage);
        overlayButton = new JButton();
        overlayButton.setIcon(scaledMinimizeIcon);
        overlayButton.setPreferredSize(new Dimension(20, 20));
        overlayButton.setContentAreaFilled(false);
        overlayButton.setBackground(new Color(0f,0f,0f,0f));
        overlayButton.setFocusPainted(false);
        overlayButton.setBorder(null);

        ImageIcon min2Image = new ImageIcon("src/View/Images/TitleBarImages/minimize2.png");
        Image scaledMinimize2Image = min2Image.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon scaledMinimize2Icon = new ImageIcon(scaledMinimize2Image);
        minimizeButton = new JButton();
        minimizeButton.setIcon(scaledMinimize2Icon);
        minimizeButton.setPreferredSize(new Dimension(20, 20));
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setFocusPainted(false);
        minimizeButton.setBackground(new Color(0f,0f,0f,0f));
        minimizeButton.setBorder(null);

        add(overlayButton);
        add(minimizeButton);
        add(closeButton);

    }
    public JButton getCloseButton() {
        return closeButton;
    }
    public void setCloseButton(JButton closeButton) {
        this.closeButton = closeButton;
    }
    public JButton getOverlayButton() {
        return overlayButton;
    }
    public void setOverlayButton(JButton minimizeButton) {
        this.overlayButton = minimizeButton;
    }
    public JButton getMinimizeButton() {
        return minimizeButton;
    }
    public void setMinimizeButton(JButton minimizeButton) {
        this.minimizeButton = minimizeButton;
    }
}
