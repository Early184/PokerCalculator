package View.Subwindow;


import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JPanel;


public class CardContainer extends JPanel {
    

    public CardContainer (){
        setPreferredSize(new Dimension(150, 750));
        setLayout(new GridBagLayout());
        setOpaque(false);
        // containerConfig(this, suit);
        
    }


    
}
