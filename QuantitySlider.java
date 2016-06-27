/*Programmer: Rodrigo Mesquita
Project 8 - Quantity Slider 
Date: 04/25/2016
*/

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class QuantitySlider extends JPanel{

   private final JLabel sliderInstruction;
   private final JPanel instructionPanel;
   private final JPanel sliderPanel;
   private final JSlider slider;
   
   public static int numPackages;
   
   public QuantitySlider(){
   
      setLayout(new GridLayout(2,1));
      setBackground(Color.CYAN);

      Font myFont = new Font("Arial", Font.ITALIC + Font.BOLD, 22);

      sliderInstruction = new JLabel(" select number of ticket packages");
      sliderInstruction.setFont(myFont);
      
      slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
      slider.setBackground(Color.white);

      slider.setMajorTickSpacing(1);
      slider.setMinorTickSpacing(1);
      slider.setPaintTicks(true);
      slider.setPaintLabels(true);
      slider.addChangeListener(new SliderListener());
      
      instructionPanel = new JPanel();
      instructionPanel.setLayout(new BorderLayout());
      instructionPanel.setBackground(Color.white);
      instructionPanel.add(sliderInstruction, BorderLayout.WEST);
      
      sliderPanel = new JPanel();
      sliderPanel.setLayout(new BorderLayout());

      sliderPanel.add(slider);
      
      add(instructionPanel);
      add(sliderPanel);
      
      setPackages(0);
   }
   
   public int getPackages(){
       
      return numPackages;
   }
   
    public void setPackages(int num){
       numPackages = num;
    }
    
   public class SliderListener implements ChangeListener{
   
      public void stateChanged (ChangeEvent e){  
          setPackages(slider.getValue());
      }
    }
   
   public void clear(){
       slider.setValue(0);
   }
}