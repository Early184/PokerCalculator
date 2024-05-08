package View.Mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;




public class CalcPanel extends JPanel {
    JTable chanceForDrawTable, scenarioTable;

    

    public CalcPanel(){
        setLayout(new BorderLayout()); 
        
        final BufferedImage[] image = new BufferedImage[1];
        try {
            File file = new File("src/View/Images/testBildAnwendung1.png");
            image[0]= ImageIO.read(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        chanceForDrawTable = new JTable(){
            @Override
            protected void paintComponent(Graphics g) {
                g.drawImage(image[0], 0, 0,getWidth(),getHeight(),null);
                super.paintComponent(g);
            }
            @Override
            public void createDefaultColumnsFromModel() {
                super.createDefaultColumnsFromModel();
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                centerRenderer.setOpaque(false);
                for (int i = 0; i < getColumnCount(); i++) {
                    getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                    
                }
            }
        };
        chanceForDrawTable.setPreferredSize(new Dimension(400, 300));
        chanceForDrawTable.setPreferredScrollableViewportSize(chanceForDrawTable.getPreferredSize());
        chanceForDrawTable.setBackground(new Color(0f,0f,0f,0f));
        chanceForDrawTable.getTableHeader().setResizingAllowed(false);
        chanceForDrawTable.setOpaque(false);

        
        
        
        scenarioTable = new JTable(){
            
            @Override
            public void createDefaultColumnsFromModel() {
                super.createDefaultColumnsFromModel();
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
                for (int i = 0; i < getColumnCount(); i++) {
                    getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
                }
            }
        };
        scenarioTable.setPreferredSize(new Dimension(500, 300));
        scenarioTable.getTableHeader().setResizingAllowed(false);
        scenarioTable.setPreferredScrollableViewportSize(scenarioTable.getPreferredSize());
        
        
        
        
        
        add(chanceForDrawTable, BorderLayout.WEST);
        add(new JScrollPane(scenarioTable){
            @Override
            public void setPreferredSize(Dimension preferredSize) {
                setPreferredSize(new Dimension(500, 300));
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
