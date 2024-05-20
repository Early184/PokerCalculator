package Controller;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.TableHeaderUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import CustomCompontents.ImageIconFactory;
import CustomCompontents.ProbabilityCellRenderer;
import Model.AllDraws;
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
    private int mpX = 0;
    private int mpY = 0;
    private boolean isMinimized = false;
    


    public ControllerMainwindow(MainFrame mainWindow, SubFrame subFrame, Playground playground){
        this.mainWindow = mainWindow;
        this.subFrame = subFrame;
        this.deck = Deck.getInstance();
        this.playground = playground;

        handlePlayground();
        gamePanelActions();
        tableModel();
        titleBarHandler();
        titleBarHandlerSub();
        mainWindow.getTitleBar().getOverlayButton().addMouseListener(minimizeListenerTitleBar());
        mainWindow.getTitleBar().getMinimizeButton().addMouseListener(minimize2ListenerTitleBar());
        mainWindow.getTitleBar().getCloseButton().addMouseListener(closeListenerTitleBar());
        
        
    }
    public MouseListener closeListenerTitleBar(){
        return new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/closePressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getCloseButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/closePressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getCloseButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/closePressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getCloseButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/close.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getCloseButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }

           
        };
    }
    public MouseListener minimizeListenerTitleBar(){
        return new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/minimizePressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getOverlayButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/minimizePressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getOverlayButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/minimizePressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getOverlayButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/minimize.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getOverlayButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }

           
        };
    }
    public MouseListener minimize2ListenerTitleBar(){
        return new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/minimize2Pressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getMinimizeButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/minimize2Pressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getMinimizeButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/minimize2Pressed.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getMinimizeButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                BufferedImage originalImage;
                try {
                    originalImage = ImageIO.read(new File("src/View/Images/TitleBarImages/minimize2.png"));
                    Image scaledImage = originalImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                    mainWindow.getTitleBar().getMinimizeButton().setIcon(new ImageIcon(scaledImage));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }

           
        };
    }
    public void titleBarHandler(){
        mainWindow.addWindowListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                // Überprüfen, ob der Frame minimiert ist
                if ((e.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                    subFrame.setVisible(false);
                } else {
                    subFrame.setVisible(true);
                }
            }
        });
        mainWindow.addWindowStateListener(new WindowAdapter() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if ((e.getNewState() & Frame.ICONIFIED) != Frame.ICONIFIED) {
                    subFrame.setVisible(true);
                }
            }
        });
        mainWindow.getTitleBar().getCloseButton().addActionListener(close ->{
            System.exit(0);
        });
        mainWindow.getTitleBar().getMinimizeButton().addActionListener(minimize ->{
            mainWindow.setState(Frame.ICONIFIED);
            subFrame.setVisible(false);
        });
        mainWindow.getTitleBar().getOverlayButton().addActionListener(overlay ->{
            if(isMinimized != true){
                mainWindow.getGamePanel().setVisible(false);
                mainWindow.setSize(835,345);
                mainWindow.setOpacity(0.9f);
                mainWindow.setLocation(0,0);
                mainWindow.setAlwaysOnTop(true);
                subFrame.setVisible(false);
                mainWindow.repaint();
                isMinimized = true;
            }else if (isMinimized == true){
                mainWindow.getGamePanel().setVisible(true);
                mainWindow.setSize(1000,810);
                mainWindow.setOpacity(1);
                mainWindow.setLocation(0,220);
                mainWindow.setAlwaysOnTop(false);
                subFrame.setVisible(true);
                mainWindow.repaint();
                isMinimized = false;
            }
            

        });
        mainWindow.getTitleBar().addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e){
            mpX = e.getX();
            mpY = e.getY();
        }      
        });
    
        mainWindow.getTitleBar().addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {
            
               int windowLocationX =((int)mainWindow.getLocation().getX());
               int windowLocationY = ((int)mainWindow.getLocation().getY());
                
                mainWindow.setLocation(windowLocationX+ e.getX() - mpX ,windowLocationY + e.getY() - mpY);
        }
        });
    }
    public void titleBarHandlerSub(){
        subFrame.getTitleBar().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e){
                mpX = e.getX();
                mpY = e.getY();
            }      
            });
        
            subFrame.getTitleBar().addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                
                   int windowLocationX =((int)subFrame.getLocation().getX());
                   int windowLocationY = ((int)subFrame.getLocation().getY());
                    
                    subFrame.setLocation(windowLocationX+ e.getX() - mpX ,windowLocationY + e.getY() - mpY);
            }
            });
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

