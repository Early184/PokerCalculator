package Model;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
   
    private int value;
    private String suit;
    private Image img;
    private String imagePath;
    private boolean isSelected = false;

    public CardPanel (int value, String suit){
        this.value = value;
        this.suit = suit;
        this.imagePath = "src/View/Images/CardImages/"+ value + suit +".png";
        this.img = new ImageIcon(imagePath).getImage();
        setLayout(new BorderLayout());
        setOpaque(false);
        setVisible(true);
    }
    
    @Override
    protected void paintComponent(Graphics g){
        if(value == 0 && suit.equals("")){
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
            setOpaque(false);
        }else{
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(),this);
                
        }
        super.paintComponent(g);
    }
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public String getSuit() {
        return suit;
    }
    public void setSuit(String suit) {
        this.suit = suit;
    }
    public Image getImg() {
        return img;
    }
    public void setImg(Image img) {
        this.img = img;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setValuesTo(CardPanel selectedCard) {
        if(selectedCard.getImagePath() != ""){
            setImg(selectedCard.getImg());
            setBorder(null);
            setImagePath(selectedCard.getImagePath());
            setSuit(selectedCard.getSuit());
            setValue(selectedCard.getValue());
        }
        
    }
}
