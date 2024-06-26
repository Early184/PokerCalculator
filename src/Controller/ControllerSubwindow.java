package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import Model.CardPanel;
import Model.Deck;
import Model.Playground;
import View.Mainwindow.MainFrame;
import View.Subwindow.CardContainer;
import View.Subwindow.SubFrame;

public class ControllerSubwindow {
    private SubFrame subWindow;
    private MainFrame mainWindow;
    private ControllerMainwindow controllerMain;
    private CardContainer heart;
    private CardContainer clubs;
    private CardContainer diamonds;
    private CardContainer spades;
    private Deck deck;
    private Playground playground;
    
    
    

    public ControllerSubwindow(SubFrame subWindow, MainFrame mainWindow, Playground playground, ControllerMainwindow controllerMain){
        this.mainWindow = mainWindow;
        this.subWindow = subWindow;
        this.playground = playground;
        this.controllerMain = controllerMain;
       
        deck = Deck.getInstance();
        createCardContainers(subWindow);

        clearButton();
        
    }
    private void createCardContainers(SubFrame subWindow) {
        heart = new CardContainer();
        clubs = new CardContainer();
        diamonds = new CardContainer();
        spades = new CardContainer();
        containerConfig(heart, "heart");
        subWindow.getCardContainerPanel().add(heart);
        containerConfig(clubs, "clubs");
        subWindow.getCardContainerPanel().add(clubs);
        containerConfig(diamonds, "diamonds");
        subWindow.getCardContainerPanel().add(diamonds);
        containerConfig(spades, "spades");
        subWindow.getCardContainerPanel().add(spades);
        subWindow.getCardContainerPanel().repaint();
        subWindow.getCardContainerPanel().revalidate();
    }
    
    public void containerConfig(CardContainer test1, String suit) {
        
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        // grid position starts at 0/0 xy 
        gbc.gridx = 0;
        gbc.gridy = 12;
        // next container got 1 grid in width
        gbc.weightx = 1.0;
        
        // number of cells in col
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
       
        
        for(int i = 1; i < 14; i++){
            if(i == 1){
                gbc.insets = new Insets(0, 0, 0, 0);
                //first item height of 4
                gbc.weighty = 4.0;
                CardPanel cardPanel = new CardPanel(i,suit);
                cardPanel.addMouseListener(cardListener());
                deck.addCard(cardPanel);
                
                test1.add(deck.getLastCardPanel(), gbc);
                gbc.gridy--;// grid y increment for jumping in cell
            }else{
                gbc.insets = new Insets(0, 0, -160, 0);
                gbc.weighty = 1.0;
                CardPanel cardPanel = new CardPanel(i,suit);
                cardPanel.addMouseListener(cardListener());
                //cards.add(cardPanel);
                deck.addCard(cardPanel);
                test1.add(deck.getLastCardPanel(), gbc);
                gbc.gridy--;
            }
            
        }
    }
    public void clearButton(){
        subWindow.getRefreshButton().addActionListener(refresh -> {
            
            playground.removeCards();
            mainWindow.getGamePanel().revalidate();
            mainWindow.getGamePanel().repaint();
            subWindow.getCardContainerPanel().removeAll();
            createCardContainers(subWindow);
            Playground.setCountedCards(0);
            
        });
        subWindow.getRefreshButton().addMouseListener(buttonListener());
    }
    
    public MouseListener cardListener(){
        return new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            
            
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            pickCard(e);
        }

        public void pickCard(MouseEvent e) {
            CardPanel clickedCard = (CardPanel)e.getSource();
            CardPanel emptyCardPanel = playground.getNextEmptyCardPanel();
            
            if(playground.getCountedCards() < 7){
                if(!clickedCard.isSelected()){
                    clickedCard.setSelected(true);
                    emptyCardPanel.setValuesTo(clickedCard);
        
                    JPanel overlayPanel = new JPanel(){
                        @Override
                        protected void paintComponent(Graphics g){
                            super.paintComponent(g);
                                
                            g.setColor(new Color(0,0,0,128));
                            g.fillRoundRect(0, 0, getWidth(), getHeight(), 17, 17);
                        }
                    };
                    overlayPanel.setName("overlayPanel");
                    overlayPanel.setOpaque(false);
                    
                    
                    clickedCard.add(overlayPanel, BorderLayout.CENTER);
                    clickedCard.revalidate(); 
                    clickedCard.repaint();
                    
                    playground.addCountedCards();
                }   
            }
            
        }
            
            
        


