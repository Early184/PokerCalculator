package View.Mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import CustomCompontents.CustomColors;
import CustomCompontents.CustomHeaderRenderer;
import CustomCompontents.ProbabilityCellRenderer;





public class CalcPanel extends JPanel {
    JTable chanceForDrawTable, scenarioTable;

    

    public CalcPanel(){
        setLayout(new BorderLayout()); 
        setBackground(new Color(0,40,20));

        
        final BufferedImage[] image = new BufferedImage[1];
        try {
            File file = new File("src/View/Images/testBildAnwendung1.png");
            image[0]= ImageIO.read(file);
        } catch (IOException e) {
            
            e.printStackTrace();
        }
        
        chanceForDrawTable = new JTable();
        chanceForDrawTable.setPreferredSize(new Dimension(400, 450));
        
        
        chanceForDrawTable.setBackground(new Color(0,40,20));
        chanceForDrawTable.setForeground(Color.WHITE);
        chanceForDrawTable.setAutoscrolls(true);
        chanceForDrawTable.setGridColor(new Color(80,95,27));
        chanceForDrawTable.getTableHeader().setBackground(new Color(0,40,20));
        chanceForDrawTable.getTableHeader().setForeground(Color.white);
        chanceForDrawTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        chanceForDrawTable.getTableHeader().setOpaque(false);
        chanceForDrawTable.getTableHeader().setPreferredSize(new Dimension(100,30));
        chanceForDrawTable.getTableHeader().setDefaultRenderer(new CustomHeaderRenderer(chanceForDrawTable.getTableHeader().getDefaultRenderer()));
        chanceForDrawTable.setEnabled(false);
        chanceForDrawTable.setDragEnabled(false);
        chanceForDrawTable.getTableHeader().setReorderingAllowed(false);
        chanceForDrawTable.getTableHeader().setResizingAllowed(false);
        
    
        
        
        ProbabilityCellRenderer renderer = new ProbabilityCellRenderer();
        chanceForDrawTable.setDefaultRenderer(Object.class, renderer);
        //chanceForDrawTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        
        JScrollPane scrollBar = new JScrollPane(chanceForDrawTable);
        
        scrollBar.getVerticalScrollBar().setOpaque(false); 
        scrollBar.getHorizontalScrollBar().setOpaque(false); 
        scrollBar.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); 
        scrollBar.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0)); 
        scrollBar.setPreferredSize(new Dimension(400, 300));
        scrollBar.setBorder(BorderFactory.createEmptyBorder());
        
        
        
        scenarioTable = new JTable();
        scenarioTable.setPreferredSize(new Dimension(500, 300));
        scenarioTable.getTableHeader().setResizingAllowed(false);
        scenarioTable.getTableHeader().setBackground(new Color(0,40,20));
        scenarioTable.getTableHeader().setForeground(Color.white);
        scenarioTable.setPreferredScrollableViewportSize(scenarioTable.getPreferredSize());
        scenarioTable.setBackground(new Color(0,40,20));
        
        
        
       
        
        

        
        add(scrollBar, BorderLayout.WEST);
        add(new JScrollPane(scenarioTable){
            @Override
            public void setPreferredSize(Dimension preferredSize) {
                setPreferredSize(new Dimension(400, 300));
            }
        }, BorderLayout.CENTER);

    }
    public JTable getChanceForDrawTable() {
        return chanceForDrawTable;
    }

    public JTable getScenarioTable() {
        return scenarioTable;
    }

    public void setScenarioTable(JTable scenarioTable) {
        this.scenarioTable = scenarioTable;
    }
}
