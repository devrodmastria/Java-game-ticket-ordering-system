/*Programmer: Rodrigo Mesquita
Project 8 - Seat Selection
Date: 04/25/2016
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SeatSelection extends JPanel{
   
   private JRadioButton lowerDeck;
   private JRadioButton upperDeck;
   private JRadioButton obstructedView;
   private JRadioButton luxuryBox;
   private ButtonGroup bg;
   private JLabel instruction;
   
   public final int [] prices = {40, 44, 52, 60}; 
   public final String[] seatType = {"obstructed view","upper deck","lower deck", "luxury box"};
                              
   public static String seatName;
   public static int selectedSeatPrice;
    
   
   public SeatSelection(){
   
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        luxuryBox = new JRadioButton("luxury box $60");
        lowerDeck = new JRadioButton("lower deck $52");
        upperDeck = new JRadioButton("upper deck $44");
        obstructedView = new JRadioButton("obstructed view $40");

        Font myFont = new Font("Arial", Font.ITALIC + Font.BOLD, 16);
        instruction = new JLabel(" Click seat selection ");
        instruction.setFont(myFont);
        instruction.setBackground(Color.lightGray);
        instruction.setOpaque(true);

        bg = new ButtonGroup();

        bg.add(obstructedView);
        bg.add(luxuryBox);
        bg.add(lowerDeck);
        bg.add(upperDeck);
        
        gbc.gridy = 0;
        gbc.fill = 1;
        gbc.weighty = 1;
        gbc.ipady = 50;
        add(instruction, gbc);

        gbc.gridy = 1;
        gbc.fill = 1;
        gbc.weighty = 1;
        add(obstructedView, gbc);
        
        gbc.gridy = 2;
        gbc.fill = 1;      
        gbc.weighty = 1;
        add(upperDeck, gbc);
        
        gbc.gridy = 3;
        gbc.fill = 1;
        gbc.weighty = 1;
        add(lowerDeck, gbc);
        
        gbc.gridy = 4;
        gbc.fill = 1;
        gbc.weighty = 1;
        add(luxuryBox, gbc);

        myListener itemListener = new myListener();
      
        obstructedView.addItemListener(itemListener);
        upperDeck.addItemListener(itemListener);
        lowerDeck.addItemListener(itemListener);
        luxuryBox.addItemListener(itemListener);
        
   }
   
    public class myListener implements ItemListener{
        
        public void itemStateChanged(ItemEvent actionEvent){
            
            if(actionEvent.getSource() == obstructedView)
                setSeat(seatType[0], prices[0]);
            else if(actionEvent.getSource() == upperDeck)
                setSeat(seatType[1], prices[1]);
            else if(actionEvent.getSource() == lowerDeck)
                setSeat(seatType[2], prices[2]);
            else if(actionEvent.getSource() == luxuryBox)
                setSeat(seatType[3], prices[3]);
        }
   }
   
   public void setSeat(String s, int price){
       seatName = s;
       selectedSeatPrice = price;
   }
   
   public String getSeat(){
      
        if(seatName == null){
            seatName = "No seat selected";
        }
      return seatName;
   
   }
   
   public int getSeatPrice(){
         
      return selectedSeatPrice;
   
   }
   
   public void clear(){
        bg.clearSelection();
        setSeat("No seat selected", 0);
   }
   
}