        @Override
        public void mousePressed(MouseEvent e) {
            
        
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //check for first card
            if(((CardPanel)e.getComponent()).getValue() !=1){
                // get layout from parent panel
                GridBagLayout layout = (GridBagLayout) e.getComponent().getParent().getLayout();
                GridBagConstraints gbc = layout.getConstraints(e.getComponent());

                // get actual insets
                Insets insets = gbc.insets;
                // defining new insets for getting cards go up
                Insets newInsets = new Insets(insets.top - 20, insets.left, insets.bottom, insets.right);
                // set new insets
                gbc.insets = newInsets;

                // set new constraints in layout
                layout.setConstraints(e.getComponent(), gbc);

                // re-draw parent
                e.getComponent().getParent().revalidate();
                e.getComponent().getParent().repaint();
            }
            if(((CardPanel)e.getComponent()).getValue() == 13){
            
            GridBagLayout layout = (GridBagLayout) e.getComponent().getParent().getLayout();
            GridBagConstraints gbc = layout.getConstraints(e.getComponent());

            Insets insets = gbc.insets;
            Insets newInsets = new Insets(insets.top - 30, insets.left, insets.bottom, insets.right);
            
            gbc.insets = newInsets;

            layout.setConstraints(e.getComponent(), gbc);

            e.getComponent().getParent().revalidate();
            e.getComponent().getParent().repaint();
            }
        }


        @Override
        public void mouseExited(MouseEvent e) {
            if(((CardPanel)e.getComponent()).getValue() !=1){
                GridBagLayout layout = (GridBagLayout) e.getComponent().getParent().getLayout();
                GridBagConstraints gbc = layout.getConstraints(e.getComponent());

                Insets insets = gbc.insets;
                Insets newInsets = new Insets(insets.top + 20, insets.left, insets.bottom, insets.right);
                
                gbc.insets = newInsets;

                
                layout.setConstraints(e.getComponent(), gbc);

                
                e.getComponent().getParent().revalidate();
                e.getComponent().getParent().repaint();
            }
            if(((CardPanel)e.getComponent()).getValue() == 13){
                GridBagLayout layout = (GridBagLayout) e.getComponent().getParent().getLayout();
                GridBagConstraints gbc = layout.getConstraints(e.getComponent());

                Insets insets = gbc.insets;
               
                Insets newInsets = new Insets(insets.top + 30, insets.left, insets.bottom, insets.right);
                gbc.insets = newInsets;
        
                
                layout.setConstraints(e.getComponent(), gbc);
    
                e.getComponent().getParent().revalidate();
                e.getComponent().getParent().repaint();
                }
        }};
    }
    
    public MouseListener buttonListener(){
        return new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ImageIcon icon = new ImageIcon("src/View/Images/resetPressed.png");
                int heightAndWidth = 70;
                Image scaledImage = icon.getImage().getScaledInstance(heightAndWidth, heightAndWidth, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                subWindow.getRefreshButton().setIcon(scaledIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ImageIcon icon = new ImageIcon("src/View/Images/resetHovered.png");
                int heightAndWidth = 70;
                Image scaledImage = icon.getImage().getScaledInstance(heightAndWidth, heightAndWidth, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                subWindow.getRefreshButton().setIcon(scaledIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ImageIcon icon = new ImageIcon("src/View/Images/resetHovered.png");
                int heightAndWidth = 70;
                Image scaledImage = icon.getImage().getScaledInstance(heightAndWidth, heightAndWidth, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                subWindow.getRefreshButton().setIcon(scaledIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ImageIcon icon = new ImageIcon("src/View/Images/resetNormal.png");
                int heightAndWidth = 70;
                Image scaledImage = icon.getImage().getScaledInstance(heightAndWidth, heightAndWidth, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                subWindow.getRefreshButton().setIcon(scaledIcon);
            }
            
        };
    }
}
