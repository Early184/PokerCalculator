package CustomCompontents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;


import javax.swing.border.AbstractBorder;


public class CustomBorder extends AbstractBorder {
    private Color borderColour;
    private int leftLine;
    private int rightLine;
    private int topLine;
    private int bottomLine;

    public CustomBorder(Color colour, int leftLine, int rightLine, int topLine, int bottomLine) {
        this.borderColour = colour;
        this.leftLine = leftLine;
        this.rightLine = rightLine;
        this.topLine = topLine;
        this.bottomLine = bottomLine;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(borderColour);
    
        // Draw top line
        if (topLine > 0) {
            g2d.drawLine(x, y, x + width - 1, y);
        }
    
        // Draw right line
        if (rightLine > 0) {
            g2d.drawLine(x + width - 1, y, x + width - 1, y + height - 1);
        }
    
        // Draw bottom line
        if (bottomLine > 0) {
            g2d.drawLine(x, y + height - 1, x + width - 1, y + height - 1);
        }
    
        // Draw left line
        if (leftLine > 0) {
            g2d.drawLine(x, y, x, y + height - 1);
        }
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = leftLine;
        insets.top = topLine;
        insets.right = rightLine;
        insets.bottom = bottomLine;
        return insets;
    }
}
  
  
  
