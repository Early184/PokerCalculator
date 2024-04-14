package View.Mainwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTable;

public class CalcPanel extends JPanel {
    JTable chanceForDrawTable, winChancePerNumberTable, scenarioTable ;

    public CalcPanel(){
        setLayout(new BorderLayout());
        chanceForDrawTable = new JTable();
        chanceForDrawTable.setPreferredSize(new Dimension(250, 300));
        chanceForDrawTable.setBackground(Color.BLACK);
        winChancePerNumberTable = new JTable();
        winChancePerNumberTable.setPreferredSize(new Dimension(250, 300));
        winChancePerNumberTable.setBackground(Color.BLACK);
        scenarioTable = new JTable();
        scenarioTable.setPreferredSize(new Dimension(350, 300));
        scenarioTable.setBackground(Color.green);
        setOpaque(false);

        add(chanceForDrawTable, BorderLayout.WEST);
        add(winChancePerNumberTable, BorderLayout.EAST);
        add(scenarioTable, BorderLayout.CENTER);

    }
}
