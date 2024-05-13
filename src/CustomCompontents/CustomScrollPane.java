package CustomCompontents;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CustomScrollPane extends JScrollPane{

    public CustomScrollPane(JTable table) {
        super(table);
        getVerticalScrollBar().setOpaque(false); 
        getHorizontalScrollBar().setOpaque(false); 
        getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); 
        getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0)); 
        setPreferredSize(new Dimension(400, 300));
        setBorder(BorderFactory.createEmptyBorder());
    }
    
}
