package Controller;



import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

    

    public ControllerMainwindow(MainFrame mainWindow, SubFrame subFrame, Playground playground){
        this.mainWindow = mainWindow;
        this.subFrame = subFrame;
        this.deck = Deck.getInstance();
        this.playground = playground;
        handlePlayground();
        gamePanelActions();
        System.out.println(deck.getCards().get(2));
        
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
                            System.out.println(cardPanel.getComponentCount());
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
    
}

