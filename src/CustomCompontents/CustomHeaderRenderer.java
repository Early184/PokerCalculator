package CustomCompontents;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class CustomHeaderRenderer implements TableCellRenderer {
    private final TableCellRenderer defaultRenderer;

    public CustomHeaderRenderer(TableCellRenderer defaultRenderer) {
        this.defaultRenderer = defaultRenderer;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, new CustomColors().getColor("Yellow")));
        }
        return component;
    }
}
