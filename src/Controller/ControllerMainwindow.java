package Controller;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import CustomCompontents.ProbabilityCellRenderer;
import Model.CardPanel;
import Model.Deck;
import Model.Playground;
import View.Mainwindow.MainFrame;
import View.Subwindow.SubFrame;

public class ControllerMainwindow {
    
    private MainFrame mainWindow;
    private SubFrame subFrame;
    private Deck deck;
    private Playground playground;
    private DefaultTableModel defaultTableModelChances;
    private DefaultTableModel defaultTableModelSzenarios;

    

    

    public ControllerMainwindow(MainFrame mainWindow, SubFrame subFrame, Playground playground){
        this.mainWindow = mainWindow;
        this.subFrame = subFrame;
        this.deck = Deck.getInstance();
        this.playground = playground;
        handlePlayground();
        gamePanelActions();
        tableModel();
        
        
    }
    
    public void handlePlayground(){
        
        mainWindow.getGamePanel().getHandCardPanel().add(playground.getCard(0));
        mainWindow.getGamePanel().getHandCardPanel().add(playground.getCard(1));

        mainWindow.getGamePanel().getCommunityCardPanel().add(playground.getCard(2));
        mainWindow.getGamePanel().getCommunityCardPanel().add(playground.getCard(3));
        mainWindow.getGamePanel().getCommunityCardPanel().add(playground.getCard(4));
        mainWindow.getGamePanel().getCommunityCardPanel().add(playground.getCard(5));
        mainWindow.getGamePanel().getCommunityCardPanel().add(playground.getCard(6));
       
        mainWindow.getGamePanel().add(mainWindow.getGamePanel().getCommunityCardPanel());
        mainWindow.getGamePanel().add(mainWindow.getGamePanel().getHandCardPanel());
        
        
        mainWindow.getGamePanel().revalidate();
        mainWindow.getGamePanel().repaint();
        
    }
    private void tableModel() {
        
        defaultTableModelChances = new DefaultTableModel(new Object[]{"Draw Chance","Percentage"}, 0);
        defaultTableModelSzenarios = new DefaultTableModel(new Object[]{"Szenarios"}, 0);

        mainWindow.getCalcPanel().getChanceForDrawTable().setModel(defaultTableModelChances);
        mainWindow.getCalcPanel().getScenarioTable().setModel(defaultTableModelSzenarios);

        
       
        mainWindow.getCalcPanel().getChanceForDrawTable().getColumnModel().getColumn(0).setPreferredWidth(200);
        for(int column =0; column < defaultTableModelChances.getColumnCount(); column++){
            mainWindow.getCalcPanel().getChanceForDrawTable().getColumnModel().getColumn(column).setCellRenderer(new ProbabilityCellRenderer());
        }
        
    }

    public void gamePanelActions(){
        for(int i = 0;i <7; i++){
            playground.getCardSlot(i).addMouseListener(removeCard());
        }
        

    }
    public MouseListener removeCard (){
        return new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
                

            }

            @Override
            public void mousePressed(MouseEvent e) {
                CardPanel clickedCard = (CardPanel) e.getSource();
                if (clickedCard.getImagePath() != "" ){
                    
                    for(CardPanel cardPanel : deck.getCards().values()){
                        if(cardPanel.getImagePath().equals(clickedCard.getImagePath())){
                            cardPanel.removeAll();
                            cardPanel.setSelected(false);
                            cardPanel.revalidate();
                            cardPanel.repaint();
                        }
                    }
                    playground.removeCard(clickedCard);
                    clickedCard.revalidate();
                    clickedCard.repaint();
                    Playground.subCountedCards();

                    
                    
                    
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        };
    }

    public Playground getPlayground() {
        return playground;
    }

    public void setPlayground(Playground playground) {
        this.playground = playground;
    }
    public DefaultTableModel getDefaultTableModelChances() {
        return defaultTableModelChances;
    }

    public void setDefaultTableModelChances(DefaultTableModel defaultTableModelChances) {
        this.defaultTableModelChances = defaultTableModelChances;
    }

    public DefaultTableModel getDefaultTableModelSzenarios() {
        return defaultTableModelSzenarios;
    }

    public void setDefaultTableModelSzenarios(DefaultTableModel defaultTableModelSzenarios) {
        this.defaultTableModelSzenarios = defaultTableModelSzenarios;
    }
}

