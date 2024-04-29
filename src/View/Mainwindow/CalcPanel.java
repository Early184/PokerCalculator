package View.Mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;


public class CalcPanel extends JPanel {
    JTable chanceForDrawTable, scenarioTable;

    

    public CalcPanel(){
        setLayout(new BorderLayout());
        
        chanceForDrawTable = new JTable(){
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
        chanceForDrawTable.setPreferredSize(new Dimension(400, 300));
        chanceForDrawTable.setPreferredScrollableViewportSize(chanceForDrawTable.getPreferredSize());
        chanceForDrawTable.setBackground(Color.WHITE);
        chanceForDrawTable.getTableHeader().setResizingAllowed(false);
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
        
        setOpaque(false);

        add(new JScrollPane(chanceForDrawTable){
            @Override
            public void setPreferredSize(Dimension preferredSize) {
                setPreferredSize(new Dimension(250, 300));
            }
        }, BorderLayout.WEST);
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
