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
import CustomCompontents.CustomScrollPane;
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
        setDrawTable();
        
        ProbabilityCellRenderer renderer = new ProbabilityCellRenderer();
        chanceForDrawTable.setDefaultRenderer(Object.class, renderer);
        //chanceForDrawTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        
        JScrollPane scrollBar = new CustomScrollPane(chanceForDrawTable);
        
        
        scenarioTable = new JTable();
        setScenarioTable();
        JScrollPane pane = new CustomScrollPane(scenarioTable);
        
        add(scrollBar, BorderLayout.WEST);
        add(pane, BorderLayout.CENTER);

    }
    private void setDrawTable() {
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
        chanceForDrawTable.getTableHeader().setBorder(BorderFactory.createLineBorder(new CustomColors().getColor("Yellow"), 2, false));
        chanceForDrawTable.setEnabled(false);
        chanceForDrawTable.setDragEnabled(false);
        chanceForDrawTable.getTableHeader().setReorderingAllowed(false);
        chanceForDrawTable.getTableHeader().setResizingAllowed(false);
    }
    private void setScenarioTable(){
        scenarioTable.setBackground(new Color(0,40,20));
        scenarioTable.setForeground(Color.WHITE);
        scenarioTable.setAutoscrolls(true);
        scenarioTable.setGridColor(new Color(80,95,27));
        scenarioTable.getTableHeader().setBorder(BorderFactory.createLineBorder(new CustomColors().getColor("Yellow"), 2, false));
        scenarioTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        scenarioTable.getTableHeader().setPreferredSize(new Dimension(100,30));
        scenarioTable.getTableHeader().setOpaque(false);
        scenarioTable.setPreferredSize(new Dimension(500, 300));
        scenarioTable.getTableHeader().setResizingAllowed(false);
        scenarioTable.getTableHeader().setBackground(new Color(0,40,20));
        scenarioTable.getTableHeader().setForeground(Color.white);
        scenarioTable.setPreferredScrollableViewportSize(scenarioTable.getPreferredSize());
        
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

