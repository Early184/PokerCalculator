package CustomCompontents;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class ProbabilityCellRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel cellComponent = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof String) {
            String stringValue = (String) value;

            cellComponent.setText(stringValue);
            cellComponent.setFont(new Font("Arial", Font.BOLD, 12));
            cellComponent.setBackground(new Color(00,40,20));

            if (!stringValue.isEmpty()) {
                String cleanedValue = stringValue.replaceAll("[^0-9.]", "");
                // check if cleaned value is empty
                if (cleanedValue.isEmpty()) {
                    // set cells white
                    cellComponent.setForeground(Color.WHITE); 
                    cellComponent.setHorizontalAlignment(SwingConstants.LEFT);
                    cellComponent.setBorder(new EmptyBorder(0, 10, 0, 0));
                } else {
                    
                    try {
                        double doubleValue = Double.parseDouble(cleanedValue);
                        cellComponent.setForeground(getForegroundForProbability(doubleValue));
                        if (doubleValue != 0) {
                            cellComponent.setHorizontalAlignment(SwingConstants.CENTER);
                            
                            
                        }
                    } catch (NumberFormatException e) {
                        // debugging
                        System.out.println("NumberFormatException: " + e.getMessage());
                        
                        cellComponent.setForeground(table.getForeground());
                    }
                }
            } 
            table.setRowHeight(row, 33);
            
        }

        return cellComponent;
    }


    // method for changing cell color based on value in it
    private Color getForegroundForProbability(double probability) {
        if (probability < 20) {
            return Color.RED;
        } else if (probability < 35) {
            return Color.ORANGE;
        } else if (probability < 55) {
            return Color.YELLOW;
        } else {
            return Color.GREEN;
        }
    }
}
