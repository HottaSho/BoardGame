package game;

import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
  

public class PlayingArea extends JPanel {
  

  
  public PlayingArea(){
     try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (Exception e) {
    }
  }
  private static int width = 400;
  private static int height = 700;
  public static void addComponentsToPane(Container pane) {
    pane.setLayout(null);

    JButton b1 = new JButton("Deck");
    JButton b2 = new JButton(){
        @Override
        public void paint(Graphics g){
          super.paint(g);
          g.drawRect(100, 100, 105, 105);
        }
      };
    for(int i = 0; i < 5; i++) {
      JButton bi = new JButton(){
        @Override
        public void paint(Graphics g){
          super.paint(g);
          g.drawRect(100, 100, 105, 105);
        }
      };
      pane.add(bi);
      bi.setBounds(5 + i * 80 , 480, 80, 100);
    }
    for(int i = 0; i < 5; i++) {
      JButton bi = new JButton(){
        @Override
        public void paint(Graphics g){
          super.paint(g);
          g.drawRect(100, 100, 105, 105);
        }
      };
      pane.add(bi);
      bi.setBounds(5 + i * 80 , 380, 80, 100);
    }
    JButton b3 = new JButton("Graveyard");
    JButton b4 = new JButton("Quit");
    JButton b5 = new JButton("Back");
    JButton b6 = new JButton("Play");
    
    Insets insets = pane.getInsets();
    Dimension size = b1.getPreferredSize();
    int x = width/2 - size.width*2;
    int y = height/2 - size.height*2;
    int buttonWidth = 80;
    int buttonHeight = 100;
    pane.add(b1);
    //pane.add(b2);
    pane.add(b3);
    pane.add(b4);
    pane.add(b2);
    
    
    
    b1.setBounds(320, 580, buttonWidth, buttonHeight);
    size = b3.getPreferredSize();
    b3.setBounds(45, 580, buttonWidth, buttonHeight);
    size = b4.getPreferredSize();
    b4.setBounds(0, 620, buttonWidth - 40, buttonHeight - 40);

    
    b4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
        
    b1.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          }
        });
        
 
      
 
        //Insets insets = pane.getInsets();
        
    }
  

 
    public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      addComponentsToPane(frame.getContentPane());
      frame.setSize(410, 700);
      frame.setVisible(true);
    }
     
}


